package sumeng.student.uap.common.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 出题规则详情
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QuestionOrderDetails implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId
    private String qouestionRderDetailsId;

    /**
     * 出题规则编号
     */
    private String oderId;

    /**
     * 题目类型
     */
    private Integer type;

    /**
     * 题目数量
     */
    private Integer number;

    /**
     * 题目分值
     */
    private Integer score;


}
