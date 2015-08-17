package com.example.shakingedittext.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

/**
 * An EditText which can shake
 * 
 * @author zy
 * */
public class DeletableEditText extends EditText {
	private Drawable mRightDrawable;
	private boolean isHasFocus;

	public DeletableEditText(Context context) {
		super(context);
	}

	public DeletableEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DeletableEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		Drawable[] drawables = this.getCompoundDrawables();

		// 取得右部的drawable
		mRightDrawable = drawables[2];

		// 设置焦点变化监听
		this.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				isHasFocus = hasFocus;
				if (hasFocus) {
					boolean isVisible = getText().toString().length() >= 1;
					setClearDrawableVisible(isVisible);
				} else {
					setClearDrawableVisible(false);
				}
			}
		});

		//
		this.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.length() >= 1) {
					setClearDrawableVisible(true);
				} else {
					setClearDrawableVisible(false);
				}
			}
		});

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case KeyEvent.ACTION_UP: {
			boolean isClean = (event.getX() > (getWidth() - getTotalPaddingRight()))
					&& (event.getX() > (getWidth() - getPaddingRight()));
			if (isClean) {
				// 将文本框清空
				this.setText("");
			}
		}
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	// 隐藏lear图标
	protected void setClearDrawableVisible(boolean isVisible) {
		Drawable rightDrawable;
		if (isVisible) {
			rightDrawable = mRightDrawable;
		} else {
			rightDrawable = null;
		}
		this.setCompoundDrawables(getCompoundDrawables()[0],
				getCompoundDrawables()[1], rightDrawable,
				getCompoundDrawables()[3]);
	}

	/**
	 * 开启摇动动画
	 * */
	public void setShakeAnimation() {
		this.startAnimation(shakeAnimation(5));
		this.setAnimation(shakeAnimation(5));
	}

	public Animation shakeAnimation(int cycleTimes) {
		Animation transAnimation = new TranslateAnimation(0, 10, 0, 10);
		transAnimation.setInterpolator(new CycleInterpolator(cycleTimes));
		transAnimation.setDuration(100);
		return transAnimation;
	}
}
