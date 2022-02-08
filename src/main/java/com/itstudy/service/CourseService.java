package com.itstudy.service;

import java.util.List;

import com.itstudy.entity.Course;

public interface  CourseService {

	public Course selectById(Integer id );
	
	public int addCourse(Course course);
	
	public int updateCourse(Course course);
	
	public  List<Course> selectLikeName(String name);
}
