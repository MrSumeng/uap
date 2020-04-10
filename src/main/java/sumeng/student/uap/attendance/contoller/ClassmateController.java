package sumeng.student.uap.attendance.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import sumeng.student.uap.attendance.service.MyClassmateService;
import sumeng.student.uap.common.entity.ClassmateMessage;
import sumeng.student.uap.common.utils.JwtUtil;
import sumeng.student.uap.common.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 同学 前端控制器
 * </p>
 *
 * @author sumeng
 * @since 2020-03-18
 */
@RestController
@RequestMapping("/classmate")
public class ClassmateController {
    @Autowired
    private MyClassmateService classmateService;

    @GetMapping("/searchclassmate")
    public Result searchclassmate(String key){
        return null;
    }
    @GetMapping("/getMyClassmate")
    public Result getMyClassmate(HttpServletRequest request,String key){
        String userId = JwtUtil.checkTocken(request);
        List<ClassmateMessage> messageList = classmateService.getMyClassmate(userId);
        return Result.isSuccess(messageList,"获取同学列表");
    }
}

