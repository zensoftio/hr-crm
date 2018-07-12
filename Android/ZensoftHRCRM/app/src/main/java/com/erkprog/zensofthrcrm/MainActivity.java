package com.erkprog.zensofthrcrm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.ui.candidates.candidatesList.CandidatesFragment;
import com.erkprog.zensofthrcrm.ui.interviews.interviewsList.InterviewsFragment;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  CircleImageView userImage;
  TextView userName;
  TextView userEmail;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    View navHeader = navigationView.getHeaderView(0);
    userName = navHeader.findViewById(R.id.user_name);
    userEmail = navHeader.findViewById(R.id.user_email);
    userImage = navHeader.findViewById(R.id.user_profile_image);
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
    getMenuInflater().inflate(R.menu.main, menu);
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

    if (id == R.id.nav_requests) {
      // Handle the camera action
    } else if (id == R.id.nav_vacancies) {

    } else if (id == R.id.nav_candidates) {
      switchFragment(new CandidatesFragment());

    } else if (id == R.id.nav_interviews) {
      switchFragment(new InterviewsFragment());
    } else if (id == R.id.nav_statistics) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  protected void switchFragment(Fragment fragment) {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.main_fragment_container, fragment)
        .commit();
  }


  @Override
  protected void onStart() {
    super.onStart();
    //TODO: set header with google account data here
    userName.setText("Zensoft Hr");
    userEmail.setText("hr@zensoft.io");
    Picasso.get()
        .load("https://www.codeproject.com/KB/GDI-plus/ImageProcessing2/img.jpg")
        .placeholder(R.drawable.base_account)
        .error(R.drawable.base_account)
        .into(userImage);
  }
}
