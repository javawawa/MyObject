package com.jeecg.ljcj.utils.createImg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateHeaderImage {
    /**
     * 头部
     * @param srcImgPath
     * @param tarImgPath
     * @param waterMarkContent
     * @param markContentColor
     * @param font
     */
    public static BufferedImage createHeader(String srcImgPath, String waterMarkContent, Color markContentColor, Font font) {
        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            //设置水印的坐标 84 328
            int x = 84;
            int y = 325;
            g.drawString(waterMarkContent, x, y);  //画出水印
            g.dispose();
            return bufImg;
            // 输出图片
//            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
//            ImageIO.write(bufImg, "png", outImgStream);
//            System.out.println("添加水印完成");
//            outImgStream.flush();
//            outImgStream.close();
        } catch (Exception e) {
            return null;
            // TODO: handle exception
        }
    }

    public static void main(String[] args) throws IOException {
//        Font font = new Font("PingFang-SC-Regular", Font.PLAIN, 19);
//        Font font = ImageUtils.getSelfDefinedFont(false,19);
//        String srcImgPath="D:/image/header.png"; //源图片地址
//        String tarImgPath="D:/image/addHeader.png"; //待存储的地址
//        String waterMarkContent="2018-12-10 13:20:22";  //水印内容
//        Color color=new Color(0,0,0, 250);
//        createHeader(srcImgPath, tarImgPath,waterMarkContent,color,font);
//        image();
    }
}
