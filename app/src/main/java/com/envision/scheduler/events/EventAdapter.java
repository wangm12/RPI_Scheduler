package com.envision.scheduler.events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.envision.scheduler.R;
import com.envision.scheduler.base.BaseRecyclerAdapter;
import com.envision.scheduler.base.BaseViewHolder;
import com.envision.scheduler.beans.TodayEventBean;

import java.util.List;

/**
 * TODO
 *
 * @author shizhi
 * @version V 1.0
 * @date 8/2/2018 下午9:05
 */
public class EventAdapter extends BaseRecyclerAdapter<TodayEventBean> {
    public EventAdapter(List<TodayEventBean> mData) {
        super(mData);
    }

    @Override
    public View bindItemView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_today, parent, false);
    }

    @Override
    public void bindDataForView(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_time, mData.get(position).start_time + "\n"+mData.get(position).end_time);
        holder.setText(R.id.tv_location, mData.get(position).location);
        holder.setText(R.id.tv_event_name, mData.get(position).eventName);
    }
}
