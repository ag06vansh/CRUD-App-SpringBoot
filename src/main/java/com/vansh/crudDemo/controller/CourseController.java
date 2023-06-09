package com.vansh.crudDemo.controller;

import com.vansh.crudDemo.dto.CourseDto;
import com.vansh.crudDemo.entity.Course;
import com.vansh.crudDemo.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

//    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    /**
     * API to fetch all courses details
     *
     * @return ResponseEntity
     */
    @GetMapping("/")
    @Operation(summary = "Get all courses details")
    public ResponseEntity<?> getAllCourse() {
        log.info("Calling and starting getAllCourse()");
        List<Course> courses = courseService.getAllCourse();
        if (courses == null) {
            log.error("Unable to fetch courses");
            return new ResponseEntity<>("Unable to fetch course", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


    /**
     * API to fetch course details based on courseId
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get course details by id")
    public ResponseEntity<?> getCourse(@PathVariable int id) {
        log.info("Calling and starting getCourse()");
        CourseDto courseDto = courseService.getCourse(id);
        if (courseDto == null) {
            log.error("Unable to fetch course");
            return new ResponseEntity<>("Unable to fetch course", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courseDto, HttpStatus.OK);
    }


    /**
     * API to add course details
     *
     * @param c
     * @return
     */
    @PostMapping("/")
    @Operation(summary = "Add course details")
    public ResponseEntity<?> addCourse(@RequestBody(required = true) CourseDto c) {
        log.info("Calling and starting addCourse()");
        CourseDto courseDto = courseService.addCourse(c);
        if (courseDto == null) {
            log.error("Unable to add course");
            return new ResponseEntity<>("Unable to add course", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(courseDto, HttpStatus.OK);
    }


    /**
     * API to update course details based on courseId
     *
     * @param id
     * @param c
     * @return
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update course details by id")
    public ResponseEntity<?> updateCourse(@PathVariable int id, @RequestBody CourseDto c) {
        log.info("Calling and starting updateCourse()");
        CourseDto courseDto = courseService.updateCourse(id, c);
        if (courseDto == null) {
            log.error("Unable to update course");
            return new ResponseEntity<>("Unable to update course", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courseDto, HttpStatus.OK);
    }


    /**
     * API to delete course based on courseId
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete course details by id")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        log.info("Calling and starting deleteCourse()");
        String response = courseService.deleteCourse(id);
        if (response == "Success") {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
