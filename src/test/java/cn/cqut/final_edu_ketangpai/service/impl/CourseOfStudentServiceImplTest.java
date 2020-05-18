package cn.cqut.final_edu_ketangpai.service.impl;

import cn.cqut.final_edu_ketangpai.entity.CourseOfStudent;
import cn.cqut.final_edu_ketangpai.entity.User;
import cn.cqut.final_edu_ketangpai.service.CourseOfStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

	@Test
	@Transactional
	public void test() {
		List<CourseOfStudent> list = new ArrayList<>();
		CourseOfStudent courseOfStudent = new CourseOfStudent();

		courseOfStudent.setCourseId("5diuvc");
		courseOfStudent.setStudentId("Asdasd");
		list.add(courseOfStudent);
		CourseOfStudent courseOfStudent2 = new CourseOfStudent();
		courseOfStudent2.setCourseId("zJb2Fz");
		courseOfStudent2.setStudentId("dasdasd");
		list.add(courseOfStudent2);
		courseOfStudentService.saveOrUpdateBatch(list);
	}
}