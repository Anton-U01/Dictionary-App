package com.dictionaryapp.controller;

import ch.qos.logback.core.model.Model;
import com.dictionaryapp.model.entity.dto.UserLoginDto;
import com.dictionaryapp.model.entity.dto.UserRegisterDto;
import com.dictionaryapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegister")
    public UserRegisterDto createDto(){
        return new UserRegisterDto();
    }

    @GetMapping("/register")
    public String viewRegister(Model model){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDto userRegisterDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors() || !userService.register(userRegisterDto)){
                  redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegister",bindingResult);
                  redirectAttributes.addFlashAttribute("userRegister",userRegisterDto);
                  return "redirect:/register";
        }

        return "redirect:/login";
    }


    @ModelAttribute("userLogin")
    public UserLoginDto createLoginDto(){
        return new UserLoginDto();
    }
    @GetMapping("/login")
    public String viewLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid UserLoginDto userLoginDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLogin",userLoginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLogin",bindingResult);
            return "redirect:/login";
        }

        boolean success = userService.login(userLoginDto);
        if(!success){
            redirectAttributes.addFlashAttribute("userLogin",userLoginDto);
            redirectAttributes.addFlashAttribute("userPasMismatch",true);
            return "redirect:/login";
        }

        return "redirect:/home";
    }

}
