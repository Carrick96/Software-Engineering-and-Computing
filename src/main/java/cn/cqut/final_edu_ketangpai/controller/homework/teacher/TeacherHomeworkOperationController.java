package cn.cqut.final_edu_ketangpai.controller.homework.teacher;

import cn.cqut.final_edu_ketangpai.entity.Homework;
import cn.cqut.final_edu_ketangpai.entity.User;
import cn.cqut.final_edu_ketangpai.enums.HomeworkStateEnum;
import cn.cqut.final_edu_ketangpai.enums.UserStateEnum;
import cn.cqut.final_edu_ketangpai.service.HomeworkService;
import cn.cqut.final_edu_ketangpai.util.HttpServletRequestUtil;
import cn.cqut.final_edu_ketangpai.util.UserTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

	@PostMapping("addhomework")
	private Map<String, Object> addHomework(HttpServletRequest request) {
		Map<String,Object> modelMap = new HashMap<>();
		String homeworkStr = HttpServletRequestUtil.getString(request, "homeworkStr");
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
			if (homework == null) {
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
