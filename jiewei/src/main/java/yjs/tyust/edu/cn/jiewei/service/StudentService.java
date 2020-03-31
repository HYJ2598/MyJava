package yjs.tyust.edu.cn.jiewei.service;


import com.baomidou.mybatisplus.extension.service.IService;
import yjs.tyust.edu.cn.jiewei.entity.Student;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author woyuno
 * @since 2019-05-26
 */

public interface StudentService extends IService<Student> {
    Student getById(Long id);
    Student login(String idcard);
}
