package com.indev.calculator.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.indev.calculator.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
                                                        GoogleApiClient.OnConnectionFailedListener{

    private EditText editLogin;
    private EditText editPassword;

    private LoginButton loginFacebook;
    private CallbackManager callbackFacebookManager;

    /* Client for accessing Google APIs */
    private GoogleApiClient mGoogleApiClient;

    /* RequestCode for resolution involving sign-in */
    private static final int RC_SIGN_IN = 9001;

    /* Keys for persisting instance variables in savedInstanceState */
    private static final String KEY_IS_RESOLVING = "is_resolving";
    private static final String KEY_SHOULD_RESOLVE = "should_resolve";

    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible?*/
    private boolean mShouldResolve = false;

    private TwitterLoginButton loginTwitter;
    private static final String TWITTER_KEY = "9OmW5YDIaaBGfsUYpeB9ziD7h";
    private static final String TWITTER_SECRET = "jxkJB4yUZNzrTgDMt2uejIGUjHYT1B8SPgpoAXshrJ8QMPx3hB";

    private String userName;
    private String userPassword;

    private final String ERROR_CONNECTION = "You have some problem with Internet connection!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        integrateSocialNetworks(savedInstanceState);
        setContentView(R.layout.activity_main);
        onFacebookClick();
        onGoogleClick();
        onTwitterClick();
    }

    private void integrateSocialNetworks(Bundle savedInstanceState) {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        FacebookSdk.sdkInitialize(getApplicationContext());
        if (savedInstanceState != null) {
            mIsResolving = savedInstanceState.getBoolean(KEY_IS_RESOLVING);
            mShouldResolve = savedInstanceState.getBoolean(KEY_SHOULD_RESOLVE);
        }
    }

    public void onForgottenPassClick(View v) {
        Intent intent = new Intent(this, RecoveryPassActivity.class);
        startActivity(intent);
    }

    public void onSendClick(View v) {
        editLogin = (EditText) findViewById(R.id.editLogin);
        editPassword = (EditText) findViewById(R.id.editPassword);
        userName = editLogin.getText().toString();
        userPassword = editPassword.getText().toString();
        if (!userName.equals("") && !userPassword.equals("")) {
            Intent intent = new Intent(this, CalculatorActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Please, fill out the input fields", Toast.LENGTH_LONG).show();
        }

    }

    private void onFacebookClick() {
        callbackFacebookManager = CallbackManager.Factory.create();
        loginFacebook = (LoginButton) findViewById(R.id.facebook_login_button);
        loginFacebook.registerCallback(callbackFacebookManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                userName = Profile.getCurrentProfile().getName();
                Intent intent = new Intent(getApplicationContext(), CalculatorActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, ERROR_CONNECTION, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onGoogleClick() {
        findViewById(R.id.google_login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShouldResolve = true;
                mGoogleApiClient.connect();

            }
        });
        /* [START create_google_api_client
        *   Build GoogleApiClient with access to basic profile */
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();
        /* [END create_google_api_client */

    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            //Show signed-in user's name
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            if (currentPerson != null) {
                userName = currentPerson.getDisplayName();
            }

            /* Set button visibility */
            findViewById(R.id.google_login_button).setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    // [START on_save_instance_state]
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_IS_RESOLVING, mIsResolving);
        outState.putBoolean(KEY_SHOULD_RESOLVE, mShouldResolve);
    }
    // [END on_save_instance_state]

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        /* onConnected indicates that an account was selected on the device, that the selected
        *  account has granted any requested permissions to our app that we were able to establish
        *  a service connection to Google Play services */
        if (mShouldResolve) {
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            userName = currentPerson.getDisplayName();
            Intent intent = new Intent(getApplicationContext(), CalculatorActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        /* The connection to Google Play services was lost. The GoogleApiClient will automatically
        *  attempt to re-connect. Any UI elements that depend on connection to Google APIs should
        *  be hidden or disabled until onConnected is called again */

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        /* Could not connect to Google Play Services. The user needs to select an account, grant
        *  permissions or resolve an error in order to sign in. Refer to the javadoc for
        *  ConnectionResult to see possible error codes. */

        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                //Could not resolve the connection result, show the user an error dialog
                showErrorDialog(connectionResult);
            }
        }
    }

    private void showErrorDialog(ConnectionResult connectionResult) {
        int errorCode = connectionResult.getErrorCode();
        if (GooglePlayServicesUtil.isUserRecoverableError(errorCode)) {
            //Show the default Google Play services error dialog which may still start an intent
            //on our behalf if the user can resolve the issue.
            GooglePlayServicesUtil.getErrorDialog(errorCode, this, RC_SIGN_IN, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mShouldResolve = false;
                    updateUI(false);
                }
            }).show();
        } else {
            // No default Google Play Services error, display a message to the user
            mShouldResolve = false;
            updateUI(false);
        }
    }

    public void onTwitterClick() {
        loginTwitter = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginTwitter.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                userName = result.data.getUserName();
                Intent intent = new Intent(getApplicationContext(), CalculatorActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(MainActivity.this, ERROR_CONNECTION, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackFacebookManager.onActivityResult(requestCode, resultCode, data);
        loginTwitter.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // If the error resolution was not successful we should not resolve further errors.
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }
            mIsResolving = false;
            mGoogleApiClient.connect();
        }
    }

    public void onRegistrationClick(View v) {

    }
}
