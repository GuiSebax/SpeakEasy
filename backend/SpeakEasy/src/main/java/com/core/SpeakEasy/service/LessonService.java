package com.core.SpeakEasy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.SpeakEasy.model.Lesson;
import com.core.SpeakEasy.repository.LessonRepository;

@Service
public class LessonService {
  
  @Autowired
  private LessonRepository lessonRepository;

  public List<Lesson> getAllLessons() {
    return lessonRepository.findAll();
  }

  public Optional<Lesson> getLessonById(Long id) {
    return lessonRepository.findById(id);
  }

  public Optional<Lesson> getLessonByTitle(String title) {
    return lessonRepository.findByTitle(title);
  }

  public Lesson saveLesson(Lesson lesson) {
    return lessonRepository.save(lesson);
  }

  public void deleteLesson(Long id) {
    lessonRepository.deleteById(id);
  }
}
