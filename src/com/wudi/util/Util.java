package com.wudi.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.alibaba.druid.util.Base64;

public class Util {
	public final static String Cookie_NAME="cname";
    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        bos.close();  
        return bos.toByteArray();  
    } 
	public static boolean isBlankOrEmpty(String string){
		return string==null || string.trim().length() == 0;
	}
	public static String getId() {
		Long t=new Date().getTime();
		Random ra =new Random();
		int a=ra.nextInt(10000);
		return t.toString()+a;
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getCurrentTime(){
		DateFormat bf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		Date date = new Date();
        String format = bf.format(date);
		return format;
	}
	
	/**
     * @Description： 图片转化成base64字符串
     * @param:    path
     * @Return:
     */
    public static String GetImageStr(String path)
    {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        //待处理的图片
        String imgFile = path;
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        String  base=Base64.byteArrayToBase64(data);  
        //返回Base64编码过的字节数组字符串
        return base;
    }
    

	
	public static void main(String[]args) {
		String base=Util.GetImageStr("../baiduface/WebContent/images/user.png");
		System.out.println("ss:"+base);
	}
	
	
	
}
