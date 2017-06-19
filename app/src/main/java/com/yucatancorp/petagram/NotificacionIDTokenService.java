package com.yucatancorp.petagram;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by marcos on 28/05/2017.
 */

public class NotificacionIDTokenService extends FirebaseInstanceIdService {

    public static final String TAG = "FIREBASE_TOKEN";

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();

    }

    private void enviarTokenRegistro(String token){
        Log.d(TAG, token);
    }
}
