package com.polpp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.polpp.LoginActivity;
import com.polpp.R;
import com.polpp.entity.EntityWalkthrough;

import java.util.ArrayList;


public class AdapterWalktrought extends PagerAdapter{

    private ArrayList<EntityWalkthrough> arrayData;
    private Context mContext;

    public AdapterWalktrought(ArrayList<EntityWalkthrough> arrayData, Context mContext) {
        this.arrayData = arrayData;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return arrayData.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return ((ViewPager) object).getCurrentItem();
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }


    @Override
    public void finishUpdate(ViewGroup arg0) {
    }


    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(ViewGroup arg0) {
    }

    public Object instantiateItem(ViewGroup collection, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.walktrought_item, null);

        TextView txtDesc = (TextView) layout.findViewById(R.id.wi_txt_desc);
        ImageView img = (ImageView) layout.findViewById(R.id.wi_img);
        TextView txtTittle = (TextView)layout.findViewById(R.id.txt_tittle_wk);
        LinearLayout imgBack = (LinearLayout) layout.findViewById(R.id.lin_back_wk);
        Button btnGPlus = (Button) layout.findViewById(R.id.wi_btn_started);


        Button btnStarted = (Button) layout.findViewById(R.id.wi_btn_started);
        btnStarted.setVisibility(View.INVISIBLE);

//        ImageView imgSlide = (ImageView) layout.findViewById(R.id.wi_img_desc);


        EntityWalkthrough data = arrayData.get(position);

        if (data != null) {
            img.setVisibility(View.GONE);
            txtTittle.setVisibility(View.GONE);
            txtDesc.setText(data.getDescWalktrought());
            if(position==0) {
                img.setImageResource(data.getImageWalktrought());
                img.setVisibility(View.VISIBLE);
                txtTittle.setVisibility(View.GONE);
            }
            if(position!=0){
                txtTittle.setText(data.getTitleWalktrought());
                img.setVisibility(View.GONE);
                txtTittle.setVisibility(View.VISIBLE);
            }

            if(position == (arrayData.size() - 1)){
                btnGPlus.setVisibility(View.VISIBLE);
                btnGPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       mContext.startActivity(new Intent(mContext, LoginActivity.class));
                    }
                });
            }

            imgBack.setBackground(mContext.getResources().getDrawable(data.getBackWalktrought()));
//            img.setBackgroundResource(data.getImageWalktrought());
        }

        collection.addView(layout, 0);

        return layout;

    }
}
