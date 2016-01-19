package com.softorg.rock.timer.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.entity.Event;
import com.softorg.rock.timer.R;
import com.softorg.rock.timer.activity.SubActivity;
import com.softorg.rock.timer.bean.Distance;
import com.softorg.rock.timer.util.MyItem;

import java.util.List;

/**
 * Author       : rock
 * Date         : 2015-06-02
 * Time         : 09:47
 * Description  :
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
 /*   private int[] colors = {R.color.color_0, R.color.color_1, R.color.color_2, R.color.color_3,
            R.color.color_4, R.color.color_5, R.color.color_6, R.color.color_7,
            R.color.color_8, R.color.color_9,};*/

    private Context mContext;
    private List<MyItem> eventList;

    public RecyclerViewAdapter(Context mContext, List<MyItem> eventList) {
        this.mContext = mContext;
        this.eventList=eventList;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.content, parent, false);
       ViewHolder myViewHolder = new ViewHolder(view,mContext);
        return myViewHolder ;
}

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.date.setText(eventList.get(position).getDate());
        holder.time.setText(eventList.get(position).getTime());
        holder.content.setText(eventList.get(position).getContent());
        holder.leftTime.setText(eventList.get(position).getLeftTime());
        holder.notification.setText(eventList.get(0).getNotification());


    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        public final View view ;
        public final TextView date;
        public final TextView time;
        public final TextView content;
        public final TextView leftTime;
        public final TextView notification;
        public Context context;
        /*,TextView date,TextView time,TextView content,TextView leftTime,TextView notification */

        public ViewHolder(View view,Context context) {
            super(view);
            this.view = view;

            this.date = (TextView) view.findViewById(R.id.date);
            this.time=(TextView) view.findViewById(R.id.time);
            this.content=(TextView) view.findViewById(R.id.content);
            this.leftTime=(TextView) view.findViewById(R.id.leftTime);
            this.notification=(TextView) view.findViewById(R.id.notification);
            this.context = context;

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            {

                /////////////
                Distance distance = new Distance();
                //getLayoutPosition();
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("是否删除本条");
                        builder.setPositiveButton("确认",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        //TODO
                                    }
                                });
                        builder.setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                    }
                                });
                        builder.show();
                    }
                }
                return false;
            }
        }
    }
}
