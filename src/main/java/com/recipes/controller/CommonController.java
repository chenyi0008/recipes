package com.recipes.controller;

import com.recipes.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * common
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${recipes.path}")
    private String basePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
        log.info(file.getName());
        String originalFilename = file.getOriginalFilename();
        //得到后缀 如：.jpg
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //避免文件名相同导致覆盖
        String fileName = UUID.randomUUID().toString() + suffix;

        //创建一个目录对象
        File dir = new File(basePath);
        //判断当前目录是否存在
        if(!dir.exists()){
            //目录不存在，需要创建
            dir.mkdir();
        }

        file.transferTo(new File(basePath + fileName));
        return R.success(fileName);
    }

    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("download/{name}")
    public void download(@PathVariable String name, HttpServletResponse response) throws IOException {
        //输入流，通过输入流读取文件内容
        FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

        ServletOutputStream outputStream = response.getOutputStream();
        //输出流，通过输出流将文件写回浏览器，在浏览器展示图片

        response.setContentType("image/jpeg");

        int len = 0;
        byte[] bytes = new byte[1024];
        while((len = fileInputStream.read(bytes)) != -1){
            outputStream.write(bytes,0,len);
            outputStream.flush();
        }

        fileInputStream.close();
        outputStream.close();

    }
}
