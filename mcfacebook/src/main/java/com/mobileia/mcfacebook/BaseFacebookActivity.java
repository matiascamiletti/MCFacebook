package com.mobileia.mcfacebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.mobileia.mcfacebook.model.MCUserFacebook;

import org.json.JSONObject;

import java.util.Collection;

/**
 * Created by matiascamiletti on 9/7/15.
 */
public abstract class BaseFacebookActivity extends AppCompatActivity {

    private CallbackManager mCallbackManager;
    private ProfileTracker mProfileTracker;
    private AccessTokenTracker mAccessTokenTracker;

    private MCUserFacebook mUser;
    private boolean m

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init Facebook SDK
        FacebookSdk.sdkInitialize(getApplicationContext());
        // Create CallbackManager
        mCallbackManager = CallbackManager.Factory.create();
        // Create Token Listener
        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, final AccessToken currentAccessToken) {

                mUser.id = currentAccessToken.getUserId();
                mUser.token = currentAccessToken.getToken();

                GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                        System.out.println("Facebook Data: " + jsonObject.toString());

                        // Fill data user
                        mUser.fill(jsonObject);
                        // Success login with data
                        onSuccessLoginWithFacebook(mUser);
                    }
                });
                request.executeAsync();

            }
        };
        // Create default User
        mUser = new MCUserFacebook();
    }



    public void loginWithFacebook(Collection<String> permissions){
        LoginManager.getInstance().logInWithReadPermissions(this, permissions);
    }

    public abstract void onSuccessLoginWithFacebook(MCUserFacebook user);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAccessTokenTracker.stopTracking();
        //mProfileTracker.stopTracking();
    }
}
