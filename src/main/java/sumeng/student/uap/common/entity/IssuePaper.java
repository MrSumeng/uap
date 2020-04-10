package sumeng.student.uap.common.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 发布试卷
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class IssuePaper implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId
    private String issuePaperId;

    /**
     * 发布人
     */
    private String userId;

    /**
     * 题库编号
     */
    private String repositoryId;

    /**
     * 出题规则
     */
    private String orderId;

    /**
     * 答题限时
     */
    private Integer answerTime;

    /**
     * 答题开始时间
     */
    private Date startTime;

    /**
     * 答题结束时间
     */
    private Date endTime;

    /**
     * 试卷名
     */
    private String paperName;

    /**
     * 试卷描述
     */
    private String paperInfo;

    /**
     * 答题群组
     */
    private String groupId;

    /**
     * 试卷总分
     */
    private Integer allScore;


}
