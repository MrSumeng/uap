package sumeng.student.uap.common.shrio;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sumeng.student.uap.common.utils.Result;

@ControllerAdvice
public class NoPermissionException {
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public Result handleShiroException(Exception ex) {
        Result result = Result.custom(201, 0, null, "无权限访问");
        return result;
    }

    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public Result AuthorizationException(Exception ex) {
        Result result = Result.custom(201, 0, null, "权限验证失败");
        return result;
    }
}
