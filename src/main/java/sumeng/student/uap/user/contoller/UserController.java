package sumeng.student.uap.user.contoller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import okhttp3.Request;
import org.apache.http.HttpRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.Body;
import sumeng.student.uap.common.entity.User;
import sumeng.student.uap.common.jwt.JwtToken;
import sumeng.student.uap.common.utils.*;
import sumeng.student.uap.user.entity.RegisterUser;
import sumeng.student.uap.user.service.MyUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MyUserService userService;
    @PostMapping(value = "/addUser")
    public Result addUser(@RequestBody RegisterUser user) throws Exception {
        if(userService.isHaveUser(user.getAccount())){
            return Result.error("用户名已经存在，请重新输入！");
        }else if(userService.isUserTel(user.getTel())){
            return Result.error("该号码已经使用，请重新输入！");
        }
        else {
            if(userService.addUser(user))return Result.success("注册成功");
            else return Result.error("注册失败");
        }
    }
    @PostMapping("/login")
    public Result login(HttpServletRequest request, String account, String password) {
        Result result = null;
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();

        // 在认证提交前准备 token（令牌）
        JwtToken jwtToken=null;
        String token=request.getHeader("token");
        if (account != null && !account.equals("")){
            jwtToken = new JwtToken(account,password,UserType.MINE);
        }else {
            jwtToken = new JwtToken(token,UserType.MINE);
        }
        // 执行认证登陆
        try {
            subject.login(jwtToken);
        } catch (UnknownAccountException uae) {
            result = Result.error("未知账户");
        } catch (IncorrectCredentialsException ice) {
            result = Result.error("密码不正确");
        } catch (LockedAccountException lae) {
            result = Result.error("账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            result = Result.error("用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            result = Result.error("用户名或密码不正确");
        }
        if (subject.isAuthenticated()) {
            result = Result.success(jwtToken.getToken(), "登录成功");
        } else {
            jwtToken.clear();
        }
        return result;
    }
}

