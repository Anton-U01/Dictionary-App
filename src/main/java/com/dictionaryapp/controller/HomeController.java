package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.CurrentUser;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController{
    private final CurrentUser currentUser;
    private final WordService wordService;

    public HomeController(CurrentUser currentUser, WordRepository wordRepository, WordService wordService) {
        this.currentUser = currentUser;
        this.wordService = wordService;
    }

    @GetMapping("/home")
    public String logged(Model model){
        if(!currentUser.isLoggedIn()){
            return "redirect:/";
        }

        List<Word> allWords = wordService.findAllWords();
        List<Word> germanWords = allWords.stream().filter(w -> w.getLanguage()
                .getLanguageName()
                .equals(LanguageEnum.GERMAN))
                .collect(Collectors.toList());
        List<Word> spanishWords = allWords.stream().filter(w -> w.getLanguage()
                        .getLanguageName()
                        .equals(LanguageEnum.SPANISH))
                .collect(Collectors.toList());
        List<Word> italianWords = allWords.stream().filter(w -> w.getLanguage()
                        .getLanguageName()
                        .equals(LanguageEnum.ITALIAN))
                .collect(Collectors.toList());
        List<Word> frenchWords = allWords.stream().filter(w -> w.getLanguage()
                        .getLanguageName()
                        .equals(LanguageEnum.FRENCH))
                .collect(Collectors.toList());
        model.addAttribute("allWords",allWords);
        model.addAttribute("germanWords",germanWords);
        model.addAttribute("spanishWords",spanishWords);
        model.addAttribute("italianWords",italianWords);
        model.addAttribute("frenchWords",frenchWords);

        return "home";
    }
    @GetMapping("/")
    public String notLogged(){
        if(currentUser.isLoggedIn()){
            return "redirect:/home";
        }
        return "index";
    }
}
