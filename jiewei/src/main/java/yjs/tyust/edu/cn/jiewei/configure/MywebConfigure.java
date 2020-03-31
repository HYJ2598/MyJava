package yjs.tyust.edu.cn.jiewei.configure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import yjs.tyust.edu.cn.jiewei.inteceptor.LoginIntecpeptor;
/**
 * <p>
 * 拦截器
 * 作用：未登录用户不可修改地址栏地址直接进入页面
 * 若出现未登录用户修改地址栏地址进入页面的情况 请将下面函数内的excludePathPatterns路径中对应的内容删除
 * 原则上excludePathPatterns中只应该有登录和注册对应的页面和请求接口
 * </p>
 *
 * @author 贾焱鑫 郝彦杰修改
 * @since 2019-06
 */
@SpringBootApplication
public class MywebConfigure implements WebMvcConfigurer {

    @Autowired
    private LoginIntecpeptor loginIntecpeptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntecpeptor).addPathPatterns("/**")
                .excludePathPatterns("http://www.lemsun.cn:77/Exam/Education/EduInterface.aspx/**","/student/help_center.html","/student/question.html","/student/login.html","/student/login","/student/httplogin","/course/list","/course/selectyear","/course/selectkind","/course/selectkind2","/course/selectbyyear/{year}","/course/selectbykind/{kind}","/course/selectbykind2/{kind}","/student/reg.html","/student/zhuye.html","/student/add","/js/**","/css/**","/fonts/**","/img/**","/images/**","/admin/login_1.html","/login","/upload/**","/selcou/loc","/video/play","/selcou/updata");

    }
}
