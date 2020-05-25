package cn.cqut.final_edu_ketangpai.controller.homework.teacher;

import cn.cqut.final_edu_ketangpai.dto.HomeworkExecution;
import cn.cqut.final_edu_ketangpai.dto.UserExecution;
import cn.cqut.final_edu_ketangpai.entity.*;
import cn.cqut.final_edu_ketangpai.enums.HomeworkStateEnum;
import cn.cqut.final_edu_ketangpai.enums.UserStateEnum;
import cn.cqut.final_edu_ketangpai.service.HomeworkOfStudentService;
import cn.cqut.final_edu_ketangpai.service.HomeworkOfTeacherService;
import cn.cqut.final_edu_ketangpai.service.HomeworkService;
import cn.cqut.final_edu_ketangpai.util.CodeUtil;
import cn.cqut.final_edu_ketangpai.util.HttpServletRequestUtil;
import cn.cqut.final_edu_ketangpai.util.UserTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @CLASSNAME:TeacherhomeworkOperationController
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-15 21:15
 */
@RestController
@RequestMapping("teacherhomework")
public class TeacherHomeworkOperationController {
	@Autowired
	private HomeworkService homeworkService;
	@Autowired
	private HomeworkOfTeacherService homeworkOfTeacherService;
	@Autowired
	private HomeworkOfStudentService homeworkOfStudentService;

	@GetMapping("getnosubmitcount")
	private Map<String, Object> getNoSubmitCount(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String courseId = HttpServletRequestUtil.getString(request, "courseId");
		int noSubmitCount = homeworkOfTeacherService.getNoSubmitCount(courseId);
		if (noSubmitCount <= 0) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "出现了错误");
		}
		modelMap.put("noSubmitCount", noSubmitCount);
		modelMap.put("success", true);
		return modelMap;
	}

	@GetMapping("getsubmitedcount")
	private Map<String, Object> getSubmitedCount(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String courseId = HttpServletRequestUtil.getString(request, "courseId");
		int submitedCount = homeworkOfTeacherService.getSubmitedCount(courseId);
		if (submitedCount <= 0) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "出现了错误");
		}
		modelMap.put("submitedCount", submitedCount);
		modelMap.put("success", true);
		return modelMap;
	}

	@GetMapping("getnoreadcount")
	private Map<String, Object> getNoReadCount(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String courseId = HttpServletRequestUtil.getString(request, "courseId");
		int noReadCount = homeworkOfTeacherService.getNoReadCount(courseId);
		if (noReadCount <= 0) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "出现了错误");
		}
		modelMap.put("noReadCount", noReadCount);
		modelMap.put("success", true);
		return modelMap;
	}

	@GetMapping("getteahomewoeks")
	private Map<String, Object> getTeaHomewoeks(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		User currentUser = UserTool.getCurrentUser();
		if (currentUser == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", UserStateEnum.NULL_USER.getStateInfo());
			return modelMap;
		}
		try {
			HomeworkExecution execution = homeworkOfTeacherService.getTeaHomeworkList(currentUser.getUserId());
			if (execution.getHomeworkList().size() == 0) {
				modelMap.put("success", false);
				modelMap.put("errMsg", HomeworkStateEnum.NULL_HOMEWORK.getStateInfo());
				return modelMap;
			}
			request.getSession().setAttribute("homeworkList",execution.getHomeworkList());
			modelMap.put("homeworkList", execution.getHomeworkList());
			modelMap.put("success", true);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}

	@PostMapping("addhomework")
	private Map<String, Object> addHomework(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String homeworkStr = HttpServletRequestUtil.getString(request, "releaseHomeworkStr");

		ObjectMapper mapper = new ObjectMapper();
		Homework homework;
		User currentUser = UserTool.getCurrentUser();
		try {
			if (currentUser == null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", UserStateEnum.NULL_USER.getStateInfo());
				return modelMap;
			}
			assert homeworkStr != null;

			homework = mapper.readValue(homeworkStr, Homework.class);
			homework.setCreateTime(LocalDateTime.now());
			homework.setModifyDate(LocalDateTime.now());
			homework.setDeadLine(null);
			homework.setHomeworkId(CodeUtil.generateId());
			HomeworkOfTeacher homeworkOfTeacher = new HomeworkOfTeacher();
			homeworkOfTeacher.setHomeworkId(homework.getHomeworkId());
			homeworkOfTeacher.setTeacherId(currentUser.getUserId());
			HomeworkExecution homeworkExecution = homeworkService.createHomework(homework);
			boolean isSave = homeworkOfTeacherService.save(homeworkOfTeacher);
			UserExecution stuListOfHomework = homeworkService.getStuListOfHomework(homework.getCourseId());
			List<HomeworkOfStudent> homeworkOfStudentList = new ArrayList<>();
			for (int i = 0; i < stuListOfHomework.getUserList().size(); i++) {
				HomeworkOfStudent homeworkOfStudent = new HomeworkOfStudent();
				homeworkOfStudent.setHomeworkId(homework.getHomeworkId());
				homeworkOfStudent.setCourseId(homework.getCourseId());
				homeworkOfStudent.setStudentId(stuListOfHomework.getUserList().get(i).getUserId());
				homeworkOfStudent.setCreateTime(LocalDateTime.now());
				homeworkOfStudent.setModifyTime(LocalDateTime.now());
				homeworkOfStudentList.add(homeworkOfStudent);
			}
			boolean isSaveBatch = homeworkOfStudentService.saveBatch(homeworkOfStudentList);
			if (!isSave && homeworkExecution.getState() != HomeworkStateEnum.SUCCESS.getState() && !isSaveBatch) {
				modelMap.put("success", false);
				modelMap.put("errMsg", HomeworkStateEnum.NULL_HOMEWORK.getStateInfo());
				return modelMap;
			}

			modelMap.put("success", true);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "操作失败，原因是: " + e.getMessage());
			return modelMap;
		}
	}
}
