package sumeng.student.uap.user.contoller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import retrofit2.http.Body;
import sumeng.student.uap.common.entity.Apply;
import sumeng.student.uap.common.entity.NewApply;
import sumeng.student.uap.common.utils.JwtUtil;
import sumeng.student.uap.common.utils.Result;
import sumeng.student.uap.user.service.MyApplyService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 申请表 前端控制器
 * </p>
 *
 * @author sumeng
 * @since 2020-03-17
 */
@RestController
@RequestMapping("/apply")
public class MyApplyController {
    @Autowired
    private MyApplyService myApplyService;

    @Transactional
    @PostMapping("/submitApply")
    public Result submitApply(HttpServletRequest request,@RequestBody Apply apply){
        String userId = JwtUtil.checkTocken(request);
        apply.setApplyUserId(userId);
        apply.setApplyTime(new Date());
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("apply_user_id",userId)
                .eq("verify_user_id",apply.getVerifyUserId())
                .eq("apply_type",apply.getApplyType());
        boolean b = myApplyService.saveOrUpdate(apply,queryWrapper);
        return Result.isSuccess(b,"提交申请");
    }

    @GetMapping("/getMyapply")
    public Result getMyapply(HttpServletRequest request){
        String userId = JwtUtil.checkTocken(request);
        List<NewApply> applyList = myApplyService.getMyApply(userId);
        return  Result.isSuccess(applyList,"获取申请信息");
    }
    @PostMapping("/dealApply")
    public Result dealApply(HttpServletRequest request,String applyId,String applyStatus){
        String userId=JwtUtil.checkTocken(request);
        Boolean apply = myApplyService.dealApply(userId, applyId, applyStatus);
        return Result.isSuccess(apply,"处理申请");
    }


}

