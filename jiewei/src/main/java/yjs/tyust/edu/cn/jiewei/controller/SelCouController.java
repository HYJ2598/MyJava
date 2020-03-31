package yjs.tyust.edu.cn.jiewei.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yjs.tyust.edu.cn.jiewei.entity.SelCou;
import yjs.tyust.edu.cn.jiewei.entity.Student;
import yjs.tyust.edu.cn.jiewei.service.CourseService;
import yjs.tyust.edu.cn.jiewei.service.SelCouService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 选课表（关系表）
包含：编号，学生编号，课程编号，最后观看到的视频位置 前端控制器
 * </p>
 *
 * @author HYJ 徐超
 * @since 2019-06-04
 */
@RestController

@RequestMapping("/selcou")
public class SelCouController {

    @Autowired
    SelCouService selCouService;
    @Autowired
    CourseService courseService;

    @RequestMapping("/list")
    public List<SelCou> list() {
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del",0);
        return selCouService.list(queryWrapper);
    }

    /*
     *添加一门选课记录
     **/
    @RequestMapping("/add/{id}")
    public boolean save(@PathVariable Integer id, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        int stuId = student.getStuId();
        SelCou selCou = new SelCou();
        selCou.setStuId(stuId);
        selCou.setCouId(id);
        selCou.setDel(false);
        return selCouService.save(selCou);
    }

    /*
     * 删除一门选课记录
     * */
    @RequestMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        int stuId = student.getStuId();
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cou_id", id);
        queryWrapper.eq("stu_id", stuId);
        SelCou selCou = new SelCou();
        selCou.setDel(true);
        selCouService.update(selCou,queryWrapper);
        return true;

    }

    /*
     * 更新一门选课记录中视频位置
     * */
    //BY 徐超
    @RequestMapping("/updata")
    public boolean updata(Integer id1,Integer id2,Integer id3, String t,HttpSession session){
        //id1学生id，id2课程id，id3视频，t视频位置
        //将传入的信息放到一个selCou实体内，用于更新
        SelCou selCou= new SelCou();
        selCou.setVidId(id3+1);
        selCou.setPosTime(t);
        UpdateWrapper<SelCou> selCouUpdateWrapper=new UpdateWrapper<>();
        Student student = (Student) session.getAttribute("student");
        selCouUpdateWrapper.eq("stu_id",student.getStuId()).eq("cou_id",id2).eq("del",0);

        boolean updata=selCouService.update(selCou,selCouUpdateWrapper);
        return updata;
    }

    /*
     * 查询一门选课记录中视频位置
     * */
    //BY 徐超
    @RequestMapping("/loc")
    public String loc(Integer id1,Integer id2,HttpSession session){
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        Student student = (Student) session.getAttribute("student");
        queryWrapper.eq("del",0).eq("stu_id",student.getStuId()).eq("cou_id",id2);
        return selCouService.getOne(queryWrapper).getPosTime();
    }

   /* *//**
    * 查询当前学习的进度
    * *//*
    @RequestMapping("jindu")
    public String jindu(Integer id2,HttpSession session){
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del",0).eq("stu_id",student.getStuId()).eq("cou_id",id2);
        String timenow=selCouService.getOne(queryWrapper).getPosTime();
        Integer vidId = selCouService.getOne(queryWrapper).();
        for (int i= 0;i<vidId;i++){

        }
    }*/
}
