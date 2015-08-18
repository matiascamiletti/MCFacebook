package com.mobileia.mcfacebook;

import android.os.Bundle;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareContent;
import com.facebook.share.widget.ShareDialog;

import java.util.Arrays;

/**
 * Created by matiascamiletti on 17/8/15.
 */
public abstract class BaseFacebookShareActivity extends BaseFacebookActivity {

    protected ShareContent mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Listener login
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                if(/*loginResult.getRecentlyGrantedPermissions().isEmpty() || */mContent == null){
                    return;
                }

                ShareDialog.show(BaseFacebookShareActivity.this, mContent);
                //System.out.println("Post facebook");
            }

            @Override
            public void onCancel() {
                //System.out.println("Post facebook cancel");
            }

            @Override
            public void onError(FacebookException e) {
                //System.out.println("Post facebook error");
            }
        });
    }

    public void shareContent(ShareContent content){
        mContent = content;
        LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList("publish_actions"));
    }
}
