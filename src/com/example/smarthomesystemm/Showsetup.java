package com.example.smarthomesystemm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/*此项目的入口，即APP进入的开始界面*/

public class Showsetup extends Activity {

	private static TextView light;
	private static TextView music;
	private TextView autho;
	private TextView autho_light;
	private TextView autho_music;
	private Button btn;
	private final int TRUE = 1;
	private final int FALSE = 0;
	private Handler mhandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_setup);
		
	
	light = (TextView) this.findViewById(R.id.show_light);	
	music = (TextView) this.findViewById(R.id.show_music);
	autho = (TextView) this.findViewById(R.id.show_autho);
	
	/*new Thread(new Runnable(){
		@Override
		public void run(){
			try {
				new DatabaseFunction().show();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}).start();
	
	/*mhandler = new Handler(){
		
		public void handlerMessage(Message msg){


			int arg0 = msg.what/100;
			int arg1 = msg.what%100;
			String s1 = "无", s2="无";
			switch(arg0){
			case 11:
				s1 = "明亮";
				break;
			case 12:
				s1 = "柔和";
				break;
			case 14:
				s1 = "浪漫";
				break;
				
				
			}
			switch(arg1){
			case 21:
				s2 = "热情";
				break;
			case 22:
				s2 = "怀旧";
				break;
			case 24:
				s2 = "抒情";
				break;	
			}
			light.setText(s1);
			music.setText(s2);
		}
		
	};*/
	
	//String data[] = DatabaseFunction.findInfo(name, pw)
	btn = (Button) this.findViewById(R.id.s_setup);
	btn.setOnClickListener(new OnClickListener(){
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(Showsetup.this,SetupActivity.class);
			startActivity(intent);
		}
	});
	}
	
	class DatabaseFunction{

		private Statement stmt;
		private Connection con;
		//private String s = "00";
		
		//@Override
		public void show()throws ClassNotFoundException, SQLException{
			
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
			String d_ip = Login.database_ip;
	        String d_username = "sa";
	        String d_pw = "123";
            String dbDriver = "net.sourceforge.jtds.jdbc.Driver";
            Connection con=null;
            Class.forName(dbDriver);
			Log.e("MSSQL", "Attempting to connect");
			String dbUrl= "jdbc:jtds:sqlserver://"+d_ip+":1433/SMS"; 
			con =DriverManager.getConnection(dbUrl, d_username, d_pw);
			Log.e("MSSQL", "Connected");
			stmt = con.createStatement();
			
			
			 
			int result = 0;
			String name = "'"+Login.user_name+"'";
			String pw = "'"+Login.user_pw+"'";
			//String music = "Music = '"+s+"'";
			String sql = "SELECT * FROM UserFile WHERE Username = "+name+" and Password = "+pw;

			String s[] = new String[2];
			int istrue = FALSE;
			try{
				//getConnection();
				ResultSet rs = stmt.executeQuery(sql); 
				
				if(rs.next()){
					istrue = TRUE;
				s[0] = rs.getString("Light");
				s[1] = rs.getString("Music");}
				Log.e("MSSQL", "find");
				
				//String sql2 = "UPDATE UserFile SET "+music+"WHERE Username = "+name+" and Password = "+pw;
				Log.e("MSSQL", s[0]);
				   // int result1 =0 ;
				   // result = stmt.executeUpdate(sql2);
				Log.e("MSSQL", s[1]);  
				
				con.close();
			    
				
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("MSSQL", e.toString());
			}     
			/*if(result==1)
				mhandler.obtainMessage(TRUE).sendToTarget();
			else
				mhandler.obtainMessage(FALSE).sendToTarget();*/
			
			//mhandler.obtainMessage(Integer.parseInt(s[0].trim())*100+Integer.parseInt(s[1].trim())).sendToTarget();
		}
		
	}	
	
}
