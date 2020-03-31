package yjs.tyust.edu.cn.jiewei.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import yjs.tyust.edu.cn.jiewei.Result.UploadResult;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//BY 徐超
//上传文件，不用改，yml文件里改上传文件地址
@Controller

public class FileController {

    @Value("${xc.upload}")
    private String path;

    @RequestMapping("/upload")
    @ResponseBody
    public UploadResult upload(MultipartFile file){
        String oldName=file.getOriginalFilename();
        //得到后缀
        String ext=oldName.substring(oldName.lastIndexOf("."));
        String newName= UUID.randomUUID()+ext;

        File file2=new File(path+newName);
        file2.getParentFile().mkdirs();
        try {
            file.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UploadResult uploadResult=new UploadResult();
        Map map=new HashMap();
        map.put("src",newName);
        uploadResult.setData(map);
        System.out.println(map);
        return uploadResult;
    }
}
