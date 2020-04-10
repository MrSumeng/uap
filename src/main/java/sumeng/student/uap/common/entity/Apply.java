package sumeng.student.uap.common.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 申请表
 * </p>
 *
 * @author sumeng
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Apply implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 申请id
     */
    @TableId
    private String applyId;

    /**
     * 申请人
     */
    private String applyUserId;

    /**
     * 审核人
     */
    private String verifyUserId;
    /**
     * 申请信息 添加群组时存放群号
     */
    private String applyMsg;

    /**
     * 申请类型 (1添加好友  2 加入群组)
     */
    private Integer applyType;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 申请状态 (2 未处理 0拒绝 1同意)
     */
    private String applyStatus;


}
