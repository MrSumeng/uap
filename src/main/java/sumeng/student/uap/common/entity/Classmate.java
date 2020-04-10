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
 * 同学
 * </p>
 *
 * @author sumeng
 * @since 2020-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Classmate implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId
    private String classmateId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 同学编号
     */
    private String classmateUserId;

    /**
     * 备注
     */
    private String remake;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态(0 已删除 1 好友 2黑名单)
     */
    private String status;


}
