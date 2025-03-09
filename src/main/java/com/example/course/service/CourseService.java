package com.example.course.service;

import com.example.course.model.courseModel;
import com.example.course.repo.courseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class courseService {

    @Autowired
    private courseRepository courseRepository;

    public List<courseModel> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<courseModel> getCourseById(int id) {
        return courseRepository.findById(id);
    }

    public courseModel createCourse(courseModel course) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        course.setCreatedAt(now);
        course.setUpdatedAt(now);
        return courseRepository.save(course);
    }

    public courseModel updateCourse(int id, courseModel courseDetails) {
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
