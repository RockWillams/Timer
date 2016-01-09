package com.softorg.rock.timer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.softorg.rock.timer.R;
import com.softorg.rock.timer.adapter.WearListAdapter;
import com.softorg.rock.timer.bean.MyItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity  implements WearableListView.ClickListener,GestureDetector.OnGestureListener{

    private WearableListView mListView;
    private float x1,x2;
    private float y1,y2;


    final int RIGHT = 0;
    final int LEFT = 1;
    private static final int FLING_MIN_DISTANCE = 50;
    private static final int FLING_MIN_VELOCITY = 0;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "进入oncreate", Toast.LENGTH_SHORT).show();
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mListView = (WearableListView) stub.findViewById(R.id.list);
                mListView.setAdapter(new WearListAdapter(getBaseContext(), getData()));
                mListView.setClickListener(MainActivity.this);
                mListView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_UP){
                            Toast.makeText(MainActivity.this, "进入list的enent", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
            }
        });
        gestureDetector = new GestureDetector(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            x1 = event.getX();
            y1 = event.getY();
            Toast.makeText(MainActivity.this, "按下", Toast.LENGTH_SHORT).show();
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            x2 = event.getX();
            y2 = event.getY();

            if(y1 - y2 > 50) {
                Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
            } else if(y2 - y1 > 50) {
                Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
            } else if(x1 - x2 > 50) {
                Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
            } else if(x2 - x1 > 50) {
                Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
            }
        }


        super.onTouchEvent(event);
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // TODO Auto-generated method stub
        int ix= 0;
        if (e1.getX()-e2.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            // Fling left
            Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();
        } else if (e2.getX()-e1.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            // Fling right
            Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onClick(WearableListView.ViewHolder v) {
       v.getItemId();
        Intent mintent = new Intent();
        mintent.setClass(MainActivity.this, DetailNotif_.class);
        startActivity(mintent);
    }

    @Override
    public void onTopEmptyRegionClick() {

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
