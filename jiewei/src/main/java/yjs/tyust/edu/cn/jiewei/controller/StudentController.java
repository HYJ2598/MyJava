package yjs.tyust.edu.cn.jiewei.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yjs.tyust.edu.cn.jiewei.Result.R;
import yjs.tyust.edu.cn.jiewei.entity.Course;
import yjs.tyust.edu.cn.jiewei.entity.SelCou;
import yjs.tyust.edu.cn.jiewei.entity.Student;
import yjs.tyust.edu.cn.jiewei.mapper.StudentMapper;
import yjs.tyust.edu.cn.jiewei.service.CourseService;
import yjs.tyust.edu.cn.jiewei.service.SelCouService;
import yjs.tyust.edu.cn.jiewei.service.StudentService;
import yjs.tyust.edu.cn.jiewei.service.TestService;
import yjs.tyust.edu.cn.jiewei.util.HttpClientToInterface;
import yjs.tyust.edu.cn.jiewei.util.md5;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学生表 前端控制器
 * </p>
 *
 * @author yuanshuai
 * @since 2019-05-30
 */

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SelCouService selCouService;
    // 贾焱鑫 在login中直接调用了studentmapper中的函数
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TestService testService;
    //袁帅
    @RequestMapping("/student/{id}")
    public Student getUser(@PathVariable Integer id) {
        Student student = studentService.getById(id);
            QueryWrapper<SelCou> wrapper=new QueryWrapper<>();
            wrapper.eq("stu_id",id);
            List<SelCou> list= selCouService.list(wrapper);
            student.setCourNum(list.size());
             return student;
    }
    //成彦颖
    // 通过数据库中id 查询学员信息
    @RequestMapping("/getStudent")
    public Student getStuById() {
        Student student = studentService.getById(24);
        return student;

    }
    //成彦颖
    //更新学员表
    @RequestMapping("/update")
    public Map update(Student student) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("flag",studentService.updateById(student));
        return map;


    }

    //增加学员信息
   /* @RequestMapping("/add")
    public boolean add(Student student) {

        return studentService.save(student);
    }*/

    // 注册功能
    // 贾焱鑫 学员的注册功能
    @RequestMapping("/add")
    public boolean save(Student student) {

        String idcard = student.getIdcard();

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("idcard",idcard);

        //查询数据库 若查询出结果 表明已经存在
        if(studentMapper.selectOne(queryWrapper) == null){

            return studentService.save(student);
        }else {

            return false;
        }

        //System.out.println(student);

    }

    //成彦颖
    //模糊搜索
    @RequestMapping("/search/")
    public List<Student> search(Student student) {
        System.out.println(student);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (!"".equals(student.getStuName()) && student.getStuName() != null) {
            queryWrapper.like("stu_name", student.getStuName());
        }
        if (!"".equals(student.getIdcard()) && student.getIdcard() != null) {
            queryWrapper.like("idcard", student.getIdcard());
        }
        if (!"".equals(student.getCompany()) && student.getCompany() != null) {
            queryWrapper.like("company", student.getCompany());
        }
        queryWrapper.eq("del",0);
        List<Student> dents = studentService.list(queryWrapper);
        return dents;
    }

    //成彦颖
    //删除
    @RequestMapping("/remove/{id}")
    public boolean removeById(@PathVariable Long id) {
        //假删除
        Student student = new Student();
        student.setDel(1);
        UpdateWrapper<Student> dentUpdateWrapper = new UpdateWrapper<>();
        //  注意是表中属性名字
        dentUpdateWrapper.eq("stu_id", id);
        boolean update = studentService.update(student, dentUpdateWrapper);

        return update;
    }
    //成彦颖
    //展示列表
    @RequestMapping("/list")
    public List<Student> list() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", 0);
        List<Student> dentList = studentService.list(queryWrapper);
        return dentList;
        //return dentService.list();
    }
    // 贾焱鑫 学员的登录功能
    @PostMapping("/login")
    public R login(String idcard, HttpSession session) {

        Student student = studentService.login(idcard);
        //System.out.println(student);

        if (student != null) {
            // 获取idacrd

            session.setAttribute("student", student);
            return R.ok().put("student", student);

        } else {
            return R.error();
        }
    }
    // 贾焱鑫 退出登录功能
    @GetMapping("/logout")
    public R logout(HttpSession session){
        session.removeAttribute("student");
        return R.ok();
    }
    // 郝彦杰
    @GetMapping("getUserName")
    public R getUserName(HttpSession session){
        Student student = (Student) session.getAttribute("student");
        student = studentMapper.selectById(student.getStuId());
        return R.ok().put("student",student);
    }

    /**
    * -----------------第二版添加的功能-----------------------------------------------------------------------------
    * */
    /**
     * 最近学习的课程
     * */
    @RequestMapping("/laststudy")
    public Course lastStudy(HttpSession session, HttpServletResponse response) throws IOException {
        Student student =(Student) session.getAttribute("student");
        int stuid=student.getStuId();
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id",student.getStuId());
        Student one = studentService.getOne(queryWrapper);
        int lastcouid=one.getLastCouid();
        QueryWrapper<SelCou> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("stu_id",stuid).eq("del",0).eq("cou_id",lastcouid);
        SelCou selCou = selCouService.getOne(queryWrapper1);
        System.out.println("2019-7-26 11:27"+selCou);
        Course lastCourse = new Course();
        if (selCou!=null){
            lastCourse = courseService.getById(lastcouid);
            System.out.println("2019-7-26 11:29"+lastCourse);
            lastCourse.setSelCou(selCou);
            System.out.println("2019-7-26 11:30"+lastCourse);
        }
//        Integer situation = lastCourse.getSelCou().getSituation();
       /* JSONObject jsonObject= (JSONObject) JSONObject.toJSON(lastCourse);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(jsonObject.toJSONString());*/
        return lastCourse;
    }

    @RequestMapping("laststudy1")
    public Integer lastStudy1(HttpSession session, HttpServletResponse response) throws IOException {
        Student student =(Student) session.getAttribute("student");
        int stuid=student.getStuId();
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id",student.getStuId());
        Student one = studentService.getOne(queryWrapper);
        int lastcouid=one.getLastCouid();
        QueryWrapper<SelCou> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("stu_id",stuid).eq("del",0).eq("cou_id",lastcouid);
        SelCou selCou = selCouService.getOne(queryWrapper1);
        System.out.println("2019-7-26 11:27"+selCou);
        Course lastCourse = new Course();
        if (selCou!=null){
            lastCourse = courseService.getById(lastcouid);
            System.out.println("2019-7-26 11:29"+lastCourse);
            lastCourse.setSelCou(selCou);
            System.out.println("2019-7-26 11:30"+lastCourse);
        }
        Integer situation = lastCourse.getSelCou().getSituation();
       /* JSONObject jsonObject= (JSONObject) JSONObject.toJSON(lastCourse);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(jsonObject.toJSONString());*/
        return situation;
    }

    /**
    * 最近考的试
    * */
    @RequestMapping("/lasttest")
    public Course lastTest(HttpSession session){
        Student student =(Student) session.getAttribute("student");
        int stuid=student.getStuId();
        int lasttestid=student.getLastTestid();
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id",stuid).eq("del",0).eq("last_testid",lasttestid);
        Course lastTest=courseService.getOne(queryWrapper);
        return lastTest;
    }

    /**
    * 调用接口注册验证
    * */
    @RequestMapping("httpreg")
    public boolean newsave(@PathVariable Integer idcard) throws Exception {
        Integer cid =idcard;
        md5 md5 = new md5();
        String cecret = "1461dd9025fd39cf";
        String str = cid+cecret;
        String sign = md5.getMD5(str);
        HttpClientToInterface httpClientToInterface = new HttpClientToInterface();
        String response= httpClientToInterface.doGet("http://www.lemsun.cn:77/Exam/Education/EduInterface.aspx?cid="+cid+"&sign="+sign,"UTF-8");
        System.out.println(response);
        return  true;
//        if (response==){
//
//        }

    }

    /**
    * 调用接口登录
    * */
    @RequestMapping("httplogin")
    public boolean httplogin(String idcard,HttpSession session){
        String cid = idcard;
        md5 md5 =new md5();
        String cecret = "1461dd9025fd39cf";
        String str = cid+cecret;
        String sign =md5.getMD5(str);
        HttpClientToInterface httpClientToInterface = new HttpClientToInterface();
        String response = httpClientToInterface.doGet("http://www.lemsun.cn:77/Exam/Education/EduInterface.aspx?cid="+cid+"&sign="+sign,"UTF-8");
        System.out.println(response+"=============");
        String statu="1";
        if (response.split("、")[0].equals(statu)){
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("idcard",idcard);
            Student student = studentMapper.selectOne(queryWrapper);
            if (student==null){
                Student student1= new Student();
                student1.setIdcard(idcard);
                student1.setStuName(response.split("、")[1]);
                student1.setArea(response.split("、")[3]);
                student1.setEduYear(response.split("、")[4]);
                studentService.save(student1);
                session.setAttribute("student",student1);

            }else {
                session.setAttribute("student",student);

            }
            return true;
        }
        return false;

    }

    /**
    * 学习完成的接口
    * */
//    @RequestMapping("httpstudyfinish")
//    public Integer studyfinish(HttpSession session,){
//
//    }

}
