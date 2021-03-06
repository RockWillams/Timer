package com.softorg.rock.timer.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softorg.rock.timer.R;
import com.softorg.rock.timer.adapter.RecyclerViewAdapter;
import com.softorg.rock.timer.util.MyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Author       : rock
 * Date         : 2015-06-01
 * Time         : 15:09
 * Description  :
 */
public class InfoDetailsFragment extends Fragment {
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.info_details_fragment, container, false);
        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new RecyclerViewAdapter(getActivity(),getData()));
    }
    private List<MyItem> getData() {
        // 新建一个集合类，用于存放多条数据
        ArrayList<MyItem> list = new ArrayList<MyItem>();
        //HashMap<String, Object> map = null;
        for (int i = 1; i <= 15; i++) {
            MyItem  map =new MyItem("2015年3月21号","13:30","参加飞行启动活动","剩余3小时","单次通知");
            list.add(map);
        }
        return list;
    }
}
