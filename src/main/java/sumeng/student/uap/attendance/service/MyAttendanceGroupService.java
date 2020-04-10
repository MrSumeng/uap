/**
 * Copyright (C), 2019-2020, sumeng
 * FileName: MyAttendanceGroupService
 * Author: sumeng
 * Date: 2020/3/14 14:35
 */
package sumeng.student.uap.attendance.service;
/**
 * @ClassName: MyAttendanceGroupService
 * @Description: 考勤组服务层接口
 * @Author: sumeng
 * @Version: 1.0.0
 * @Date: 2020/3/14 14:35
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import sumeng.student.uap.common.entity.AttendanceGroup;
import sumeng.student.uap.common.service.AttendanceGroupService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 *
 * @date 2020/3/14  14:35
 * @author 12068
 */
public interface MyAttendanceGroupService extends AttendanceGroupService {
    public AttendanceGroup createGroup(HttpServletRequest request, AttendanceGroup attendanceGroup);
    public List<AttendanceGroup> getMyGroup(String userId);
    public IPage<AttendanceGroup> searchGroup(String key,int page,int limit);
}
