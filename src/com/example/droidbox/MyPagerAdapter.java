package com.example.droidbox;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

public class MyPagerAdapter extends PagerAdapter{

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == ((View) arg1);
	}
	@Override
	public Object instantiateItem(View collection, int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int resId = 0;
        switch (position) {
        case 0:
            resId = R.layout.title;
            break;
        case 1:
            resId = R.layout.artist;
            break;
        case 2:
            resId = R.layout.album;
            break;
        case 3:
            resId = R.layout.genre;
            break;
        }
        View view = inflater.inflate(resId, null);
        ((ViewPager) collection).addView(view, 0);
        return view;
    }
	@Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }
}
