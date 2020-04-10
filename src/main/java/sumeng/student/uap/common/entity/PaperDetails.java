package sumeng.student.uap.common.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 试卷详情
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PaperDetails implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId
    private String paperDetailsId;

    /**
     * 题目编号
     */
    private String questionId;

    /**
     * 用户试卷编号
     */
    private String issuePaperId;

    /**
     * 用户答案
     */
    private String userAnswer;

    /**
     * 题目分数
     */
    private Integer questionScore;

    /**
     * 题目得分
     */
    private Integer userScore;

    /**
     * 审批人
     */
    private String approId;


}
