package com.example.bikramkoju.barberfinalapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    Toolbar toolbar;

    DatabaseHelper db;

    DatabaseHelper dp;

    //  ArrayList<InfoModule> lists = new ArrayList<InfoModule>();

    PagerSlidingTabStrip pagertab;
    ViewPager viewPager;
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar_id);

        pagertab = (PagerSlidingTabStrip) findViewById(R.id.pager_tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        setSupportActionBar(toolbar);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_favorites) {
                    Toast.makeText(MainActivity.this, TabMessage.get(tabId, false), Toast.LENGTH_SHORT).show();
                } else if (tabId == R.id.tab_nearby) {
                    //Toast.makeText(MainActivity.this,TabMessage.get(tabId, false),Toast.LENGTH_SHORT).show();

                   /* Intent i = new Intent(MainActivity.this, Output.class);
                    startActivity(i);*/

                    final Dialog dialogbox = new Dialog(MainActivity.this);
                    dialogbox.setContentView(R.layout.dialog_layout);


                    final TextView outputs = (TextView) dialogbox.findViewById(R.id.income);
                    final TextView expensesz = (TextView) dialogbox.findViewById(R.id.expen);

                    db = new DatabaseHelper(MainActivity.this);

                    String exp = db.getExpense();
                    int inco = db.getIncome();

                    outputs.setText("Your Income is: " + inco);
                    expensesz.setText("\nYour Expenses is: " + exp);

                    dialogbox.show();

                } else if (tabId == R.id.tab_friends) {
                    Toast.makeText(MainActivity.this, TabMessage.get(tabId, false), Toast.LENGTH_SHORT).show();
                    /*final Dialog dialog=new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.list);

                    final TextView title = (TextView) dialog.findViewById(R.id.title);
                    final TextView price = (TextView) dialog.findViewById(R.id.price);

                    ListView lv= (ListView) findViewById(R.id.listview);

                    db= new DatabaseHelper(MainActivity.this);

                    lists=db.getInfo();
                    lv.setAdapter(new CustomAdapterForInfo(MainActivity.this,lists));*/

                }
            }
        });

        /*bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });*/

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(this, fragmentManager));
        pagertab.setViewPager(viewPager);
    }


    private class MyAdapter extends FragmentStatePagerAdapter {
        Context c;

        public MyAdapter(FragmentActivity mainActivity, FragmentManager fragmentManager) {
            super(fragmentManager);
            c = mainActivity;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TypeA();
                case 1:
                    return new TypeB();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Income";
                case 1:
                    return "Expenses";
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:

              Intent intent=new Intent(MainActivity.this,ShowForUpdate.class);
                startActivity(intent);

                //Toast.makeText(this, "Edit pressed", Toast.LENGTH_LONG).show();
                break;
            case R.id.setting:
               // Toast.makeText(this, "Setting pressed", Toast.LENGTH_LONG).show();
                final Dialog dialogbox= new Dialog(MainActivity.this);
                dialogbox.setContentView(R.layout.addrecord);

                final EditText etitle=(EditText)dialogbox.findViewById(R.id.utitle);
                final EditText eprice=(EditText) dialogbox.findViewById(R.id.titlep);
                Button save=(Button)dialogbox.findViewById(R.id.sbtn);
                Button cancel= (Button) dialogbox.findViewById(R.id.cbtn);

                dialogbox.show();

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dp = new DatabaseHelper(MainActivity.this);
                        InfoModule infoModule = new InfoModule();
                        infoModule.setUtitle(etitle.getText().toString());
                        infoModule.setTitle(eprice.getText().toString());

                        dp.addInfo(infoModule);
                        dialogbox.dismiss();
                        Intent intent= new Intent(MainActivity.this,ShowForUpdate.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Record added successful", Toast.LENGTH_LONG).show();
                        onResume();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogbox.dismiss();
                    }
                });

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
