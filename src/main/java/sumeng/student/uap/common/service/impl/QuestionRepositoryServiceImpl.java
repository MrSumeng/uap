package sumeng.student.uap.common.service.impl;

import sumeng.student.uap.common.entity.QuestionRepository;
import sumeng.student.uap.common.mapper.QuestionRepositoryMapper;
import sumeng.student.uap.common.service.QuestionRepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 题库 服务实现类
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Service
public class QuestionRepositoryServiceImpl extends ServiceImpl<QuestionRepositoryMapper, QuestionRepository> implements QuestionRepositoryService {

}
