package com.itstudy.util;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

	// 每页显示的大小
	public static int size =3;
	
	public Integer totalPage ;
	
	public Integer currentPageNo ;
	
	
	public Integer totalCount;
	
	public List<T> list =new ArrayList<T>();
		
	public Integer getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		
		if (totalCount % Page.size == 0) {

			this.totalPage=( totalCount/Page.size);
		} else {

			this.totalPage=((totalCount / Page.size) + 1);
		}
		
		
	}





	public static int getSize() {
		return size;
	}


	public static void setSize(int size) {
		Page.size = size;
	}


	public Integer getTotalPage() {
		return totalPage;
	}


	public Integer getCurrentPageNo() {
		return currentPageNo;
	}


	public void setCurrentPageNo(Integer currentPageNo) {
		if( currentPageNo==null) {
			this.currentPageNo=1;
		}else {
			
			this.currentPageNo = currentPageNo;
		}
		
	}


	public List<T> getList() {
		return list;
	}


	public void setList(List<T> list) {
		this.list = list;
	}


}
