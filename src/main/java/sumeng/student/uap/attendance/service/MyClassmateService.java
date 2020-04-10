package sumeng.student.uap.attendance.service;

import sumeng.student.uap.common.entity.Classmate;
import sumeng.student.uap.common.entity.ClassmateMessage;
import sumeng.student.uap.common.service.ClassmateService;

import java.util.List;

/**
 * @author 12068
 * @date 2020/3/18  11:05
 */
public interface MyClassmateService extends ClassmateService {

    public Boolean addClassmate(Classmate classmate);
    public List<ClassmateMessage> getMyClassmate(String userId);
}
