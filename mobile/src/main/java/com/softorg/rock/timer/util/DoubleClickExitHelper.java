package com.softorg.rock.timer.util;

import android.content.Context;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Toast;

import static com.softorg.rock.timer.util.GeneralUtil.showToast;

/** 
 * @ClassName: DoubleClickExitHelper 
 * @Description: ˫���˳�
 * @author: Psyche
 * @date: 2015��9��25��
 */
public class DoubleClickExitHelper
{
	/** 
	 * @Fields exitTime : ˫�����ʱ��
	 */ 
	private long exitTime = 2000;
	
	private boolean mIsExit = false;
	private Context mContext;

	public DoubleClickExitHelper(Context context)
	{
		mContext = context;
	}

	/**
	 * @Title: onKeyDown
	 * @Description: ˫���˳�
	 * @return: boolean
	 * @param keyCode
	 * @author: Psyche
	 * @date: 2015��9��25��
	 */
	public boolean onKeyDown(int keyCode)
	{
		if(keyCode != KeyEvent.KEYCODE_BACK)
			return false;
		if (mIsExit)
		{
			System.exit(0);
		} else
		{
			mIsExit = true;
			showToast(mContext, "�ٰ�һ���˳�Ӧ��", Toast.LENGTH_SHORT);
			new Handler().postDelayed(new Runnable()
			{

				@Override
				public void run()
				{
					mIsExit = false;
				}
			}, exitTime);
		}
		return true;
	}
}
