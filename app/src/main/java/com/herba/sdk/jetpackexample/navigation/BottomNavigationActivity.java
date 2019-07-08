package com.herba.sdk.jetpackexample.navigation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.herba.sdk.jetpackexample.R;

public class BottomNavigationActivity extends AppCompatActivity {

/*    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    NavController navController = Navigation.findNavController(BottomNavigationActivity.this, R.id.bottom_nav_host_fragment);
                    navController.navigate(R.id.homeFragment);
                    return true;
                case R.id.navigation_dashboard:
                    navController = Navigation.findNavController(BottomNavigationActivity.this, R.id.bottom_nav_host_fragment);
                    navController.navigate(R.id.dashboardFragment);
                    return true;
                case R.id.navigation_notifications:
                    navController = Navigation.findNavController(BottomNavigationActivity.this, R.id.bottom_nav_host_fragment);
                    navController.navigate(R.id.notificationFragment);
                    return true;
            }
            return false;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        //BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        //bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}