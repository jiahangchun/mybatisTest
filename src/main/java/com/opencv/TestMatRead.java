//package com.opencv;
//
//public class TestMatRead {
//    public static void main(String[] args) {
//        Mat img = Imgcodecs.imread("/Users/wuxi/Pictures/medianBlur.png");
//        //中值滤波将图像的每个像素用邻域 (以当前像素为中心的正方形区域)像素的 中值 代替
//        //图像平滑处理：中值滤波：输入、输出、基数
//        Imgproc.medianBlur(img, img, 7);
//        Imgcodecs.imwrite("/Users/wuxi/Pictures/medianBlur1.png",img);
//        img.release();
//
//    }
//}
