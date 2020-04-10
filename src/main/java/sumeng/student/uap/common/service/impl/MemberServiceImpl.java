package sumeng.student.uap.common.service.impl;

import sumeng.student.uap.common.entity.Member;
import sumeng.student.uap.common.mapper.MemberMapper;
import sumeng.student.uap.common.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组员 服务实现类
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

}
