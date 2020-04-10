package sumeng.student.uap.common.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户试卷
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TestPaper implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId
    private String paperId;

    /**
     * 归属用户
     */
    private String userId;

    /**
     * 所用试卷
     */
    private String issuePaperId;

    /**
     * 答题开始时间
     */
    private Date startTime;

    /**
     * 答题结束时间
     */
    private Date endTime;

    /**
     * 试卷状态
     */
    private String status;

    /**
     * 获得分值
     */
    private Integer allScore;


}
