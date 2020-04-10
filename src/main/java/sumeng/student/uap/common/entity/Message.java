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
 * 消息（message）
 * </p>
 *
 * @author sumeng
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Message implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId
    private String messageId;

    /**
     * 发送人
     */
    private String sendUserId;

    /**
     * 接收人
     */
    private String receiveUserId;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 接收时间
     */
    private Date receiveTime;

    /**
     * 是否发送成功
     */
    private Integer isSendSuccess;

    /**
     * 是否接收
     */
    private Integer isReceive;

    /**
     * 消息内容
     */
    private String messageContant;

    /**
     * 接收人类型 0:服务器 1:用户 2:群组
     */
    private Integer receiveType;


}
