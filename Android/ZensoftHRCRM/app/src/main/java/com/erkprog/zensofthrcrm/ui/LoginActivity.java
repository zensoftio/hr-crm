package com.erkprog.zensofthrcrm.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.erkprog.zensofthrcrm.MainActivity;
import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.Defaults;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

  GoogleSignInClient mGoogleSignInClient;
  private static final int RC_SIGN_IN = 1;
  private static final String TAG = "LOGIN";
  SignInButton signInButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(Defaults.SERVER_CLIENT_ID)
        .requestEmail()
        .build();

    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    signInButton = findViewById(R.id.sign_in_button);
    signInButton.setSize(SignInButton.SIZE_WIDE);
    signInButton.setOnClickListener(this);

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case (R.id.sign_in_button):
        signIn();
        break;

      default:
        break;
    }

  }

  private void signIn() {
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
    if (requestCode == RC_SIGN_IN) {
      Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
      handleSignInResult(task);
    }
  }

  private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
    try {
      GoogleSignInAccount account = completedTask.getResult(ApiException.class);

      // Signed in successfully
      Log.d(TAG, "handleSignInResult: " + account.getDisplayName()
          + " \n " + account.getEmail()
          + " \n " + account.getPhotoUrl()
          + " \n " + account.getIdToken());

      startMainActivity();

    } catch (ApiException e) {
      // The ApiException status code indicates the detailed failure reason.
      // Please refer to the GoogleSignInStatusCodes class reference for more information.
      Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
    }

  }

  private void startMainActivity() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
    finish();
  }

  @Override
  protected void onStart() {
    super.onStart();
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    if (account != null) {
      Log.d(TAG, "onStart: Already signed in");
      Log.d(TAG, "onStart: " + account.getIdToken());
      Log.d(TAG, "onStart: " + account.getDisplayName());

      startMainActivity();
    }
  }
}
