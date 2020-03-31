package yjs.tyust.edu.cn.jiewei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import yjs.tyust.edu.cn.jiewei.entity.Course;

import java.util.List;

/**
 * <p>
 * 课程表 Mapper 接口
 * </p>
 *
 * @author 徐超
 * @since 2019-05-28
 */
public interface CourseMapper extends BaseMapper<Course> {

    Course getByCourseName(String coursename);

    List<Course> selectInfo(Integer id);
}
