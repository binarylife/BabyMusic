package com.beifeng.babymusic.util;

import android.widget.Toast;
import com.beifeng.babymusic.BabyApplication;

public class ToastUtils {

	private static Toast mToast;

	public static void showLong(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(BabyApplication.getApplication(), text, Toast.LENGTH_LONG);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

	public static void showShort(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(BabyApplication.getApplication(), text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

}
