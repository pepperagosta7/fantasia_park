package com.example.fantasia_park.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data // Genera getter, setter, toString, equals, hashCode
@NoArgsConstructor // Genera costruttore senza argomenti
@AllArgsConstructor // Genera costruttore con tutti gli argomenti
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    @OneToMany(mappedBy = "custode")
    private List<Creature> creatures;
    // Getters e Setters...
}
