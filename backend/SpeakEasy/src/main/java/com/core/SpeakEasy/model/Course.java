package com.core.SpeakEasy.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private String nativeLanguage;

  @Column(nullable = false)
  private String targetLanguage;

  @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
  private Boolean isActive;

  @Column(name = "dateOfCreation", updatable = false)
  private LocalDateTime initialDate = LocalDateTime.now();

  @Column(name = "courseDuration")
  private Date duration;

  @ManyToMany(mappedBy = "courses")
  private Set<User> users;

  @Column(nullable = false, name = "imgUrl")
  private String imgUrl;

  // I don't if is necessary to use orphanRemoval = true
  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Lesson> lessons;

  @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
  private Integer level;
}