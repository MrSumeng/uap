package sumeng.student.uap.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import sumeng.student.uap.common.entity.User;
import sumeng.student.uap.common.entity.UserDetail;
import sumeng.student.uap.common.mapper.UserMapper;
import sumeng.student.uap.common.service.UserDetailService;
import sumeng.student.uap.common.utils.GenstrUtil;
import sumeng.student.uap.user.entity.RegisterUser;
import sumeng.student.uap.user.service.MyUserDetailService;
import sumeng.student.uap.user.service.MyUserService;

import java.util.Date;


/**
 * @author 12068
 * @date 2020/2/22  12:08
 */
@Service
public class MyUserServiceImpl extends ServiceImpl<UserMapper, User> implements MyUserService {
    @Autowired private MyUserDetailService userDetailService;
    @Override
    public Boolean isHaveUser(String account) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        User one=getOne(queryWrapper);
        if (one != null) return true;
        else return false;
    }

    @Override
    public Boolean isUserTel(String tel) {
        QueryWrapper<UserDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel",tel);
        UserDetail one = userDetailService.getOne(queryWrapper);
        if (one != null) return true;
        else return false;
    }

    @Override
    public Boolean isUserEmail(String email) {
        QueryWrapper<UserDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        UserDetail one = userDetailService.getOne(queryWrapper);
        if (one != null) return true;
        else return false;
    }

    @Transactional
    @Override
    public Boolean addUser(RegisterUser user) {
        String salt = GenstrUtil.generateString(4);
        user.setSalt(salt);
        SimpleHash simpleHash = new SimpleHash("MD5",user.getPassword(), ByteSource.Util.bytes(user.getSalt()),1);
        user.setPassword(String.valueOf(simpleHash));
        user.setCreateTime(new Date());
        boolean n = save(user);
        if (n) {
            UserDetail detail = new UserDetail();
            detail.setUserId(user.getUserId());
            detail.setUsername(user.getUsername());
            detail.setTel(user.getTel());
            boolean save = userDetailService.save(detail);
            if(save){
                return true;
            }else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }

        }
        else return false;
    }

    @Override
    public User getUser(String accountOrTel) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",accountOrTel);
        User user = getOne(queryWrapper);
        if(user!=null) return user;
        else return getUserByTel(accountOrTel);
    }

    @Override
    public User getUserByTel(String tel) {
        QueryWrapper<UserDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel",tel);
        UserDetail one = userDetailService.getOne(queryWrapper);
        if(one!=null){
            User user = getById(one.getUserId());
            return user;
        }
        return null;
    }

    @Override
    public String getUserNameById(String userId) {
        QueryWrapper<UserDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        UserDetail one = userDetailService.getOne(queryWrapper);
        if(one!=null)
            one.getUsername();
        return null;
    }
}
