package yjs.tyust.edu.cn.jiewei.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yjs.tyust.edu.cn.jiewei.Result.R;
import yjs.tyust.edu.cn.jiewei.entity.Share;
import yjs.tyust.edu.cn.jiewei.entity.Student;
import yjs.tyust.edu.cn.jiewei.service.ShareService;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 成彦颖
 * @since 2019-06-23
 */
@RestController

@RequestMapping("/share")
public class ShareController {

    @Autowired
    private ShareService shareService;

    /* 保存信息*/
    @RequestMapping("/add")
    public R save(@RequestParam("plat") String str, @RequestParam("vidId") Integer videoID , HttpSession session){
        String str1 = str;

        Share share = new Share();
        Student student= (Student) session.getAttribute("student");
        System.out.println(student);

        share.setStuId(student.getStuId());
        share.setPlat(str1);
        share.setVidId(videoID);
        share.setDel(0);
        System.out.println(str1);

        shareService.save(share);
        System.out.println(share);

        return R.ok();
    }




}
