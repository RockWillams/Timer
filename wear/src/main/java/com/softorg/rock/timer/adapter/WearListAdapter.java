package com.softorg.rock.timer.adapter;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.softorg.rock.timer.R;

import com.softorg.rock.timer.bean.MyItem;
import com.softorg.rock.timer.layout.MyItemView;

import java.util.List;

/**
 * Created by Rock on 2016/1/7.
 */
public class WearListAdapter extends WearableListView.Adapter {

    private final Context context;
    private final List<MyItem> items;

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
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
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

       /* ((TextView) myItemView.findViewById(R.id.content)).setText(item.content);
        ((TextView) myItemView.findViewById(R.id.notification)).setText(item.notification);
        ((TextView)myItemView.findViewById(R.id.time)).setText(item.date);
        ((TextView) myItemView.findViewById(R.id.date)).setText(item.leftTime);
        ((TextView) myItemView.findViewById(R.id.content)).setText(item.time);*/

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}