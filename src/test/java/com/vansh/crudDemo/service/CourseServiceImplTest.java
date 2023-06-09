package com.vansh.crudDemo.service;

import com.vansh.crudDemo.dto.CourseDto;
import com.vansh.crudDemo.entity.Course;
import com.vansh.crudDemo.repository.CourseRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseServiceImplTest {
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseServiceImpl courseServiceImpl;

    @Test
    @Order(1)
    public void testAddCourse() {
        Course expectedResult = new Course(1, "Java", "Rs.2000", "2months");
        CourseDto courseDto = new CourseDto("Java", "Rs.2000", "2months");
        when(courseRepository.save(any())).thenReturn(expectedResult);
        CourseDto actualResult = courseServiceImpl.addCourse(courseDto);

        assertEquals(expectedResult.getCourseName(), actualResult.getCourseName());
        assertEquals(expectedResult.getCourseFees(),actualResult.getCourseFees());
        assertEquals(expectedResult.getCourseDuration(),actualResult.getCourseDuration());
    }

    @Test
    @Order(2)
    public void testGetAllCourse() {
        List<Course> expectedResult = new ArrayList<>();
        Course course = new Course(1, "Java", "Rs.2000", "2months");
        Course course1 = new Course(2, "NodeJs", "Rs.1000", "1month");
        expectedResult.add(course);
        expectedResult.add(course1);

        when(courseRepository.findAll()).thenReturn(expectedResult);
        List<Course> actualResult = courseServiceImpl.getAllCourse();

        assertEquals(expectedResult.size(), actualResult.size());
    }

    @Test
    @Order(3)
    public void testGetCourse() {
        Course expectedResult = new Course(1,"Java", "Rs.2000", "2months");
        when(courseRepository.findById(any())).thenReturn(Optional.of(expectedResult));
        CourseDto actualResult = courseServiceImpl.getCourse(1);

        assertEquals(expectedResult.getCourseDuration(), actualResult.getCourseDuration());
        assertEquals(expectedResult.getCourseFees(), actualResult.getCourseFees());
        assertEquals(expectedResult.getCourseName(), actualResult.getCourseName());
    }

    @Test
    @Order(4)
    public void testUpdateCourse() {
        Course expectedResult = new Course(1,"Java", "Rs.2000", "2months");
        when(courseRepository.findById(any())).thenReturn(Optional.of(expectedResult));
        when(courseRepository.save(any())).thenReturn(expectedResult);
        CourseDto actualResult = courseServiceImpl.getCourse(1);

        assertEquals(expectedResult.getCourseDuration(), actualResult.getCourseDuration());
        assertEquals(expectedResult.getCourseFees(), actualResult.getCourseFees());
        assertEquals(expectedResult.getCourseName(), actualResult.getCourseName());
    }

    @Test
    @Order(5)
    public void testDeleteCourse() {
        Course course = new Course(1, "Java", "Rs.2000", "2months");
        courseServiceImpl.deleteCourse(course.getId());

        verify(courseRepository, times(1)).deleteById(course.getId());
    }

}
