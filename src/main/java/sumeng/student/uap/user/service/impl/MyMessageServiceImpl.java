package sumeng.student.uap.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sumeng.student.uap.common.entity.Message;
import sumeng.student.uap.common.mapper.MessageMapper;
import sumeng.student.uap.user.service.MyMessageService;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author 12068
 * @date 2020/4/3  19:09
 */
@Service
public class MyMessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MyMessageService {
    @Override
    public List<Message> getMessage(String userId,String receiveUserId) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .and(
                        wapper->wapper
                                .eq("send_user_id",userId)
                                .eq("receive_user_id",receiveUserId))
                .or(
                        wapper->wapper
                                .eq("send_user_id",receiveUserId)
                                .eq("receive_user_id",userId))
                .orderByAsc("send_time");
        List<Message> message = list(queryWrapper);
        updateMessage(userId,receiveUserId);
        return message;
    }
    @Override
    public Boolean updateMessage(String userId,String receiveUserId) {
        Message message = new Message();
        message.setIsReceive(1);
        message.setReceiveTime(new Date());
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("send_user_id",receiveUserId)
                .eq("receive_user_id",userId)
                .eq("is_receive",0);
        boolean update = update(message,queryWrapper);
        return update;
    }
}
