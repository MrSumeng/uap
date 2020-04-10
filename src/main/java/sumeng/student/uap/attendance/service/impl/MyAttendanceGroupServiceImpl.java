/**
 * Copyright (C), 2019-2020, sumeng
 * FileName: MyAttendanceGroupServiceImpl
 * Author: sumeng
 * Date: 2020/3/14 14:36
 */
package sumeng.student.uap.attendance.service.impl;/**
 * @ClassName: MyAttendanceGroupServiceImpl
 * @Description: 考勤组服务层实现
 * @Author: sumeng
 * @Version: 1.0.0
 * @Date: 2020/3/14 14:36
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sumeng.student.uap.attendance.service.MyAttendanceGroupService;
import sumeng.student.uap.common.entity.AttendanceGroup;
import sumeng.student.uap.common.mapper.AttendanceGroupMapper;
import sumeng.student.uap.common.utils.GenUUID;
import sumeng.student.uap.common.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 *
 *
 * @date 2020/3/14  14:36
 * @author 12068
 */
@Service
public class MyAttendanceGroupServiceImpl extends ServiceImpl<AttendanceGroupMapper, AttendanceGroup> implements MyAttendanceGroupService {

    @Override
    public AttendanceGroup createGroup(HttpServletRequest request, AttendanceGroup attendanceGroup) {
        String userId= JwtUtil.checkTocken(request);
        String groupNumber= GenUUID.getRandomNum(10);
        attendanceGroup.setGroupNumber(groupNumber);
        attendanceGroup.setCreateTime(new Date());
        attendanceGroup.setUserId(userId);
        if (save(attendanceGroup))
            return attendanceGroup;
        else return null;
    }

    @Override
    public List<AttendanceGroup> getMyGroup(String userId) {
        List<AttendanceGroup> groupList =baseMapper.getMyGroup(userId);
        return groupList;
    }

    @Override
    public IPage<AttendanceGroup> searchGroup(String key,int page,int limit) {
        IPage<AttendanceGroup> groupIPage = new Page<>(page,limit);
        QueryWrapper<AttendanceGroup> groupQueryWrapper = new QueryWrapper<>();
        groupQueryWrapper.like("group_number",key)
                .or()
                .like("group_name",key);
        groupIPage=page(groupIPage,groupQueryWrapper);
        return groupIPage;
    }
}
