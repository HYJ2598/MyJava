package yjs.tyust.edu.cn.jiewei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import yjs.tyust.edu.cn.jiewei.entity.Test;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gaoqiang
 * @since 2019-05-30
 */

public interface TestService extends IService<Test> {

    /*Integer testCountSelectById(@Param("couId")Integer couId);
    List<Test> testSelectallByid(@Param("couId")Integer couId);*/
    /*张乐*/
    /*上传试题*/
    boolean uplodeText(@Param("sort") int sort, @Param("ques") String ques, @Param("answer") String answer, @Param("cou_id") int cou_id, @Param("ana") String ana, @Param("optiogroup") String optiongroup);

    /*查询全部
    List<Test> text();*/

    /*模糊搜索*/
    /*List<Test> selectByQues(@Param("couName") String couName, @Param("ques") String ques);*/

    /*List<Test> text();*/

    //List<Test> selectByQues(@Param("couName")String couName, @Param("ques")String ques);
    /*杨芬玉*/
    //按课程Id查询
    List<Test> selectByQues1(@Param("couId")String couId);
}
