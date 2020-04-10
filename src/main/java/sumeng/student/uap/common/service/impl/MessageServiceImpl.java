package sumeng.student.uap.common.service.impl;

import sumeng.student.uap.common.entity.Message;
import sumeng.student.uap.common.mapper.MessageMapper;
import sumeng.student.uap.common.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息（message） 服务实现类
 * </p>
 *
 * @author sumeng
 * @since 2020-03-30
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
