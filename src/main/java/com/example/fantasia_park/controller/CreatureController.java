package com.example.fantasia_park.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.fantasia_park.model.Creature;
import com.example.fantasia_park.model.User;
import com.example.fantasia_park.repository.CreatureRepository;
import com.example.fantasia_park.service.UserService;

import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/creatures")
@RequiredArgsConstructor
public class CreatureController {

    private final CreatureRepository creatureRepository;
    private final UserService userService;

    @GetMapping
    public String getMyCreatures(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        List<Creature> creatures = creatureRepository.findByCustode(user);
        model.addAttribute("creatures", creatures);
        return "creatures";
    }

    @PostMapping
    public String addCreature(@ModelAttribute Creature creature, Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        creature.setCustode(user);
        creatureRepository.save(creature);
        model.addAttribute("message", "Creatura aggiunta con successo.");
        return "redirect:/creatures";
    }

    @DeleteMapping("/{id}")
    public String deleteCreature(@PathVariable Long id, Model model) {
        creatureRepository.deleteById(id);
        model.addAttribute("message", "Creatura eliminata con successo.");
        return "redirect:/creatures";
    }

    @PutMapping("/{id}")
    public String updateCreature(@PathVariable Long id, @ModelAttribute Creature creature, Model model) {
        creature.setId(id);
        creatureRepository.save(creature);
        model.addAttribute("message", "Creatura aggiornata con successo.");
        return "redirect:/creatures";
    }
}