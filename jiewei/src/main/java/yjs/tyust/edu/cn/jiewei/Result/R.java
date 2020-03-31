package yjs.tyust.edu.cn.jiewei.Result;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;
/**
 * 贾焱鑫 抄的老师的
 * 类R 用于判断controller的返回
 * 凡返回类型为R都是调用此处
 * 不可删除
 * */

@Data
public class R{
    private int code;
    private String msg;
    private Map<String,Object> data = new HashMap<>();

    public static R ok(){
        R r = new R();
        r.setCode(0);
        r.setMsg("成功");
        return r;
    }

    public static R error(){
        R r = new R();
        r.setCode(500);
        r.setMsg("失败");
        return r;
    }

    public R put(String s,Object o){
        this.data.put(s,o);
        return this;
    }
}

