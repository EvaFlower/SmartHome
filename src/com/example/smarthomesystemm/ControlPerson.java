package com.example.smarthomesystemm;

import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;

/*为自己设置灯光模式功能*/

public class ControlPerson extends Fragment{

	private View layoutView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 加载person.xml布局
		layoutView = inflater.inflate(R.layout.c_person,null);
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
										case R.id.c_pedradiobtn2:
											Send_to_System.send("11");
											break;
										case R.id.c_pe1radiobtn2:
											Send_to_System.send("11");
											break;
										case R.id.c_pe2radiobtn2:
											Send_to_System.send("12");
											break;
										case R.id.c_pe3radiobtn2:
											Send_to_System.send("14");
											break;
										case R.id.c_peradiobtn2_close:
											Send_to_System.send("10");
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
				case R.id.c_pedradiobtn2:
					builder.setTitle("设置为默认模式？");
					break;
				case R.id.c_pe1radiobtn2:
					builder.setTitle("设置为明亮模式？");
					break;
				case R.id.c_pe2radiobtn2:
					builder.setTitle("设置为柔和模式？");
					break;
				case R.id.c_pe3radiobtn2:
					builder.setTitle("设置为浪漫模式？");
					break;
				case R.id.c_pecradiobtn2:
					builder.setTitle("设置为模式组合？");
					break;
				}
			    AlertDialog dialog = builder.create();
			    dialog.show();
		   }
		 		
		};
		RadioButton rbtn1 = (RadioButton)layoutView.findViewById(R.id.c_pedradiobtn2);
		rbtn1.setOnClickListener(handler);
		RadioButton rbtn2 = (RadioButton)layoutView.findViewById(R.id.c_pe1radiobtn2);
		rbtn2.setOnClickListener(handler);	
		RadioButton rbtn3 = (RadioButton)layoutView.findViewById(R.id.c_pe2radiobtn2);
		rbtn3.setOnClickListener(handler);
		RadioButton rbtn4 = (RadioButton)layoutView.findViewById(R.id.c_pe3radiobtn2);
		rbtn4.setOnClickListener(handler);
		//模式组合？？？
		/*RadioButton rbtn5 = (RadioButton) layoutView.findViewById(R.id.pecradiobtn2);
		rbtn5.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(getActivity());
				//Dialog.Builder builder = new AlertDialog.Builder(getActivity());
				Button btn1 = (Button) dialog.findViewById(R.id.alertbtn1);
				dialog.setContentView(R.layout.modealertdialog);
				dialog.setTitle("选择模式");
				dialog.show();
				/*btn1.setOnClickListener(new OnClickListener(){
				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//v.setVisibility(View.INVISIBLE);
						dialog.hide();
					}

					
				});
				Button btn2 = (Button) layoutView.findViewById(R.id.alertbtn2);
				btn2.setOnClickListener(new OnClickListener(){
				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//发送选择模式！！！
						//v.setVisibility(View.INVISIBLE);
						dialog.hide();
					}
				});	
				
				
				/*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						arg0.cancel();
						//AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
						final Dialog dialog = new Dialog(getActivity());
						//Dialog.Builder builder = new AlertDialog.Builder(getActivity());
						Button btn1 = (Button) dialog.findViewById(R.id.alertbtn1);
						btn1.setOnClickListener(new OnClickListener(){
						
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								dialog.dismiss();
							}
						});
						Button btn2 = (Button) dialog.findViewById(R.id.alertbtn2);
						btn2.setOnClickListener(new OnClickListener(){
						
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								//发送选择模式！！！
								dialog.dismiss();
							}
						});	
						dialog.setTitle("选择模式");
						dialog.setContentView(R.layout.modealertdialog);
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
			    builder.setTitle("设置为模式组合？");
			    AlertDialog dialog = builder.create();
			    dialog.show();
			}
			
		});*/
		return layoutView;
	}
	public void send(String s){
		//Socket socket;
		
		byte[] b = s.getBytes();
		DatagramPacket packet = null;
		try {
			packet = new DatagramPacket(b,b.length, InetAddress.getByName("10.20.117.187"),2333);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatagramSocket dsocket = null;
		try {
			dsocket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dsocket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dsocket.close();
		
	}
}
