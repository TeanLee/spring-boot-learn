package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传测试
 */
@Slf4j
@Controller
public class FormTestController {

    @GetMapping("/form_layouts")
    public String form_layouts() {
        return "form/form_layouts";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {

        log.info("上传的信息：email={}，username={}，headerImg={}，photos={}",
                email,username,headerImg.getSize(),photos.length);


//        if (!headerImg.isEmpty()) {
//            // 保存到文件服务器，OSS 服务器
//            String originalFilename = headerImg.getOriginalFilename(); // 获取文件名，用于文件存储
//            // transferTo 将文件转换成流，用于文件上传、存储等操作
//            headerImg.transferTo(new File("H:\\cache\\"+originalFilename)); // 文件名拼接
//        }
//
//
//        if(photos.length > 0){
//            for (MultipartFile photo : photos) {
//                if(!photo.isEmpty()){
//                    String originalFilename = photo.getOriginalFilename();
//                    photo.transferTo(new File("H:\\cache\\"+originalFilename));
//                }
//            }
//        }
        return "index";
    }
}
