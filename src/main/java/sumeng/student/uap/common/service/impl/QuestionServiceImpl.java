package sumeng.student.uap.common.service.impl;

import sumeng.student.uap.common.entity.Question;
import sumeng.student.uap.common.mapper.QuestionMapper;
import sumeng.student.uap.common.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 题目 服务实现类
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

}
