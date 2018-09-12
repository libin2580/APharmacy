package com.meridian.cybraum.zulekha.alexis.alexispharmacy;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kotlin.Pair;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.forgot)
    TextView forgot;
    @BindView(R.id.login)
    LinearLayout login;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.skip)
    TextView skip;
    List<Pair<String, String>> params;
    SharedPreferences preferences;
    String refreshedToken;
    @BindView(R.id.progressWheel)
    ProgressBar progressWheel;
    @BindView(R.id.layout)
    RelativeLayout layout;
    private static final int RC_SIGN_IN = 1;

    static FirebaseAuth fAuth;
    static GoogleApiClient mGoogleApiClient;
    @BindView(R.id.fb)
    LinearLayout fb;
    @BindView(R.id.google)
    LinearLayout google;

    LoginButton loginButton;
    CallbackManager mCallbackManager;
    String social_name,social_email,social_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        preferences = getApplicationContext().getSharedPreferences("login_method", MODE_PRIVATE);
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        fAuth = FirebaseAuth.getInstance();
        loginButton = (LoginButton) findViewById(R.id.fblogin);
        mCallbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email", "public_profile");
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    @OnClick({R.id.fb,R.id.google,R.id.forgot, R.id.login, R.id.register, R.id.skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fb:
                loginButton.performClick();
                LoginManager.getInstance().registerCallback(mCallbackManager,
                        new FacebookCallback<LoginResult>()
                        {
                            @Override
                            public void onSuccess(LoginResult loginResult)
                            {
                               // handleFacebookAccessToken(loginResult.getAccessToken());
                                getUserDetails();
                            }

                            @Override
                            public void onCancel()
                            {
                                Toast.makeText(LoginActivity.this,"cancel", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(FacebookException exception)

                            {
                                Toast.makeText(LoginActivity.this,"cancel", Toast.LENGTH_LONG).show();
                            }
                        });

                break;
            case R.id.google:
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setIsSmartLockEnabled(false)
                                .setProviders(Collections.singletonList(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
                                ))
                                .build(),
                        RC_SIGN_IN);
                break;
            case R.id.forgot:
                Intent jr = new Intent(LoginActivity.this, Forgot_password.class);
                startActivity(jr);
                finish();
                break;
            case R.id.login:
                if (email.getText().toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {

                    SweetAlertDialog dialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Enter valid email!")
                            .setConfirmText("Ok")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();

                                }
                            })
                            .show();
                    dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));

                    return;
                }

                if (password.getText().toString().length() < 5) {
                    SweetAlertDialog dialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Password too short, enter minimum 6 characters!")
                            .setConfirmText("Ok")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();

                                }
                            })
                            .show();
                    dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));
                    return;
                }
                params = new ArrayList<Pair<String, String>>() {{
                    add(new Pair<String, String>("email", email.getText().toString()));
                    add(new Pair<String, String>("password", password.getText().toString()));
                    add(new Pair<String, String>("deviceToken", refreshedToken));
                    add(new Pair<String, String>("deviceType", "android"));
                }};
                httpPost();
                break;
            case R.id.register:
                Intent j = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(j);
                break;
            case R.id.skip:
                Intent h = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(h);
                break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == ResultCodes.OK) {
                FirebaseUser user = fAuth.getCurrentUser();
                social_name=user.getDisplayName();
                social_email=user.getEmail();
                social_id=user.getUid();
                String pic= String.valueOf(user.getPhotoUrl());
                Log.e("Loginpic",social_name+","+social_email+","+pic);
                socialgoogle();

                return;
            } else {
                // Sign in failed
                if (response == null) {
                    Log.e("Login","Login canceled by User");
                    return;
                }
                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Log.e("Login","No Internet Connection");
                    return;
                }
                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Log.e("Login","Unknown Error");
                    return;
                }
            }
            Log.e("Login","Unknown sign in response");
        }
    }
    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        fAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = fAuth.getCurrentUser();
                            social_name=user.getDisplayName();
                            social_email=user.getEmail();
                            social_id=user.getUid();
                            String pic= String.valueOf(user.getPhotoUrl());
                            Log.e("Loginpic",social_name+","+social_email+","+pic);
                            socialfb();

                        } else {

                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }
    protected void getUserDetails( ) {

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                JSONObject json = response.getJSONObject();
                try
                {
                    if(json != null)
                    {
                        loginButton.setReadPermissions(Arrays.asList("id","name","link","email","picture","gender"));
                        social_id=json.getString("id");
                        social_name=json.getString("name");
                        social_email=json.getString("email");
                        System.out.println("fbbb------"+social_name+","+social_email);
                        socialfb();


                    }
                }

                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void httpPost() {
        progressWheel.setVisibility(View.VISIBLE);
        Fuel.post("http://www.alexishospital.com/pharmacyapp/json/log_in.php", params).responseString(new Handler<String>() {
            @Override
            public void failure(@NotNull Request request, @NotNull Response response, @NotNull FuelError error) {
                progressWheel.setVisibility(View.GONE);

            }

            @Override
            public void success(@NotNull Request request, @NotNull Response response, String data) {
                progressWheel.setVisibility(View.GONE);

                try {
                    JSONObject object = new JSONObject(data);
                    String status = object.getString("status");
                    if (!status.equals("true")) {
                        SweetAlertDialog dialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Invalid email or password")
                                .setConfirmText("Ok")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();

                                    }
                                })
                                .show();
                        dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));

                    } else {
                        final String user_id = object.getJSONObject("data").getString("user_id");
                        SharedPreferences.Editor editor1 = preferences.edit();
                        editor1.putString("login", "login");
                        editor1.putString("user_id", user_id);
                        editor1.apply();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    progressWheel.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void socialfb() {
        progressWheel.setVisibility(View.VISIBLE);
        String URL= "http://www.alexishospital.com/pharmacyapp/json/social-login.php";
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST,URL,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressWheel.setVisibility(View.GONE);
                        try {
                            JSONObject object=new JSONObject(response);
                            System.out.println("______________________");
                            System.out.println("__________response____________"+response);
                            System.out.println("______________________");
                            String status=object.getString("status");
                            if (!status.equals("true")){
                                String messege=object.getString("message");
                                SweetAlertDialog dialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE);
                                dialog.setContentText(messege)
                                        .setConfirmText("Ok")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismiss();

                                            }
                                        })
                                        .show();
                                dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));

                            }
                            else {
                                final String jsonmessege = object.getJSONObject("data").getString("user_id");
                                System.out.println("jsonmessege"+jsonmessege);
                                SweetAlertDialog dialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                                dialog.setContentText("Login Succsessfull")
                                        .setConfirmText("Ok")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismissWithAnimation();
                                                SharedPreferences.Editor editor1 = preferences.edit();
                                                editor1.putString("login", "fb");
                                                editor1.putString("user_id",jsonmessege);
                                                editor1.apply();
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                                FirebaseAuth.getInstance().signOut();
                                                LoginManager.getInstance().logOut();
                                            }
                                        })
                                        .show();
                                dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));

                            }


                        }
                        catch (Exception e){
                            e.printStackTrace();
                            progressWheel.setVisibility(View.GONE);
                        }


                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressWheel.setVisibility(View.GONE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("fb_id",social_id);
                params.put("type","facebook");
                params.put("email",social_email);
                params.put("name",social_name);
                params.put("device_token",refreshedToken);
                params.put("device_type","android");
                Log.d("hash", String.valueOf(params));
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }
    public void  socialgoogle() {

       progressWheel.setVisibility(View.VISIBLE);
       String URL= "http://www.alexishospital.com/pharmacyapp/json/social-login.php";
        System.out.println("______________________");
        System.out.println("______________________");
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST,URL,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressWheel.setVisibility(View.GONE);
                        try {
                            JSONObject object=new JSONObject(response);
                            System.out.println("response"+response);
                            String status=object.getString("status");
                            if (!status.equals("true")){
                                String messege=object.getString("message");
                                SweetAlertDialog dialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE);
                                dialog.setContentText(messege)
                                        .setConfirmText("Ok")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismiss();

                                            }
                                        })
                                        .show();
                                dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));

                            }
                            else {
                                final String jsonmessege = object.getJSONObject("data").getString("user_id");
                                System.out.println("jsonmessege"+jsonmessege);
                                SweetAlertDialog dialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                                dialog.setContentText("Login Succsessfull")
                                        .setConfirmText("Ok")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismissWithAnimation();
                                                SharedPreferences.Editor editor1 = preferences.edit();
                                                editor1.putString("login", "google");
                                                editor1.putString("user_id",jsonmessege);
                                                editor1.apply();
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                                FirebaseAuth.getInstance().signOut();
                                                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                                                        new ResultCallback<Status>() {
                                                            @Override
                                                            public void onResult(@NonNull Status status) {


                                                            }
                                                        });
                                            }
                                        })
                                        .show();
                                dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));

                            }

                        }
                        catch (Exception e){
                            e.printStackTrace();
                            progressWheel.setVisibility(View.GONE);
                        }


                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressWheel.setVisibility(View.GONE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("g_id",social_id);
                params.put("type","google");
                params.put("email",social_email);
                params.put("name",social_name);
                params.put("device_token",refreshedToken);
                params.put("device_type","android");
                Log.d("hash", String.valueOf(params));
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }

}
