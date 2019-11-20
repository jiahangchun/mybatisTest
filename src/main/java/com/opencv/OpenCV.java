//package com.opencv;
//
//import org.opencv.core.Core;
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//import org.opencv.core.Scalar;
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.imgproc.Imgproc;
//
//public class OpenCV {
//    static{ System.load("F:\\opencv\\opencv\\build\\java\\x64\\opencv_java341.dll"); }
//    public static void main(String[] args) {
//        Mat img = Imgcodecs.imread("F:/opencv/pic/a.png");
//        //中值滤波将图像的每个像素用邻域 (以当前像素为中心的正方形区域)像素的 中值 代替
//        //图像平滑处理：中值滤波：输入、输出、基数
//        Imgproc.medianBlur(img, img, 5);
//        Imgcodecs.imwrite("F:\\opencv\\pic\\c.png",img);
//        img.release();
//    }
//}
