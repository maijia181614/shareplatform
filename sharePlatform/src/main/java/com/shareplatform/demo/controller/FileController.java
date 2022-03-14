package com.shareplatform.demo.controller;

import com.shareplatform.demo.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/upload")
    public String fileUpload(@RequestParam(value = "img") MultipartFile file){
        System.out.println("进入上传");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        System.out.println(user);
        if(null!=user){
            if(file.isEmpty()){
                System.out.println("文件是空的");
                return "fail";
            }
            System.out.println("有文件");
            // 文件名
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
//            // 后缀名
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 上传后的路径
            String filePath = "/Users/maijiaqiang/IdeaProjects/sharePlatform/target/classes/img/";
            // 新文件名
            UUID uuid=UUID.randomUUID();
            fileName = uuid + ".jpg";
            System.out.println(fileName);
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return uuid.toString();
        }else {
            System.out.println("没有用户");
        }
        return null;
    }
}
