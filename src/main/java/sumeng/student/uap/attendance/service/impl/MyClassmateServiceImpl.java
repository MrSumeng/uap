package sumeng.student.uap.attendance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sumeng.student.uap.attendance.service.MyClassmateService;
import sumeng.student.uap.common.entity.Classmate;
import sumeng.student.uap.common.entity.ClassmateMessage;
import sumeng.student.uap.common.mapper.ClassmateMapper;

import java.util.Date;
import java.util.List;

/**
 * @author 12068
 * @date 2020/3/18  11:05
 */
@Service
public class MyClassmateServiceImpl extends ServiceImpl<ClassmateMapper, Classmate> implements MyClassmateService {
    @Override
    public Boolean addClassmate(Classmate classmate) {
        classmate.setCreateTime(new Date());
        classmate.setStatus("1");
        Boolean b=save(classmate);
        return b;
    }

    @Override
    public List<ClassmateMessage> getMyClassmate(String userId) {
        return baseMapper.getMyClassmate(userId);
    }
}
