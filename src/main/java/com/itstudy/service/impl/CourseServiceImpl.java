package com.itstudy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itstudy.dao.CourseDao;
import com.itstudy.entity.Course;
import com.itstudy.service.CourseService;
@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseDao courseDao;
	
	@Override
	public Course selectById(Integer id) {
		// TODO Auto-generated method stub
		return courseDao.selectById(id);
	}

	@Override
	public int addCourse(Course course) {
		// TODO Auto-generated method stub
		return courseDao.addCourse(course);
	}

	@Override
	public int updateCourse(Course course) {
		// TODO Auto-generated method stub
		return courseDao.updateCourse(course);
	}

	@Override
	public List<Course> selectLikeName(String name) {
		// TODO Auto-generated method stub
		return courseDao.selectLikeName(name);
	}

	
}
