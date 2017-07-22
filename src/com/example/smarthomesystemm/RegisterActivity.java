package com.example.smarthomesystemm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.smarthomesystemm.Login.DatabaseFunction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*注册*/

public class RegisterActivity extends Activity{

	private Button btn;
	private Handler mhandler;
	private final int REG_FAIL = 0;
	private final int REG_SUCCESS = 1;
	private final int USER_EXIST = -1;
	String s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		btn = (Button)this.findViewById(R.id.registerbtn2);
		final EditText et1, et2;
		et1 = (EditText)this.findViewById(R.id.registerName);
		et2 = (EditText)this.findViewById(R.id.registerPW);
	    btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				
				AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
	            	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							arg0.cancel();
							final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
							String s1 = et1.getText().toString();
							String s2 = et2.getText().toString();
							final String name = "'"+s1+"'";
							final String pw = "'"+s2+"'";
						
							Thread t = new Thread(new Runnable(){
								public void run(){
									new DatabaseFunction().addUser(name, pw);
								}
							});
							t.start();
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									// TODO Auto-generated method stub
									arg0.cancel();
									//RegisterActivity.this.setVisible(false);
									Intent intent = new Intent();
									intent.setClass(RegisterActivity.this,Login.class);
									startActivity(intent);
								}
							});
							builder.setTitle(s);
							
							 /*Verify the name and password of login*/
							//final AlertDialog dialog;
							mhandler = new Handler(){
								public void handleMessage(Message msg){
									switch(msg.what){
									case REG_SUCCESS:
										builder.setTitle("注册成功");
										AlertDialog dialog = builder.create();
										dialog.show();
										break;
									
									case REG_FAIL:
										builder.setTitle("注册失败");
										AlertDialog dialog1 = builder.create();
										dialog1.show();
										
									    break;
									case USER_EXIST:
										builder.setTitle("用户已存在");
										AlertDialog dialog11 = builder.create();
										dialog11.show();
										break;
										
									}
								}
							};
							
							
							//dialog.show();
						}
					});
				    builder.setNegativeButton("返回", new DialogInterface.OnClickListener(){
				    	
				    	@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							arg0.cancel();
						}
				    });
				    builder.setTitle("信息确认无误？");
				    AlertDialog dialog = builder.create();
				    dialog.show();
			}
		});
	}
	class DatabaseFunction{

		private Statement stmt;
		private Connection con;
		
		
		public void addUser(String name, String pw){
			
			
			String d_ip = Login.database_ip;
	        String d_username = "sa";
	        String d_pw = "123";
            String dbDriver = "net.sourceforge.jtds.jdbc.Driver";
            Connection con=null;
			try {
				Class.forName(dbDriver);
				Log.e("MSSQL", "Attempting to connect");
				String dbUrl= "jdbc:jtds:sqlserver://"+d_ip+":1433/SMS"; 
				con =DriverManager.getConnection(dbUrl, d_username, d_pw);
				Log.e("MSSQL", "Connected");
				stmt = con.createStatement(); 
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	
			String sql = "SELECT * FROM UserFile WHERE Username = "+name+" and Password = "+pw;
			//ResultSet rs;
			boolean b = false;
			try {
				ResultSet rs = stmt.executeQuery(sql);
				b = rs.next();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				if(!b){
				    
					Log.e("MSSQL","add");
					String sql1 = "INSERT INTO UserFile (Username, Password) "
						+ "VALUES (" + name + "," + pw +")";
				
				    int result = stmt.executeUpdate(sql1);
				    if(result==0){
				        con.close();
				        mhandler.obtainMessage(REG_FAIL).sendToTarget(); //0:"注册失败"
				    }
				    else{
				    	con.close();
				    	mhandler.obtainMessage(REG_SUCCESS).sendToTarget();
				    }
				    
				}
				     
				else{
					
					con.close();
					mhandler.obtainMessage(USER_EXIST).sendToTarget();
					//con.close();
				    Log.e("MSSQL", "close");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
	}	
}
