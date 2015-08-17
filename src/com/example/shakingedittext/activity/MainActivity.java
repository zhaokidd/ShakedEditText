package com.example.shakingedittext.activity;

import com.example.shakingedittext.R;
import com.example.shakingedittext.view.DeletableEditText;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends Activity {
	/**
	 * 输入密码
	 * */
	private DeletableEditText et_password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	/**
	 * 
	 * init view
	 * */
	private void initView() {
		et_password = (DeletableEditText) findViewById(R.id.et_password);
		et_password.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				et_password.setShakeAnimation();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
