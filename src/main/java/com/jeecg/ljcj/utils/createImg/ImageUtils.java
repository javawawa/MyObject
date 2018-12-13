package com.jeecg.ljcj.utils.createImg;

import java.awt.*;
import java.io.*;

public class ImageUtils {
    public static Font getSelfDefinedFont(boolean isBold, int fontsize) {
        Font font = null;
        //字体文件在conf下面
        String filepath =ImageCreate.class.getClassLoader().getResource("shareImages/pingfang.ttf").getPath();
//        String filepath = "d:/image/pingfang.ttf";//RandomCodeChinese.class.getResource("/").getFile().replaceAll("%20", " ") + "../conf/" + filename;
        File file = new File(filepath);
        try {
            FileInputStream fi = new FileInputStream(file);
            BufferedInputStream fb = new BufferedInputStream(fi);
            font = Font.createFont(Font.TRUETYPE_FONT, fb);
        } catch (FontFormatException e) {
            return null;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        if (isBold == true) {
            font = font.deriveFont(Font.BOLD, fontsize);
        } else {
            font = font.deriveFont(Font.PLAIN, fontsize);
        }
        return font;
    }


}
