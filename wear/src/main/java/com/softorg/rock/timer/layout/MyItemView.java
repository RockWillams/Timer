package com.softorg.rock.timer.layout;

import android.content.Context;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import com.softorg.rock.timer.R;

/**
 * Created by Rock on 2016/1/7.
 */
public final class MyItemView extends RelativeLayout implements WearableListView.OnCenterProximityListener {

     ImageView icon;
     TextView notification;
     TextView date;
     TextView time;
     TextView  content;
     TextView leftTime;


    public MyItemView(Context context) {
        super(context);
        View.inflate(context, R.layout.list_item, this);
        icon = (ImageView) findViewById(R.id.icon);
        notification = (TextView) findViewById(R.id.notification);
        time = (TextView) findViewById(R.id.time);
        date = (TextView) findViewById(R.id.date);
        content = (TextView) findViewById(R.id.content);
        leftTime = (TextView) findViewById(R.id.leftTime);

    }




    @Override
    public void onCenterPosition(boolean b) {

        //Animation example to be ran when the view becomes the centered one
        icon.animate().scaleX(1f).scaleY(1f).alpha(1);
        notification.animate().scaleX(1f).scaleY(1f).alpha(1);
        time.animate().scaleX(1f).scaleY(1f).alpha(1);
        date.animate().scaleX(1f).scaleY(1f).alpha(1);
        content.animate().scaleX(1f).scaleY(1f).alpha(1);
        leftTime.animate().scaleX(1f).scaleY(1f).alpha(1);

    }

    @Override
    public void onNonCenterPosition(boolean b) {

        //Animation example to be ran when the view is not the centered one anymore
        icon.animate().scaleX(0.8f).scaleY(0.8f).alpha(0.6f);
        notification.animate().scaleX(0.8f).scaleY(0.8f).alpha(0.6f);
        time.animate().scaleX(0.8f).scaleY(0.8f).alpha(0.6f);
        date.animate().scaleX(0.8f).scaleY(0.8f).alpha(0.6f);
        content.animate().scaleX(0.8f).scaleY(0.8f).alpha(0.6f);
        leftTime.animate().scaleX(0.8f).scaleY(0.8f).alpha(0.6f);

    }
}