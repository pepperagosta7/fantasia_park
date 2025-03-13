package com.example.fantasia_park.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fantasia_park.model.Creature;
import com.example.fantasia_park.model.User;

public interface CreatureRepository extends JpaRepository<Creature, Long>{
    
    List<Creature> findByCustode(User custode);


}
