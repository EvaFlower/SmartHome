package com.example.smarthomesystemm;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/*智能设置主界面功能 */

public class SetupActivity extends Activity{

	private RadioGroup setupG;
	private RadioGroup modeG;
	private String tabs[] = {"个人设置","为他人设置","权限设置"};
	private String tabs2[] = {"灯光模式","音乐模式"};
	private String tags[] = {"person1", "person2","others1","others2", "autho1","autho2"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setup);
		showOverflowMenu();
		//初始化设置底栏
		setupG = (RadioGroup)findViewById(R.id.setupGroup);
		setupG.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId){
				switch(checkedId){
				
				case R.id.personalset:
					switchAllSupport(tabs[0]); 
					break;
				case R.id.otherset:
				    switchAllSupport(tabs[1]);
				    break;
				case R.id.authoset:
			        switchAllSupport(tabs[2]);
			        break;	
				}
			}
		});
		//默认选中最左边的radiobutton 
		RadioButton btn = (RadioButton)setupG.getChildAt(0);
		btn.toggle();
		
	}
	//菜单栏
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
			
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		case R.id.quit:
			finish();
			break;
		case R.id.change:
			//退出当前账号登陆
			Intent intent = new Intent();
			intent.setClass(SetupActivity.this, Login.class);
			startActivity(intent);
			break;
		}
		return true;
	}
	private void showOverflowMenu(){
		
		try{
			//得到当前Activity的ViewConfiguration配置
			//溢出菜单通过ViewConfiguration进行设置
			ViewConfiguration config = ViewConfiguration.get(this);
			//sHasPermanentMenuKey是ViewConfiguration类的私有成员
			//这里通过反射机制获取ViewConfiguration的这个私有成员
			java.lang.reflect.Field menuKeyField = ViewConfiguration.class.getDeclaredField
					("sHasPermanentMenuKey");
			if(menuKeyField!=null){
				
				//解除ViewConfiguration类中对sHasPermanentMenuKey的private修饰，
				//虚拟机将放宽对这个成员变量的限制
				menuKeyField.setAccessible(true);
				//再通过menuKeyField修改sHasPermanetMenuKey的值
				menuKeyField.setBoolean(config, false);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void switchAllSupport(final String tag){
		
		if(tag.equals(tabs[0])){
			switchSupport(tabs[0],0);
		}
		else if(tag.equals(tabs[1])){
			switchSupport(tabs[1],2);
		}
		else if(tag.equals(tabs[2])){
			switchSupport(tabs[2],4);
		}
	}
	public void switchSupport(final String tag,final int i){  //why final
		
		switchFragmentSupport(R.id.content, tag, tabs2[0],tags[i]);
		modeG = (RadioGroup)findViewById(R.id.modeGroup);
		modeG.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			
			public void onCheckedChanged(RadioGroup group, int checkedId){
				switch(checkedId){
				case R.id.c_light:
					switchFragmentSupport(R.id.content, tag, tabs2[0],tags[i]);
					break;
				case R.id.music:
					switchFragmentSupport(R.id.content, tag, tabs2[1],tags[i+1]);
				}
			}
		});
		RadioButton btn = (RadioButton)modeG.getChildAt(0);
		btn.toggle();
	}
	
	public void switchFragmentSupport(int containerId, String tag, String tag2,String t){
		
		
		//获取FragmentManager管理器
		FragmentManager manager = getFragmentManager();
		//根据tag标签名查找是否已经存在对应的Fragment对象
		Fragment destFragment = manager.findFragmentByTag(t);
		//如果tag标签对应的Fragment对象不存在，则初始化它
		if(destFragment == null){
			if(tag.equals(tabs[0])){
				if(tag2.equals(tabs2[0])){
				    destFragment = new Person();
				}
				else{
					destFragment = new Person2();

				}
			}
			else if(tag.equals(tabs[1])){
				if(tag2.equals(tabs2[0])){
				    destFragment = new Others();

				}
				else{
					destFragment = new Others2();

				}
			}
			else if(tag.equals(tabs[2])){
				if(tag2.equals(tabs2[0])){
				    destFragment = new Authority();

				}
				else{
					destFragment = new Authority2();

				}
			}
		}
		//获取FragmentTransaction事务对象
		FragmentTransaction ft = manager.beginTransaction();
		//将组件id为contanerId的内容替换成destFragment， 并把destFragment的标签设为t的值
		ft.replace(containerId, destFragment,t);
		//下面代码是设置Fragment切换效果
		ft.setTransition(FragmentTransaction.TRANSIT_NONE);
		ft.commit();
	}
	
	
	
}
