package sumeng.student.uap.common.jwt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import sumeng.student.uap.common.entity.User;
import sumeng.student.uap.common.utils.JwtUtil;
import sumeng.student.uap.common.utils.UserType;

public class JWTCredentialsMatcher implements CredentialsMatcher {
    /**
     * Macher 认证，直接调用工具包中的verify 方法即可
     * @author      ZCL
     * @param token
     * @param info
     * @return      boolean
     * @exception
     * @date        2019/12/17 18:42
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Boolean verify;
        UserType userType = ((JwtToken)token).getUserType();
        if (UserType.MINE == userType){
            String mtoken = ((JwtToken)token).getToken();
            User user = (User) info.getPrincipals().getPrimaryPrincipal();
            verify = JwtUtil.isMineVerify(mtoken,user);
            return verify;
        }
        return false;
    }
}
