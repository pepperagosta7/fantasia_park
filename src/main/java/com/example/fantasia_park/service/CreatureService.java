package com.example.fantasia_park.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fantasia_park.model.User;

import com.example.fantasia_park.model.Creature;
import com.example.fantasia_park.repository.CreatureRepository;
import com.example.fantasia_park.repository.UserRepository;

@Service
public class CreatureService {
    
    private final CreatureRepository creatureRepository;
    private final UserRepository userRepository;

    public CreatureService(CreatureRepository creatureRepository, UserRepository userRepository) {
        this.creatureRepository = creatureRepository;
        this.userRepository = userRepository;
    }

    public List<Creature> findAllCreatures() {
        return creatureRepository.findAll();
    }

    public List<Creature> findCreaturesByCustode(String username) {
        User custode = userRepository.findByUsername(username);
        return creatureRepository.findByCustode(custode);
    }

    public Creature saveCreature(Creature creature, String username) {
        User custode = userRepository.findByUsername(username);
        creature.setCustode(custode);
        return creatureRepository.save(creature);
    }

    public void deleteCreature(Long id) {
        creatureRepository.deleteById(id);
    }

    public Creature getCreatureById(Long id) {
        return creatureRepository.findById(id).orElse(null);
    }

    public void assignCreatureToCustode(Long creatureId, Long custodeId) {
        
        Creature creature = creatureRepository.findById(creatureId).orElse(null);
        User custode = userRepository.findById(custodeId).orElse(null);
        
        if (creature != null && custode != null) {
            creature.setCustode(custode);
            creatureRepository.save(creature);
        }
    }

    public void modifyCreature(Creature creature) {
        creatureRepository.save(creature);
    }

}
