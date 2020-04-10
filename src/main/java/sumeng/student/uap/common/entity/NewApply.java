/**
 * Copyright (C), 2019-2020, sumeng
 * FileName: NewApply
 * Author: sumeng
 * Date: 2020/3/31 13:43
 */
package sumeng.student.uap.common.entity;/**
 * @ClassName: NewApply
 * @Description: 我的申请
 * @Author: sumeng
 * @Version: 1.0.0
 * @Date: 2020/3/31 13:43
 */

import lombok.Data;

/**
 *
 *
 * @date 2020/3/31  13:43
 * @author 12068
 */
@Data
public class NewApply {
    private String applyId;
    private String applyUserId;
    private String account;
    private String username;
    private String applyMsg;
    private String faceUrl;
    private Integer applyType;
}
