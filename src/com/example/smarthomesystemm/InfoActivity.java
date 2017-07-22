package com.example.smarthomesystemm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*选择智能模式或手动模式界面功能*/

public class InfoActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_activity);
		Button btn1 = (Button) findViewById(R.id.inforadiobtn);
		btn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				new Thread(new Runnable(){
					@Override
						public void run(){
							Send_to_System.send("00");
						}
						
					}
				).start();
				Intent intent = new Intent();
				intent.setClass(InfoActivity.this, Showsetup.class); //control 改下！
				startActivity(intent);
				
			}
			
		});
		Button btn2 = (Button) findViewById(R.id.inforadiobtn2);
		btn2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    /*AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);  
				builder.setMessage("已选择为实时控制模式")  
				       .setCancelable(false)  
				       .setPositiveButton("确定", new DialogInterface.OnClickListener() {  
				           public void onClick(DialogInterface dialog, int id) {  
				                dialog.dismiss();  
				                Intent intent = new Intent();
								intent.setClass(InfoActivity.this, Showsetup.class);
								startActivity(intent);
				           }  
				       });  
				AlertDialog dialog = builder.create();
			    dialog.show();*/
				
				new Thread(new Runnable(){
					@Override
						public void run(){
							Send_to_System.send("01");
						}
						
					}
				).start();
				Intent intent = new Intent();
				intent.setClass(InfoActivity.this,  ControlActivity.class); //control 改下！
				startActivity(intent);
			}
			
		});
	}

}
