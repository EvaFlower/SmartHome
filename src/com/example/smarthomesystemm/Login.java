package com.example.smarthomesystemm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.smarthomesystemm.R.id;

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

/*登陆界面*/

public class Login extends Activity {

	private EditText name;
	private EditText pw;
	private Button loginbtn;
	private Button registerbtn;
	static Handler mhandler;
	final int TRUE = 1;
	final int FALSE = 0;
	static String user_name;
	static String user_pw;
	static String database_ip = "10.122.0.96";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		registerbtn = (Button)findViewById(R.id.registerbtn);
		registerbtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				Intent intent = new Intent();
				intent.setClass(Login.this, RegisterActivity.class);
				startActivity(intent);
			}
		});
		loginbtn = (Button)findViewById(R.id.loginbtn);
		loginbtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				name = (EditText) findViewById(R.id.loginName);
				pw = (EditText) findViewById(R.id.loginPW);
				
				//
				
				
				final String loginname;
				final String loginpw;//, temp = null;
				boolean temp = false;
				loginname = name.getText().toString();
				loginpw = pw.getText().toString();
				
				final String name = "'"+loginname+"'";
				final String pw = "'"+loginpw+"'";
				
				
				Thread t = new Thread(new Runnable(){
					public void run(){
						try {
							new DatabaseFunction().findUser(name, pw);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//new DatabaseFunction2().findUser(loginname, loginpw);
					}
				});
				t.start();
				
				 /*Verify the name and password of login*/
				mhandler = new Handler(){
					public void handleMessage(Message msg){
						switch(msg.what){
						case TRUE:
							user_name = loginname;
							user_pw = loginpw;
							//findViewById(R.id.login).setVisibility(false);
							//new InfoActivity();
							Intent intent = new Intent();
							intent.setClass(Login.this, InfoActivity.class);
							startActivity(intent);
							
							break;
						
						case FALSE:
							AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);  
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
						}
					}
				};
				
			}
		});
	}
	class DatabaseFunction{

		private Statement stmt;
		private Connection con;
		
		
		
		public void findUser(String name, String pw) throws ClassNotFoundException, SQLException{
			
			String d_ip = Login.database_ip;
	        String d_username = "sa";
	        String d_pw = "123";
            String dbDriver = "net.sourceforge.jtds.jdbc.Driver";
			
			Class.forName(dbDriver); 
			Connection con=null;
			Log.e("MSSQL", "Attempting to connect");
			String dbUrl= "jdbc:jtds:sqlserver://"+d_ip+":1433/SMS"; ; 
			con =DriverManager.getConnection(dbUrl, d_username, d_pw);
			Log.e("MSSQL", "Connected");
			stmt = con.createStatement(); 
			
			String sql = "SELECT * FROM UserFile WHERE Username = "+name+" and Password = "+pw;
			
			ResultSet rs;
			boolean result = false;
			int re;
			try{
				//getConnection();
				rs = stmt.executeQuery(sql); 
				result = rs.next();
			
				
				String sql2 = "INSERT INTO UserFile (Username, Password, Light, Music) "
						+ "VALUES ('a', 's', NULL, NULL)";
				
				con.close();
			    Log.e("MSSQL", "close");
				
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("MSSQL", e.toString());
			}     
			if(result==true)
				mhandler.obtainMessage(TRUE).sendToTarget();
			else
				mhandler.obtainMessage(FALSE).sendToTarget();
			//mhandler.obtainMessage(re).sendToTarget();
			
				
		}
		
	}	
}
