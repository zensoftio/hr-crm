package com.erkprog.zensofthrcrm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.erkprog.zensofthrcrm.ui.LoginActivity;
import com.erkprog.zensofthrcrm.ui.candidates.candidatesList.CandidatesFragment;
import com.erkprog.zensofthrcrm.ui.interviews.interviewsList.InterviewsFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.erkprog.zensofthrcrm.ui.requests.requestsList.RequestsFragment;
import com.erkprog.zensofthrcrm.ui.vacancies.vacanciesList.VacanciesFragment;

import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  GoogleSignInClient mGoogleSignInClient;
  CircleImageView userImage;
  TextView userName;
  TextView userEmail;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    initNavigationView();
    setUpGoogleAccount();
  }

  private void initNavigationView() {
    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    View navHeader = navigationView.getHeaderView(0);
    userName = navHeader.findViewById(R.id.user_name);
    userEmail = navHeader.findViewById(R.id.user_email);
    userImage = navHeader.findViewById(R.id.user_profile_image);
  }

  private void setUpGoogleAccount() {
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();

    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    if (account != null) {
      setUpNavHeader(account);
    }
  }

  private void setUpNavHeader(GoogleSignInAccount account) {
    userName.setText(account.getDisplayName());
    userEmail.setText(account.getEmail());
    Picasso.get()
        .load(account.getPhotoUrl())
        .placeholder(R.drawable.base_account)
        .error(R.drawable.base_account)
        .into(userImage);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.nav_requests) {
      switchFragment(RequestsFragment.newInstance());

    } else if (id == R.id.nav_vacancies) {
      switchFragment(VacanciesFragment.newInstance());

    } else if (id == R.id.nav_candidates) {
      switchFragment(new CandidatesFragment());

    } else if (id == R.id.nav_interviews) {
      switchFragment(new InterviewsFragment());

    } else if (id == R.id.nav_statistics) {

    } else if (id == R.id.nav_sign_out) {
      signOut();
    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  private void signOut() {
    mGoogleSignInClient.signOut()
        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
          @Override
          public void onComplete(@NonNull Task<Void> task) {
            onSignedOut();
          }
        });
  }

  private void onSignedOut() {
    Intent intent = new Intent(this, LoginActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
    finish();
  }

  protected void switchFragment(Fragment fragment) {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.main_fragment_container, fragment)
        .commit();
  }
}