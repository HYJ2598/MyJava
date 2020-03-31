package yjs.tyust.edu.cn.jiewei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yjs.tyust.edu.cn.jiewei.entity.Student;
import yjs.tyust.edu.cn.jiewei.mapper.StudentMapper;
import yjs.tyust.edu.cn.jiewei.service.StudentService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author woyuno
 * @since 2019-05-26
 */
@Service
   public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
   @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student getById(Long id){
       return studentMapper.getById(id);
    }


    public Student login(String idcard) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();


        queryWrapper.eq("idcard",idcard);

        Student student = studentMapper.selectOne(queryWrapper);

        return student;
    }


}
