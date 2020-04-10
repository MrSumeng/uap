package sumeng.student.uap.common.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sumeng.student.uap.common.entity.AttendanceGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 考勤组 Mapper 接口
 * </p>
 *
 * @author sumeng
 * @since 2020-02-22
 */
public interface AttendanceGroupMapper extends BaseMapper<AttendanceGroup> {
    @Select("SELECT * FROM attendance_group WHERE attendance_group.group_id in (SELECT group_id FROM member WHERE user_id = #{user_id})")
    List<AttendanceGroup> getMyGroup(@Param("user_id") String userId);
}
