package sumeng.student.uap.user.service;

import sumeng.student.uap.common.entity.Message;
import sumeng.student.uap.common.service.MessageService;
import sumeng.student.uap.common.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 12068
 * @date 2020/4/3  19:09
 */
public interface MyMessageService extends MessageService {
    public List<Message > getMessage(String userId,String receiveUserId);
    public Boolean updateMessage(String userId,String receiveUserId);
}
