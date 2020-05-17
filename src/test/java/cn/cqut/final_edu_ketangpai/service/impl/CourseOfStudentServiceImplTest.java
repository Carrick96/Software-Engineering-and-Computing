package cn.cqut.final_edu_ketangpai.service.impl;

import cn.cqut.final_edu_ketangpai.entity.User;
import cn.cqut.final_edu_ketangpai.service.CourseOfStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseOfStudentServiceImplTest  {

	@Autowired
	private CourseOfStudentService courseOfStudentService;
	@Test
	public void getStudentName() {
		List<User> studentName = courseOfStudentService.getStudentName("5diuvc");
		System.out.println(studentName.size());
	}
}