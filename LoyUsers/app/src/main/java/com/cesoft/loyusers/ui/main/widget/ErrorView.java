package com.cesoft.loyusers.ui.main.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.cesoft.loyusers.R;


/**
 * Created by ccasanova on 12/02/2018
 */
public class ErrorView extends RelativeLayout {

	public interface ErrorListener {
		void onTryAgainClicked();
	}

	public ErrorListener errorListener;
	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.view_error, this);
		Button butTryAgain = findViewById(R.id.button_try_again);
		butTryAgain.setOnClickListener(view -> {
			if(errorListener!=null)errorListener.onTryAgainClicked();
		});
	}


	public ErrorView(Context context) {
		super(context);
		init(context);
	}

	public ErrorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public ErrorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context);
	}
}



