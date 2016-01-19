package com.softorg.rock.timer.util;

import android.content.Context;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GeneralUtil
{
	private GeneralUtil()
	{
	}

	private static Toast mCurrentToast;
	
	/**
	 * @Fields ONLINE_TIME : ��ѯʱ���ڸ�ʱ����δ�ϴ�������ж�Ϊ����
	 */
	public static final long OFFLINE_TIME = 60000;

	/**
	 * @Fields TIME_DIF_UTC : ��������α�׼ʱ��������
	 */
	public static final long TIME_DIF_UTC = 8 * 60 * 60 * 1000;

	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String UTF8Encode(String origin)
	{
		String result = null;
		try
		{
			result = URLEncoder.encode(origin, "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public static final void showToast(Context context, CharSequence text,
									   int duration)
	{
		if (mCurrentToast != null)
		{
			mCurrentToast.cancel();
		}
		mCurrentToast = Toast.makeText(context, text, duration);
		mCurrentToast.show();
	}

	public static final void showToast(Context context, int resId, int duration)
	{
		showToast(context, context.getResources().getText(resId), duration);
	}

}
