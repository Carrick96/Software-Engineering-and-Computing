package cn.cqut.final_edu_ketangpai.controller.course.student;

import cn.cqut.final_edu_ketangpai.dto.CourseExecution;
import cn.cqut.final_edu_ketangpai.entity.User;
import cn.cqut.final_edu_ketangpai.enums.CourseStateEnum;
import cn.cqut.final_edu_ketangpai.enums.StatusEnum;
import cn.cqut.final_edu_ketangpai.service.CourseOfStudentService;
import cn.cqut.final_edu_ketangpai.service.CourseService;
import cn.cqut.final_edu_ketangpai.util.HttpServletRequestUtil;
import cn.cqut.final_edu_ketangpai.util.UserTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @CLASSNAME:StudentCourseOperationController
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-15 02:20
 */
@RestController
@RequestMapping("/studentcourse")
public class StudentCourseOperationController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseOfStudentService courseOfStudentService;

	@PostMapping("joincourse")
	private Map<String, Object> joinCourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String courseId = HttpServletRequestUtil.getString(request, "extrCode");
		User currentUser = UserTool.getCurrentUser();
		if (currentUser == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", StatusEnum.ERROR_20011.getStateInfo());
			return modelMap;
		}
		try {
			CourseExecution courseExecution = courseOfStudentService.studentJoinCourse(courseId, currentUser.getUserId());
			if (courseExecution.getState() != CourseStateEnum.SUCCESS.getState()) {
				modelMap.put("success", false);
				modelMap.put("errMsg", CourseStateEnum.NULL_COURSEID.getStateInfo());
				return modelMap;
			}
			modelMap.put("success", true);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", StatusEnum.SERVICE_ERROR.getStateInfo());
			return modelMap;
		}
	}

	@GetMapping("getcourselist")
	private Map<String, Object> getCourseList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		try {
			User currentUser = UserTool.getCurrentUser();
			if (currentUser == null) {
				modelMap.put("errMsg", StatusEnum.ERROR_20011.getStateInfo());
				return modelMap;
			}
			CourseExecution courseExecution = courseService.getStudentCourseList(currentUser.getUserId());
			request.getSession().setAttribute("courseList",courseExecution.getCourseList());
			modelMap.put("courseList", courseExecution.getCourseList());
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", StatusEnum.SERVICE_ERROR.getStateInfo());
		}
		return modelMap;
	}
}
