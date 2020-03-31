package yjs.tyust.edu.cn.jiewei.inteceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 放行器
 * 根据session中是否对象来放行
 * 对象来自相应的登录功能中
 * 注释来自贾焱鑫
 * 代码好像已被修改过（郝彦杰修改）
 */
@Component
public class LoginIntecpeptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String url = request.getRequestURL().toString();


        if(session.getAttribute("student")!=null||session.getAttribute("admin")!=null){
            //登录:
            return true;
        }else{
            response.sendRedirect(request.getContextPath()+"/student/zhuye.html");
            return false;
        }


//        else if (url.indexOf("student")!=-1){
//            response.sendRedirect(request.getContextPath()+"/student/login.html");
//            return false;
//        }else {
//            response.sendRedirect(request.getContextPath()+"/admin/login_1.html");
//            return false;
//        }

    }

}
