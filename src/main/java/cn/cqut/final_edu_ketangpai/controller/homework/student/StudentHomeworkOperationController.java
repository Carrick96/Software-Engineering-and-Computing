package cn.cqut.final_edu_ketangpai.controller.homework.student;

import cn.cqut.final_edu_ketangpai.dto.*;
import cn.cqut.final_edu_ketangpai.entity.Homework;
import cn.cqut.final_edu_ketangpai.entity.HomeworkOfStudent;
import cn.cqut.final_edu_ketangpai.entity.User;
import cn.cqut.final_edu_ketangpai.enums.CourseStateEnum;
import cn.cqut.final_edu_ketangpai.enums.HomeworkStateEnum;
import cn.cqut.final_edu_ketangpai.exception.HomeworkOperationException;
import cn.cqut.final_edu_ketangpai.service.CourseOfStudentService;
import cn.cqut.final_edu_ketangpai.service.CourseService;
import cn.cqut.final_edu_ketangpai.service.HomeworkOfStudentService;
import cn.cqut.final_edu_ketangpai.service.HomeworkService;
import cn.cqut.final_edu_ketangpai.util.HttpServletRequestUtil;
import cn.cqut.final_edu_ketangpai.util.UserTool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @CLASSNAME:StudentHomeworkoperationController
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-19 19:37
 */
@RestController
@RequestMapping("studenthomework")
public class StudentHomeworkOperationController {
	@Autowired
	private HomeworkService homeworkService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseOfStudentService courseOfStudentService;
	@Autowired
	private HomeworkOfStudentService homeworkOfStudentService;

	@GetMapping("gethomeworkbyid")
	private Map<String, Object> getHomeworkById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		int id = HttpServletRequestUtil.getInt(request, "id");
		Homework homework = homeworkService.getById(id);
		if (homework == null) {
			throw new HomeworkOperationException(HomeworkStateEnum.NULL_HOMEWORKID.getStateInfo());
		}
		modelMap.put("success", true);
		modelMap.put("homework", homework);
		return modelMap;
	}

	@GetMapping("getstuhomework")
	private Map<String, Object> getStuHomework(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String courseId = HttpServletRequestUtil.getString(request, "courseId");
		User currentUser = UserTool.getCurrentUser();
		try {
			HomeworkExecution execution = homeworkOfStudentService.getStuHomeworkList(courseId, currentUser.getUserId());
			assert execution.getHomeworkList().size() != 0;
			modelMap.put("success", true);
			modelMap.put("homeworkList", execution.getHomeworkList());
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	@PostMapping("submithomework")
	private Map<String, Object> submitHomework(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 1.接收并转化相应的参数，即作业文件信息
		String homeworkStr = HttpServletRequestUtil.getString(request, "homeworkStr");
		User currentUser = UserTool.getCurrentUser();
		HttpServletRequestUtil.getString(request, "homeworkStr");
		ObjectMapper mapper = new ObjectMapper();
		HomeworkOfStudent homeworkOfStudent;
		try {
			homeworkOfStudent = mapper.readValue(Objects.requireNonNull(homeworkStr), HomeworkOfStudent.class);
			homeworkOfStudent.setStudentId(currentUser.getUserId());
		} catch (JsonProcessingException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile file = null;
		CommonsMultipartResolver commonsMultipartResolver =
				new CommonsMultipartResolver(request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest
					= (MultipartHttpServletRequest) request;
			file = (CommonsMultipartFile) multipartHttpServletRequest
					.getFile("file");
		}
		//2 提交作业
		if (homeworkOfStudent.getHomeworkId() != null) {
			HomeworkOfStudentExecution execution;
			try {
				if (file == null) {
					execution = homeworkOfStudentService.submitHomework(homeworkOfStudent, null);
				} else {
					ImageHolder imageHolder = new ImageHolder(file.getOriginalFilename(), file.getInputStream());
					execution = homeworkOfStudentService.submitHomework(homeworkOfStudent, imageHolder);
				}
				if (execution.getState() == HomeworkStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", execution.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺Id");
			return modelMap;
		}
	}

	@GetMapping("getcourse")
	private Map<String, Object> getCourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String courseId = HttpServletRequestUtil.getString(request, "courseId");
		try {
			CourseExecution courseExecution = courseService.getCourseById(courseId);
			CourseOfStudentExecution execution = courseOfStudentService.getStudentNum(courseId);
			assert execution.getCount() != 0;
			if (courseExecution.getCourse() == null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", CourseStateEnum.NULL_COURSEID.getStateInfo());
				return modelMap;
			}
			modelMap.put("course", courseExecution.getCourse());
			modelMap.put("stuCount", execution.getCount());
			modelMap.put("success", true);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

	}

/*	@GetMapping("getstuhomeworklist")
	private Map<String, Object> getStuHomeworkList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String courseId = HttpServletRequestUtil.getString(request, "courseId");
		User currentUser = UserTool.getCurrentUser();
		try {
			HomeworkExecution homeworkExecution = homeworkService.getStuHomeworks(currentUser.getUserId(), courseId);
			assert homeworkExecution.getHomeworkList() != null;
			modelMap.put("success", true);
			modelMap.put("homeworkList", homeworkExecution.getHomeworkList());
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}*/
}
