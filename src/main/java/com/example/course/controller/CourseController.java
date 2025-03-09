package com.example.course.controller;



import com.example.course.model.courseModel;
import com.example.course.service.courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class courseController {

    @Autowired
    private courseService courseService;

    @GetMapping
    public List<courseModel> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<courseModel> getCourseById(@PathVariable int id) {
        Optional<courseModel> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public courseModel createCourse(@RequestBody courseModel course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<courseModel> updateCourse(@PathVariable int id, @RequestBody courseModel courseDetails) {
        try {
            courseModel updatedCourse = courseService.updateCourse(id, courseDetails);
            return ResponseEntity.ok(updatedCourse);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
