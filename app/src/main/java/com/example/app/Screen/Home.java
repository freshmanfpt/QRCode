package com.example.app.Screen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.app.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private fragment_tao fragment_tao;
    private fragment_quet fragment_quet;
    private  fragment_lichsu fragment_lichsu;
    private fragmenet_caidat fragmenet_caidat;
    private long backPressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.home_viewPager);
        tabLayout = findViewById(R.id.home_tabLayout);

        fragment_tao = new fragment_tao();
        fragment_quet = new fragment_quet();
        fragment_lichsu = new fragment_lichsu();
        fragmenet_caidat = new fragmenet_caidat();
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);

        viewPagerAdapter.addFragment(fragment_quet,"Quét");
        viewPagerAdapter.addFragment(fragment_tao,"Tạo");
        viewPagerAdapter.addFragment(fragment_lichsu,"Lịch Sử");
        viewPagerAdapter.addFragment(fragmenet_caidat,"Cài Đặt");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.scan);
        tabLayout.getTabAt(1).setIcon(R.drawable.pencil);
        tabLayout.getTabAt(2).setIcon(R.drawable.list);
        tabLayout.getTabAt(3).setIcon(R.drawable.setting);

//        BadgeDrawable badgeDrawable = tabLayout.getTabAt(0).get

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();
        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment,String tilte){
            fragments.add(fragment);
            fragmentTitle.add(tilte);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }

    @Override
    public void onBackPressed() {
//        finishAndRemoveTask();

        finishAffinity();
        System.exit(0);
    }
}