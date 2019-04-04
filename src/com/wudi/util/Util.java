package com.wudi.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
	/*public static void main(String[]args) {
		System.out.println(getCurrentTime());
	}
	*/
	
	
}
