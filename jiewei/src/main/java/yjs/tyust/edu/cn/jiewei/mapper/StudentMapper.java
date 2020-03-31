package yjs.tyust.edu.cn.jiewei.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import yjs.tyust.edu.cn.jiewei.entity.Student;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author woyuno
 * @since 2019-05-26
 */
public interface StudentMapper extends BaseMapper<Student> {
    Student getById(Long id);
    Student getByIdcard(String idcard);

}
