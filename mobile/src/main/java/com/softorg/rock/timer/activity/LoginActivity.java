package com.softorg.rock.timer.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.entity.User;
import com.entity.UserService;
import com.softorg.rock.timer.R;
import com.softorg.rock.timer.util.DbSync;
import com.softorg.rock.timer.util.DbUtil;
import com.softorg.rock.timer.util.GeneralUtil;
import com.softorg.rock.timer.util.NetworkUtil;
import com.softorg.rock.timer.util.RockApplication;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;
import org.json.JSONException;
import org.json.JSONObject;



@WindowFeature(
{ Window.FEATURE_NO_TITLE })
@Fullscreen
@EActivity(R.layout.aty_login)
public class LoginActivity extends Activity
{
	@ViewById(R.id.username)
	EditText usernameET;

	@ViewById(R.id.password)
	EditText passwordET;

	private UserService userService;

	private RequestQueue mQueue;

	private ProgressDialog pd;

	@Bean
	DbSync dbSync;



	@AfterInject
	void init()
	{
		mQueue = NetworkUtil.getVolleyRequestQueue(this);
		userService= DbUtil.getUserService();
	}

	@Click
	void login()
	{
//		LoginActivity.this.startActivity(LoginActivity.intent(LoginActivity.this).get());
		final String username = getUsername();
		final String password = getPassword();
		if (username != null && !"".equals(username) && password != null)
		{
			pd = ProgressDialog.show(this, null, getResources().getString(R.string.aty_login_progress_dialog), false, true);
			String url = NetworkUtil.HTTP_SERVER + NetworkUtil.SERVER_PAGE_LOGIN +"?name="+username+"&password="+password;
			mQueue.add(new StringRequest(Method.GET, url,new Response.Listener<String>()
			{
				@Override
				public void onResponse(String response)
				{
					pd.dismiss();
					JSONObject jObject;
					try
					{
						jObject = new JSONObject(response);
						JSONObject status = jObject.getJSONObject("status");// （0，成功；1，用户名不存在；2，密码错误）
						if ("Success".equals(status.getString("reason")))
						{
							User user  = new User();
							String key = jObject.getJSONObject("result").getString("key");
							user.setKey(key);
							user.setName(username);
							user.setPassword(password);
							user.setStatus("1");//online
							userService.save(user);
							((RockApplication)getApplication()).setUser(user);


							GeneralUtil.showToast(LoginActivity.this, R.string.aty_login_success, Toast.LENGTH_SHORT);
							LoginActivity.this.startActivity(new Intent(LoginActivity.this,MainActivity.class));
							LoginActivity.this.finish(); // TODO 进入主页面，存储userid

							//dao.setUserid(userid);
						} /*else if ("1".equals(code))
						{
							GeneralUtil.showToast(LoginActivity.this, R.string.aty_login_error_username, Toast.LENGTH_LONG);
						} else if ("2".equals(code))
						{
							GeneralUtil.showToast(LoginActivity.this, R.string.aty_login_error_password, Toast.LENGTH_LONG);
						} // TODO 其它错误*/
					} catch (JSONException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}
			}, new Response.ErrorListener()
			{

				@Override
				public void onErrorResponse(VolleyError error)
				{
					pd.dismiss();

					GeneralUtil.showToast(LoginActivity.this, R.string.common_error_net + error.getCause().toString(), Toast.LENGTH_LONG);
				}
			})
			{

			/*	@Override
				protected Map<String, String> getParams() throws AuthFailureError
				{
					Map<String, String> map = new HashMap<String, String>();
					map.put("username", GeneralUtil.UTF8Encode(username));
					map.put("password", SecurityUtil.MD5Encode(password));
					return map;
				}*/
			});

		} else
		{
			GeneralUtil.showToast(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_LONG);
		}

	}

	private String getUsername()
	{
		return usernameET.getText().toString();
	}

	private String getPassword()
	{
		return passwordET.getText().toString();
	}
}
