package sumeng.student.uap.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sumeng.student.uap.common.config.SecretKey;
import sumeng.student.uap.common.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 12068
 * @date 2020/2/22  13:25
 */
public class JwtUtil {
    public static long ttMillis = 2*60*60*1000; // 设置过期时间为2小时
    /**
     * 用户登录成功后删除JWT
     * @param user
     * @return
     */
    public static String createJWT(User user){
        // 指定签名时使用的算法，header，jjwt已经给我们封装好了
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String,Object> map = new HashMap<>();
        map.put("account",user.getAccount());
        map.put("userId",user.getUserId());
        // 生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，
        // 切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        String key = SecretKey.JWTKey;

        // 生成签发人
        String subject = user.getAccount();

        //下面就是在为payload添加各种标准声明和私有声明了
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(map)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击
                .setId(UUID.randomUUID().toString())
                //iat: jwt的签发时间
                .setIssuedAt(now)
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm,key);
        if (ttMillis>=0){
            long expMilis = nowMillis + ttMillis;
            Date exp = new Date(expMilis);
            //设置过期时间
            builder.setExpiration(exp);
        }
        return builder.compact();
    }
    /**
     * 校验token
     * 在这里可以使用官方的校验，我这里校验的是token中携带的密码于数据库一致的话就校验通过
     *
     * @author      sumeng
     * @param token
     * @param user
     * @return      boolean
     * @exception
     * @date        2020/2/22  13:25
     */
    public static Boolean isMineVerify(String token, User user){
        try {

            // 得到DefaultJwtParser
            Claims claims = Jwts.parser()
                    // 设置签名秘钥
                    .setSigningKey(SecretKey.JWTKey)
                    // 设置需要解析的jwt
                    .parseClaimsJws(token).getBody();
            if (claims.get("account").equals(user.getAccount())){
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return null;
    }

    /**
     * Token的解密
     * @author      sumeng
     * @param token 加密后的token
     * @param secret 签名秘钥，和生成签名的秘钥一模一样
     * @return      io.jsonwebtoken.Claims
     * @exception
     * @date        2020/2/22  13:25
     */
    public static Claims parseJWT(String token, String  secret){
        if(token!=null){
            //得到DefaultJwtParser
            Claims claims = Jwts.parser()
                    //设置签名的秘钥
                    .setSigningKey(secret)
                    //设置需要解析的jwt
                    .parseClaimsJws(token).getBody();
            return claims;
        }
        return null;
    }

    public static String checkTocken(HttpServletRequest request){
        String token=request.getHeader("token");
        if (token != null && !token.equals("")){
            try {
                Claims claims = parseJWT(token,SecretKey.JWTKey);
                String userId = String.valueOf(claims.get("userId"));
                return userId;
            }catch (Exception e){
                System.out.println("token过期");
            }
        }
        return null;
    }
}
