/**
 * Copyright (C), 2019-2020, sumeng
 * FileName: MyMessageController
 * Author: sumeng
 * Date: 2020/3/30 16:47
 */
package sumeng.student.uap.user.contoller;
/**
 * @ClassName: MyMessageController
 * @Description: 消息
 * @Author: sumeng
 * @Version: 1.0.0
 * @Date: 2020/3/30 16:47
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sumeng.student.uap.common.entity.Message;
import sumeng.student.uap.common.utils.JwtUtil;
import sumeng.student.uap.common.utils.Result;
import sumeng.student.uap.user.service.MyMessageService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MyMessageController {
    @Autowired private MyMessageService messageService;

    @PostMapping("/sendMessage")
    public Result sendMessage(Message message){
        message.setSendTime(new Date());
        boolean save = messageService.save(message);
        return Result.isSuccess(save,"发送消息");
    }
    @GetMapping("/getMessage")
    public Result getMessage(HttpServletRequest request,String receiveUserId){
        String userId = JwtUtil.checkTocken(request);
        List<Message> message = messageService.getMessage(userId,receiveUserId);
        return Result.isSuccess(message,"获取消息");
    }

}
