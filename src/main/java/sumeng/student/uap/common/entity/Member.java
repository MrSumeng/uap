package sumeng.student.uap.common.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 组员
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Member implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId
    private String memberId;

    /**
     * 考勤组编号
     */
    private String groupId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户角色
     */
    private String roleId;

    /**
     * 加入时间
     */
    private Date jionTime;

    /**
     * 最近登录时间
     */
    private Date lastLoginTime;

    /**
     * 退出时间
     */
    private Date logoutTime;

    /**
     * 组内状态
     */
    private String status;

    /**
     * 逻辑删除
     */
    private String isdel;

    /**
     * 组内名称
     */
    private String memberName;


}
