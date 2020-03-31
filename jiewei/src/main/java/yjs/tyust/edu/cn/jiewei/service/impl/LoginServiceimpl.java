package yjs.tyust.edu.cn.jiewei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yjs.tyust.edu.cn.jiewei.entity.Admin;
import yjs.tyust.edu.cn.jiewei.mapper.AdminMapper;
import yjs.tyust.edu.cn.jiewei.service.LoginService;

import java.util.HashMap;
import java.util.Map;



@Service
public class LoginServiceimpl implements LoginService {


    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String ad_name, String ad_pwd) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        map.put("ad_name",ad_name);
        map.put("ad_pwd",ad_pwd);
        queryWrapper.allEq(map);

        Admin admin = adminMapper.selectOne(queryWrapper);

        return admin;
    }
}
