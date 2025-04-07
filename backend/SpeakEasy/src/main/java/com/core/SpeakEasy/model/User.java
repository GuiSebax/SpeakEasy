package com.core.SpeakEasy.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, unique = true)
  private String password;

  @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
  private Integer level;
  
  @Column(nullable = true, columnDefinition = "INT DEFAULT 0")
  private Integer xp;

  @Column(nullable = false, name = "targetLanguage")
  private String language;
  
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;
  
  @Column(name = "date", updatable = false)
  private LocalDateTime createdAt = LocalDateTime.now();

  @ManyToMany
  @JoinTable(name = "userCourses", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "courseId"))
  private Set<Course> courses;
}
