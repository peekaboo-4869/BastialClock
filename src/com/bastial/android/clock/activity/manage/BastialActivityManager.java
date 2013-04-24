package com.bastial.android.clock.activity.manage;

import java.util.Stack;

import android.app.Activity;
import android.util.Log;

/**
 * 
 * @author Peekaboo
 * 
 */
public class BastialActivityManager extends Stack<Activity> {
	private static final long serialVersionUID = 1L;
	private final String TAG = "BastialActivityManager";
	private static BastialActivityManager mInstance = null;
	private Stack<Activity> mActivityManager = null;

	private BastialActivityManager() {
		mActivityManager = new Stack<Activity>();
	}

	/**
	 * ��ȡ��ǰʵ��
	 * @return
	 */
	public static BastialActivityManager getInstance() {
		if (mInstance == null)
			mInstance = new BastialActivityManager();
		return mInstance;
	}

	/**
	 * ��ȡ��ǰջ����Activity
	 * @return ջ����Activity
	 */
	public synchronized Activity peekActivity() {
		Log.d(TAG, "peekActivity");
		return mActivityManager.peek();
	}

	/**
	 * ��ջ�е���ջ����Activity
	 * 
	 * @return Activity ���ܷ���null��������Ҫ��һ��null���
	 */
	public synchronized void popTopActivity() {
		if (!mActivityManager.isEmpty()) {
			Activity activity = mActivityManager.pop();
			if (activity != null) {
				activity.finish();
			}
		}
	}

	/**
	 * ��ջ�е���ָ����Activity
	 * 
	 * @return Activity ���ܷ���null��������Ҫ��һ��null���
	 */
	public synchronized void popActivity(Activity activity) {
		if (!mActivityManager.isEmpty()) {
			if (hasActivity(activity)) {
				mActivityManager.remove(activity);
			}
			if (activity != null) {
				activity.finish();
			}
		}
	}

	/**
	 * ��Activityѹ��ջ��
	 * @param activity
	 */
	public synchronized void pushActivity(Activity activity) {
		Log.d(TAG, "pushActivity");
		if (!hasActivity(activity)) {
			mActivityManager.push(activity);
		}
	}

	/**
	 * �ж�һ��Activity�Ƿ��Ѿ�����ջ��
	 * @param activity Ҫ����Activity
	 * @return �������
	 */
	private boolean hasActivity(Activity activity) {
		Log.d(TAG, "hasActivity");
		for (Activity mActivity : mActivityManager) {
			if (mActivity.getClass().equals(activity.getClass())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ��õ�ǰջ��Activity
	 * 
	 * @return ��ǰλ��ջ����Activity
	 */
	public Activity currentActivity() {
		Activity activity = null;
		if (!mActivityManager.empty())
			activity = mActivityManager.peek();
		return activity;
	}

	/**
	 * ����ջ�����е�Activity
	 */
	public void popAllActivity() {
		Log.d(TAG, "popAllActivity");
		while (mActivityManager.size() > 0) {
			Activity mainActivity = mActivityManager.peek();
			mActivityManager.remove(mainActivity);
			while (!mActivityManager.isEmpty()) {
				Activity activity = mActivityManager.peek();
				if (activity.getClass().equals(mainActivity.getClass())) {
					break;
				}
				
				assert(activity != null);
				if (activity != null) {
					activity.finish();
				}
				mActivityManager.remove(activity);
				activity = null;
			}
			if (mainActivity != null) {
				mainActivity.finish();
			}
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}
}
