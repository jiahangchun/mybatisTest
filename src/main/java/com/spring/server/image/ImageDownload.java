package com.spring.server.image;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jiahangchun
 */
@Controller
public class ImageDownload {

    /**
     * 下载图片
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String path="/Users/jiahangchun/Desktop/timg.jpeg";
        File file=new File(path);
        if(file.exists()){
            //设置MIME类型
            response.setContentType("application/octet-stream");
            //设置头信息,设置文件下载时的默认文件名，同时解决中文名乱码问题
            response.addHeader("Content-disposition", "attachment;filename="+new String("test.jpg".getBytes(), "ISO-8859-1"));
            InputStream inputStream=new FileInputStream(file);
            ServletOutputStream outputStream=response.getOutputStream();
            byte[] bs=new byte[1024];
            while((inputStream.read(bs)>0)){
                outputStream.write(bs);
            }
            outputStream.close();
            inputStream.close();
        }
    }
}
