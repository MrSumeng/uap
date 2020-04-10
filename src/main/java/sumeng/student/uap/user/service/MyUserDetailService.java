/**
 * Copyright (C), 2019-2020, sumeng
 * FileName: MyUserDetailService
 * Author: sumeng
 * Date: 2020/3/7 15:18
 */
package sumeng.student.uap.user.service;/**
 * @ClassName: MyUserDetailService
 * @Description: 用户详情
 * @Author: sumeng
 * @Version: 1.0.0
 * @Date: 2020/3/7 15:18
 */

import com.baomidou.mybatisplus.extension.api.R;
import sumeng.student.uap.common.entity.UserDetail;
import sumeng.student.uap.common.service.UserDetailService;

/**
 *
 *
 * @date 2020/3/7  15:18
 * @author 12068
 */
public interface MyUserDetailService extends UserDetailService {
    public UserDetail getUserDetailByUserId(String userId);
}
