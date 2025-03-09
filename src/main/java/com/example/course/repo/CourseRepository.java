package com.example.course.repo;



import com.example.course.model.courseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface courseRepository extends JpaRepository<courseModel, Integer> {
}
