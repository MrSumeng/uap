/**
 * Copyright (C), 2019-2020, sumeng
 * FileName: MyApplyServiceImpl
 * Author: sumeng
 * Date: 2020/3/17 12:10
 */
package sumeng.student.uap.user.service.impl;/**
 * @ClassName: MyApplyServiceImpl
 * @Description: 申请接口实现
 * @Author: sumeng
 * @Version: 1.0.0
 * @Date: 2020/3/17 12:10
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sumeng.student.uap.attendance.service.MyClassmateService;
import sumeng.student.uap.attendance.service.MyMemberService;
import sumeng.student.uap.common.entity.Apply;
import sumeng.student.uap.common.entity.Classmate;
import sumeng.student.uap.common.entity.Member;
import sumeng.student.uap.common.entity.NewApply;
import sumeng.student.uap.common.mapper.ApplyMapper;
import sumeng.student.uap.common.utils.MemberType;
import sumeng.student.uap.user.service.MyApplyService;

import java.util.List;

/**
 *
 *
 * @date 2020/3/17  12:10
 * @author 12068
 */
@Service
public class MyApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements MyApplyService {
    @Autowired
    private MyMemberService myMemberService;
    @Autowired
    private MyClassmateService myClassmateService;
    @Transactional
    @Override
    public Boolean dealApply(String userId, String applyId, String applyStatus) {
        Apply apply = getById(applyId);
        apply.setApplyStatus(applyStatus);
        Boolean b=false;
        if (apply!=null){
            switch (apply.getApplyType()){
                case 1:
                   b=addClassmate(apply);
                    break;
                case 2:
                   b=addGroup(apply);
                    break;
                default:break;

            }
        }
        b = updateById(apply);
        return b;
    }



    //只处理添加好友的申请
    @Override
    public Boolean addClassmate(Apply apply) {
        if (apply.getApplyStatus().equals("1")){
            Classmate classmate = new Classmate();
            classmate.setUserId(apply.getApplyUserId());
            classmate.setClassmateUserId(apply.getVerifyUserId());
            Boolean b = myClassmateService.addClassmate(classmate);
            classmate.setClassmateId(null);
            classmate.setUserId(apply.getVerifyUserId());
            classmate.setClassmateUserId(apply.getApplyUserId());
            b = myClassmateService.addClassmate(classmate);
            return b;
        }
        return true;
    }
    //只处理加入群组的申请
    @Override
    public Boolean addGroup(Apply apply) {
        if (apply.getApplyStatus().equals("1"))
        {
            //加入群组
            Member member = new Member();
            member.setRoleId(MemberType.Member);
            member.setGroupId(apply.getApplyMsg());
            member.setUserId(apply.getApplyUserId());
            Boolean a=myMemberService.addMember(member);
            if (a){
                return true;
            }else {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<NewApply> getMyApply(String userId) {
        return baseMapper.getMyApply(userId);
    }


}
