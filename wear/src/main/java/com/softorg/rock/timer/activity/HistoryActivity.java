package com.softorg.rock.timer.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;

import com.softorg.rock.timer.R;
import com.softorg.rock.timer.Utils.CustomBuilder;
import com.softorg.rock.timer.adapter.WearListAdapter;
import com.softorg.rock.timer.adapter.WearListHistoryAdapter;
import com.softorg.rock.timer.bean.MyItem;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends Activity {
    WatchViewStub stub;
    WearableListView mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        stub = (WatchViewStub)findViewById(R.id.watch_view_stub_history);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub watchViewStub) {

                WearableListView mList  = (WearableListView) findViewById(R.id.list_history);
                mList.setAdapter(new WearListHistoryAdapter(getBaseContext(),getData()));
            }
        });
    }



    private List<MyItem> getData() {
        // 新建一个集合类，用于存放多条数据
        ArrayList<MyItem> list = new ArrayList<MyItem>();
        //HashMap<String, Object> map = null;
        for (int i = 1; i <= 40; i++) {
            MyItem  map =new MyItem(R.mipmap.sun,"2015年3月21号","13:30","content","leftTime","notification");
            list.add(map);
        }
        return list;
    }
}
