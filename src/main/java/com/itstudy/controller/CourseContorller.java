package com.itstudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstudy.entity.Course;
import com.itstudy.exception.ParameterException;
import com.itstudy.service.CourseService;
import com.itstudy.util.Constant;
import com.itstudy.util.Result;

@RestController
@RequestMapping("/course")
public class CourseContorller {

	@Autowired
	private CourseService courseService;

	@GetMapping("/{id}")
	public ResponseEntity<Course> selectById(@PathVariable("id") Integer  id) {

		Result<Course> result = new Result<Course>();

		Course courseResut = courseService.selectById(id);

		return ResponseEntity.ok(courseResut);
	}

	@PostMapping("/add")
	public ResponseEntity<Integer> addCourse(@RequestBody Course course) {

		// 参数校验。要求course的name长度要大于5
		System.out.println("course "+course);
		if(course.getName()==null || course.getName().length()<4 ) {
			
			throw new ParameterException("E001","parameter error");
		}
		Integer count = courseService.addCourse(course);

		return ResponseEntity.ok().body(count);

		 

	}

	@PutMapping("/update")
	public Result<Integer> updateCourse(@RequestBody Course course) {

		Result<Integer> result = new Result<Integer>();

		Integer count = courseService.updateCourse(course);

		result.setStatus(Constant.SUCCESS);
		result.setData(count);

		return result;

	}

	@RequestMapping("/like")
	public Result<List<Course>> selectLikeName(Course course) {

		Result<List<Course>> result = new Result<List<Course>>();
		String name = course.getName();
		List<Course> list = courseService.selectLikeName(name);

		result.setStatus(Constant.SUCCESS);
		result.setData(list);

		return result;
	}

}
