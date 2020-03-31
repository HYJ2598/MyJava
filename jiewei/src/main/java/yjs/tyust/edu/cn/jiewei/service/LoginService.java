package yjs.tyust.edu.cn.jiewei.service;


import yjs.tyust.edu.cn.jiewei.entity.Admin;

public interface LoginService {

    Admin login(String username, String password);
}
