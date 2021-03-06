package onlyloveyd.com.gankioclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.activity.WebActivity;
import onlyloveyd.com.gankioclient.gsonbean.HttpBean;
import onlyloveyd.com.gankioclient.utils.PublicTools;

/**
 * Created by lisa on 2016/12/19.
 * Email: 457420045@qq.com
 */

public class BonusAdapter extends GankAdapter {

    public BonusAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_bonus, parent, false);
        return (new BonusViewHolder(view));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mGankData != null && mGankData.size() != 0 && holder instanceof BonusViewHolder) {
            BonusViewHolder bonusViewHolder = (BonusViewHolder) holder;
            final HttpBean.ResultsBean resultsBean = mGankData.get(position);
            final String url = resultsBean.getUrl();
            if (url != null) {
                AnimationDrawable animationDrawable = (AnimationDrawable) mContext.getResources().getDrawable(R.drawable.image_loading);
                animationDrawable.start();
                Glide.with(mContext).load(url).placeholder(animationDrawable).crossFade().into(bonusViewHolder.mainPic);
            }
            //direct ro web activity with extra data
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, WebActivity.class);
                    intent.putExtra(PublicTools.KEY_BUNDLE_URL, url);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class BonusViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_bonus)
        ImageView mainPic;

        public BonusViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
