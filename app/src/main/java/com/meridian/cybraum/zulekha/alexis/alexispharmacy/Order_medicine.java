/*
package com.meridian.cybraum.zulekha.alexis.alexispharmacy;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kotlin.Pair;

*/
/**
 * Created by Ansal on 12/19/2017.
 *//*


public class Order_medicine extends AppCompatActivity {


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.home_txt)
    TextView homeTxt;


    @BindView(R.id.order_one)
    LinearLayout orderOne;
    @BindView(R.id.picture)
    ImageView picture;
    @BindView(R.id.proceed)
    LinearLayout proceed;
    @BindView(R.id.order_two)
    LinearLayout orderTwo;
    @BindView(R.id.handpick)
    ImageView handpick;
    @BindView(R.id.deliver)
    ImageView deliver;
    @BindView(R.id.order_three)
    LinearLayout orderThree;
    int PICK_IMAGE = 1;
    View custompopup_view, custompopup_view1;
    List<Pair<String, String>> params;
    String Reg_id, selectedFilePath;
    Uri uri;
    @BindView(R.id.lin_close)
    LinearLayout linClose;
    @BindView(R.id.layout)
    RelativeLayout layout;
    @BindView(R.id.progressWheel)
    ProgressBar progressWheel;
    private static final int ACTIVITY_START_CAMERA_APP = 0;
    private static final int PICTURE_RESULT= 2;
    @BindView(R.id.closebutton)
    LinearLayout closebutton;
    @BindView(R.id.room_no)
    EditText roomNo;
    @BindView(R.id.ok)
    LinearLayout ok;
    @BindView(R.id.order_four)
    LinearLayout orderFour;
    private String mImageFileLocation = "";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_medicine);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED&&(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }


        }

        homeTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));
        SharedPreferences sopre2 = getSharedPreferences("login_method", MODE_PRIVATE);
        Reg_id = sopre2.getString("user_id", null);
        final LayoutInflater inflator = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        custompopup_view = inflator.inflate(R.layout.room_popoup, null);
        custompopup_view1 = inflator.inflate(R.layout.thanks_popup, null);

    }


    @OnClick({R.id.back, R.id.take, R.id.attach, R.id.picture, R.id.order_two, R.id.handpick, R.id.deliver, R.id.proceed, R.id.lin_close, R.id.closebutton,R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent(Order_medicine.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.lin_close:
                orderOne.setVisibility(View.VISIBLE);
                orderTwo.setVisibility(View.GONE);
                orderThree.setVisibility(View.GONE);
                orderFour.setVisibility(View.GONE);
                break;
            case R.id.take:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        Intent callCameraApplicationIntent = new Intent();
                        callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        File photoFile = null;
                        try {
                            photoFile = createImageFile();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String authorities = getApplicationContext().getPackageName() + ".provider";
                        Uri imageUri = FileProvider.getUriForFile(Order_medicine.this, authorities, photoFile);
                        callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        // callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        startActivityForResult(callCameraApplicationIntent, ACTIVITY_START_CAMERA_APP);


                }
                else {
                    Intent chooseImageIntent = ImagePicker.getPickImageIntent(this);
                    startActivityForResult(chooseImageIntent, PICTURE_RESULT);

                }

                break;
            case R.id.attach:

                Intent w = new Intent();
                w.setType("image/*");
                w.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(w, "Select Picture"), PICK_IMAGE);
                break;
            case R.id.picture:
                break;
            case R.id.order_two:
                break;
            case R.id.handpick:
                uploadVideo2();
                progressWheel.setVisibility(View.VISIBLE);
                break;
            case R.id.deliver:
                orderOne.setVisibility(View.GONE);
                orderTwo.setVisibility(View.GONE);
                orderThree.setVisibility(View.GONE);
                orderFour.setVisibility(View.VISIBLE);
                break;
            case R.id.proceed:
                orderOne.setVisibility(View.GONE);
                orderTwo.setVisibility(View.GONE);
                orderThree.setVisibility(View.VISIBLE);
                orderFour.setVisibility(View.GONE);
                break;
            case R.id.closebutton:
                orderOne.setVisibility(View.GONE);
                orderTwo.setVisibility(View.GONE);
                orderThree.setVisibility(View.VISIBLE);
                orderFour.setVisibility(View.GONE);
                break;
            case R.id.ok:

                if (TextUtils.isEmpty(roomNo.getText().toString())) {

                    SweetAlertDialog dialog = new SweetAlertDialog(Order_medicine.this, SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Enter room number!")
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

                UploadVideo uv = new UploadVideo();
                uv.execute();
                progressWheel.setVisibility(View.VISIBLE);
                break;
        }
    }

    File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);
        mImageFileLocation = image.getAbsolutePath();
        return image;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_RESULT && resultCode == RESULT_OK) {
            orderOne.setVisibility(View.GONE);
            orderTwo.setVisibility(View.VISIBLE);
            orderThree.setVisibility(View.GONE);
            Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
            picture.setImageBitmap(bitmap);
        }
        if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
            setReducedImageSize();
        }
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            orderOne.setVisibility(View.GONE);
            orderTwo.setVisibility(View.VISIBLE);
            orderThree.setVisibility(View.GONE);
            uri = data.getData();
            selectedFilePath = Vis_FilePath.getPath(getApplicationContext(), uri);
            Log.d("attach", String.valueOf(uri));
            System.out.println("mImageFileLocation________" + selectedFilePath);
            picture.setImageURI(uri);
        }
    }


    public void setReducedImageSize() {

        Bitmap photoReducedSizeBitmp = BitmapFactory.decodeFile(mImageFileLocation);
        if(photoReducedSizeBitmp != null){
            orderOne.setVisibility(View.GONE);
            orderTwo.setVisibility(View.VISIBLE);
            orderThree.setVisibility(View.GONE);
            picture.setImageBitmap(photoReducedSizeBitmp);
            selectedFilePath = mImageFileLocation;
            System.out.println("mImageFileLocation________" + selectedFilePath);

        }else{
            Toast.makeText(this,"Error while capturing Image",Toast.LENGTH_LONG).show();
        }



    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Order_medicine.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public class UploadVideo extends AsyncTask<Void, Void, String> {
        String Serverresponse = "";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressWheel.setVisibility(View.GONE);

            try {
                JSONObject object = new JSONObject(s);
                String status = object.getString("status");
                String msg = object.getString("message");
                if (!status.equals("true")) {
                    SweetAlertDialog dialog = new SweetAlertDialog(Order_medicine.this, SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText(msg)
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
                    SweetAlertDialog dialog = new SweetAlertDialog(Order_medicine.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    dialog.setTitleText("order placed successful")
                            .setCustomImage(R.drawable.like)
                            .setConfirmText("Ok")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                    Intent intent = new Intent(Order_medicine.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            })
                            .show();
                    dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));
                }


            } catch (Exception e) {
                e.printStackTrace();
                progressWheel.setVisibility(View.GONE);
            }


        }

        @Override
        protected String doInBackground(Void... params) {

            String charset = "UTF-8";
            String requestURL = "http://www.alexishospital.com/pharmacyapp/json/order_medicine.php";
            try {
                System.out.println("testtt_:" + Reg_id + "," + selectedFilePath + "," + roomNo.getText().toString());
                Serverresponse = "";
                Vis_Upload multipart = new Vis_Upload(requestURL, charset);
                multipart.addHeaderField("User-Agent", "CodeJava");
                multipart.addHeaderField("Test-Header", "Header-Value");
                multipart.addFormField("user_id", Reg_id);
                multipart.addFormField("delivery_type", "room_delivery");
                multipart.addFormField("room_no", roomNo.getText().toString());
                multipart.addFormField("patient_type", "in_patient");
                multipart.addFormField("deviceType", "android");
                multipart.addFormField("deviceToken", FirebaseInstanceId.getInstance().getToken());
                multipart.addFilePart("prescription", selectedFilePath);
                Serverresponse = multipart.finish();
                System.out.println("SERVER REPLIED:" + Serverresponse);

            } catch (IOException ex) {
                System.err.println(ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Serverresponse:" + Serverresponse);


            return Serverresponse;
        }
    }

    private void uploadVideo2() {
        class UploadVideo extends AsyncTask<Void, Void, String> {
            String Serverresponse = "";


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                progressWheel.setVisibility(View.GONE);
                super.onPostExecute(s);
                try {
                    JSONObject object = new JSONObject(s);
                    String status = object.getString("status");
                    String msg = object.getString("message");
                    if (!status.equals("true")) {
                        SweetAlertDialog dialog = new SweetAlertDialog(Order_medicine.this, SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText(msg)
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
                        SweetAlertDialog dialog = new SweetAlertDialog(Order_medicine.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                        dialog.setTitleText("order placed successful")
                                .setCustomImage(R.drawable.like)
                                .setConfirmText("Ok")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();

                                        Intent intent = new Intent(Order_medicine.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .show();
                        dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    progressWheel.setVisibility(View.GONE);
                }


            }

            @Override
            protected String doInBackground(Void... params) {

                String charset = "UTF-8";
                String requestURL = "http://www.alexishospital.com/pharmacyapp/json/order_medicine.php";
                try {
                    System.out.println("testtt_:" + "dd");

                    Vis_Upload multipart = new Vis_Upload(requestURL, charset);
                    multipart.addHeaderField("User-Agent", "CodeJava");
                    multipart.addHeaderField("Test-Header", "Header-Value");
                    multipart.addFormField("user_id", Reg_id);
                    multipart.addFormField("delivery_type", "hand pick");
                    multipart.addFormField("room_no", "NIL");
                    multipart.addFormField("patient_type", "out_patient");
                    multipart.addFormField("deviceType", "android");
                    multipart.addFormField("deviceToken", FirebaseInstanceId.getInstance().getToken());
                    multipart.addFilePart("prescription", selectedFilePath);
                    Serverresponse = multipart.finish();
                    System.out.println("SERVER REPLIED:" + Serverresponse);

                } catch (IOException ex) {
                    System.err.println(ex);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                return Serverresponse;
            }
        }
        UploadVideo uv = new UploadVideo();
        uv.execute();
    }

}
*/
