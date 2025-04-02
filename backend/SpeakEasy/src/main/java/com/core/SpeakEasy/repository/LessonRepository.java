package com.core.SpeakEasy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.SpeakEasy.model.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
  Optional<Lesson> findByTitle(String title);
  boolean existsByTitle(String title);
}
