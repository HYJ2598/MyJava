package yjs.tyust.edu.cn.jiewei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import yjs.tyust.edu.cn.jiewei.entity.Test;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gaoqiang
 * @since 2019-05-30
 */
@Component
public interface TestMapper extends BaseMapper<Test> {

   /*List<Test> testSelectallByid();
   Integer testCountSelectById();*/
   /*高强*/
   /*根据cou_id查询试题和所属课程的试题总数*/
   Integer testCountSelectById(@Param("couId") int cou_id);
   List<Test> testSelectallByid(@Param("couId") int cou_id);
   /*张乐*/
   /*//上传试题*/
   boolean uplodeText(@Param("sort") int sort, @Param("ques") String ques, @Param("answer") String answer, @Param("cou_id") int cou_id, @Param("ana") String ana, @Param("optiongroup") String optiongroup);

   /*查询全部 List<Test> text();*/

   /*模糊搜索*/
  /* List<Test> selectByQues(@Param("couName") String couName, @Param("ques") String ques);*/

   /*List<Test> text();*/

   //    List<Test> selectByQues(@Param("couName")String couName, @Param("ques")String ques);
//按课程Id查询
   /*杨芬玉*/
   List<Test> selectByQuesl(@Param("couId")String couId);
}
