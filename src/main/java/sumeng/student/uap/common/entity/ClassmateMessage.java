/**
 * Copyright (C), 2019-2020, sumeng
 * FileName: ClassmateMessage
 * Author: sumeng
 * Date: 2020/4/2 12:47
 */
package sumeng.student.uap.common.entity;/**
 * @ClassName: ClassmateMessage
 * @Description: ClassmateMessage
 * @Author: sumeng
 * @Version: 1.0.0
 * @Date: 2020/4/2 12:47
 */

import lombok.Data;

/**
 *
 *
 * @date 2020/4/2  12:47
 * @author 12068
 */
@Data
public class ClassmateMessage extends Classmate{
    private String faceUrl;
    private String personInfo;
    private String username;
}
