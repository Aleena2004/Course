package com.example.course.service;

import com.example.course.model.Course;
import com.example.course.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(int id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        course.setCreatedAt(now);
        course.setUpdatedAt(now);
        return courseRepository.save(course);
    }

    public Course updateCourse(int id, Course courseDetails) {
        return courseRepository.findById(id).map(course -> {
            course.setName(courseDetails.getName());
            course.setShortCode(courseDetails.getShortCode());
            course.setDegreeTypeId(courseDetails.getDegreeTypeId());
            course.setDepartmentId(courseDetails.getDepartmentId());
            course.setDuration(courseDetails.getDuration());
            course.setActive(courseDetails.isActive());
            course.setUpdatedAt(new Timestamp(System.currentTimeMillis())); // Explicitly update timestamp
            return courseRepository.save(course);
        }).orElseThrow(() -> new RuntimeException("Course not found with id " + id));
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }
}
