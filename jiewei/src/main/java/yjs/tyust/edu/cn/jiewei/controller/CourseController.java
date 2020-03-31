package yjs.tyust.edu.cn.jiewei.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yjs.tyust.edu.cn.jiewei.Result.TableResult;
import yjs.tyust.edu.cn.jiewei.entity.Course;
import yjs.tyust.edu.cn.jiewei.entity.SelCou;
import yjs.tyust.edu.cn.jiewei.entity.Student;
import yjs.tyust.edu.cn.jiewei.service.CourseService;
import yjs.tyust.edu.cn.jiewei.service.SelCouService;
import yjs.tyust.edu.cn.jiewei.service.StudentService;
import yjs.tyust.edu.cn.jiewei.util.HttpClientToInterface;
import yjs.tyust.edu.cn.jiewei.util.md5;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 课程表 前端控制器
 * </p>
 *
 * @author 徐超 郝彦杰
 * @since 2019-05-31
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    SelCouService selCouService;
//    @Autowired
//    private CourseMapper courseMapper;

    // 	验证课程 BY 徐超
    @RequestMapping("/check/{id}")
    public Course check(@PathVariable int id) {
        //利用id验证课程
        Course course = new Course();
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cou_id", id);

        course = courseService.getOne(queryWrapper);
        System.out.println(course);
        return course;
    }

    //以下 BY 郝彦杰
    @RequestMapping("/getById/{id}")
    public Course listById(@PathVariable int id) {
        //按id查询一个记录
        return courseService.getById(id);
    }


    /*
     * 查询全部课程信息
     * */
    @RequestMapping("/list")
    public List<Course> list() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", 0);
        List<Course> list = courseService.list(queryWrapper);
        System.out.println(list);
        return list;
    }

    /*
     * 根据课程Id查询课程
     * */
    @RequestMapping("/find1/{id}")
    public List<Course> findById(@PathVariable int id) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cou_id", id).eq("del", 0);
        List<Course> courseList = courseService.list(queryWrapper);
        System.out.println(courseList);
        return courseList;

    }

    /*
     * 根据课程名查询课程
     * */
    @RequestMapping("/find2/")
    public List<Course> findByCoursename(Course course) {

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (!"".equals(course.getCouId()) && course.getCouId() != null) {
            queryWrapper.like("cou_id", course.getCouId()).eq("del", 0);
        }
        if (!"".equals(course.getTeacher().trim()) && course.getTeacher() != null) {
            queryWrapper.like("teacher", course.getTeacher()).eq("del", 0);
        }
        if (!"".equals(course.getCouName().trim()) && course.getCouName() != null) {
            queryWrapper.like("cou_name", course.getCouName()).eq("del", 0);
        }
        queryWrapper.eq("del", 0);
        List<Course> course1 = courseService.list(queryWrapper);
        return course1;
    }

    /*
     * 根据教师名查询课程
     * */
    @RequestMapping("/find3/{teacher}")
    public Course findByTeacher(@PathVariable String teacher) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher", teacher).eq("del", 0);
        Course course = courseService.getOne(queryWrapper);
        return course;
    }

    /*
     * 添加课程
     * */
    @RequestMapping("/add")
    public boolean add(Course course) {

        courseService.save(course);
//         Integer courseId = course.getCouId();
//         List<Student> studentList = new ArrayList<>();
//         studentList=studentService.list();
//         for (int i=0;i<studentList.size();i++){
//             SelCou selCou = new SelCou();
//             selCou.setCouId(courseId);
//             selCou.setStuId(studentList.get(i).getStuId());
//             selCou.setDel(true);
//             selCouService.save(selCou);
//         }
        return true;
    }

    /*
     * 修改课程
     * */
    @RequestMapping("/update")
    public boolean update(Course course) {
        return courseService.updateById(course);
    }

    /*
     * 删除课程
     * */
    //假删除 BY 徐超
    @RequestMapping("/remove/{id}")
    public boolean removeById(@PathVariable int id) {

        Course course = new Course();
        course.setDel(1);
        UpdateWrapper<Course> courseUpdateWrapper = new UpdateWrapper<>();
        courseUpdateWrapper.eq("cou_Id", id);
        boolean removeRes = courseService.update(course, courseUpdateWrapper);
        return removeRes;
    }


    /*
     * 学生相关 BY HYJ
     * */
    /*
     * layui查询这个学生还没选的全部课程(学生端选课中心)
     * */


    @RequestMapping("/list1")
    public TableResult list1(HttpSession session) {
        //int id=2;
        Student student = (Student) session.getAttribute("student");
        int id = student.getStuId();
        QueryWrapper<SelCou> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.notInSql("cou_id", "select cou_id from sel_cou where stu_id=" + id).or(
                i -> i.inSql("cou_id", "select cou_id from sel_cou where stu_id=" + id).eq("del", "1")
        );
        List<SelCou> selCouList1 = selCouService.list(queryWrapper1);

        QueryWrapper<SelCou> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("stu_id", id);
        queryWrapper3.eq("del", "0");
        List<SelCou> selCouList2 = selCouService.list(queryWrapper3);
        Set<Integer> set1 = new HashSet<>();
        for (SelCou selCou : selCouList2) {
            set1.add(selCou.getCouId());
        }

        List<Course> courses = new ArrayList<>();
        Set<Integer> sets = new HashSet<>();
        for (SelCou selCou : selCouList1) {
            if (!set1.contains(selCou.getCouId())) {
                sets.add(selCou.getCouId());
            }
        }

        for (Integer set : sets) {
            QueryWrapper<Course> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("cou_id", set);
            courses.add(courseService.getOne(queryWrapper2));
        }
        TableResult tableResult = new TableResult();
        tableResult.setData(courses);
        tableResult.setCount((Integer) courses.size());
        tableResult.setCode(0);
        return tableResult;
    }

    /*
     * 改：选课中心应该根据课程表查再抛去该学生已选的课
     * */
    @RequestMapping("/coursecenter")
    public TableResult find(HttpSession session) {
        QueryWrapper<Course> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("del", 0);
        List<Course> courselist = courseService.list(queryWrapper1);
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", student.getStuId()).eq("del", 0);
        List<SelCou> list = selCouService.list(queryWrapper);
        ArrayList<Integer> list1 = new ArrayList<>();
        if (list != null) {
            for (SelCou s : list) {
                list1.add(s.getCouId());
            }
        }
        for (Course c : courselist) {
            if (list1.contains(c.getCouId())) {
                c.setFlag(0);   //0是选择
            } else {
                c.setFlag(1);   //1未选择
            }

        }


//        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("stu_id",student.getStuId()).and(i->i.eq("del",true)).or(i->i.ne("stu_id",student.getStuId()));
        TableResult tableResult = new TableResult();
        tableResult.setData(courselist);
        tableResult.setCount((Integer) courselist.size());
        tableResult.setCode(0);
        return tableResult;

    }

    /**
     * 选课中心
     */
    @RequestMapping("/coursecenter1")
    public PageInfo<Course> find1(@RequestParam("num") Integer num,@RequestParam(name = "year",required = false) Integer year, HttpSession session) {
        int pagesize = 3;
        int pagenum = num;
        QueryWrapper<Course> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("del", 0);
        if (year != null){
            queryWrapper1.eq("year",year);

        }

        PageHelper.startPage(pagenum,pagesize);
        List<Course> courselist = courseService.list(queryWrapper1);
        PageInfo<Course> pageInfo = new PageInfo<>(courselist);
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", student.getStuId()).eq("del", 0);

        List<SelCou> list = selCouService.list(queryWrapper);

        ArrayList<Integer> list1 = new ArrayList<>();
        if (list != null) {
            for (SelCou s : list) {
                list1.add(s.getCouId());
            }
        }
        for (Course c : pageInfo.getList()) {
            if (list1.contains(c.getCouId())) {
                c.setFlag(0);   //0是选择
            } else {
                c.setFlag(1);   //1未选择
            }

        }


//        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("stu_id",student.getStuId()).and(i->i.eq("del",true)).or(i->i.ne("stu_id",student.getStuId()));
        return pageInfo;

    }

    /*
     * 根据学生Id查询其选课信息（学生端已选课程）
     * */
    @RequestMapping("/findByStuId")
    public TableResult findByStuId(HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        System.out.println(student);
        int id = student.getStuId();
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", id);
        queryWrapper.eq("del", 0);
        List<SelCou> selCous = selCouService.list(queryWrapper);
        System.out.println(selCous);
        ArrayList<Course> courses = new ArrayList<>();
        for (SelCou cous : selCous) {
            QueryWrapper<Course> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("cou_id", cous.getCouId());
            courses.add(courseService.getOne(queryWrapper1).setSelCou(cous));
        }

        System.out.println(courses);
        TableResult tableResult = new TableResult();
        tableResult.setData(courses);
        tableResult.setCount((Integer) courses.size());
        tableResult.setCode(0);
        return tableResult;
    }

    @RequestMapping("/findByStuId1")
    public PageInfo findByStuId1(@RequestParam("num") Integer num, @RequestParam(name="year", required =false) Integer year, HttpSession session) {
        int pagesize = 1;
        int pagenum = num;


        Student student = (Student) session.getAttribute("student");
        int id = student.getStuId();
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", id);
        queryWrapper.eq("del", 0);
        List<SelCou> selCous = selCouService.list(queryWrapper);

        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Object> list1 = new ArrayList<>();
        for (SelCou cous : selCous) {
            list1.add(cous.getCouId());
            System.out.println(cous.getCouId());
//            QueryWrapper<Course> queryWrapper1 = new QueryWrapper<>();
//            queryWrapper1.eq("cou_id", cous.getCouId());
//            courses.add(courseService.getOne(queryWrapper1).setSelCou(cous));
        }
        System.out.println(list1);
        QueryWrapper<Course> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.in("cou_id", list1);
        if (year!=null){
            queryWrapper1.eq("year",year);
        }
        PageHelper.startPage(pagenum, pagesize);

        List<Course> list = courseService.list(queryWrapper1);
        PageInfo<Course> pageInfo = new PageInfo<Course>(list);

        for (Course cous : pageInfo.getList()) {
            QueryWrapper<SelCou> queryWrapper2= new QueryWrapper<>();
            queryWrapper2.eq("stu_id", id);
            queryWrapper2.eq("del", 0);
            queryWrapper2.eq("cou_id",cous.getCouId());
            SelCou one = selCouService.getOne(queryWrapper2);
            cous.setSelCou(one);
            Integer situation = cous.getSelCou().getSituation();
            System.out.println("hyj 2019-7-26 13:32"+situation);
        }

        System.out.println(pageInfo);
        System.out.println(pageInfo.getList().size());

        return pageInfo;
    }

    /*
     * 客户端根据学生查课程的信息和选课状态，多表连接
     * */
    @RequestMapping("/selectinfo/{id}")
    public TableResult<Course> selectInfo(@PathVariable int id, HttpSession session) {
//        Student student = (Student) session.getAttribute("student");
//        int id = student.getStuId();
//        System.out.println(id+"---------------------------------------------");
        List<Course> courseList = courseService.selectInfo(id);
        TableResult<Course> tableResult = new TableResult<>();
        tableResult.setData(courseList);
        System.out.println(courseList);
        tableResult.setCode(0);
        return tableResult;
/**
 * -------------------------第二版新添加的功能----------------------------------------------------------------------
 * */
    }

    /**
     * 我的课程、选课中心、考试中心该学生已选课程总数
     */
    @RequestMapping("selectnum")
    public Integer num(HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", student.getStuId()).eq("del", 0);
        int size = selCouService.list(queryWrapper).size();
        return size;
    }

    /**
     * 根据年份的已选课程总数
     */
    @RequestMapping("selectnumbyyear/{year}")
    public int num1(HttpSession session, @PathVariable Integer year) {


/*

        // 新的逻辑 start
        // 得到session里的学生
        Student student = (Student) session.getAttribute("student");
        // 查询当前学生当年的课程
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("");






        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", student.getStuId()).eq("del", 0);
        List<SelCou> list = selCouService.list(queryWrapper);
        //list是该学生没有删除的选课的记录
        List<Course> courseList = new ArrayList<>();
        List<Course> courseList1 = new ArrayList<>();
        //遍历学生的选课记录，将其课程拿出来，放入课程list中
        System.out.println("hyj21点27分：" + list.size());
        for (SelCou s : list) {
            //建立一个课程对象
            Course course = new Course();
            //根据id查询课程
            QueryWrapper<Course> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("cou_id",s.getCouId());
            course= courseService.getOne(queryWrapper1);
            // 如果年份和del满足，就加到要显示的课程的list里
            int myyear = course.getYear();
            int mydel = course.getDel();
            if (myyear == year && mydel == 0) {
                courseList.add(course);
            }

        }

        // 新的逻辑 end
*/





//         郝彦杰的逻辑 start
//         得到session里的学生
        Student student = (Student) session.getAttribute("student");
        //查询当前学生的选课记录
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", student.getStuId()).eq("del", 0);
        List<SelCou> list = selCouService.list(queryWrapper);
        //list是该学生没有删除的选课的记录
        List<Course> courseList = new ArrayList<>();
        //遍历学生的选课记录，将其课程拿出来，放入课程list中
        System.out.println("hyj21点27分：" + list.size());
        for (SelCou s : list) {
            //建立一个课程对象
            Course course = new Course();
            //根据id查询课程
            QueryWrapper<Course> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("cou_id",s.getCouId());
            course= courseService.getOne(queryWrapper1);
            // 如果年份和del满足，就加到要显示的课程的list里
            int myyear = course.getYear();
            int mydel = course.getDel();
            if (myyear == year && mydel == 0) {
                courseList.add(course);
            }

        }
        int size=courseList.size();

//         郝彦杰的逻辑 end



        return size;
    }

    /**
     * 已选学分
     */
    @RequestMapping("/selectcredit/{year}")
    public Integer creditnum(HttpSession session,@PathVariable Integer year) {
        int credit = 0;
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("stu_id", student.getStuId()).eq("del", 0);
        List<SelCou> selCouList = selCouService.list(queryWrapper1);
        for (SelCou s : selCouList) {
            Course course = new Course();
            QueryWrapper<Course> queryWrapper2= new QueryWrapper();
            queryWrapper2.eq("cou_id",s.getCouId());
            course = courseService.getOne(queryWrapper2);
            int myyear =course.getYear();
            int mydel = course.getDel();
            if (myyear == year && mydel==0){
                credit+=course.getCredit();
            }
        }
        return credit;
    }

    /**
     * 年度完成学分
     */
    @RequestMapping("finishcredit/{year}")
    public Integer finishcredit(HttpSession session,@PathVariable Integer year) {
        int credit = 0;
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("stu_id", student.getStuId()).eq("del", 0).eq("stu_statu",2);
        List<SelCou> selCouList = selCouService.list(queryWrapper1);
        for (SelCou s:selCouList){
            Course course = new Course();
            QueryWrapper<Course> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("cou_id",s.getCouId());
            course=courseService.getOne(queryWrapper2);
            int myyear =course.getYear();
            int mydel = course.getDel();
            if (myyear == year && mydel==0){
                credit+=course.getCredit();
            }
        }

        return credit;
    }

    /**
     * 培训状态
     */
    /*@RequestMapping("satisfy")
    public boolean ifSatisfy(HttpSession session) {
        int credit = 0;
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("stu_id", student.getStuId()).eq("del", 0);
        List<SelCou> selCouList = selCouService.list(queryWrapper1);
        QueryWrapper<Course> queryWrapper2 = new QueryWrapper<>();
        List<Course> courseList = new ArrayList<>();
        for (SelCou selCou : selCouList) {
            queryWrapper2.eq("cou_id", selCou.getCouId()).eq("stu_statu", 2);
            Course course = courseService.getOne(queryWrapper2);
            courseList.add(course);
        }
        for (Course course : courseList) {
            credit += course.getCredit();
        }
        if (credit >= 90) {
            QueryWrapper<Student> queryWrapper3 = new QueryWrapper<>();
            queryWrapper3.eq("stu_id",student.getStuId());
            Student one = studentService.getOne(queryWrapper3);
            one.setPassDate(new Date());
            UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("stu_id",student.getStuId());
            studentService.update(one,updateWrapper);
            finishedstudy(session);
            return true;
        } else
            return false;
    }
*/

    /**
     * 可考试课程
     */
    @RequestMapping("cantest")
    public Integer numtest(HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id",student.getStuId()).eq("situation", 1).eq("del", 0);
        int size = selCouService.list(queryWrapper).size();
        return size;
    }

    /**
     * 已通过考试课程
     */
    @RequestMapping("passtest")
    public Integer numpassed(HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", student.getStuId()).eq("stu_statu", 2).eq("del", 0);
        int size = selCouService.list(queryWrapper).size();
        return size;
    }

    /**
     * 考试通过率
     */
    @RequestMapping("passrate")
    public Integer passrate(HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("stu_id", student.getStuId()).eq("stu_statu", 2).eq("del", 0);
        double size1 = selCouService.list(queryWrapper1).size();
        QueryWrapper<SelCou> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("stu_id", student.getStuId()).eq("del", 0);
        double size2 = selCouService.list(queryWrapper2).size();
        int rate = (int) ((size1 / size2)*100);
        System.out.println(rate);
        return rate;
    }

    /**
     * 根据年份搜索课程
     */
    @RequestMapping("selectbyyear/{year}")
    public List<Course> byYear(@PathVariable Integer year) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", 0).eq("year", year);
        List<Course> list = courseService.list(queryWrapper);
        return list;
    }

    /**
     * 根据课程类别搜索课程
     */
    @RequestMapping("selectbykind/{kind}")
    public List<Course> byKind(@PathVariable String kind) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", 0).eq("kind", kind);
        List<Course> list = courseService.list(queryWrapper);
        return list;
    }

    /**
    * 根据公需课还是专业课搜索课程
    * */
    @RequestMapping("selectbykind2/{kind}")
    public List<Course> bykind2(@PathVariable String kind){
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del",0).eq("kind2",kind);
        List<Course> list = courseService.list(queryWrapper);
        return list;
    }

    /**
     * 查共有哪些年份的课程
     */
    @RequestMapping("selectyear")
    public Set<Integer> year() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", 0);
        List<Course> courseList = courseService.list(queryWrapper);
        Set<Integer> set = new HashSet<>();
        for (Course c : courseList) {
            set.add(c.getYear());
        }
        return set;
    }

    /**
     * 查共有哪些种类的课程
     */
    @RequestMapping("selectkind")
    public Set<String> kind() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", 0);
        List<Course> courseList = courseService.list(queryWrapper);
        Set<String> set = new HashSet<>();
        for (Course c : courseList) {
            set.add(c.getKind());
        }
        return set;
    }

    /**
    * 查出专业课和公需课两项
    * */
    @RequestMapping("selectkind2")
    public Set<String> kind2(){
            QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("del",0);
            List<Course> courseList = courseService.list(queryWrapper);
            Set<String> set = new HashSet<>();
            for (Course c : courseList){
                set.add(c.getKind2());
            }
            return set;
    }

    /**
    * 统计此人公需科目的总学分
    * */
    @RequestMapping("gongxucredit")
    public Integer gongxucredit(HttpSession session){
        Integer credit=0;
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("stu_id",student.getStuId()).eq("del",0).eq("stu_statu",2);
        List<SelCou> list = selCouService.list(queryWrapper);
        for (SelCou s : list){
            QueryWrapper<Course> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("cou_id",s.getCouId()).eq("kind2","公需课");
            Course one = courseService.getOne(queryWrapper1);
            if (one!=null){
                credit+=one.getCredit();
            }
        }
        return credit;
    }


    /**
    * 统计此人专业科目总学分
    * */
    @RequestMapping("zhuanyecredit")
    public Integer zhuanyecredit(HttpSession session){
        Integer credit=0;
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("stu_id",student.getStuId()).eq("del",0).eq("stu_statu",2);
        List<SelCou> list = selCouService.list(queryWrapper);
        for (SelCou s : list){
            QueryWrapper<Course> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("cou_id",s.getCouId()).eq("kind2","专业课");
            Course one = courseService.getOne(queryWrapper1);
            if (one!=null){
                credit+=one.getCredit();
            }
        }
        return credit;
    }

    /**
    * 统计此人继续教育的总学分
    * */
    @RequestMapping("allcredit")
    public Integer allcredit(HttpSession session){
        Integer credit = 0;
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<SelCou> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id",student.getStuId()).eq("del",0).eq("stu_statu",2);
        List<SelCou> list = selCouService.list(queryWrapper);
        for (SelCou s : list){
            QueryWrapper<Course> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("cou_id",s.getCouId());
            Course one = courseService.getOne(queryWrapper1);
            if (one!=null){
                credit+=one.getCredit();
            }
        }
        return credit;
    }

    /**
    * 学员学习完成后记录完成时间
    * */
    @RequestMapping("notefinishtime")
    public void note(HttpSession session){
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<Student> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("stu_id",student.getStuId());
        Student one = studentService.getOne(queryWrapper3);
        Date date =new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        one.setPassDate(sdf.format(date));
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("stu_id",student.getStuId());
        studentService.update(one,updateWrapper);
        finishedstudy(session);
    }

    /**
    *学员学习完成后的交互
    * */
    @RequestMapping("finishedstudy")
    public boolean finishedstudy(HttpSession session){
        System.out.println("开始finishedstudy函数");
        Student student = (Student) session.getAttribute("student");
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id",student.getStuId());
        Student one = studentService.getOne(queryWrapper);
        //String cid = one.getIdcard();
        String cid = "142401198204061444";
        System.out.println("hyj 2019-7-26 14:14"+cid);
        //String EDU_YEAR=one.getEduYear();
        String EDU_YEAR = "2019";
        System.out.println("hyj 2019-7-26 14:15"+EDU_YEAR);
        //String PASS_DATE=student.getPassDate();
        String PASS_DATE = "2019-7-26";
        System.out.println("hyj 2019-7-26 14:16"+PASS_DATE);
        //Integer EDU_CREDIT=allcredit(session);
        //System.out.println("hyj 2019-7-26 14:17"+EDU_CREDIT);
        Integer EDU_CREDIT = 30;

        //Integer EDU_CREDIT_P=gongxucredit(session);
        //System.out.println("hyj 2019-7-26 14:18"+EDU_CREDIT_P);
        Integer EDU_CREDIT_P=30;
        Integer EDU_CREDIT_F=0;
        //Integer EDU_CREDIT_F=zhuanyecredit(session);
        //System.out.println("hyj 2019-7-26 14:19"+EDU_CREDIT_F);
        //String PASS_AREA=student.getArea();
        String PASS_AREA = "149901";
        System.out.println("hyj 2019-7-26 14:20"+PASS_AREA);
        md5 md5 = new md5();
        String cecret = "1461dd9025fd39cf";
        String str=cid+EDU_YEAR+cecret;
        String sign = md5.getMD5(str);
        System.out.println("hyj 2019-7-26 14:21"+sign);
        HttpClientToInterface httpClientToInterface = new HttpClientToInterface();
        System.out.println("开始发送");

        String response=httpClientToInterface.doGet("http://www.lemsun.cn:77/Exam/Education/EndStudy.aspx?cid="+cid+
                "&EDU_YEAR="+EDU_YEAR+"&PASS_DATE="+PASS_DATE+"&EDU_CREDIT="+EDU_CREDIT+ "&EDU_CREDIT_P="+EDU_CREDIT_P+
                "&EDU_CREDIT_F="+EDU_CREDIT_F+"&PASS_AREA="+PASS_AREA+"&SIGN="+sign,"UTF-8");
        System.out.println("hyj 2019 7-26 14:25"+response);
        System.out.println("发送结束");
        String statu = "1";
        if (response.equals(statu)){
            return true;
        }else {
            return false;
        }

    }
}
