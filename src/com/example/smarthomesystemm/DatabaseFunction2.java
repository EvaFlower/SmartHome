package com.example.smarthomesystemm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.util.Log;

public class DatabaseFunction2{

	static Statement stmt;
	static Connection con;
	
	
	private static void getConnection() throws ClassNotFoundException, SQLException{
		//STEP 2: Register JDBC driver 
		String dbDriver = "net.sourceforge.jtds.jdbc.Driver";
		
		Class.forName(dbDriver); 
		Connection con=null;
		Log.e("MSSQL", "Attempting to connect");
		String dbUrl= "jdbc:jtds:sqlserver://10.21.3.202:1433/SMS"; 
		con =DriverManager.getConnection(dbUrl,"sa","123");
		Log.e("MSSQL", "Connected");
		stmt = con.createStatement(); 
	}
	public static String[] findInfo(String name, String pw){

        String data[] = new String[2];
        try {
			getConnection();
			String sql; 
		    sql = "SELECT * FROM UserFile Where Username = "+name+" and Password = "+pw;
			
			ResultSet rs;
			rs = stmt.executeQuery(sql);


	        while(rs.next()){
	            data[0] = rs.getString("Light");
	            data[1] = rs.getString("Music");
	        }
	        con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e("MSSQL", e.toString());
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.e("MSSQL", e.toString());
			e.printStackTrace();
		}
        
        return data;
	}
	public static boolean findUser(String name, String pw){
		
		String sql = "SELECT * FROM UserFile WHERE Username = "+name+" and Password = "+pw;
		
		ResultSet rs;
		boolean result = false;
		try{
			getConnection();
			result = stmt.execute(sql); 
			String sql2 = "INSERT INTO UserFile (Username, Password, Light, Music) "
					+ "VALUES ('a', 's', NULL, NULL)";
			
			    int result1 =0 ;
			    result1 = stmt.executeUpdate(sql2);
			    if(result1==1){
			    	Log.e("MSSQL", "SUCCESS");
			    	
			    }
			    else{
			    	Log.e("MSSQL", "FAIL");
			    }
			    //MainActivity.txt.setText(result);
			con.close();
		    Log.e("MSSQL", "close");
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("MSSQL", e.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("MSSQL", e.toString());
		}     
		if(result==true)
			Login.mhandler.obtainMessage(1).sendToTarget();
		else
			Login.mhandler.obtainMessage(0).sendToTarget();
		return result;	
	}
	//public static 
	public static String checkUser(String name, String pw){

       
	    String sql; 
		
		String queryUser = name;
		String queryPw = pw;

	    sql = "SELECT * FROM UserFile";
		
		ResultSet rs;
		String users[] = null;
        String pws[] = null;
		try {
			getConnection();
			rs = stmt.executeQuery(sql);
			int j=0;
			while(rs.next()){
	            users[j++] = rs.getString("Username");
	            pws[j++] = rs.getString("Password");
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("MSSQL", e.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("MSSQL", e.toString());
		}     
		for(int i=0;i<users.length;i++){
			
			if(users[i].equals(name)){

				if(pws[i].equals(pw)){
					
					return "true";
					
				}
				else{
					
					return "密码错误";
					
				}				
			}		
		}
		
		return "无此用户";

	}
	public static String addUser(String name, String pw){

        try {
			getConnection();
			if(checkUser(name,pw).equals("无此用户")){
			    
				String sql = "INSERT INTO UserFile (Username, Password) "
					+ "VALUES (" + name + "," + pw +")";
			
			    int result = stmt.executeUpdate(sql);
			    if(result==0){
			        con.close();
				    return "注册失败";
			    }
			    else{
			    	con.close();
				    return "注册成功";
			    }
	            
		    }
			     
			else{
				
				con.close();
				return "用户已存在";	
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e("MSSQL", e.toString());
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.e("MSSQL", e.toString());
			e.printStackTrace();
		}
	    return "数据库连接失败";
	}
	public static int updateLight(String name, String pw, String light){

		int result = -1;
        try {
			getConnection();
			String sql = "UPDATE UserFile Set Light = "+light+" Where Username = "+ name  +" and Password = "+ pw;
	        result = stmt.executeUpdate(sql);
	        con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e("MSSQL", e.toString());
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.e("MSSQL", e.toString());
			e.printStackTrace();
		}
        
        return result;
	}
	public static int updateMusic(String name, String pw, String music){

		int result = -1;
        try {
			getConnection();
			String sql = "UPDATE UserFile Set Music = "+music+" Where Username = "+ name  +" and Password = "+ pw;
	        result = stmt.executeUpdate(sql);
	        con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e("MSSQL", e.toString());
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.e("MSSQL", e.toString());
			e.printStackTrace();
		}
        
        return result;
	}
	
	
}
