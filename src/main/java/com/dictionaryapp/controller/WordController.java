package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.dto.AddWordDto;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @ModelAttribute("addWord")
    public AddWordDto createWordDto(){
        return new AddWordDto();
    }

    @GetMapping("/word-add")
    public String viewAddWordPage(){
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
}
