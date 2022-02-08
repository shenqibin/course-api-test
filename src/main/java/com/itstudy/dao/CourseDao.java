package com.itstudy.dao;

import java.util.List;

import com.itstudy.entity.Course;

public interface CourseDao {

	public Course selectById(Integer id );
	
	public int addCourse(Course course);
	
	public int updateCourse(Course course);
	
	public  List<Course> selectLikeName(String name);
}
