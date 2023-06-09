package com.vansh.crudDemo.service;

import com.vansh.crudDemo.dto.CourseDto;
import com.vansh.crudDemo.entity.Course;
import com.vansh.crudDemo.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

//    private final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    /**
     * method to add course details
     *
     * @param c
     * @return
     */
    @Override
    public CourseDto addCourse(CourseDto c) {
        Course course = null;
        try {
            course = courseRepository.save(dtoToEntityConverter(c));
        } catch (Exception e) {
            log.error("Error in addCourse() method - " + e.getMessage());
        }
        return entityToDtoConverter(Objects.requireNonNull(course));
    }

    /**
     * method to fetch all courses details
     *
     * @return
     */
    @Override
    public List<Course> getAllCourse() {
        List<Course> c = new ArrayList<>();
        try {
            c = courseRepository.findAll();
        } catch (Exception e) {
            log.error("Error in getAllCourse() method - " + e.getMessage());
        }
        return c;
    }

    /**
     * method to fetch course details based on courseId
     *
     * @param id
     * @return
     */
    @Override
    public CourseDto getCourse(int id) {
        Course course = null;
        try {
            course = courseRepository.findById(id).get();
        } catch (Exception e) {
            log.error("Error in getCourse() method - " + e.getMessage());
            return null;
        }
        return entityToDtoConverter(Objects.requireNonNull(course));
    }

    /**
     * method to delete course based on courseId
     *
     * @param id
     * @return
     */
    @Override
    public String deleteCourse(int id) {
        try {
            courseRepository.deleteById(id);
            return "Success";
        } catch (Exception e) {
            log.error("Error in deleteCourse() method - " + e.getMessage());
            return "Failure";
        }
    }

    /**
     * method to update course details based on courseId
     *
     * @param id
     * @param c
     * @return
     */
    @Override
    public CourseDto updateCourse(int id, CourseDto c) {
        Course obj = null;
        try {
            obj = courseRepository.findById(id).get();
            obj.setCourseName(c.getCourseName());
            obj.setCourseDuration(c.getCourseDuration());
            obj.setCourseFees(c.getCourseFees());
        } catch (Exception e) {
            log.error("Error in updateCourse() method - " + e.getMessage());
            return null;
        }
        return entityToDtoConverter(courseRepository.save(Objects.requireNonNull(obj)));
    }

    /**
     * method to convert dto to entity
     *
     * @param courseDto
     * @return
     */
    public Course dtoToEntityConverter(CourseDto courseDto) {
        Course course = new Course();
        course.setCourseName(courseDto.getCourseName());
        course.setCourseFees(courseDto.getCourseFees());
        course.setCourseDuration(courseDto.getCourseDuration());
        return course;
    }

    /**
     * method to convert entity to dto
     *
     * @param course
     * @return
     */
    public CourseDto entityToDtoConverter(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName(course.getCourseName());
        courseDto.setCourseFees(course.getCourseFees());
        courseDto.setCourseDuration(course.getCourseDuration());
        return courseDto;
    }
}
