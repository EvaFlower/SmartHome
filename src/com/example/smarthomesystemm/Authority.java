package com.example.smarthomesystemm;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/*灯光设置的权限设置功能*/

public class Authority extends Fragment{

	private View layoutView;
	private RadioGroup radiogroup;
	private RadioButton rb1, rb2;
	private CheckBox cb;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 加载person.xml布局
		layoutView = inflater.inflate(R.layout.authority,null);
		radiogroup = (RadioGroup)layoutView.findViewById(R.id.AradioGroup2);
		radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId){
				switch(checkedId){
				case R.id.auacceptrbtn2:
				    layoutView.findViewById(R.id.AcheckBox12).setVisibility(0);
				    layoutView.findViewById(R.id.AcheckBox22).setVisibility(0);
				    layoutView.findViewById(R.id.AcheckBox32).setVisibility(0);
				    layoutView.findViewById(R.id.AeditText12).setVisibility(0);
				    layoutView.findViewById(R.id.AtextView12).setVisibility(0);
				    layoutView.findViewById(R.id.AtextView22).setVisibility(0);
				    break;
				case R.id.auunacceptrbtn2:
				    layoutView.findViewById(R.id.AcheckBox12).setVisibility(4);
				    layoutView.findViewById(R.id.AcheckBox22).setVisibility(4);
				    layoutView.findViewById(R.id.AcheckBox32).setVisibility(4);
				    layoutView.findViewById(R.id.AeditText12).setVisibility(4);
				    layoutView.findViewById(R.id.AtextView12).setVisibility(4);
				    layoutView.findViewById(R.id.AtextView22).setVisibility(4);
				    break;
				}
			}
		});
		Button btn = (Button)layoutView.findViewById(R.id.setbtn2);
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
				else if(select==R.id.auunacceptrbtn2){
					builder.setTitle("已设置为不接受他人设置！");
					AlertDialog dialog = builder.create();
					dialog.show();
				}
				else if(select==R.id.auacceptrbtn2){
					builder.setTitle("已设置为接收他人设置！");
					AlertDialog dialog = builder.create();
					dialog.show();
				}
			}
		});
		return layoutView;
	}
}
