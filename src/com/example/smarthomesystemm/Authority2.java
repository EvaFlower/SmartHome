package com.example.smarthomesystemm;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/*音乐设置的权限设置功能*/

public class Authority2 extends Fragment{

	private View layoutView;
	private RadioGroup radiogroup;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 加载person.xml布局
		layoutView = inflater.inflate(R.layout.authority2,null);
		radiogroup = (RadioGroup)layoutView.findViewById(R.id.AradioGroup);
		radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId){
				switch(checkedId){
				case R.id.auacceptrbtn:
				    layoutView.findViewById(R.id.AcheckBox1).setVisibility(View.VISIBLE);
				    layoutView.findViewById(R.id.AcheckBox2).setVisibility(View.VISIBLE);
				    layoutView.findViewById(R.id.AcheckBox3).setVisibility(View.VISIBLE);
				    layoutView.findViewById(R.id.AeditText1).setVisibility(View.VISIBLE);
				    layoutView.findViewById(R.id.AtextView1).setVisibility(View.VISIBLE);
				    layoutView.findViewById(R.id.AtextView2).setVisibility(View.VISIBLE);
				    break;
				case R.id.auunacceptrbtn:
				    layoutView.findViewById(R.id.AcheckBox1).setVisibility(4);
				    layoutView.findViewById(R.id.AcheckBox2).setVisibility(4);
				    layoutView.findViewById(R.id.AcheckBox3).setVisibility(4);
				    layoutView.findViewById(R.id.AeditText1).setVisibility(4);
				    layoutView.findViewById(R.id.AtextView1).setVisibility(4);
				    layoutView.findViewById(R.id.AtextView2).setVisibility(4);
				    break;
				}
			}
		});
		Button btn = (Button)layoutView.findViewById(R.id.setbtn);
		btn.setOnClickListener(new OnClickListener(){

			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int select = radiogroup.getCheckedRadioButtonId();
				if(select==-1){
					builder.setTitle("请设置后再按确定键！");
					AlertDialog dialog = builder.create();
					dialog.show();
				}
				else if(select==R.id.auunacceptrbtn){
					builder.setTitle("已设置为不接受他人设置！");
					AlertDialog dialog = builder.create();
					dialog.show();
				}
				else if(select==R.id.auacceptrbtn){
					builder.setTitle("已设置为接收他人设置！");
					AlertDialog dialog = builder.create();
					dialog.show();
				}
			}
		});
		return layoutView;
	}
}
