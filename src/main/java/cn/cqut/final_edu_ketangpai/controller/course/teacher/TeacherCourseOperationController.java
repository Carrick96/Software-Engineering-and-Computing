package cn.cqut.final_edu_ketangpai.controller.course.teacher;

import cn.cqut.final_edu_ketangpai.dto.CourseExecution;
import cn.cqut.final_edu_ketangpai.dto.CourseOfStudentExecution;
import cn.cqut.final_edu_ketangpai.dto.CourseOfTeacherExecution;
import cn.cqut.final_edu_ketangpai.entity.Course;
import cn.cqut.final_edu_ketangpai.entity.Homework;
import cn.cqut.final_edu_ketangpai.entity.User;
import cn.cqut.final_edu_ketangpai.enums.CourseStateEnum;
import cn.cqut.final_edu_ketangpai.enums.StatusEnum;
import cn.cqut.final_edu_ketangpai.service.Course0fTeacherService;
import cn.cqut.final_edu_ketangpai.service.CourseOfStudentService;
import cn.cqut.final_edu_ketangpai.service.CourseService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @CLASSNAME:TeacherCourseOperationController
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-14 23:56
 */
@RestController
@RequestMapping("teacgercourse")
public class TeacherCourseOperationController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private Course0fTeacherService course0fTeacherService;
	@Autowired
	private CourseOfStudentService courseOfStudentService;

	@GetMapping("topcourse")
	private Map<String, Object> topCourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		int id = HttpServletRequestUtil.getInt(request, "id");
		try {
			CourseExecution courseExecution = courseService.topCourse(id);
			if (courseExecution.getState() != CourseStateEnum.SUCCESS.getState()) {
				modelMap.put("success", false);
				modelMap.put("errMsg", CourseStateEnum.NULL_COURSEID.getStateInfo());
				return modelMap;
			}
			modelMap.put("success", true);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}

	@GetMapping("untopcourse")
	private Map<String, Object> untopCourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		int id = HttpServletRequestUtil.getInt(request, "id");
		try {
			CourseExecution courseExecution = courseService.untopCourse(id);
			if (courseExecution.getState() != CourseStateEnum.SUCCESS.getState()) {
				modelMap.put("success", false);
				modelMap.put("errMsg", CourseStateEnum.NULL_COURSEID.getStateInfo());
				return modelMap;
			}
			modelMap.put("success", true);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}

	@GetMapping("getteanum")
	private Map<String, Object> getTeacherNum(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		Course course = (Course) request.getSession().getAttribute("course");
		try {
			CourseOfTeacherExecution teacherNum = course0fTeacherService.getTeacherNum(course.getCourseId());
			if (teacherNum.getCount() <= 0) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "查询失败");
				return modelMap;
			}
			modelMap.put("success", true);
			modelMap.put("teacherCount", teacherNum.getCount());
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}

	@GetMapping("getstunum")
	private Map<String, Object> getStuNum(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		Course course = (Course) request.getSession().getAttribute("course");
		try {
			CourseOfStudentExecution studentNum = courseOfStudentService.getStudentNum(course.getCourseId());
			if (studentNum.getCount() <= 0) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "查询失败");
				return modelMap;
			}
			modelMap.put("success", true);
			modelMap.put("studentCount", studentNum.getCount());
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}

	@GetMapping("getcoursebyid")
	private Map<String, Object> getCourseById(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		String courseId = HttpServletRequestUtil.getString(request, "courseId");
		CourseExecution courseExecution = courseService.getCourseById(courseId);
		if (courseExecution.getCourse() == null) {
			map.put("success", false);
			map.put("errMsg", CourseStateEnum.NULL_COURSE);
		}
		/*map.put("course", courseExecution.getCourse());*/
		map.put("success", true);
		request.getSession().setAttribute("course", courseExecution.getCourse());
		return map;
	}

	@GetMapping("/getcoursedetail")
	private Map<String, Object> getCourseDetail(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		Course course = (Course) request.getSession().getAttribute("course");
		if (course == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", CourseStateEnum.NULL_COURSEID.getStateInfo());
			return modelMap;
		}
		modelMap.put("course", course);
		modelMap.put("success", true);
		return modelMap;
	}

	@GetMapping("/coursememberofteacher")
	private Map<String, Object> courseMemberOfTeacher(HttpServletRequest request) {

		Map<String, Object> modelMap = new HashMap<>();
		Course course = (Course) request.getSession().getAttribute("course");
		try {
			List<User> userList = course0fTeacherService.getTeacherName(course.getCourseId());
			assert userList != null;
			modelMap.put("success", true);

			modelMap.put("teacherMemberList", userList);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}

	@GetMapping("/coursememberofstudent")
	private Map<String, Object> courseMember(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		Course course = (Course) request.getSession().getAttribute("course");
		try {
			List<User> userList = courseOfStudentService.getStudentName(course.getCourseId());
			assert userList != null;
			modelMap.put("success", true);
			modelMap.put("user_role", 2);
			modelMap.put("studentMemberList", userList);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}

	@GetMapping("archivecourse")
	private Map<String, Object> archiveCourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		int id = HttpServletRequestUtil.getInt(request, "id");
		try {
			CourseExecution courseExecution = courseService.archiveCourse(id);
			if (courseExecution.getState() != CourseStateEnum.SUCCESS.getState()) {
				modelMap.put("success", false);
				modelMap.put("errMsg", CourseStateEnum.NULL_COURSEID.getStateInfo());
				return modelMap;
			}
			modelMap.put("success", true);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}

	@GetMapping("unarchivecourse")
	private Map<String, Object> unarchiveCourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		int id = HttpServletRequestUtil.getInt(request, "id");
		try {
			CourseExecution courseExecution = courseService.unarchiveCourse(id);
			if (courseExecution.getState() != CourseStateEnum.SUCCESS.getState()) {
				modelMap.put("success", false);
				modelMap.put("errMsg", CourseStateEnum.NULL_COURSEID.getStateInfo());
				return modelMap;
			}
			modelMap.put("success", true);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}

	@PostMapping("modifycourse")
	private Map<String, Object> modifyCourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String editStr = HttpServletRequestUtil.getString(request, "editStr");
		ObjectMapper mapper = new ObjectMapper();
		Course course;
		User currentUser = UserTool.getCurrentUser();
		if (currentUser != null) {
			try {
				assert editStr != null;
				course = mapper.readValue(editStr, Course.class);
				CourseExecution courseExecution = courseService.updateCourse(course);
				if (courseExecution.getState() == StatusEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", StatusEnum.OPERATE_FAIL.getStateInfo());
				}
				return modelMap;
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", StatusEnum.OPERATE_FAIL.getStateInfo());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", StatusEnum.ERROR_20011.getStateInfo());
			return modelMap;
		}
	}

	@GetMapping("deletecourse")
	private Map<String, Object> deleteCourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String courseId = HttpServletRequestUtil.getString(request, "courseId");
		try {
			assert courseId != null;
			CourseExecution courseExecution = courseService.deleteCourseById(courseId);
			if (courseExecution.getState() != StatusEnum.SUCCESS.getState()) {
				modelMap.put("success", false);
				modelMap.put("errMsg", StatusEnum.SERVICE_ERROR.getStateInfo());
			} else {
				modelMap.put("success", true);
			}
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

	}

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
			CourseExecution courseExecution = course0fTeacherService.joinCourse(currentUser.getUserId(), courseId);
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

	@GetMapping("/getcourselist")
	private Map<String, Object> getCourseList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		try {
			User currentUser = UserTool.getCurrentUser();
			if (currentUser == null) {
				modelMap.put("errMsg", StatusEnum.ERROR_20011.getStateInfo());
				return modelMap;
			}
			CourseExecution courseExecution = courseService.getTeacherCourseList(currentUser.getUserId());
			List<Homework> homeworkList = (List<Homework>) request.getSession().getAttribute("homeworkList");
			assert homeworkList.size() != 0;
			modelMap.put("homeworkList",homeworkList);
			modelMap.put("courseList", courseExecution.getCourseList());
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", StatusEnum.SERVICE_ERROR.getStateInfo());
		}
		return modelMap;
	}

	@PostMapping("createcourse")
	private Map<String, Object> createCourse(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String establishStr = HttpServletRequestUtil.getString(request, "establishStr");
		ObjectMapper mapper = new ObjectMapper();
		Course course;
		User currentUser = UserTool.getCurrentUser();
		try {
			assert establishStr != null;
			course = mapper.readValue(establishStr, Course.class);
			course.setCourseId(CodeUtil.generateId());
			course.setTeacherId(currentUser.getUserId());
			course.setTeacherName(currentUser.getUsername());
			course.setCreateDate(LocalDateTime.now());
			course.setModifyDate(LocalDateTime.now());
			course.setTopStatus(false);
			CourseExecution courseExecution = courseService.addCourse(course);
			CourseExecution courseExecution1 = course0fTeacherService.joinCourse(currentUser.getUserId(), course.getCourseId());
			if (courseExecution.getState() == StatusEnum.SUCCESS.getState()
					&& courseExecution1.getState() == CourseStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", StatusEnum.OPERATE_FAIL.getStateInfo());
			}
			return modelMap;

		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", StatusEnum.OPERATE_FAIL.getStateInfo());
			return modelMap;
		}
	}
}
