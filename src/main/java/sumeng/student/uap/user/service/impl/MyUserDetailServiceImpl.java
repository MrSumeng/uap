/**
 * Copyright (C), 2019-2020, sumeng
 * FileName: MyUserDetailServiceImpl
 * Author: sumeng
 * Date: 2020/3/7 15:19
 */
package sumeng.student.uap.user.service.impl;/**
 * @ClassName: MyUserDetailServiceImpl
 * @Description: 用户详情
 * @Author: sumeng
 * @Version: 1.0.0
 * @Date: 2020/3/7 15:19
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sumeng.student.uap.common.entity.UserDetail;
import sumeng.student.uap.common.mapper.UserDetailMapper;
import sumeng.student.uap.user.service.MyUserDetailService;

/**
 *
 *
 * @date 2020/3/7  15:19
 * @author 12068
 */
@Service
public class MyUserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements MyUserDetailService {

    @Override
    public UserDetail getUserDetailByUserId(String userId) {
        QueryWrapper<UserDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        UserDetail userDetail = getOne(queryWrapper);
        return userDetail;
    }
}
