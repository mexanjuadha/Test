package com.polpp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.polpp.adapter.AdapterWalktrought;
import com.polpp.entity.EntityWalkthrough;

import java.util.ArrayList;


public class Home extends Activity {

    private ViewPager mPager;
    private ArrayList<EntityWalkthrough> arrayWalktrought;
    private AdapterWalktrought adapterWalktrought;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        mContext = this;
        bindView();
        configWalktrougt();
    }

    private void bindView() {
        mPager = (ViewPager) findViewById(R.id.pager_walktrought);
    }

    private void configWalktrougt() {
        arrayWalktrought = Dummy.getWalktrought();
        adapterWalktrought = new AdapterWalktrought(arrayWalktrought, mContext);
        mPager.setAdapter(adapterWalktrought);

//        mIndicator.setViewPager(mPager);
//        mIndicator.notifyDataSetChanged();
//
//        mIndicator.setBackgroundColor(Color.parseColor("#6fd49c"));
//        mIndicator.setPageColor(Color.parseColor("#3e9f6a"));
//        mIndicator.setFillColor(Color.parseColor("#FFFFFF"));
//        mIndicator.setStrokeColor(Color.parseColor("#FFFFFF"));
//        mIndicator.setStrokeWidth(2 * density);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
