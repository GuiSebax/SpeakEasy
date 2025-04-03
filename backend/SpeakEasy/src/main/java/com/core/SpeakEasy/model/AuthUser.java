package com.core.SpeakEasy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authUsers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, name = "targetLanguage")
    private String language;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer level;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}