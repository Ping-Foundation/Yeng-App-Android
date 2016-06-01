package hsm.yeng.syllabus;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hsm.yeng.R;
import hsm.yeng.util.Util;

public class SyllabusSingleViewActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String position;
    private TextView mCourse_no;
    private TextView mCourse_name;
    private TextView mCourse_ltp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_single_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        position=getIntent().getStringExtra("pos");
        Log.e("position","pos"+position);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mCourse_ltp= (TextView) findViewById(R.id.course_ltp);
        mCourse_name= (TextView) findViewById(R.id.course_name);
        mCourse_no= (TextView) findViewById(R.id.course_no);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        Util util = new Util();
        try {
            JSONObject object=new JSONObject(util.loadJSONFromAsset(SyllabusSingleViewActivity.this,"ktusyllabusbtech.json"));
            JSONArray syllabus=object.getJSONArray("syllabus");

            JSONObject jsonObject1=syllabus.getJSONObject(0);
            JSONArray Json=jsonObject1.getJSONArray("modules");
            JSONObject obj=Json.getJSONObject(0);
            Log.e("modules","success"+obj);

                JSONObject jsonObject=syllabus.getJSONObject(Integer.parseInt(position));

            Log.e("name","sadsa"+jsonObject) ;
            mCourse_name.setText(jsonObject.optString("Subject"));
            mCourse_no.setText(jsonObject.optString("code"));
            mCourse_ltp.setText(jsonObject.optString("LTP"));
            jsonObject.optString("Subject");




        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("catch","working");
        }
        setupViewPager(viewPager);

       /* tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);*/
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ModuleOneFragment(),"");
        adapter.addFragment(new ModuleTwoFragment(), "");
        adapter.addFragment(new ModuleThreeFragment(), "");
        adapter.addFragment(new ModuleFourFragment(), "");
        adapter.addFragment(new ModuleFiveFragment(), "");
        adapter.addFragment(new ModuleSixFragment(), "");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    public String getMyData() {
        return position;
    }
}
