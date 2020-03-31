package yjs.tyust.edu.cn.jiewei.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yjs.tyust.edu.cn.jiewei.entity.Admin;
import yjs.tyust.edu.cn.jiewei.service.AdminService;

import java.util.List;


/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author woyuno
 * @since 2019-05-31
 */
@RestController

@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/list")
    public List<Admin> list() {
        return adminService.list();
    }


    @RequestMapping("/admin/{id}")
    public Admin getUser(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        return admin;
    }

    @RequestMapping("/update")
    public boolean update(Admin admin) {
        return adminService.updateById(admin);
    }


    @RequestMapping("/remove/{id}")
    public boolean removeById(@PathVariable Long id) {
        return adminService.removeById(id);
    }

    @RequestMapping("/addAdmin")
    public Boolean addAdmin(Admin admin) {
        System.out.println("hyj070201"+admin);
        return adminService.save(admin);
    }

/**
* 和上getUser方法功能一样，不知道在哪用，之后看看能否合并
 * requestMapping不同，是不是另一种重构
* */
    @RequestMapping("/getAdminById{id}")
    public Admin getAdminById(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        return admin;
    }

}
