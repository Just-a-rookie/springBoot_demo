package com.example.controller;

import com.example.util.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.UUID;

@RestController
public class UploadController {

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name","cheng");
        mv.setViewName("upload");
        return  mv;
    }

    @PostMapping("/upload")
    @ResponseBody
    public Object upload(MultipartFile fileUpload){
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID()+suffixName;
        //指定本地文件夹存储图片
        //String path = ResourceUtils.getURL("").getPath();
        String filePath = "F:/IdeaProjects/ssm/src/main/resources/static/upload";
        try {
            //将图片保存到static文件夹里
            fileUpload.transferTo(new File(filePath+fileName));
            return new Message(0,"success to upload");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(-1,"fail to upload");
        }
    }
}
