package sumeng.student.uap.attendance.contoller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import sumeng.student.uap.attendance.service.MyAttendanceGroupService;
import sumeng.student.uap.attendance.service.MyMemberService;
import sumeng.student.uap.common.entity.AttendanceGroup;
import sumeng.student.uap.common.entity.Member;
import sumeng.student.uap.common.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 考勤组 前端控制器
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@RestController
@RequestMapping("/group")
public class AttendanceGroupController {
    @Autowired
    private MyAttendanceGroupService myAttendanceGroupService;
    @Autowired
    private MyMemberService myMemberService;

    @Transactional
    @PostMapping("/createGroup")
    public Result createGroup(HttpServletRequest request, @RequestBody AttendanceGroup attendanceGroup){
        attendanceGroup=myAttendanceGroupService.createGroup(request,attendanceGroup);
        if (attendanceGroup!=null){
            Member member = new Member();
            member.setRoleId(MemberType.SuperAdmin);
            member.setGroupId(attendanceGroup.getGroupId());
            member.setUserId(attendanceGroup.getUserId());
            Boolean b=myMemberService.addMember(member);
            return Result.isSuccess(b,"创建考勤组");
        }
        return Result.error("创建考勤组失败");
    }
    @Transactional
    @PostMapping("/addGroup")
    public Result addGroup(HttpServletRequest request,String groupId){
        Member member = new Member();
        member.setRoleId(MemberType.Member);
        member.setGroupId(groupId);
        member.setUserId(JwtUtil.checkTocken(request));
        Boolean b=myMemberService.addMember(member);
        return Result.isSuccess(b,"加入考勤组");
    }

    @Transactional
    @PostMapping("/changeGroupRole")
    public Result changeGroupRole(HttpServletRequest request,String memberId,String groupRole){
        return myMemberService.changeGroupRole(request,memberId,groupRole);
    }

    @Transactional
    @PostMapping("/searchGroup")
    public Result searchGroup(String key,int page,int limit){
        return Result.isSuccess(myAttendanceGroupService.searchGroup(key,page,limit),"搜索");
    }

    @Transactional
    @GetMapping("/getMygroup")
    public Result getMygroup(HttpServletRequest request){
        String userId=JwtUtil.checkTocken(request);
        List<AttendanceGroup> groupList = myAttendanceGroupService.getMyGroup(userId);
        return Result.isSuccess(groupList,"获取我的考勤组");
    }
}

