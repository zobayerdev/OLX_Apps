package com.example.manus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.manus.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            startLoginOption();
        }

        // activity_main.xml = ActivityMain show home fragments
        // ei line binding er nichei likhte hobe
        showHomeFragment();
        binding.bottomNv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_home) {

                    showHomeFragment();
                    return true;
                }
                else if (itemId == R.id.menu_chat) {
                    if (firebaseAuth.getCurrentUser() == null) {
                        Utils.toast(MainActivity.this, "Login Required");
                        startLoginOption();
                        return false;

                    } else {
                        showChatFragment();
                        return true;
                    }

                }
                else if (itemId == R.id.menu_my_ads) {
                    if (firebaseAuth.getCurrentUser() == null) {
                        Utils.toast(MainActivity.this, "Login Required");
                        startLoginOption();
                        return false;
                    }
                    else {
                        showMyAdsFragment();
                        return true;
                    }
                }
                else if (itemId == R.id.menu_account) {
                    if (firebaseAuth.getCurrentUser() == null) {
                        Utils.toast(MainActivity.this, "Login Required");
                        startLoginOption();
                        return false;
                    }
                    else {
                        showAccountFragment();
                        return true;
                    }

                }
                else {
                    return false;
                }

            }
        });
    }

    private void showHomeFragment() {
        binding.toolbarTitleTv.setText("Home");

        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.fragmentsFl.getId(), fragment, "HomeFragment");
        fragmentTransaction.commit();
    }

    private void showChatFragment() {
        binding.toolbarTitleTv.setText("Chat");

        ChatFragment fragment = new ChatFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.fragmentsFl.getId(), fragment, "ChatFragment");
        fragmentTransaction.commit();
    }

    private void showMyAdsFragment() {
        binding.toolbarTitleTv.setText("My Ads");

        MyAdsFragment fragment = new MyAdsFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.fragmentsFl.getId(), fragment, "MyAdsFragment");
        fragmentTransaction.commit();
    }

    private void showAccountFragment() {
        binding.toolbarTitleTv.setText("Account");

        AccountFragment fragment = new AccountFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.fragmentsFl.getId(), fragment, "AccountFragment");
        fragmentTransaction.commit();
    }

    private void startLoginOption() {
        startActivity(new Intent(this, LoginOptionActivity.class));
    }
}