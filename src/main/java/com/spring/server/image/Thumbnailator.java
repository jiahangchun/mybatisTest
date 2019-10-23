package com.spring.server.image;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Thumbnailator {
    public static void main(String[] args) throws IOException {

        Thumbnailator thumbnailatorTest = new Thumbnailator();
        //thumbnailatorTest.test1();
        //thumbnailatorTest.test2();
        //thumbnailatorTest.test3();
        //thumbnailatorTest.test4();
//        thumbnailatorTest.test5();
        thumbnailatorTest.testSize();
    }

    public  void testSize() throws IOException {
        String path="/Users/jiahangchun/Desktop/timg.jpeg";
        String resultPath="/Users/jiahangchun/Desktop/tes.jpeg";
        File file=new File(path);
        File resultFile=new File(resultPath);
        if(resultFile.exists()){
            resultFile.delete();
        }

        Long size=file.length()/1024;
        System.out.println(size+"K");

        Float scale= 100f/size;
        Thumbnails.of(path).scale(scale).toFile(resultPath);
        resultFile=new File(resultPath);

        System.out.println((resultFile.length()/1024)+"K");
    }

    /**
     * 指定大小进行缩放 * @throws IOException
     */

    public void test1() throws IOException {
        /*    * size(width,height) 若图片横比200小，高比300小，不变    * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变    * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300    */
        Thumbnails.of("D:\\img\\test.jpg").size(200, 300).toFile("D:\\img\\end\\test_200_300.jpg");

    }

    /**
     * 按照比例缩放 * @throws IOException
     */

    public void test2() throws IOException {

        /*    * size(width,height) 若图片横比200小，高比300小，不变    * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变    * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300    */
        Thumbnails.of("D:\\img\\test.jpg").scale(0.5f).toFile("D:\\img\\end\\test_0.5f.jpg");

    }

    /**
     * 不按照比例，指定大小进行缩放 *  * @throws IOException
     */

    private void test3() throws IOException {

        /**    * keepAspectRatio(false) 默认是按照比例缩放的    */

        Thumbnails.of("D:\\img\\test.jpg").size(120, 120).keepAspectRatio(true).toFile("D:\\img\\end\\image_120x120_2.jpg");

    }

    /**
     * 旋转 *  * @throws IOException
     */

    private void test4() throws IOException {

        /**    * rotate(角度),正数：顺时针 负数：逆时针    */

        Thumbnails.of("D:\\img\\test.jpg").size(1280, 1024).rotate(90).toFile("D:\\img\\end\\image+90.jpg");

    }

    /**
     * 水印 *  * @throws IOException
     */

    private void test5() throws IOException {

        /**    * watermark(位置，水印图，透明度)    */

        Thumbnails.of("D:\\img\\test.jpg").size(1280, 1024).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("D:\\img\\water.jpg")), 0.5f).outputQuality(0.8f).toFile("D:\\img\\end\\image_watermark_bottom_right.jpg");
    }


}
