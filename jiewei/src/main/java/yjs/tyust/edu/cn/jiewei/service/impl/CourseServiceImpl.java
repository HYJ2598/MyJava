package yjs.tyust.edu.cn.jiewei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yjs.tyust.edu.cn.jiewei.entity.Course;
import yjs.tyust.edu.cn.jiewei.mapper.CourseMapper;
import yjs.tyust.edu.cn.jiewei.service.CourseService;

import java.util.List;

/**
 * <p>
 * 课程表 服务实现类
 * </p>
 *
 * @author 徐超
 * @since 2019-05-28
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> selectInfo(Integer id) {
        List<Course> course = courseMapper.selectInfo(id);
        return course;
    }
}
