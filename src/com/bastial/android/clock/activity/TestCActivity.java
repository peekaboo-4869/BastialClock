package com.bastial.android.clock.activity;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author Peekaboo
 *
 */
public class TestCActivity extends BastialBaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Button button = new Button(this);
		button.setText("�����ײ��˵����Ĳ��֣��̳е��಻һ����");
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});
		setContentView(button);
	}
}
