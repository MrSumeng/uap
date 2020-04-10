package sumeng.student.uap.common.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 请假
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Leave implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId
    private String leaveId;

    /**
     * 考勤组编号
     */
    private String groupId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 请假时间
     */
    private Date leaveTime;

    /**
     * 请假原因
     */
    private String reason;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 审批人
     */
    private String approId;

    /**
     * 审批状态
     */
    private String approStatus;

    /**
     * 审批时间
     */
    private Date approTime;


}
