package sumeng.student.uap.common.jwt;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     *  应用的HTTP方法列表配置基本身份验证筛选器。
     *  获取 request 请求 拒绝拦截登录请求
     *  执行登录认证方法
     *
     * @author ZCL
     * @date 2019/12/17 20:32
     * @param request
     * @param response
     * @param mappedValue
     * @return boolean 
     **/        
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        // 配置拒绝拦截请求的地址
        if (requestURI.equals("/wechat/callBack")){
            return true;
        }else {
            try {
                executeLogin(request,response);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * Authorization携带的参数为token
     *  JwtToken实现了AuthenticationToken接口封装了token参数
     *  通过getSubject方法获取 subject对象
     *  login()发送身份验证
     *
     *  为什么需要在Filter中调用login,不能在controller中调用login?
     *  由于Shiro默认的验证方式是基于session的，在基于token验证的方式中，不能依赖session做为登录的判断依据．
     * 
     * @author ZCL
     * @date 2019/12/17 20:39
     * @param request
     * @param response
     * @return boolean
     **/
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String token = httpServletRequest.getHeader("token");
            String userType = httpServletRequest.getHeader("userType");
            JwtToken jwtToken = new JwtToken(token,userType);
            //提交给realm进行登入，如果错误他会抛出异常并被捕获
            Subject subject = getSubject(request,response);
            subject.login(jwtToken);
            // 如果没有抛出异常代表登入成功，返回true
            return true;
        } catch (Exception e) {
            /**
             *  原生的shiro验证失败会进入全局异常 但是 和JWT结合以后却不进入了  之前一直想不通
             *  原因是 JWT直接在过滤器里验证  验证成功与否 都是直接返回到过滤器中 成功在进入controller
             *  失败直接返回进入springboot自定义异常处理页面
             */
            JSONObject responseJSONObject = new JSONObject();
            responseJSONObject.put("result","401");
            responseJSONObject.put("resultCode","token无效，请重新获取。");
            responseJSONObject.put("resultData","null");
            responseJSONObject.put("resultTime", new Date());
            PrintWriter out = null;
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            out = httpServletResponse.getWriter();
            out.append(responseJSONObject.toString());
            out.close();
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 对跨域提供支持
     *
     * @author ZCL
     * @date 2019/12/17 20:48
     * @param request
     * @param response
     * @return boolean
     **/
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
