package com.example.smarthomesystemm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;

/*为自己设置灯光模式功能*/

public class Person extends Fragment{

	private View layoutView;
	private Handler mhandler, mhandler1;
	final int TRUE = 1;
	final int FALSE = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 加载person.xml布局
		layoutView = inflater.inflate(R.layout.person,null);
		OnClickListener handler = new OnClickListener(){
			public void onClick(final View v){
				final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						arg0.cancel();
						final DatabaseFunction dbf = new DatabaseFunction();
						Thread t = new Thread(new Runnable(){
							@Override
							public void run(){
								switch(v.getId()){
								case R.id.pedradiobtn2:
									//mhandler1.obtainMessage(11).sendToTarget();
									Log.e("Begin","sending");
									try {
										dbf.send("11");
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;
								case R.id.pe1radiobtn2:
									try {
										dbf.send("11");
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;
								case R.id.pe2radiobtn2:
									try {
										dbf.send("12");
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;
								case R.id.pe3radiobtn2:
									try {
										dbf.send("14");
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;
								
								}
								/*mhandler = new Handler(){
							    	public void handlerMessage(Message msg){
							    		switch(msg.what){
							    		case TRUE:
							    			builder.setTitle("设置成功！");
							    			AlertDialog dialog = builder.create();
											dialog.show();
							    			break;
							    		case FALSE: 
							    			builder.setTitle("设置失败，请重新设置！");
							    			AlertDialog dialog1 = builder.create();
											dialog1.show();
							    		}
							    	}
							    };*/
							}
						});
						t.start();
						
					   
						/*final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
						mhandler = new Handler(){
					    	public void handlerMessage(Message msg){
					    		switch(msg.what){
					    		case TRUE:
					    			//AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());  
									builder.setMessage("无此用户/密码错误")  
									       .setCancelable(false)  
									       .setPositiveButton("确定", new DialogInterface.OnClickListener() {  
									           public void onClick(DialogInterface dialog, int id) {  
									                dialog.dismiss();  
									           }  
									       });  
									AlertDialog dialog = builder.create();
								    dialog.show();
					    			break;
					    		case FALSE: 
					    			builder.setTitle("设置失败，请重新设置！");
					    			AlertDialog dialog1 = builder.create();
									dialog1.show();
					    		}
					    	}
					    };*/
						builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								arg0.cancel();
								 
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
				case R.id.pedradiobtn2:
					builder.setTitle("设置为默认模式？");
					break;
				case R.id.pe1radiobtn2:
					builder.setTitle("设置为明亮模式？");
					break;
				case R.id.pe2radiobtn2:
					builder.setTitle("设置为柔和模式？");
					break;
				case R.id.pe3radiobtn2:
					builder.setTitle("设置为浪漫模式？");
					break;
				case R.id.pecradiobtn2:
					builder.setTitle("设置为模式组合？");
					break;
				}
			    AlertDialog dialog = builder.create();
			    dialog.show();
		   }
		 		
		};
		RadioButton rbtn1 = (RadioButton)layoutView.findViewById(R.id.pedradiobtn2);
		rbtn1.setOnClickListener(handler);
		RadioButton rbtn2 = (RadioButton)layoutView.findViewById(R.id.pe1radiobtn2);
		rbtn2.setOnClickListener(handler);	
		RadioButton rbtn3 = (RadioButton)layoutView.findViewById(R.id.pe2radiobtn2);
		rbtn3.setOnClickListener(handler);
		RadioButton rbtn4 = (RadioButton)layoutView.findViewById(R.id.pe3radiobtn2);
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
	
	class DatabaseFunction{

		private Statement stmt;
		private Connection con;
		//private String s = "00";
		
		//@Override
		public void send(String s)throws ClassNotFoundException, SQLException{
			
			//Looper.prepare();
			/*mhandler1 = new Handler(){
				public void handleMessage(Message msg){
					switch(msg.what){
					case 11:
						s = "'"+Integer.toString(11)+"'";
						break;
					case 12:
						s = "'"+Integer.toString(12)+"'";
						break;
					case 14:
						s = "'"+Integer.toString(14)+"'";
						break;
					}
					
				}
			};*/
	        String d_ip =  Login.database_ip;
	        String d_username = "sa";
	        String d_pw = "123";
	        
            String dbDriver = "net.sourceforge.jtds.jdbc.Driver";
            Connection con=null;
            Class.forName(dbDriver);
			Log.e("MSSQL", "Attempting to connect");
			String dbUrl= "jdbc:jtds:sqlserver://"+d_ip+":1433/SMS"; 
			con =DriverManager.getConnection(dbUrl,d_username, d_pw);
			Log.e("MSSQL", "Connected");
			stmt = con.createStatement();
			
			
			 
			int result = 0;
			String name = "'"+Login.user_name+"'";
			String pw = "'"+Login.user_pw+"'";
			String light = "Light = '"+s+"'";
			String sql = "SELECT * FROM UserFile WHERE Username = "+name+" and Password = "+pw;

			try{
				//getConnection();
				ResultSet rs = stmt.executeQuery(sql); 
				Log.e("MSSQL", "find");
				
				String sql2 = "UPDATE UserFile SET "+light+"WHERE Username = "+name+" and Password = "+pw;
				Log.e("MSSQL", "update");
				    int result1 =0 ;
				    result = stmt.executeUpdate(sql2);
				  
				con.close();
			    Log.e("MSSQL", "close");
				
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("MSSQL", e.toString());
			}     
			/*if(result==1)
				mhandler.obtainMessage(TRUE).sendToTarget();
			else
				mhandler.obtainMessage(FALSE).sendToTarget();*/
		}
		
	}	
}
