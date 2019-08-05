package com.test;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.*;

public class ImageUtil {
    public static void main(String[] args) throws IOException {
        System.out.println("开始");
        ByteArrayOutputStream out = QRCode.from("http://192.168.31.71:8080/platform/erp/test/qr").to(ImageType.PNG).stream();
        byte[] data = out.toByteArray();
        OutputStream oute = new FileOutputStream(new File("/Users/jiahangchun/Desktop/test.jpg"));
        oute.write(data);
        oute.flush();
        oute.close();
        System.out.println("收工");
    }
}
