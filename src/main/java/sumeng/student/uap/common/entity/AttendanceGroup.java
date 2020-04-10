package sumeng.student.uap.common.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 考勤组
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AttendanceGroup implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId
    private String groupId;

    /**
     * 组号
     */
    private String groupNumber;
    /**
     * 创建人id
     */
    private String userId;

    /**
     * 组名
     */
    private String groupName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 描述
     */
    private String groupInfo;

    /**
     * 状态
     */
    private String status;

    /**
     * 删除
     */
    private String isdel;


}
