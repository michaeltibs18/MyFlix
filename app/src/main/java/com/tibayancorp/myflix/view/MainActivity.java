package com.tibayancorp.myflix.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.tibayancorp.myflix.R;
import com.tibayancorp.myflix.helper.BottomNavigationViewHelper;
import com.tibayancorp.myflix.view.navigation_fragments.HomeFragment;
import com.tibayancorp.myflix.view.navigation_fragments.MoviesFragment;
import com.tibayancorp.myflix.view.navigation_fragments.TVFragment;
import com.tibayancorp.myflix.view.navigation_fragments.UserFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment2 = new MoviesFragment();
    final Fragment fragment3 = new TVFragment();
    final Fragment fragment4 = new UserFragment();
    Fragment active = fragment1;
    final FragmentManager fm = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;
                case R.id.navigation_movie:
                    mTextMessage.setText(R.string.title_movie);
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;
                case R.id.navigation_tv:
                    mTextMessage.setText(R.string.title_tv);
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    return true;
                case R.id.navigation_user:
                    mTextMessage.setText(R.string.title_user);
                    fm.beginTransaction().hide(active).show(fragment4).commit();
                    active = fragment4;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        fm.beginTransaction().add(R.id.main_container, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main_container,fragment1, "1").commit();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
