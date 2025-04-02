package com.core.SpeakEasy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.core.SpeakEasy.model.Course;
import com.core.SpeakEasy.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
  
  @Autowired
  private CourseService courseService;

  @GetMapping
  public List<Course> getAllCourses() {
    return courseService.getAllCourses();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
    return courseService.getCourseById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<Course> getCourseByName(@PathVariable String name) {
    return courseService.getCourseByName(name)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
  
  @PostMapping
  public Course createCourse(@RequestBody Course course) {
    return courseService.saveCourse(course);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
    return courseService.getCourseById(id)
        .map(course -> {
          course.setName(updatedCourse.getName());
          course.setDescription(updatedCourse.getDescription());
          course.setNative_language(updatedCourse.getNative_language());
          course.setTarget_language(updatedCourse.getTarget_language());
          course.setIs_active(updatedCourse.getIs_active());
          course.setLevel(updatedCourse.getLevel());
          course.setDuration(updatedCourse.getDuration());
          return ResponseEntity.ok(courseService.saveCourse(course));
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
    if (courseService.getCourseById(id).isPresent()) {
      courseService.deleteCourse(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

}
