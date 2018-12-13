package com.jeecg.ljcj.utils.createImg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class CreateMiddleImage {

    private static class Tuple {
        public int next;
        public String line;

        Tuple(int next, String line) {
            this.next = next;
            this.line = line;
        }
    }

    private static int drawText(final String key, Font font, Color color, int sizePerLine, int lineHeight, Graphics2D graphics, final int start_x, final int start_y) {
        graphics.setFont(font);
        graphics.setColor(color);
        FontMetrics fontMetrics = graphics.getFontMetrics(font);
        StringBuilder sb = new StringBuilder(key);
        int start = 0;
        int lineNo = 0;
        while (start < sb.length()) {
            Tuple tp = buildLine(start, sizePerLine, sb, fontMetrics);
            start = tp.next;
            String line = tp.line;
            graphics.drawString(line, start_x, start_y + lineNo * (lineHeight));
            lineNo++;
        }
        return start_y + (lineNo - 1) * lineHeight + fontMetrics.getHeight();
    }

    private static Tuple buildLine(int start, int sizePerLine, StringBuilder sb, FontMetrics fontMetrics) {
        int offset = 0;
        double width = 0;
        while (width < sizePerLine &&
                start + offset < sb.length()) {
            char c = sb.charAt(start + offset);
            double w = fontMetrics.stringWidth(c + "");
            width += w;
            offset++;
            if(c=='\n'||c=='\r'){
                break;
            }
        }
        String substring = sb.substring(start, start + offset);
        return new Tuple(start + offset, substring);
    }

    public static BufferedImage createMiddle(String title, String content) throws IOException {
        int width=468;
        content=content.replaceAll("\t","    ");
        int lastHeight = getImgHeight(title, content);
        //RGB形式
        BufferedImage bi = new BufferedImage(width, lastHeight,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        //设置背景色
        g2.setBackground(Color.WHITE);
        //通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
        g2.clearRect(0, 0, width, lastHeight);
        //设置画板 使字体平滑
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        Font titleFont = ImageUtils.getSelfDefinedFont(true,26);
        Font contentfont = ImageUtils.getSelfDefinedFont(false,22);
        int next_y = drawText(title, titleFont, new Color(0, 0, 0), 410, 36, g2, 20, 40);
        next_y = drawText(content, contentfont, new Color(0, 0, 0), 401, 36, g2, 20, next_y + 25);
        //构建图片流
        BufferedImage tag = new BufferedImage(width, lastHeight, BufferedImage.TYPE_INT_RGB);
        //绘制改变尺寸后的图
        tag.getGraphics().drawImage(bi, 0, 0,width, lastHeight, null);
        g2.dispose();
        return bi;
    }

    public static int getImgHeight(String title, String content){
        BufferedImage bi = new BufferedImage(468, 800,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        Font titleFont = ImageUtils.getSelfDefinedFont(true,26);
        Font contentfont = ImageUtils.getSelfDefinedFont(false,22);
        int next_y = drawText(title, titleFont, new Color(0, 0, 0), 410, 36, g2, 20, 40);
        next_y = drawText(content, contentfont, new Color(0, 0, 0), 401, 36, g2, 20, next_y + 25);
        int lastHeight = next_y +10;
        return lastHeight;
    }

    public static void main(String[] args) throws IOException {
        String title = "【TokenGazer深度研究】QuarkChain：扩容设计可能牺牲安全性，主网落地时间存疑】";
        String content = "\t123按时打算打打打\n" +
                "213花四大四大\n" +
                "\n" +
                "是低俗撒旦那是的asd\n" +
                "啊实打实的";
        BufferedImage buf = createMiddle(title, content);
        String margeImagePath = "D://image//testMiddle.jpg";
        ImageCreate.generateSaveFile(buf,margeImagePath);
    }

}
