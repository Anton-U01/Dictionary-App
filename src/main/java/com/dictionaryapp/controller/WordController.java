package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.dto.AddWordDto;
import com.dictionaryapp.service.CurrentUser;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {
    private final WordService wordService;
    private final CurrentUser currentUser;

    public WordController(WordService wordService, CurrentUser currentUser) {
        this.wordService = wordService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("addWord")
    public AddWordDto createWordDto(){
        return new AddWordDto();
    }

    @GetMapping("/word-add")
    public String viewAddWordPage(){
        if(!currentUser.isLoggedIn()){
            return "redirect:/";
        }
        return "word-add";
    }

    @PostMapping("/word-add")
    public String addWord(@Valid AddWordDto addWordDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() || !wordService.addWord(addWordDto)){
            redirectAttributes.addFlashAttribute("addWord",addWordDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addWord",bindingResult);
            return "redirect:/word-add";
        }
        return "redirect:/home";
    }

    @DeleteMapping("/words/{id}")
    public String deleteWord(@PathVariable("id") long id){
        wordService.deleteWord(id);
        return "redirect:/home";
    }
    @DeleteMapping("/words/delete-all")
    public String deleteAllWords(){
        wordService.deleteAllWords();
        return "redirect:/home";
    }
}
