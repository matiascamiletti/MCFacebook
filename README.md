# MCFacebook
The easy way use Facebook Library.

# Usage
* 1 - Add library
<pre>compile 'com.mobileia:mcfacebook:0.1.2'</pre>

* 2- Add in Manifest and replace "{facebook_id}" for your App ID. (You do not forget to add Key Hashes in Facebook Developer.)
<pre>
<application>
    ...
  
  
        <!-- Facebook -->
        <meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="{facebook_id}" />
        
        <provider
            android:name="com.facebook.FacebookContentProvider"
            android:authorities="com.facebook.app.FacebookContentProvider{facebook_id}"
            android:exported="true" />
        
        <activity
            android:name="com.facebook.FacebookActivity"
            android:configChanges="keyboard|keyboardHidden|screenLayout|screenSize|orientation"
            android:label="@string/app_name"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
    
    
    
</application>
</pre>

# Login with Facebook
* 1 - Extend activity for BaseFacebookActivity
<pre>
public class LoginActivity extends BaseFacebookActivity
</pre>

* 2 - Implement methods required.
<pre>
@Override
public void onSuccessLoginWithFacebook(MCUserFacebook mcUserFacebook) {
      System.out.println("Facebook Data: " + mcUserFacebook.id + mcUserFacebook.fullname + mcUserFacebook.email + mcUserFacebook.picture);
}
</pre>

* 3 - How to call facebook log in?
<pre>
loginWithFacebook(Arrays.asList("public_profile", "user_photos", "email"));
</pre>

* 3b - Example:
<pre>
ImageButton btnFacebook = (ImageButton)findViewById(R.id.btnFacebook);
btnFacebook.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
         loginWithFacebook(Arrays.asList("public_profile", "user_photos", "email"));
      }
});
</pre>

# About
* Developed by Matias Camiletti
* CTO - MobileIA (www.mobileia.com)
