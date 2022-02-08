package com.itstudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstudy.entity.Course;
import com.itstudy.service.CourseService;
import com.itstudy.util.Constant;
import com.itstudy.util.Result;

@RestController
@RequestMapping("/course")
public class CourseContorller {

	@Autowired
	private CourseService courseService;

	@RequestMapping("/{id}")
	public Result<Course> selectById(@PathVariable("id") Integer  id) {

		Result<Course> result = new Result<Course>();

		Course courseResut = courseService.selectById(id);

		System.out.println("courseResut" +courseResut);
		result.setStatus(Constant.SUCCESS);
		result.setData(courseResut);

		return result;
	}

	@RequestMapping("/add")
	public Result<Course> addCourse(@RequestBody Course course) {

		Result<Course> result = new Result<Course>();

		courseService.addCourse(course);

		result.setStatus(Constant.SUCCESS);
		result.setData(course);

		return result;

	}

	@RequestMapping("/update")
	public Result<Integer> updateCourse(@RequestBody Course course) {

		Result<Integer> result = new Result<Integer>();

		Integer count = courseService.updateCourse(course);

		result.setStatus(Constant.SUCCESS);
		result.setData(count);

		return result;

	}

	@RequestMapping("/like")
	public Result<List<Course>> selectLikeName(@RequestBody Course course) {

		Result<List<Course>> result = new Result<List<Course>>();
		String name = course.getName();
		List<Course> list = courseService.selectLikeName(name);

		result.setStatus(Constant.SUCCESS);
		result.setData(list);

		return result;
	}

}
