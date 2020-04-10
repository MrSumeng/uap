package sumeng.student.uap.user.service;

import sumeng.student.uap.common.entity.User;
import sumeng.student.uap.common.service.UserService;
import sumeng.student.uap.user.entity.RegisterUser;

/**
 * @author 12068
 * @date 2020/2/22  12:07
 */
public interface MyUserService extends UserService {
    public Boolean isHaveUser(String account);
    public Boolean isUserTel(String tel);
    public Boolean isUserEmail(String email);
    public Boolean addUser(RegisterUser user);
    public User getUser(String accountOrTel);
    public User getUserByTel(String tel);
    public String getUserNameById(String userId);
}
