package sumeng.student.uap.common.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户详情
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserDetail implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId
    private String userDetailId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 姓名
     */
    private String username;

    /**
     * 性别
     */
    private String sex;

    /**
     * 归属班级
     */
    private String belongClass;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系方式
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 个人简介
     */
    private String personInfo;

    /**
     * 头像地址
     */
    private String faceUrl;


}
