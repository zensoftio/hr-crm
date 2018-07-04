package com.erkprog.zensofthrcrm.firebase;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
  @Override
  public void onTokenRefresh() {
    // Get updated InstanceID token.
    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
    Log.d("TOKEN", "Refreshed token: " + refreshedToken);

//    sendRegistrationToServer(refreshedToken);
  }
}
