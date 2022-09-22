package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

  @GetMapping("/api/course/{_id}")
  public List<Course> getCourseWithId(@PathVariable Integer _id) {
    return Arrays.asList(
        new Course(_id, "JohnCena"));
  }

  @GetMapping("/api/courses")
  public List<Course> getAllCourses(@RequestParam(required = false) Integer offset, @RequestParam(required = false) Integer limit) {
    if (offset == null)
      offset = 0;
    if (limit == null)
      limit = 10;
    limit += offset;
    List<Course> response = new Vector<Course>();
    for (int i = offset; i < limit; i++) {
      response.add(new Course(i, "Course " + i));
    }
    return response;
  }
}
