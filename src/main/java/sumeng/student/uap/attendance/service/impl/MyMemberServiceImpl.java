package sumeng.student.uap.attendance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sumeng.student.uap.attendance.service.MyMemberService;
import sumeng.student.uap.common.entity.Member;
import sumeng.student.uap.common.mapper.MemberMapper;
import sumeng.student.uap.common.utils.JwtUtil;
import sumeng.student.uap.common.utils.MemberType;
import sumeng.student.uap.common.utils.Result;
import sumeng.student.uap.user.service.MyUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author 12068
 * @date 2020/3/14  14:58
 */
@Service
public class MyMemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MyMemberService {
    @Autowired
    private MyUserService userService;
    @Override
    public Boolean addMember(Member member) {
        member.setJionTime(new Date());
        member.setMemberName(userService.getUserNameById(member.getUserId()));
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id",member.getGroupId())
                .eq("user_id",member.getUserId());
        return saveOrUpdate(member,queryWrapper);
    }

    @Override
    public Result changeGroupRole(HttpServletRequest request, String memberId, String groupRole) {
        Member member = new Member();
        if (groupRole.equals(MemberType.SuperAdmin)){
            Member superAdmin = new Member();
            superAdmin.setRoleId(MemberType.Member);
            QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", JwtUtil.checkTocken(request));
            boolean a = update(superAdmin,queryWrapper);
            if (!a) return  Result.isSuccess(a,"修改角色");
        }
        member.setMemberId(memberId);
        member.setRoleId(groupRole);
        boolean b = updateById(member);
        return Result.isSuccess(b,"修改角色");
    }

    @Override
    public List<Member> getMemberBygroupId(String groupId) {
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("group_id",groupId)
                .eq("status","1");
        List<Member> members=list(memberQueryWrapper);
        return members;
    }
}
