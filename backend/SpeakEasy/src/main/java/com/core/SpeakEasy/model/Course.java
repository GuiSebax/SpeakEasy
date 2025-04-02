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
  private String native_language;

  @Column(nullable = false)
  private String target_language;

  @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
  private Boolean is_active;

  @Column(name = "date_of_creation", updatable = false)
  private LocalDateTime initial_date = LocalDateTime.now();

  @Column(name = "course_duration")
  private Date duration;

  @ManyToMany(mappedBy = "courses")
  private Set<User> users;

  //I don't if is necessary to use orphanRemoval = true
  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Lesson> lessons;

  private Integer level;
}