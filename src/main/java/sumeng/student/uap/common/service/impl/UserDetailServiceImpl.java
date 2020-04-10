package sumeng.student.uap.common.service.impl;

import sumeng.student.uap.common.entity.UserDetail;
import sumeng.student.uap.common.mapper.UserDetailMapper;
import sumeng.student.uap.common.service.UserDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户详情 服务实现类
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Service
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements UserDetailService {

}
