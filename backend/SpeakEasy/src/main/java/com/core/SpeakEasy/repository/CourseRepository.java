package com.core.SpeakEasy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.SpeakEasy.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
  Optional<Course> findByName(String name);
  boolean existsByName(String name);
}
