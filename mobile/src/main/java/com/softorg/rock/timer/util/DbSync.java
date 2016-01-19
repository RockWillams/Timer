package com.softorg.rock.timer.util;

import android.app.Application;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.entity.User;
import com.entity.Version;
import com.entity.VersionDao;
import com.entity.VersionService;
import com.softorg.rock.timer.R;
import com.softorg.rock.timer.activity.MainActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.greenrobot.dao.query.Query;

/**
 * Created by Administrator on 2016/1/16.
 */
@EBean
public class DbSync {

    private Context context;

    private VersionService versionService;
    private RequestQueue mQueue;
    private Version localVersion;
    private User user;
    RockApplication application;
    List<Version> versionList;


    public void  DbSync(Context context, RockApplication application){
        this.context = context;
        mQueue = NetworkUtil.getVolleyRequestQueue(this.context);
        versionService = DbUtil.getVersionService();
        this.application = application;
        Query query = versionService.queryBuilder().where(VersionDao.Properties.Status.eq("1")).build();
        if(query.list().size()!=0){
           localVersion = (Version) query.list().get(0);
        }
        else localVersion.setLatestVersion("0");


    }

    public void init(RockApplication application){

        String url = NetworkUtil.HTTP_SERVER + NetworkUtil.SERVER_PAGE_VERSION +"?key="+application.getUser().getKey()+"&"+"clientversion="+localVersion.getLatestVersion();
        mQueue.add(new StringRequest(Request.Method.GET,url,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {

                JSONObject jObj;
                try
                {
                    jObj = new JSONObject(response);
                    JSONObject status = jObj.getJSONObject("status");// （0，成功；1，用户名不存在；2，密码错误）
                    if("Success".equals(status.getString("reason")))
                    {
                        String lastest_version = jObj.getJSONObject("result").getString("lastest_version");

                        if(localVersion.equals("0")){
                            //cache and insert into database


                            //show

                            return;
                        }else if(lastest_version.equals( localVersion)){
                            //TODO
                        }else if(Double.valueOf(lastest_version.toString()) > Double.valueOf(localVersion.toString())){
                            Version version  = new Version();

                        }else if(Double.valueOf(lastest_version.toString()) < Double.valueOf(localVersion.toString())) {



                        }


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
        },new Response.ErrorListener(){


            @Override
            public void onErrorResponse(VolleyError error)
            {
                //pd.dismiss();

               // GeneralUtil.showToast(LoginActivity.this, R.string.common_error_net + error.getCause().toString(), Toast.LENGTH_LONG);
            }

        }));
    }







}
