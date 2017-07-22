package com.example.smarthomesystemm;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/*为自己设置音乐模式功能*/

public class ControlPerson2 extends Fragment{

	private View layoutView;
	private RadioGroup radiogroup;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 加载person.xml布局
		layoutView = inflater.inflate(R.layout.c_person_music,null);
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
										case R.id.c_pedradiobtn:
											Send_to_System.send("21");
											break;
										case R.id.c_pe1radiobtn:
											Send_to_System.send("21");
											break;
										case R.id.c_pe2radiobtn:
											Send_to_System.send("22");
											break;
										case R.id.c_pe3radiobtn:
											Send_to_System.send("24");
											break;
										case R.id.c_pecradiobtn_close:
											Send_to_System.send("20");
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
				case R.id.c_pedradiobtn:
					builder.setTitle("设置为默认模式？");
					break;
				case R.id.c_pe1radiobtn:
					builder.setTitle("设置为热情模式？");
					break;
				case R.id.c_pe2radiobtn:
					builder.setTitle("设置为怀旧模式？");
					break;
				case R.id.c_pe3radiobtn:
					builder.setTitle("设置为抒情模式？");
					break;
				case R.id.c_pecradiobtn:
					builder.setTitle("选择模式组合？");
					break;
				}
			    
			    AlertDialog dialog = builder.create();
			    dialog.show();
		   }
		 		
		};
		RadioButton rbtn1 = (RadioButton)layoutView.findViewById(R.id.c_pedradiobtn);
		rbtn1.setOnClickListener(handler);
		RadioButton rbtn2 = (RadioButton)layoutView.findViewById(R.id.c_pe1radiobtn);
		rbtn2.setOnClickListener(handler);	
		RadioButton rbtn3 = (RadioButton)layoutView.findViewById(R.id.c_pe2radiobtn);
		rbtn3.setOnClickListener(handler);
		RadioButton rbtn4 = (RadioButton)layoutView.findViewById(R.id.c_pe3radiobtn);
		rbtn4.setOnClickListener(handler);
		//模式组合？？？
		return layoutView;
	}
	/*public boolean send(String s){
		Socket socket;
		boolean b = true;
		try{
			socket = new Socket();  
			socket.connect(new InetSocketAddress("10.20.117.187",2333));
			OutputStream os = socket.getOutputStream(); 
			//String s=r;
			os.write(s.getBytes());
			socket.close();
			
			}catch(SocketException e){
			e.printStackTrace();
			b = false;
			
		}
	    	
		catch(IOException e){
			e.printStackTrace();
			b = false;
		}
		return b;
	}*/
}
