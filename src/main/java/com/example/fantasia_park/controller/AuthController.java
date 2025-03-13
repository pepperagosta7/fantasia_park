package com.example.fantasia_park.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.fantasia_park.model.User;
import com.example.fantasia_park.service.UserService;

/**
 * Controller per la gestione della registrazione e del login degli
 * utenti.
 */
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    /**
     * Mostra il modulo di registrazione.
     *
     * @param model Model per passare dati alla vista
     * @return Nome del template Thymeleaf per la registrazione
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Gestisce la registrazione di un nuovo utente.
     *
     * @param user Oggetto User ottenuto dal form di registrazione
     * @return Redirect alla pagina di login
     */
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.register(user);
        return "redirect:/auth/login";
    }

    /**
     * Mostra il modulo di login.
     *
     * @return Nome del template Thymeleaf per il login
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
