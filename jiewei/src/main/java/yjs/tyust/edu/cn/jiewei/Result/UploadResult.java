package yjs.tyust.edu.cn.jiewei.Result;

import lombok.Data;

import java.util.Map;
//上传文件封装类
//上传的信息为map
//BY 徐超

@Data
public class UploadResult {

    private int code;
    private String msg;
    private Map data;
}
