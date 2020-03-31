package yjs.tyust.edu.cn.jiewei.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import yjs.tyust.edu.cn.jiewei.Result.R;
import yjs.tyust.edu.cn.jiewei.entity.Admin;
import yjs.tyust.edu.cn.jiewei.service.LoginService;

import javax.servlet.http.HttpSession;

@RestController

public class LoginController {

//陈亭亭
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public R login(String adName, String adPwd, HttpSession session){
        Admin admin = loginService.login(adName, adPwd);

        if(admin!=null){
            session.setAttribute("admin",admin);
            return R.ok().put("admin",admin);

        }else{
            return R.error();
        }

    }

    @GetMapping("/logout")
    public R logout(HttpSession session) {
        session.removeAttribute("admin");
        return R.ok();
    }

}
