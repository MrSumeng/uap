package sumeng.student.uap.user.service;

import sumeng.student.uap.common.entity.Apply;
import sumeng.student.uap.common.entity.NewApply;
import sumeng.student.uap.common.service.ApplyService;

import java.util.List;


/**
 * @author 12068
 * @date 2020/3/17  12:10
 */
public interface MyApplyService extends ApplyService {
    public Boolean dealApply(String userId, String applyId, String applyStatus);
    public Boolean addClassmate (Apply apply);
    public Boolean addGroup(Apply apply);
    public List<NewApply> getMyApply(String userId);
}
