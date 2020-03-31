package yjs.tyust.edu.cn.jiewei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yjs.tyust.edu.cn.jiewei.entity.Test;
import yjs.tyust.edu.cn.jiewei.mapper.TestMapper;
import yjs.tyust.edu.cn.jiewei.service.TestService;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoqiang
 * @since 2019-05-30
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Autowired
    private TestMapper testMapper;

   /* public Integer testCountSelectById(@Param("couId")Long couId){
        return testMapper.testCountSelectById(couId);
    }
    public List<Test> testSelectallByid(@Param("couId")Long couId){
        return testMapper.testSelectallByid(couId);
    }*/

    /*张乐*/
    /*上传试题*/
    @Override
    public boolean uplodeText(@Param("sort")int sort,@Param("ques")String ques, @Param("answer")String answer,@Param("cou_id")int cou_id,@Param("ana")String ana,@Param("optiongroup")String optiongroup) {

        return testMapper.uplodeText(sort,ques,answer,cou_id,ana,optiongroup);
    }

   /* 查询全部
     @Override
    public List<Test> text(){
        return testMapper.text();
    }*/

    /*模糊搜索*/
    /*@Override
    public List<Test> selectByQues(@Param("couName")String couName, @Param("ques")String ques){
        return testMapper.selectByQues(couName,ques);
    }*/
    /*杨芬玉*/
    //按课程Id查询
    @Override
    public  List<Test> selectByQues1(@Param("couId")String couId){
        return testMapper.selectByQuesl(couId);
    }
}
