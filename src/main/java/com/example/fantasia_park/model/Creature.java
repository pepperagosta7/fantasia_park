package com.example.fantasia_park.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "creatures")
@Data // Genera getter, setter, toString, equals, hashCode
@NoArgsConstructor // Genera costruttore senza argomenti
@AllArgsConstructor // Genera costruttore con tutti gli argomenti
public class Creature {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String species;

    private int dangerLevel; // Da 1 a 10

    @ManyToOne
    @JoinColumn(name = "custode_id")
    private User custode;
    // Getters e Setters...
}