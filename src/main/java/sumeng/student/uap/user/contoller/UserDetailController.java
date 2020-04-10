package sumeng.student.uap.user.contoller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import sumeng.student.uap.common.entity.UserDetail;
import sumeng.student.uap.common.utils.JwtUtil;
import sumeng.student.uap.common.utils.Result;
import sumeng.student.uap.user.service.MyUserDetailService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 用户详情 前端控制器
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@RestController
@RequestMapping("/user-detail")
public class UserDetailController {
    @Autowired private MyUserDetailService userDetailService;


    @GetMapping("/getUserDetail")
    public Result getUserDetail(HttpServletRequest request,String userId){
        if(userId==null) {
            userId = JwtUtil.checkTocken(request);
        }
        if (userId!=null){
           return Result.success(userDetailService.getUserDetailByUserId(userId));
        }else {
           return Result.error("token过期");
        }
    }
    @GetMapping("/serchUser")
    public Result serchUser(String key){
        QueryWrapper<UserDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",key)
                .or()
                .like("tel",key);
        List<UserDetail> userDetailList=userDetailService.list(queryWrapper);
        return Result.isSuccess(userDetailList,"查询");

    }
}

