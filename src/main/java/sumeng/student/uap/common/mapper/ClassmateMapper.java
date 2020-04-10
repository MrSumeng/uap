package sumeng.student.uap.common.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sumeng.student.uap.common.entity.Classmate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import sumeng.student.uap.common.entity.ClassmateMessage;

import java.util.List;

/**
 * <p>
 * 同学 Mapper 接口
 * </p>
 *
 * @author sumeng
 * @since 2020-03-18
 */
public interface ClassmateMapper extends BaseMapper<Classmate> {
    @Select(" select classmate.*,user_detail.person_info,user_detail.face_url,user_detail.username "+
            " from "+
            " classmate,user_detail "+
            " where "+
            " classmate.classmate_user_id=user_detail.user_id "+
            " and "+
            " classmate.status = 1 "+
            " and "+
            " classmate.user_id= #{user_id}"
    )
    List<ClassmateMessage> getMyClassmate(@Param("user_id") String userId);
}
