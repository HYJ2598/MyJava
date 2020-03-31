package yjs.tyust.edu.cn.jiewei.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yjs.tyust.edu.cn.jiewei.Result.TableResult;
import yjs.tyust.edu.cn.jiewei.entity.Course;
import yjs.tyust.edu.cn.jiewei.entity.SelCou;
import yjs.tyust.edu.cn.jiewei.entity.Student;
import yjs.tyust.edu.cn.jiewei.entity.Video;
import yjs.tyust.edu.cn.jiewei.service.CourseService;
import yjs.tyust.edu.cn.jiewei.service.SelCouService;
import yjs.tyust.edu.cn.jiewei.service.StudentService;
import yjs.tyust.edu.cn.jiewei.service.VideoService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 视频表 前端控制器
 * </p>
 *
 * @author 徐超
 * @since 2019-05-28
 */

@RestController

@RequestMapping("/video")
public class VideoController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private VideoService videoService;

    @Autowired
    private SelCouService selCouService;

    @Autowired
    private StudentService studentService;
    @RequestMapping("/upload")
    public boolean upload(Video video){
//        添加
        videoService.save(video);
        QueryWrapper<Course> queryWrapper=new QueryWrapper<Course>();
        queryWrapper.eq("cou_id",video.getCouId());
        Course course = courseService.getOne(queryWrapper);
        UpdateWrapper<Course> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("cou_id",course.getCouId());
        course.setVidNum(course.getVidNum()+1);
        courseService.update(course,updateWrapper);
        return true;
    }

    //wrapper为条件选择器
    @RequestMapping("/list/{id}")
    public List<Video> list(@PathVariable int id){
//        展示
        QueryWrapper<Video> queryWrapper=new QueryWrapper<>();
        // 按排序
        queryWrapper.orderByAsc(true,"sort").eq("del",0).eq("cou_id",id);
        List<Video> videoList = videoService.list(queryWrapper);
        return videoList;
    }

    @RequestMapping("/play/{id}")
    public List<Video> play(@PathVariable int id,HttpSession session){
//        展示
        QueryWrapper<Video> queryWrapper=new QueryWrapper<>();
        // 按排序
        queryWrapper.orderByAsc(true,"sort").eq("del",0).eq("cou_id",id);
        List<Video> videoList = videoService.list(queryWrapper);
//        String[] arr=new String[videoList.size()];
//        for (int i=0;i<videoList.size();i++) {
//
//            System.out.println(videoList.get(i).getPath());
//
//            arr[i]=videoList.get(i).getPath();
//        }
//        System.out.println(arr);
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<Student> queryWrapper1 =new QueryWrapper<>();
        queryWrapper1.eq("stu_id",student.getStuId());
        Student student1 = studentService.getOne(queryWrapper1);
        student1.setLastCouid(id);
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("stu_id",student.getStuId());
        studentService.update(student1,updateWrapper);


        return videoList;
    }

    @RequestMapping("/list2/{id}")
    public TableResult<Video> list2(@PathVariable int id){


//        展示，放到tableResult中
        QueryWrapper<Video> queryWrapper=new QueryWrapper<>();
        // 按排序
        queryWrapper.orderByAsc(true,"sort").eq("del",0).eq("cou_id",id);
//        PageHelper.startPage(1,10);
        List<Video> videoList = videoService.list(queryWrapper);
        TableResult<Video> videoTableResult=new TableResult<>();
        videoTableResult.setData(videoList);
        return videoTableResult;
    }

    @RequestMapping("/remove/{id}")
    public boolean remove(@PathVariable int id){
//        假删除
        Video video=new Video();
        video.setDel(1);

        UpdateWrapper<Video> videoUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        videoUpdateWrapper.eq("vid_Id",id);

        boolean update = videoService.update(video,videoUpdateWrapper);
        return update;
    }

    @RequestMapping("/getById/{id}")
    public Video listById(@PathVariable int id){
        //按id查询一个记录
        return videoService.getById(id);
    }

    @RequestMapping("/edit")
    public Boolean edit(Video video){
        //修改
        return videoService.updateById(video);
    }

    /**
    * 第二版，根据课程Id查询该课程下的视频名称
    * */
    @RequestMapping("/getvideonamelist/{couid}")
    public List<Video> getvideonamelist(@PathVariable Integer couid){
        List<Video> videoList = videoService.selectvideonamelist(couid);

        return videoList;
    }

    /**
    * 判断所有视频有没有放完(郝彦杰)
    * */
    @RequestMapping("/iffinish")
    public Integer finish(@RequestParam Integer vidId, @RequestParam Integer Id2, HttpSession session){
//    public Integer finish(HttpSession session){
//        Integer vidId=20;
//        Integer Id2=4;
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id",student.getStuId()).eq("cou_id",Id2).eq("del",0);
        SelCou selCou = selCouService.getOne(queryWrapper);
        QueryWrapper<Course> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("cou_id",selCou.getCouId());
        Course course = courseService.getOne(queryWrapper1);
        course.setSelCou(selCou);

        if (vidId==course.getVidNum()){
            UpdateWrapper<SelCou> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("stu_id",student.getStuId()).eq("cou_id",Id2).eq("del",0);
            selCou.setStuStatu(1);
            selCouService.update(selCou,updateWrapper);
            return 1;
        }
        else return 0;
    }

    @RequestMapping("/iffinish1/{id2}")
    public Integer finish1( @PathVariable Integer id2, HttpSession session){
//    public Integer finish(HttpSession session){
//        Integer vidId=20;
//        Integer Id2=4;
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id",student.getStuId()).eq("cou_id",id2).eq("del",0);
        SelCou selCou = selCouService.getOne(queryWrapper);
        System.out.println(selCou);
        QueryWrapper<Course> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("cou_id",selCou.getCouId());
        Course course = courseService.getOne(queryWrapper1);
        course.setSelCou(selCou);


            UpdateWrapper<SelCou> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("stu_id",student.getStuId()).eq("cou_id",id2).eq("del",0);
            selCou.setSituation(1);
        System.out.println(selCou);
            selCouService.update(selCou,updateWrapper);
            return 1;

    }


//    @RequestMapping("/selectvid")
//    public Integer selectvid(@RequestParam  Integer id2, @RequestParam Integer id3){
//        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("cou_id",id2).eq("sort",id3).eq("del",0);
//        Video one = videoService.getOne(queryWrapper);
//        return one.getVidId();
//
//    }

    @RequestMapping("/update")
    public boolean update(@RequestParam Integer id2,@RequestParam Integer id3,HttpSession session,String t,@RequestParam Integer k){
        Video video = new Video();
        SelCou selCou = new SelCou();
        Student student = (Student) session.getAttribute("student");

        System.out.println("hyj 2019-7-28 13:40"+ t);
        System.out.println("hyj 2019-7-28 14:26"+ k);

        video.setPosTime(t);
        video.setSort(k+1);
        selCou.setVidId(k+1);
        UpdateWrapper<Video> updateWrapper = new UpdateWrapper<>();
        UpdateWrapper<SelCou> updateWrapper1 = new UpdateWrapper<>();
        updateWrapper.eq("vid_id",id3).eq("del",0).eq("cou_id",id2);
        updateWrapper1.eq("stu_id",student.getStuId()).eq("cou_id",id2).eq("del",0);
        videoService.update(video,updateWrapper);
        selCouService.update(selCou,updateWrapper1);
        return true;
    }

}
