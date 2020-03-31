package yjs.tyust.edu.cn.jiewei.service;

import yjs.tyust.edu.cn.jiewei.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程表 服务类
 * </p>
 *
 * @author 徐超
 * @since 2019-05-28
 */
public interface CourseService extends IService<Course> {


    List<Course> selectInfo(Integer id);
}
