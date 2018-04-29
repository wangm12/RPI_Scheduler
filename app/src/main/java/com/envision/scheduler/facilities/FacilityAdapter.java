package com.envision.scheduler.facilities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.envision.scheduler.R;
import com.envision.scheduler.base.BaseRecyclerAdapter;
import com.envision.scheduler.base.BaseViewHolder;
import com.envision.scheduler.beans.FacilityBean;

import java.util.List;

/**
 * TODO
 *
 * @author shizhi
 * @version V 1.0
 * @date 8/2/2018 下午9:05
 */
public class FacilityAdapter extends BaseRecyclerAdapter<FacilityBean> {
    FacilityAdapter(List<FacilityBean> mData) {
        super(mData);
    }

    @Override
    public View bindItemView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_facility, parent,false);
    }

    @Override
    public void bindDataForView(BaseViewHolder holder, int position) {
        holder.setText(R.id.name, mData.get(position).name);
        holder.setImage(R.id.icon, mData.get(position).res);
    }
}
