package com.example.smarthomesystemm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/*此项目的入口，即APP进入的开始界面*/

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Handler().postDelayed(new Runnable() {  
            public void run() {  
                /* Create an Intent that will start the Main WordPress Activity. */  
                Intent mainIntent = new Intent(MainActivity.this, Login.class);  
                MainActivity.this.startActivity(mainIntent);  
                MainActivity.this.finish();  
            }  
        }, 6000); 
	}
	
}