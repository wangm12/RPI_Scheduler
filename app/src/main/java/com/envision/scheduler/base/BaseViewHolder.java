package com.envision.scheduler.base;

import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * TODO
 *
 * @author mj
 * @version V 1.0
 * @date 8/2/2018 下午8:32
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mItemView;


    BaseViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
        mViews = new SparseArray<>();
    }

    private <T extends View> T findViewById(int id) {
        View view = mViews.get(id);
        if (null == view) {
            view = mItemView.findViewById(id);
            mViews.put(id, view);
        }
        return (T) view;
    }

    public void setText(int id, String text) {
        View view = findViewById(id);
        if (null != view && view instanceof TextView) {
            ((TextView) view).setText(text);
        }
    }
    public void setText(int id, Spanned text) {
        View view = findViewById(id);
        if (null != view && view instanceof TextView) {
            ((TextView) view).setText(text);
        }
    }
    public void setImage(int id, int res) {
        View view = findViewById(id);
        if (null != view && view instanceof ImageView) {
            Picasso.get().load(res).into((ImageView) view);
        }
    }
}
