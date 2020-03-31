package yjs.tyust.edu.cn.jiewei.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yjs.tyust.edu.cn.jiewei.entity.SelCou;
import yjs.tyust.edu.cn.jiewei.entity.Student;
import yjs.tyust.edu.cn.jiewei.entity.Test;
import yjs.tyust.edu.cn.jiewei.mapper.SelCouMapper;
import yjs.tyust.edu.cn.jiewei.mapper.TestMapper;
import yjs.tyust.edu.cn.jiewei.service.SelCouService;
import yjs.tyust.edu.cn.jiewei.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gaoqiang
 * @since 2019-05-30
 */

@RestController

@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestService testService;
    @Autowired
    private SelCouService selCouService;
    @Autowired
    private SelCouMapper selCouMapper;
    /*学员*/
    /*根据课程ID查询所属试题*/
    @RequestMapping("/testListById/{id}")
    private List<Test> testListById(@PathVariable int id){
        /*高强*/
        List<Test> testSelectallByid = testMapper.testSelectallByid(id);
        return testSelectallByid;
    }


    /*根据课程ID查询所属试题的总数*/
    @RequestMapping("/testCountSelectById/{id}")
    private Integer testCountSelectById(@PathVariable int id){
        /*高强*/
        Integer testCount = testMapper.testCountSelectById(id);

        return testCount;
    }


    /*与数据库的答案进行对比*/
    @RequestMapping("/compareAnswer")
    public boolean compareAnswer(HttpServletRequest request, HttpSession session,@RequestParam(name = "id") Integer id){
        /*高强*/
        /*从前端页面获取到学员的答案frm和课程ID*/
      String dd1=request.getParameter("frm");
      int dd2=Integer.parseInt(request.getParameter("id"));

      /**
      * 更改该学生的考试状态（郝彦杰）
      * */
        Student student = (Student) session.getAttribute("student");
        Integer stuid =student.getStuId();
        /*根据课程ID查询出数据库中的正确答案*/
      List<Test> lis =testListById(dd2);

       StringBuilder allAnser = new StringBuilder("");
        for(int i=0;i<lis.size();i++){
            Test test=lis.get(i);
            allAnser.append(test.getAnswer());
            allAnser.append("；");
        }

        /*根据课程ID将学员的答案拼接成字符串*/
        StringBuilder strByStudent=new StringBuilder("");
        String[] len=dd1.split("&");
        for (int k=0;k<len.length;k++){
            strByStudent.append(len[k].split("=")[1]+"；");
        }

        /*学员的答案与正确答案作比较（字符串比较）*/
        if(strByStudent.toString().equals(allAnser.toString())){
            UpdateWrapper<SelCou> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("stu_id",stuid).eq("cou_id",dd2).eq("del",0);
            SelCou selCou = new SelCou();
            selCou.setStuStatu(2);
            selCouService.update(selCou,updateWrapper);
            return true;
       }else{
           return false;
       }
    }



    /*后台管理员*/
    /*上传试题*/
    @RequestMapping("/add")
    public boolean uplodeText(Integer sort,String ques,String[] answer, Integer cou_id, String ana,String[] optio){

        String answers = String.join("；", answer);

        String optiongroup = String.join("；", optio);
        return testService.uplodeText(sort,ques,answers,cou_id,ana,optiongroup);
    }

    /*模糊搜索*/
    /*@RequestMapping("/selectByQues")
    public List<Test> selectByQues(@RequestParam("couName") String couName, @RequestParam("ques") String ques){
        System.out.println(couName+","+ques);
        return testService.selectByQues(couName,ques);

    }*/

    /*删除一条试题*/
    @RequestMapping("/remove/{id}")
    public boolean removeById(@PathVariable Long id){
        return testService.removeById(id);
    }

     /*查询一条试题*/
    @RequestMapping("/getText/{id}")
    public Test getUser(@PathVariable Long id){
        Test test = testService.getById(id);
        return test;
    }

    /*修改试题*/
    @RequestMapping("/update")
    public boolean update(Test test){
        System.out.println("--------"+test);
        return testService.updateById(test);
    }

    //按课程Id查询。
    @RequestMapping("/selectByQuesl")
    public List<Test> selectByQues1(@RequestParam("couId") String couId){
        System.out.println("========");
        List<Test> list= testService.selectByQues1(couId);
        System.out.println("gaoqiangggg"+list);
        return list;
    }
}