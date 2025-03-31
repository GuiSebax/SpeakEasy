package com.core.SpeakEasy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.SpeakEasy.model.Course;
import com.core.SpeakEasy.repository.CourseRepository;

@Service
public class CourseService {

  @Autowired
  private CourseRepository courseRepository;

  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }
  
  public Optional<Course> getCourseById(Long id) {
    return courseRepository.findById(id);
  }
  
  public Optional<Course> getCourseByName(String name) {
    return courseRepository.findByName(name);
  }

  public Course saveCourse(Course course) {
    return courseRepository.save(course);
  }

  public void deleteCourse(Long id) {
    courseRepository.deleteById(id);
  }
  
}
