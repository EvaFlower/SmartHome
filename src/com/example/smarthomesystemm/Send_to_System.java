package com.example.smarthomesystemm;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

import android.util.Log;

//发送数据给myrio实现实时控制
public class Send_to_System {
	
	static String ip = "10.122.0.100";
	static int port = 2333;
	
	public static void send(String s){
		Socket socket;
		try{
			socket = new Socket();  
			socket.connect(new InetSocketAddress(ip, port));
			OutputStream os = socket.getOutputStream(); 
			//String s=r;
			os.write(s.getBytes());
			socket.close();
			}catch(SocketException e){
			e.printStackTrace();
			Log.e("Socket", e.toString());
		}
	    	
		catch(IOException e){
			Log.e("Socket", e.toString());
			e.printStackTrace();
		}
	}

}
