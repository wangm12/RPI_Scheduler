package com.envision.scheduler.time;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.envision.scheduler.R;
import com.envision.scheduler.base.BaseRecyclerAdapter;
import com.envision.scheduler.base.BaseViewHolder;
import com.envision.scheduler.beans.EventBean;

import java.util.List;

/**
 * TODO
 *
 * @author mj
 * @version V 1.0
 * @date 8/2/2018 下午9:05
 */
public class SearchEventAdapter extends BaseRecyclerAdapter<EventBean> {
    SearchEventAdapter(List<EventBean> mData) {
        super(mData);
    }

    @Override
    public View bindItemView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
    }

    @Override
    public void bindDataForView(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_name, Html.fromHtml("<b>"+mData.get(position).name + "</b>, <font><small>" + mData.get(position).subName+"</small></font>"));
        holder.setText(R.id.tv_event_name, mData.get(position).eventName);
    }
}
