package yjs.tyust.edu.cn.jiewei.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yjs.tyust.edu.cn.jiewei.Result.R;
import yjs.tyust.edu.cn.jiewei.entity.Admin;
import yjs.tyust.edu.cn.jiewei.entity.AdminMessage;
import yjs.tyust.edu.cn.jiewei.entity.StuMessage;
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

@RequestMapping("/adminMessage")
public class AdminMessageController {

    @Autowired
    private AdminMessageService adminMessageService;

    @Autowired
    private StuMessageService stuMessageService;

    /* 保存信息*/
    /*@RequestMapping("/save")
    public R save(AdminMessage adminMessage){
        boolean b = adminMessageService.save(adminMessage);
        if(adminMessage.getAdId()!=null){
            Long messId = adminMessage.getMessId();
            StuMessage stuMessage = new StuMessage();
            stuMessage.setMessId(messId);
            stuMessage.setFlag(1);
            stuMessageService.updateById(stuMessage);
        }
        return R.ok();
    }
*/
    @RequestMapping("/save")
    public R save(AdminMessage adminMessage, HttpSession session){

        Admin admin = (Admin)session.getAttribute("admin");
        adminMessage.setAdId(admin.getAdId());

        boolean b = adminMessageService.save(adminMessage);
        System.out.println(adminMessage);

        if(adminMessage.getAdId()!=null){
            Long messId = adminMessage.getMessId();
            System.out.println(messId);
            StuMessage stuMessage = new StuMessage();
            stuMessage.setMessId(messId);
            stuMessage.setFlag(1);
            stuMessageService.updateById(stuMessage);
        }
        return R.ok();
    }



    /*查找问题*/
    @RequestMapping("/getByMessId/{messId}")
    public R getByMessId(@PathVariable Long messId){

        List<AdminMessage> adminMessageList = adminMessageService.listByMessId(messId);
        System.out.println(adminMessageList);
        return R.ok().put("adminMessageList",adminMessageList);
    }

}
