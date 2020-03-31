package yjs.tyust.edu.cn.jiewei.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yjs.tyust.edu.cn.jiewei.Result.R;
import yjs.tyust.edu.cn.jiewei.entity.AdminMessage;
import yjs.tyust.edu.cn.jiewei.entity.StuMessage;
import yjs.tyust.edu.cn.jiewei.entity.Student;
import yjs.tyust.edu.cn.jiewei.service.AdminMessageService;
import yjs.tyust.edu.cn.jiewei.service.StuMessageService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 166
 * @since 2019-06-17
 */
@RestController

@RequestMapping("/stuMessage")
public class StuMessageController {


    @Autowired
    private StuMessageService stuMessageService;


    @Autowired
    private AdminMessageService adminMessageService;

    /*提交问题*/
   /* @RequestMapping("/save")
    public R save(StuMessage stuMessage){
        System.out.println(stuMessage);
        stuMessageService.save(stuMessage);

        AdminMessage adminMessage = new AdminMessage();
        adminMessage.setContent(stuMessage.getContent());
        adminMessage.setMessId(stuMessage.getMessId());
        adminMessage.setStuId(stuMessage.getStuId());
        adminMessageService.save(adminMessage);
        return R.ok();
    }*/

    /*通过学生id 查找学生所有问题*/
   /* @RequestMapping("/getByStuId/{stuId}")
    public R getByStuId(@PathVariable Long stuId){
        QueryWrapper<StuMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id",stuId);
        List<StuMessage> stuMessageList = stuMessageService.list(queryWrapper);
        System.out.println(stuMessageList);
        return R.ok().put("stuMessageList",stuMessageList);
    }*/

    /*提交问题*/
    @RequestMapping("/save")
    public R save(StuMessage stuMessage,HttpSession session){

        //获取session中的值
        Student student = (Student)session.getAttribute("student");
        //设置学生id
        stuMessage.setStuId(student.getStuId());

        stuMessageService.save(stuMessage.setContent(stuMessage.getContent().trim()));
        System.out.println(stuMessage);

        //将提问内容存入adminMessage中
        AdminMessage adminMessage = new AdminMessage();
        adminMessage.setContent(stuMessage.getContent());
        adminMessage.setMessId(stuMessage.getMessId());
        adminMessage.setStuId(stuMessage.getStuId());
        adminMessageService.save(adminMessage);
        return R.ok();
    }

    /*通过学生id 查找学生所有问题*/
    @RequestMapping("/getByStuId")
    public R getByStuId(HttpSession session){

        //获取session中的对象
        Student student = (Student)session.getAttribute("student");
        System.out.println(student);

        //获取学生id
        int stuId = student.getStuId();

        QueryWrapper<StuMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id",stuId);
        List<StuMessage> stuMessageList = stuMessageService.list(queryWrapper);
        System.out.println(stuMessageList);
        return R.ok().put("stuMessageList",stuMessageList);
    }


    @RequestMapping("/all")
    public R all(){
        List<StuMessage> stuMessageList = stuMessageService.list();
        System.out.println(stuMessageList);
        return R.ok().put("stuMessageList",stuMessageList);
    }

    /*
    * 根据mess_id删除问题 删除的数据表为stu_mess
    *
    * 删除stu_mssage中mess_id学生的问题，即可在页面不显示学生问题和老师回复
    * 但admin_message表中数据仍然存在
    *
    *
    * */
    @RequestMapping("/deleteByMessid/{messId}")
    public R deleteByMessid(@PathVariable Long messId){

        boolean stu = stuMessageService.removeById(messId);
        //boolean ad = adminMessageService.removeById(messId);

        if(stu){

             return R.ok();
        }else {

            return R.error();
        }

    }
}
