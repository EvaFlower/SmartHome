package com.example.smarthomesystemm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

/*储存用户资料*/

public class UserFiles {

	public static String Gpath;//注册用户资料保存路径
	public static String sdPath;

	public static String addUser(String name, String pw) throws IOException{
		
		sdPath = Environment.getExternalStorageDirectory().getPath()+File.separator+"data";
		Gpath = sdPath+File.separator+"userfile.txt";
		OutputStream fos = null;
		
		File f = new File(sdPath);
		if(!f.exists()){
			f.mkdir();  
		}
		File f2 = new File(Gpath);
		fos = new FileOutputStream(f2,true);
		if(readUser(name,pw).equals("无此用户")){
			    
			fos.write((name+" "+pw+"\n").getBytes());
			fos.flush();
			fos.close();
		    return "注册成功";
	    }
		     
		else{
			fos.close();
			return "用户已存在";	
		}
		    
	}
	public static String readUser(String name, String pw) throws IOException{
		
		Gpath = Environment.getExternalStorageDirectory().getPath()+File.separator+"data"+
		File.separator+"userfile.txt";
		byte b[] = null;
		InputStream ins = null;
		try{
			File f = new File(Gpath);
		    ins = new FileInputStream(f);
		    b = new byte[ins.available()];
		    ins.read(b);
		    ins.close();
		    //return 
		}catch(Exception e){
			e.printStackTrace();
			//return "";
		}
		String whole = new String(b);
		String line[] = whole.split("\n");
		String s[];
		for(int i=0;i<line.length;i++){
			s=line[i].split(" ");
			
			if(s[0].equals(name.toString())){

				if(s[1].equals(pw.toString())){
					ins.close();
					return "true";
					
				}
				else{
					ins.close();
					return "密码错误";
					
				}				
			}		
		}
		ins.close();
		return "无此用户";	
	}
    
}
