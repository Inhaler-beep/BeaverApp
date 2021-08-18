package com.inhaler.beaverapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation meowBottomNavigation;
    Boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meowBottomNavigation = (MeowBottomNavigation) findViewById(R.id.meow_bottom_navigation);

        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_account));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_book));

        Fragment fragment = new HomeFragment();
        loadFristFragment(fragment);

        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {


            }
        });


            meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {

                @Override
                public void onShowItem(MeowBottomNavigation.Model item) {

//                    Fragment fragment = null;
//                    switch (item.getId())
//                    {
//                        case  1 :
//                            fragment = new AccountFragment();
//                            break;
//                        case 2 :
//                            fragment = new HomeFragment();
//                            break;
//                        case 3:
//                            fragment = new CourseFragment();
//                            break;
//
//                    }
//                    loadFragment(fragment);
//



                }
            });



        meowBottomNavigation.setCount(1,"10");
        meowBottomNavigation.show(2,false);
        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId())
                {
                    case  1 :
                        fragment = new AccountFragment();
                        break;
                    case 2 :
                        fragment = new HomeFragment();
                        break;
                    case 3:
                        fragment = new CourseFragment();
                        break;

                }
                loadFragment(fragment);

            }
        });
    }

    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                )
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

    private void loadFristFragment(Fragment fragment)
    {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();



    }

}