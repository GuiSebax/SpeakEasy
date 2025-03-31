package com.core.SpeakEasy.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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

  @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
  private Integer xp;

  @Column(name = "date", updatable = false)
  private LocalDateTime createdAt = LocalDateTime.now();

  @ManyToMany
  @JoinTable(
    name = "user_courses",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
  )
  private Set<Course> courses;
}
