package hsm.yeng.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hsm.yeng.R;
import hsm.yeng.calendar.AcademicCalenderFragment;
import hsm.yeng.join.JoinWithUsFragment;

import hsm.yeng.rules.RulesandRegulatiionFragment;
import hsm.yeng.syllabus.BtechSyllabusFragment;
import hsm.yeng.syllabus.MBASyllabusFragment;
import hsm.yeng.syllabus.MtechSyllabusFragment;
import hsm.yeng.updates.NewsAndUpdatesFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    ExpandableListAdapter mMenuAdapter;
    ExpandableListView expandableList;
    List<ExpandedMenuModel> listDataHeader;
    HashMap<ExpandedMenuModel, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        setTitle("Rules and Regulations");
        final NewsAndUpdatesFragment fragment = new NewsAndUpdatesFragment();
        final android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);

        final ProgressDialog dialog = new ProgressDialog(HomeActivity.this);

        dialog.setMessage("Loading...");
        dialog.show();

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {


                                          fragmentTransaction.commit();


                                      }
                                  }, 300
        );
        dialog.dismiss();
        expandableList= (ExpandableListView) findViewById(R.id.navigationmenu);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        prepareListData();
        mMenuAdapter = new ExpandableListAdapter(this, listDataHeader,   listDataChild, expandableList);
        // setting list adapter
        expandableList.setAdapter(mMenuAdapter);

        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if (i1 == 0) {
                    MtechSyllabusFragment fragment = new MtechSyllabusFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content,fragment);
                    fragmentTransaction.commit();
                } else if (i1 == 1) {
                    BtechSyllabusFragment fragment = new BtechSyllabusFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content,fragment);
                    fragmentTransaction.commit();
                } else if (i1 == 2) {
                    MBASyllabusFragment fragment = new MBASyllabusFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content,fragment);
                    fragmentTransaction.commit();
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                if(i==0){

                    NewsAndUpdatesFragment fragment = new NewsAndUpdatesFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content,fragment);
                    fragmentTransaction.commit();
                }
                 if(i==1){
/*
                    startActivity(new Intent(HomeActivity.this,RulesRegulationsFragment.class));
*/
                    //startAnim();
                    final RulesandRegulatiionFragment fragment = new RulesandRegulatiionFragment();
                    final android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment);

                    final ProgressDialog dialog = new ProgressDialog(HomeActivity.this);

                    dialog.setMessage("Loading...");
                    dialog.show();

                    new Handler().postDelayed(new Runnable() {
                                                  @Override
                                                  public void run() {


                                                      fragmentTransaction.commit();


                                                  }
                                              }, 300
                    );
dialog.dismiss();
//stopAnim();
                    Log.e("sdgdggdf", "stetyert");
                }
               /* else if(i==2){
                    Toast.makeText(getApplicationContext(), "syllabus"+i, Toast.LENGTH_SHORT).show();
                }*/
                else if(i==3){
                    AcademicCalenderFragment fragment = new AcademicCalenderFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                }
                else if(i==4){
                    JoinWithUsFragment fragment = new JoinWithUsFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content,fragment);
                    fragmentTransaction.commit();
                }

                if(i!=2) {
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
    }







    private void prepareListData() {
        listDataHeader = new ArrayList<ExpandedMenuModel>();
        listDataChild = new HashMap<ExpandedMenuModel, List<String>>();

        ExpandedMenuModel item1 = new ExpandedMenuModel();
        item1.setIconName("News and Updates");
        item1.setIconImg(R.drawable.news_and_updates);
        // Adding data header
        listDataHeader.add(item1);
        ExpandedMenuModel item2 = new ExpandedMenuModel();
        item2.setIconName("Rules and Regulations");
        item2.setIconImg(R.drawable.rules_and_regualtions);
        listDataHeader.add(item2);



        ExpandedMenuModel item3 = new ExpandedMenuModel();
        item3.setIconName("Syllabus");
        item3.setIconImg(R.drawable.syllabus);
        listDataHeader.add(item3);


        ExpandedMenuModel item4 = new ExpandedMenuModel();
        item4.setIconName("Academic Calender");
        item4.setIconImg(R.drawable.calendar);
        listDataHeader.add(item4);

       ExpandedMenuModel item5 = new ExpandedMenuModel();
        item5.setIconName("Join With Us");
        item5.setIconImg(R.drawable.join_with_us);
        listDataHeader.add(item5);

        // Adding child data
        /*List<String> heading1= new ArrayList<String>();
        heading1.add("Submenu of item 1");*/

        List<String> syllabus= new ArrayList<String>();
        syllabus.add("MTech");
        syllabus.add("BTech");
        syllabus.add("MBA");

       // listDataChild.put(listDataHeader.get(0), heading1);// Header, Child data
        listDataChild.put(listDataHeader.get(2), syllabus);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void startAnim() {
        findViewById(R.id.avloadingIndicatorView).setVisibility(View.VISIBLE);
    }

    void stopAnim() {
        findViewById(R.id.avloadingIndicatorView).setVisibility(View.GONE);
    }

}
