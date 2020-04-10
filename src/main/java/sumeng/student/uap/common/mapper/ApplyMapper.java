package sumeng.student.uap.common.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sumeng.student.uap.common.entity.Apply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import sumeng.student.uap.common.entity.NewApply;

import java.util.List;

/**
 * <p>
 * 申请表 Mapper 接口
 * </p>
 *
 * @author sumeng
 * @since 2020-03-17
 */
public interface ApplyMapper extends BaseMapper<Apply> {
    @Select("SELECT "+
            " user.account,apply.apply_msg,user_detail.username,user_detail.face_url,apply.apply_id,apply.apply_type,apply.apply_user_id "+
            " FROM"+
            " user,user_detail,apply "+
            " WHERE"+
            " user.user_id=user_detail.user_id "+
            " and "+
            " apply.apply_user_id = user.user_id "+
            " and "+
            " apply.apply_status=2 "+
            " and "+
            " apply.verify_user_id =#{user_id}")
    List<NewApply> getMyApply(@Param("user_id") String userId);
}
