package com.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class AddPng {

    public void creatPDF(String imgName)
    {
        //创建一个文档对象
        Document doc = new Document();
        try {
            //定义输出文件的位置
            PdfWriter.getInstance(doc, new FileOutputStream("D:\\gif\\"+imgName+".pdf"));
            //开启文档
            doc.open();
            //设定字体 为的是支持中文
            //BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            // Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
            //向文档中加入图片
            String path = "http://img2.mklimg.com/g1/M00/16/62/rBBrBlmUJEyAY-fAAAB0TZcOfH8052.png";

            Image jpg1 = Image.getInstance(path); //原来的图片的路径
                    //获得图片的高度
                    float heigth=jpg1.getHeight();
                    float width=jpg1.getWidth();
                    System.out.println("heigth："+"----"+heigth);
                    System.out.println("width："+"-----"+width);
                    //合理压缩，h>w，按w压缩，否则按w压缩
                    //int percent=getPercent(heigth, width);
                    //统一按照宽度压缩
                    int percent=getPercent2(heigth, width);
                    System.out.println("--"+percent);
                    //设置图片居中显示
                    jpg1.setAlignment(Image.ALIGN_LEFT);
                    //直接设置图片的大小~~~~~~~第三种解决方案，按固定比例压缩
                    //jpg1.scaleAbsolute(210.0f, 297.0f);
                    //按百分比显示图片的比例
                    jpg1.scalePercent(percent);//表示是原来图像的比例;
                    //可设置图像高和宽的比例
                    //jpg1.scalePercent(50, 100);
                    doc.add(jpg1);

//            //遍历文件下的文件
//            File file = new File(path);
//            File [] files = file.listFiles();
//            for(int i=0;i<files.length;i++)
//            {
//                File file1 = files[i];
//                //根据后缀判断是否是图片
//                String[] imgTrue = file1.getName().split("\\.");
//                if("png".equals(imgTrue[1])){
//                    //取得图片~~~图片格式：
//                    System.out.println("---"+file1.getName());
//                    Image jpg1 = Image.getInstance(path+"/"+imgTrue[0]+".png"); //原来的图片的路径
//                    //获得图片的高度
//                    float heigth=jpg1.getHeight();
//                    float width=jpg1.getWidth();
//                    System.out.println("heigth："+imgTrue[0]+"----"+heigth);
//                    System.out.println("width："+imgTrue[0]+"-----"+width);
//                    //合理压缩，h>w，按w压缩，否则按w压缩
//                    //int percent=getPercent(heigth, width);
//                    //统一按照宽度压缩
//                    int percent=getPercent2(heigth, width);
//                    System.out.println("--"+percent);
//                    //设置图片居中显示
//                    jpg1.setAlignment(Image.MIDDLE);
//                    //直接设置图片的大小~~~~~~~第三种解决方案，按固定比例压缩
//                    //jpg1.scaleAbsolute(210.0f, 297.0f);
//                    //按百分比显示图片的比例
//                    jpg1.scalePercent(percent);//表示是原来图像的比例;
//                    //可设置图像高和宽的比例
//                    //jpg1.scalePercent(50, 100);
//                    doc.add(jpg1);
//                }
//            }
            //关闭文档并释放资源


            doc.close();
        } catch (Exception e) {

        }
    }
    /**
     * 第一种解决方案
     * 在不改变图片形状的同时，判断，如果h>w，则按h压缩，否则在w>h或w=h的情况下，按宽度压缩
     * @param h
     * @param w
     * @return
     */

    public int getPercent(float h,float w)
    {
        int p=0;
        float p2=0.0f;
        if(h>w)
        {
            p2=297/h*100;
        }
        else
        {
            p2=210/w*100;
        }
        p=Math.round(p2);
        return p;
    }
    /**
     * 第二种解决方案，统一按照宽度压缩
     * 这样来的效果是，所有图片的宽度是相等的，自我认为给客户的效果是最好的
     * @param
     */
    public int getPercent2(float h,float w)
    {
        int p=0;
        float p2=0.0f;
        p2=530/w*100;
        System.out.println("--"+p2);
        p=Math.round(p2);
        return p;
    }
    /**
     * 第三种解决方案，就是直接压缩，不安像素比例，全部压缩到固定值，如210*297
     *
     * @param args
     */
    public static void main(String[] args) {
        AddPng pt=new AddPng();
        pt.creatPDF("imgCreatPdf");
    }



}
