package com.vansh.crudDemo.service;

import com.vansh.crudDemo.dto.CourseDto;
import com.vansh.crudDemo.entity.Course;

import java.util.List;

public interface CourseService {

    CourseDto addCourse(CourseDto c);

    List<Course> getAllCourse();

    CourseDto getCourse(int id);

    String deleteCourse(int id);

    CourseDto updateCourse(int id, CourseDto c);
}
