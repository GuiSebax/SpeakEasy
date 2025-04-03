package com.core.SpeakEasy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.core.SpeakEasy.model.Course;
import com.core.SpeakEasy.model.Lesson;
import com.core.SpeakEasy.service.CourseService;
import com.core.SpeakEasy.service.LessonService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

  @Autowired
  private LessonService lessonService;

  private CourseService courseService;

  @GetMapping
  public List<Lesson> getAllLessons() {
    return lessonService.getAllLessons();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
    return lessonService.getLessonById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/title/{title}")
  public ResponseEntity<Lesson> getLessonByTitle(@PathVariable String title) {
    return lessonService.getLessonByTitle(title)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public Lesson createLesson(@RequestParam String title, @RequestParam String description,
      @RequestParam Long courseId) {

    Course course = courseService.getCourseById(courseId)
        .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

    Lesson lesson = new Lesson();
    lesson.setTitle(title);
    lesson.setDescription(description);
    lesson.setCourse(course);

    return lessonService.saveLesson(lesson);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson updatedLesson) {
    return lessonService.getLessonById(id)
        .map(lesson -> {
          lesson.setTitle(updatedLesson.getTitle());
          lesson.setDescription(updatedLesson.getDescription());
          lesson.setCourse(updatedLesson.getCourse());
          return ResponseEntity.ok(lessonService.saveLesson(lesson));
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
    if (lessonService.getLessonById(id).isPresent()) {
      lessonService.deleteLesson(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

}
