package com.softorg.rock.timer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.view.View;

import com.softorg.rock.timer.R;
import com.softorg.rock.timer.adapter.WearListAdapter;
import com.softorg.rock.timer.bean.MyItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity   {

    private WearableListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mListView = (WearableListView) stub.findViewById(R.id.list);
                mListView.setAdapter(new WearListAdapter(getBaseContext(), getData()));
                mListView.setClickListener(new WearableListView.ClickListener() {
                    @Override
                    public void onClick(WearableListView.ViewHolder viewHolder) {
                        if(View.GONE ==  findViewById(R.id.history).getVisibility()){
                            findViewById(R.id.history).setVisibility(View.VISIBLE);
                            findViewById(R.id.voice).setVisibility(View.VISIBLE);
                            findViewById(R.id.sample).setVisibility(View.VISIBLE);
                        }else {
                            findViewById(R.id.history).setVisibility(View.GONE);
                            findViewById(R.id.sample).setVisibility(View.GONE);
                            findViewById(R.id.voice).setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onTopEmptyRegionClick() {

                    }
                });

                findViewById(R.id.history).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
                        startActivity(intent);
                    }
                });
                findViewById(R.id.voice).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                findViewById(R.id.sample).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
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
