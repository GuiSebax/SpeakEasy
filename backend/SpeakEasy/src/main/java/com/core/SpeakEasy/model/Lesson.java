package com.core.SpeakEasy.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, name = "title")
  private String title;

  @Column(nullable = false, name = "description")
  private String description;

  @Column(nullable = false, name = "active")
  private Boolean isActive;

  @Column(nullable = false, name = "lessonOrder")
  private Integer order;

  @ManyToOne
  @JoinColumn(name = "courseId", nullable = false)
  private Course course;

}
