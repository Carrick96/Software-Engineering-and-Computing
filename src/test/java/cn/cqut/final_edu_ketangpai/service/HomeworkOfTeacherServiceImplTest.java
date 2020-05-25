package cn.cqut.final_edu_ketangpai.service;

import cn.cqut.final_edu_ketangpai.dto.HomeworkExecution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeworkOfTeacherServiceImplTest {

	@Autowired
	private HomeworkOfTeacherService homeworkOfTeacherService;
	@Test
	public void getTeaHomeworkList() {
		HomeworkExecution execution = homeworkOfTeacherService.getTeaHomeworkList("123143423432");
		System.out.println(execution.getHomeworkList()+"\n");
	}
}