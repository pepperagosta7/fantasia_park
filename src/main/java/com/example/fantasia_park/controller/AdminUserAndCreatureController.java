package com.example.fantasia_park.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fantasia_park.service.CreatureService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminUserAndCreatureController {

    private final CreatureService creatureService;

    // Accedere alla console di amministrazione
    @GetMapping("/console")
    public String accessAdminConsole(Model model) {
        // Aggiungi eventuali attributi al modello se necessario
        return "admin-dashboard";
    }

    // Gestire tutti gli utenti e assegnare creature ai custodi
    @PostMapping("/assign-creature")
    public String assignCreatureToKeeper(@RequestParam("creatureId") Long creatureId, @RequestParam("custodeId") Long custodeId, Model model) {
        creatureService.assignCreatureToCustode(creatureId, custodeId);
        model.addAttribute("message", "Creatura assegnata con successo al custode.");
        return "admin-dashboard";
    }

    // Visualizzare ogni creatura presente nel parco
    @GetMapping("/creatures")
    public String viewAllCreatures(Model model) {
        model.addAttribute("creatures", creatureService.findAllCreatures());
        return "creatures";
    }

    // Modificare ogni creatura presente nel parco
    @PutMapping("/creature")
    public String modifyCreature(@RequestParam("creatureId") Long creatureId, Model model) {
        creatureService.modifyCreature(creatureService.getCreatureById(creatureId));
        model.addAttribute("message", "Creatura modificata con successo.");
        return "creatures";
    }
}