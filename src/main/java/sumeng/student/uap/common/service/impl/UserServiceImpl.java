package sumeng.student.uap.common.service.impl;

import sumeng.student.uap.common.entity.User;
import sumeng.student.uap.common.mapper.UserMapper;
import sumeng.student.uap.common.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
