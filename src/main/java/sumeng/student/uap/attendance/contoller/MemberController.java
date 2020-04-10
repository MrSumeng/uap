package sumeng.student.uap.attendance.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import sumeng.student.uap.attendance.service.MyMemberService;
import sumeng.student.uap.common.utils.Result;

/**
 * <p>
 * 组员 前端控制器
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MyMemberService myMemberService;

    @GetMapping("/getMemberBygroupId")
    public Result getMemberBygroupId(String groupId){
        return Result.isSuccess(myMemberService.getMemberBygroupId(groupId),"获取成员");
    }
}

