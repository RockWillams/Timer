package com.softorg.rock.timer.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.softorg.rock.timer.R;

import com.softorg.rock.timer.activity.DetailNotif;
import com.softorg.rock.timer.activity.DetailNotif_;
import com.softorg.rock.timer.activity.MainActivity;
import com.softorg.rock.timer.bean.Distance;
import com.softorg.rock.timer.bean.MyItem;
import com.softorg.rock.timer.layout.MyItemView;

import java.util.List;

/**
 * Created by Rock on 2016/1/7.
 */
public class WearListAdapter extends WearableListView.Adapter {

    private final Context context;
    private final List<MyItem> items;



    final int RIGHT = 0;
    final int LEFT = 1;
    private static final int FLING_MIN_DISTANCE = 50;
    private static final int FLING_MIN_VELOCITY = 0;

    public WearListAdapter(Context context, List<MyItem> items) {
        this.context = context;
        this.items = items;
    }

    public static class ItemViewHolder extends WearableListView.ViewHolder {
        //private MyItem myItem;
        public ImageView icon;
        public TextView date;
        public  TextView time;
        public  TextView content;
        public  TextView leftTime;
        public TextView notification;


        public ItemViewHolder(View itemView) {
            super(itemView);
            // find the text view within the custom item's layout
            icon = (ImageView) itemView.findViewById(R.id.icon);
            date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time);
            content = (TextView) itemView.findViewById(R.id.content);
            leftTime = (TextView) itemView.findViewById(R.id.leftTime);
            notification = (TextView) itemView.findViewById(R.id.notification);

        }

    }

    @Override
    public WearableListView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        viewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Distance distance = new Distance();
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    distance.x1 = event.getX();
                    distance.y1 = event.getY();
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    distance. x2 = event.getX();
                    distance.y2 = event.getY();

                    if(distance.y1 -distance. y2 > 50) {
                       // Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
                    } else if(distance.y2 -distance. y1 > 50) {
                        //Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
                    } else if(distance.x1 -distance. x2 > 50) {
                        View viewGroupFocusedChild = viewGroup.getFocusedChild();
                        Intent intent  = new Intent(context, DetailNotif_.class);
                        intent.putExtra("icon",((ImageView)v.findViewById(R.id.icon)).getResources().toString());
                        intent.putExtra("lefttime",((TextView)v.findViewById(R.id.leftTime)).getText().toString());
                        intent.putExtra("notification",((TextView)v.findViewById(R.id.notification)).getText().toString());
                        intent.putExtra("date",((TextView)v.findViewById(R.id.date)).getText().toString());
                        intent.putExtra("content",((TextView)v.findViewById(R.id.content)).getText().toString());
                        intent.putExtra("time",((TextView)v.findViewById(R.id.time)).getText().toString());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }


                return false;
            }
        });
      //  return new WearableListView.ViewHolder(new MyItemView(context));
        return new ItemViewHolder(new MyItemView(context));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder viewHolder, final int position) {
        MyItemView  myItemView = (MyItemView) viewHolder.itemView;
        final MyItem item = items.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder)viewHolder;

        ((ImageView)itemViewHolder.icon).setImageResource(item.icon);
        ((TextView)itemViewHolder.leftTime).setText(item.leftTime);
        ((TextView)itemViewHolder.date).setText(item.date);
        ((TextView)itemViewHolder.time).setText(item.time);
        ((TextView)itemViewHolder.content).setText(item.content);
        ((TextView)itemViewHolder.notification).setText(item.notification);




    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}