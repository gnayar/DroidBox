package com.example.droidbox;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.example.droidbox.R;

public class MusicLibrary extends SherlockFragmentActivity
{

	ViewPager viewPager;
	TabsAdapter tabsAdapter;

	SongList songs = new SongList();
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		addSongs();//Once JSON is implemented just call a ReadFile here to read in the library. Current Situation is for Testing Only
		super.onCreate(savedInstanceState);
	
		
		
		viewPager = new ViewPager(this);
		viewPager.setId(R.id.pager);

		setContentView(viewPager);
		ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabsAdapter = new TabsAdapter(this, viewPager);
		
		tabsAdapter.addTab(
				bar.newTab().setText("Title"),
				FragmentOne.class, null);
		tabsAdapter.addTab(
				bar.newTab()
						.setText("Artist"),
				FragmentTwo.class, null);
		tabsAdapter.addTab(
				bar.newTab()
						.setText("Album"),
				FragmentThree.class, null);
		;   
		
	}
	
	public SongList getSongsTitleSort(){
	
		return songs.sortByTitle();
	}
	public SongList getSongsArtistSort(){
	
		return songs.sortByArtist();
	}
	public SongList getSongsAlbumSort(){
	
		return songs.sortByAlbum();
	}
	
	public void addSongs(){
		songs.add(new Song("song 1","g","h"));
        songs.add(new Song("song 2","e","g"));
       	songs.add(new Song("song 3","d","f"));
       	songs.add(new Song("song 4","a","e"));
       	songs.add(new Song("song 5","c","d"));
       	songs.add(new Song("song 6","b","c"));
       	songs.add(new Song("song 7","f","b"));
       	songs.add(new Song("song 8","h","a"));
	}

	
	public static class TabsAdapter extends FragmentPagerAdapter implements
			ActionBar.TabListener, ViewPager.OnPageChangeListener
	{
		private final Context mContext;
		private final ActionBar mActionBar;
		private final ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

		static final class TabInfo
		{
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(Class<?> _class, Bundle _args)
			{
				clss = _class;
				args = _args;
			}
		}

		public TabsAdapter(SherlockFragmentActivity activity, ViewPager pager)
		{
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mActionBar = activity.getSupportActionBar();
			mViewPager = pager;
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
		}

		public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args)
		{
			TabInfo info = new TabInfo(clss, args);
			tab.setTag(info);
			tab.setTabListener(this);
			mTabs.add(info);
			mActionBar.addTab(tab);
			notifyDataSetChanged();
		}

		@Override
		public int getCount()
		{
			return mTabs.size();
		}

		@Override
		public Fragment getItem(int position)
		{
			TabInfo info = mTabs.get(position);
			return Fragment.instantiate(mContext, info.clss.getName(),
					info.args);
		}

		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels)
		{
		}

		public void onPageSelected(int position)
		{
			mActionBar.setSelectedNavigationItem(position);
		}

		public void onPageScrollStateChanged(int state)
		{
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft)
		{
			Object tag = tab.getTag();
			for (int i = 0; i < mTabs.size(); i++)
			{
				if (mTabs.get(i) == tag)
				{
					mViewPager.setCurrentItem(i);
				}
			}
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft)
		{
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft)
		{
		}
	}
}