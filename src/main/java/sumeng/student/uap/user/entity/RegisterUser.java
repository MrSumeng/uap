/**
 * Copyright (C), 2019-2020, sumeng
 * FileName: RegisterUser
 * Author: sumeng
 * Date: 2020/2/23 22:02
 */
package sumeng.student.uap.user.entity;/**
 * @ClassName: RegisterUser
 * @Description: 用户注册实体类
 * @Author: sumeng
 * @Version: 1.0.0
 * @Date: 2020/2/23 22:02
 */

import lombok.Data;
import sumeng.student.uap.common.entity.User;

import java.io.Serializable;

/**
 *
 *
 * @date 2020/2/23  22:02
 * @author 12068
 */
@Data
public class RegisterUser extends User implements Serializable {
    /**
     * 姓名
     */
    private String username;
    /**
     * 联系方式
     */
    private String tel;
}
