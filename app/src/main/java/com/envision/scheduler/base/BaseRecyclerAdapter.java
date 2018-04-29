package com.envision.scheduler.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * TODO
 *
 * @author mj
 * @version V 1.0
 * @date 8/2/2018 下午8:32
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> mData;
    private OnItemClickListener mOnItemClickListener;

    public BaseRecyclerAdapter(List<T> mData) {
        this.mData = mData;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(bindItemView(parent, viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder holder, int position) {
        bindDataForView(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onItemClick(v, holder, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public abstract View bindItemView(ViewGroup parent, int viewType);

    public abstract void bindDataForView(BaseViewHolder holder, int position);

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
    }
}