package course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itstudy.CourseApplication;
import com.itstudy.controller.CourseContorller;
import com.itstudy.entity.Course;
import com.itstudy.exception.ParameterException;

@SpringBootTest(classes= {CourseApplication.class})
@EnableWebMvc
public class CourseControllerTest {

	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	//@MockBean
	//private CourseService courseService;

	@InjectMocks
	CourseContorller courseContorller;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		//mockMvc = MockMvcBuilders.standaloneSetup(courseContorller).build(); // 初始化MockMvc对象

		//mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		  mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(((request, response, chain) -> {
              response.setCharacterEncoding("UTF-8");
              chain.doFilter(request, response);
          })).build();
	}

	@Sql(value = {"classpath:course/select_id.sql"} )
	@Transactional
	@Rollback
	@Test
	@DisplayName("selectByIdTest")
	public void selectByIdTest() throws Exception {

		ResultActions resultActions = this.mockMvc.perform(get("/course/1")).andExpect(status().isOk());
		
		//resultActions.andExpect(jsonPath("$.status").value("SUCCESS"));
		//resultActions.andExpect(jsonPath("$.data").exists());
		
		MvcResult mvcResult = resultActions.andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		String contentAsString = response.getContentAsString();
		
		ObjectMapper mapper  = new ObjectMapper();

		Map map = mapper.readValue(contentAsString, Map.class);
		
		System.out.println(map);
		
		Map  objMap =  (Map)map.get("data");
		 //Course convertValue = mapper.readValue(objString, Course.class);
		Course course = mapper.convertValue(objMap, Course.class);
		
		Assertions.assertAll("course assert",() ->{
			Assertions.assertEquals(1, course.getId());
			Assertions.assertEquals("Java课程", course.getName());
			Assertions.assertEquals(1, course.getStatus());
		});
		
	}

	//@Sql(statements = "insert into course( name ,status ) values ('Java',0 )")
	@Sql(value = {"classpath:course/like_name.sql"} )
	@Transactional
	@Rollback
	@Test
	@DisplayName("likeNameTest")
	public void  selectLikeNameTest() throws Exception {
		
		String likeCondotion ="Ja";
	
		ObjectMapper mapper  = new ObjectMapper();
		//String jsonObj = mapper.writeValueAsString(c);
		
		//this.mockMvc.perform(post("/course/like").contentType(MediaType.APPLICATION_JSON).content(jsonObj)).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("SUCCESS"));

		ResultActions resultActions = this.mockMvc.perform(get("/course/like?name="+likeCondotion).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("SUCCESS"));
		
		MvcResult mvcResult = resultActions.andReturn();
		
		String contentAsString = mvcResult.getResponse().getContentAsString();
		
		Map readValue = mapper.readValue(contentAsString, Map.class);
		
		
		
		List<Course>  list  = mapper.readValue(mapper.writeValueAsString(readValue.get("data")), new TypeReference<List<Course>>() {});
		System.out.println(list);
		Assertions.assertAll("data of result assert ",()->{
			
			Assertions.assertEquals(2, list.size());
			
			
			Assertions.assertAll("item assert " ,()->{
				
				list.forEach((item) ->{
					
					Assertions.assertEquals(item.getName().indexOf(likeCondotion)>=0, true ,"item name assert");
					
				});
			});
		} );
	
		
		System.out.println(list.get(0).getClass());
		
	}
	

	@Transactional
	@Rollback
	@Test
	@DisplayName("新增课程的测试")
	public void  courseAddTest() throws Exception {
		
		Course c = new Course();
		c.setName("Python");
		c.setStatus(1);
		ObjectMapper mapper  = new ObjectMapper();
		String jsonObj = mapper.writeValueAsString(c);
		
		ResultActions resultActions = this.mockMvc.perform(post("/course/add").contentType(MediaType.APPLICATION_JSON).content(jsonObj)).andExpect(status().isOk());
		
//		MvcResult mvcResult = resultActions.andReturn();
//		
//		String contentAsString = mvcResult.getResponse().getContentAsString();
//		
//		Map readValue = mapper.readValue(contentAsString, Map.class);
//		
//		List<Course>  list  = mapper.readValue(mapper.writeValueAsString(readValue.get("data")), new TypeReference<List<Course>>() {});
//		System.out.println(list);
//		Assertions.assertAll("data of result assert ",()->{
//			
//			Assertions.assertEquals(2, list.size());
//			
//			Assertions.assertAll("item assert " ,()->{
//				
//				list.forEach((item) ->{
//					
//					Assertions.assertEquals(item.getName().indexOf(likeCondotion)>=0, true ,"item name assert");
//					
//				});
//			});
//		} );
			
	
}


@Transactional
@Rollback
@Test()
@DisplayName("新增课程失败测试")
public void  courseAddTestFail() throws Exception {
	
	Course c = new Course();
	c.setName("css");
	c.setStatus(1);
	ObjectMapper mapper  = new ObjectMapper();
	String jsonObj = mapper.writeValueAsString(c);
	
	Exception p = assertThrows(Exception.class,()->{
		ResultActions resultActions = this.mockMvc.perform(post("/course/add").contentType(MediaType.APPLICATION_JSON).content(jsonObj)).andExpect(status().isOk());

	});
	
	System.out.println(p.getClass());
    assertEquals("Cannot divide by zero", p.getMessage());

		
}
}


