package com.pdf;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import sun.misc.IOUtils;

import java.io.*;

public class Pdfdemo {

    public static void main(String[] args) throws Exception {
        addWaterImage("D:\\gif\\456.png","D:\\gif\\rrrrr.pdf");
    }

    /**
     * 添加图片水印 居中
     * @param imagepath  图片文件绝对路径
     * @param filepath  pdf 文件绝对路径
     */public static void addWaterImage(String imagepath,String filepath) throws Exception {
        InputStream is = null;
        PdfReader reader = null;
        OutputStream os = null;
        PdfStamper stamp = null;

            //1.创建pdf输入输出流
            is = new FileInputStream(filepath);
            reader = new PdfReader(is);
            os =  new FileOutputStream(filepath);
            stamp = new PdfStamper(reader, os);

            PdfContentByte contentByte = null;
            int n = reader.getNumberOfPages();
            //2. 设置透明度
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.5f);
            gs.setStrokeOpacity(0.5f);
            //3. 读取图片
            Image logo = Image.getInstance(imagepath);
            //4.在pdf每页右上角添加条码
            for (int i = 1; i <= n; i++){
                contentByte = stamp.getUnderContent(i);     // getOverContent 水印会把正文盖住    getUnderContent 水印会被正文的图片盖住
                contentByte.setGState(gs);
                Rectangle rectangle = reader.getPageSize(i);
                float width = rectangle.getWidth();
                float height = rectangle.getHeight();
                logo.setAbsolutePosition(width/2-logo.getWidth()/2, height/2);
                contentByte.addImage(logo);
            }

    }
}
