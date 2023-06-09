package com.vansh.crudDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vansh.crudDemo.dto.CourseDto;
import com.vansh.crudDemo.entity.Course;
import com.vansh.crudDemo.service.CourseServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Mock
    CourseServiceImpl courseServiceImpl;
    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    @Order(1)
    public void testGetAllCourse() throws Exception {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(1, "Java", "Rs. 2000", "2 months"));
        courses.add(new Course(2, "NodeJs", "Rs. 1000", "1 month"));

        when(courseServiceImpl.getAllCourse()).thenReturn(courses);
        this.mockMvc
                .perform(get("/courses/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(2)
    public void testGetCourse() throws Exception {
        CourseDto courseDto = new CourseDto("Java", "Rs. 2000", "2 months");
        int id = 1;

        when(courseServiceImpl.getCourse(id)).thenReturn(courseDto);
        this.mockMvc
                .perform(get("/courses/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".courseName").value("Java"))
                .andExpect(MockMvcResultMatchers.jsonPath(".courseFees").value("Rs. 2000"))
                .andExpect(MockMvcResultMatchers.jsonPath(".courseDuration").value("2 months"))
                .andDo(print());
    }

    @Test
    @Order(3)
    public void testAddCourse() throws Exception {
        CourseDto courseDto = new CourseDto("Java", "Rs. 2000", "2 months");
        when(courseServiceImpl.addCourse(any())).thenReturn(courseDto);

        //Converting our Java object to JSON format bcoz MockMvc works only with JSON format
        ObjectMapper mapper = new ObjectMapper();
        String jsonbody = mapper.writeValueAsString(courseDto);

        this.mockMvc
                .perform(
                        post("/courses/")
                                .content(jsonbody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".courseName").value("Java"))
                .andExpect(MockMvcResultMatchers.jsonPath(".courseFees").value("Rs. 2000"))
                .andExpect(MockMvcResultMatchers.jsonPath(".courseDuration").value("2 months"))
                .andDo(print());
    }

    @Test
    @Order(4)
    public void testUpdateCourse() throws Exception {
        CourseDto courseDto = new CourseDto("Java", "Rs. 2000", "2 months");
        int id = 1;
        when(courseServiceImpl.updateCourse(anyInt(),any())).thenReturn(courseDto);

        ObjectMapper mapper = new ObjectMapper();
        String jsonbody = mapper.writeValueAsString(courseDto);

        this.mockMvc
                .perform(
                        put("/courses/{id}", id)
                                .content(jsonbody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".courseName").value("Java"))
                .andExpect(MockMvcResultMatchers.jsonPath(".courseFees").value("Rs. 2000"))
                .andExpect(MockMvcResultMatchers.jsonPath(".courseDuration").value("2 months"))
                .andDo(print());
    }

    @Test
    @Order(5)
    public void testDeleteCourse() throws Exception {
        int id = 1;
        String response = "Success";
        when(courseServiceImpl.deleteCourse(id)).thenReturn(response);
        mockMvc.perform(delete("/courses/{id}",id))
                .andExpect(status().isOk())
                .andDo(print());
    }
}


//import com.vansh.crudDemo.entity.Course;
//import com.vansh.crudDemo.service.CourseService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class CourseControllerTest {
//
//    @Mock
//    private CourseService courseService;
//    @InjectMocks
//    private CourseController courseController;
//
//    @Test
//    public void getAllCourse() {
//        List<Course> courses = new ArrayList<>();
//        courses.add(new Course(1, "Java", "Rs. 2000", "2 months"));
//        courses.add(new Course(2, "NodeJs", "Rs. 1000", "1 month"));
//
//        when(courseService.getAllCourse()).thenReturn(courses);
//        ResponseEntity<List<Course>> res = courseController.getAllCourse();
//
//        assertEquals(HttpStatus.FOUND, res.getStatusCode());
//        assertEquals(courses.size(), res.getBody().size());
//    }
//
//    @Test
//    public void getCourse() {
//        Course course = new Course(1, "Java", "Rs. 2000", "2 months");
//        int id = 1;
//
//        when(courseService.getCourse(id)).thenReturn(course);
//        ResponseEntity<Course> res = courseController.getCourse(id);
//
//        assertEquals(HttpStatus.FOUND, res.getStatusCode());
//        assertEquals(id, res.getBody().getId());
//    }
//
//    @Test
//    public void addCourse() {
//        Course course = new Course(1, "Java", "Rs. 2000", "2 months");
//
//        when(courseService.addCourse(course)).thenReturn(course);
//        ResponseEntity<Course> res = courseController.addCourse(course);
//
//        assertEquals(HttpStatus.CREATED, res.getStatusCode());
//        assertEquals(course, res.getBody());
//    }
//
//    @Test
//    public void updateCourse() {
//        Course course = new Course(1, "Java", "Rs. 2000", "2 months");
//        int id = 1;
//
//        when(courseService.updateCourse(id, course)).thenReturn(course);
//        ResponseEntity<Course> res = courseController.updateCourse(id, course);
//
//        assertEquals(HttpStatus.OK, res.getStatusCode());
//        assertEquals(course, res.getBody());
//    }
//
//    @Test
//    public void deleteCourse() {
//        int id = 1;
//        ResponseEntity<String> res = courseController.deleteCourse(id);
//
//        assertEquals(HttpStatus.OK, res.getStatusCode());
//        assertEquals("Course Deleted Successfully", res.getBody());
//    }
//}