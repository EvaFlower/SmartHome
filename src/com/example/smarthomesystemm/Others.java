package com.example.smarthomesystemm;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

/*为他人设置灯光模式功能*/

public class Others extends Fragment{

	private View layoutView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 加载person.xml布局
		layoutView = inflater.inflate(R.layout.others,null);
		OnClickListener handler = new OnClickListener(){
			public void onClick(final View v){
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						arg0.cancel();
						AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
						
						builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								arg0.cancel();
								new Thread(new Runnable(){

									@Override
									public void run() {
										// TODO Auto-generated method stub
										switch(v.getId()){
										case R.id.pedradiobtn:
											send("11");
											break;
										case R.id.pe1radiobtn:
											send("11");
											break;
										case R.id.pe2radiobtn:
											send("12");
											break;
										case R.id.pe3radiobtn:
											send("14");
											break;
										
										}
									}
									
								}).start();
							}
						});
						builder.setTitle("设置成功！");
						AlertDialog dialog = builder.create();
						dialog.show();
					}
				});
			    builder.setNegativeButton("返回", new DialogInterface.OnClickListener(){
			    	
			    	@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						arg0.cancel();
					}
			    });
			    switch(v.getId()){
				case R.id.odradiobtn2:
					builder.setTitle("设置为默认模式？");
					break;
				case R.id.o1radiobtn2:
					builder.setTitle("设置为明亮模式？");
					break;
				case R.id.o2radiobtn2:
					builder.setTitle("设置为柔和模式？");
					break;
				case R.id.o3radiobtn2:
					builder.setTitle("设置为浪漫模式？");
					break;
				case R.id.ocradiobtn2:
					builder.setTitle("选择模式组合？");
					break;
				}
			    
			    AlertDialog dialog = builder.create();
			    dialog.show();
		   }
		 		
		};
		RadioButton rbtn1 = (RadioButton)layoutView.findViewById(R.id.odradiobtn2);
		rbtn1.setOnClickListener(handler);
		RadioButton rbtn2 = (RadioButton)layoutView.findViewById(R.id.o1radiobtn2);
		rbtn2.setOnClickListener(handler);	
		RadioButton rbtn3 = (RadioButton)layoutView.findViewById(R.id.o2radiobtn2);
		rbtn3.setOnClickListener(handler);
		RadioButton rbtn4 = (RadioButton)layoutView.findViewById(R.id.o3radiobtn2);
		rbtn4.setOnClickListener(handler);
		//模式组合？？？
		return layoutView;
	}
	public void send(String s){
		Socket socket;
		try{
			socket = new Socket();  
			socket.connect(new InetSocketAddress("10.20.117.187",2333));
			OutputStream os = socket.getOutputStream(); 
			//String s=r;
			os.write(s.getBytes());
			socket.close();
			}catch(SocketException e){
			e.printStackTrace();
			
		}
	    	
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
