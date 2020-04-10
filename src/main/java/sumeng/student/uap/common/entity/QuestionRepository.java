package sumeng.student.uap.common.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 题库
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QuestionRepository implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId
    private String repositoryId;

    /**
     * 创建人编号
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 题目数量
     */
    private Integer number;

    /**
     * 题库状态
     */
    private String status;

    /**
     * 逻辑删除
     */
    private String isdel;

    /**
     * 题库名
     */
    private String repositoryName;

    /**
     * 题库描述
     */
    private String repositoryInfo;


}
