package sumeng.student.uap.common.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 题目
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Question implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId
    private String questionId;

    /**
     * 归属题库
     */
    private String repositoryId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 题目类型
     */
    private Integer type;

    /**
     * 题目正文
     */
    private String questionInfo;

    /**
     * 题目选项
     */
    private String questionOption;

    /**
     * 正确答案
     */
    private String trueAnswer;


}
