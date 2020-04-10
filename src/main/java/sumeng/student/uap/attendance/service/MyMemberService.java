package sumeng.student.uap.attendance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Transactional;
import sumeng.student.uap.common.entity.Member;
import sumeng.student.uap.common.service.MemberService;
import sumeng.student.uap.common.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 12068
 * @date 2020/3/14  14:57
 */
public interface MyMemberService extends MemberService {
    @Transactional
    public Boolean addMember(Member member);
    @Transactional
    public Result changeGroupRole(HttpServletRequest request, String memberId, String groupRole);
    @Transactional
    public List<Member> getMemberBygroupId(String groupId);
}
