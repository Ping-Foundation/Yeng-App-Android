package hsm.yeng.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hsm.yeng.rules.BtechFragment;
import hsm.yeng.rules.MbaFragment;
import hsm.yeng.rules.MtechFragment;


public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new BtechFragment();
		case 1:
			// Games fragment activity
			return new MtechFragment();
		case 2:
			// Movies fragment activity
			return new MbaFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
