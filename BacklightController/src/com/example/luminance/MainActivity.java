package com.example.luminance;

import com.example.luminanceCtrl.luminanceCtrl;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	private SeekBar seekbar;
	private CheckBox checkbox;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		seekbar = (SeekBar) findViewById(R.id.seekBar1);
		checkbox = (CheckBox) findViewById(R.id.checkBox1);
		
		if(luminanceCtrl.isAutoBrightness(getContentResolver())){
			checkbox.setChecked(true);
		}else{
			checkbox.setChecked(false);			
		}
		
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					luminanceCtrl.startAutoBrightness(MainActivity.this);
				}
				else{
					luminanceCtrl.stopAutoBrightness(MainActivity.this);
				}
			}
		});
		
		int progress = luminanceCtrl.getScreenBrightness(this);
		seekbar.setProgress(progress);
		//设置拖动条改变监听器
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				luminanceCtrl.setBrightness(MainActivity.this, progress);
				luminanceCtrl.saveBrightness(getContentResolver(), progress);
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub	
				
				//System.out.println("onStopTrackingTouch");
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub	
				//System.out.println("onStartTrackingTouch");
			}
		});
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		System.out.println("onStop!!!");
		finish();
		super.onStop();
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		System.out.println("onPause!!!");
		
		super.onPause();
		
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		System.out.println("onRestart!!!");
		super.onRestart();
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		System.out.println("onResume!!!");
		super.onResume();
		
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		System.out.println("onStart!!!");
		super.onStart();
	}
}
