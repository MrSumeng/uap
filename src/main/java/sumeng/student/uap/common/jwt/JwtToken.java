package sumeng.student.uap.common.jwt;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import sumeng.student.uap.common.utils.UserType;

/**
 * AuthenticationToken: shiro中负责把username,password生成用于验证的token的封装类
 *  我们需要自定义一个对象用来包装token。
 */
public class JwtToken extends UsernamePasswordToken implements AuthenticationToken {
    private String token;

    private UserType userType;

    public JwtToken(String token, String userType) {
        this.token = token;
        this.userType = UserType.valueOf(userType);
    }

    public JwtToken(String token, UserType userType) {
        this.token = token;
        this.userType = userType;
    }

    public JwtToken(final String username, final String password,
                    final UserType userType) {
        super(username, password);
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
