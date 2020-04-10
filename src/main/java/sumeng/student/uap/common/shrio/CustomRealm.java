package sumeng.student.uap.common.shrio;

import com.auth0.jwt.JWTCreator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import sumeng.student.uap.common.config.SecretKey;
import sumeng.student.uap.common.entity.User;
import sumeng.student.uap.common.jwt.JWTCredentialsMatcher;
import sumeng.student.uap.common.jwt.JwtToken;
import sumeng.student.uap.common.utils.JwtUtil;
import sumeng.student.uap.user.service.MyUserService;


/**
 * 描述：权限验证规则
 * @author wanjy
 * @create 2019-12-27-13:57
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired private MyUserService myUserService;

    public CustomRealm() {
        this.setCredentialsMatcher(new JWTCredentialsMatcher());
    }

    /**
     * 必须重写此方法，不然Shiro会报错
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
    /**
     * 资源授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * private UserService userService;
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken token = (JwtToken)authenticationToken;
        /**
         * AuthenticationToken
         *  JwtToken重写了AuthenticationToken接口 并创建了一个接口token的变量
         *   因为在filter我们将token存入了JwtToken的token变量中
         *   所以这里直接getToken()就可以获取前端传递的token值
         */
        String Jwttoken = token.getToken();
        System.out.println("-------身份认证--------");

        String user_account = token.getUsername();
        if (user_account != null){
            String password = String.valueOf(token.getPassword());
            User user= null;
            /**
             * 从数据库获取用户数据
             */
            try {
                user = myUserService.getUser(user_account);
            } catch (Exception e) {
                e.printStackTrace();
            }
            /**
             * 判断用户数据是否存在
             */
            if (user==null) {
                System.out.println("用户名不正确或用户不存在!");
                return null;
            }
            SimpleHash authpassword = new SimpleHash("MD5",password,ByteSource.Util.bytes(user.getSalt()),1);
            if(user.getPassword().equals(String.valueOf(authpassword))){
                token.setToken(JwtUtil.createJWT(user));
                return new SimpleAuthenticationInfo(user,token,this.getName());
            } else {
                throw new AuthenticationException("用户名或密码错误");
            }
        }else {
            // 使用token时
            Claims claims = JwtUtil.parseJWT(Jwttoken, SecretKey.JWTKey);
            String acconut = claims.getSubject();
            QueryWrapper<User> queryWrapper = new QueryWrapper();
            queryWrapper.eq("account",acconut);
            User user = myUserService.getOne(queryWrapper);
            return new SimpleAuthenticationInfo(user,token,this.getName());
        }

    }
}
