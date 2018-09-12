package com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.meridian.cybraum.zulekha.alexis.alexispharmacy.ImagePicker;
import com.meridian.cybraum.zulekha.alexis.alexispharmacy.MainActivity;
import com.meridian.cybraum.zulekha.alexis.alexispharmacy.R;
import com.meridian.cybraum.zulekha.alexis.alexispharmacy.Vis_FilePath;
import com.meridian.cybraum.zulekha.alexis.alexispharmacy.Vis_Upload;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by Ansal on 12/19/2017.
 */

public class Order_medicine extends AppCompatActivity {


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.home_txt)
    TextView homeTxt;
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
    @BindView(R.id.lin_close)
    LinearLayout linClose;
    @BindView(R.id.layout)
    RelativeLayout layout;
    @BindView(R.id.progressWheel)
    ProgressBar progressWheel;
    @BindView(R.id.closebutton)
    LinearLayout closebutton;
    @BindView(R.id.room_no)
    EditText roomNo;
    @BindView(R.id.ok)
    LinearLayout ok;
    @BindView(R.id.order_four)
    LinearLayout orderFour;
    @BindView(R.id.attach1)
    LinearLayout attach1;
    @BindView(R.id.attach2)
    LinearLayout attach2;
    @BindView(R.id.picture2)
    ImageView picture2;
    @BindView(R.id.lin_close2)
    LinearLayout linClose2;
    @BindView(R.id.attach3)
    LinearLayout attach3;
    @BindView(R.id.picture3)
    ImageView picture3;
    @BindView(R.id.lin_close3)
    LinearLayout linClose3;
    @BindView(R.id.attach4)
    LinearLayout attach4;
    @BindView(R.id.picture4)
    ImageView picture4;
    @BindView(R.id.lin_close4)
    LinearLayout linClose4;
    String token;

    View custompopup_view, custompopup_view1;
    ArrayList<String>list=new ArrayList<>();
    String Reg_id;
    String selectedFilePath1,selectedFilePath2,selectedFilePath3,selectedFilePath4;
    Uri uri;

    private static final int PICTURE_RESULT1 = 1;
    private static final int PICTURE_RESULT2 = 2;
    private static final int PICTURE_RESULT3 = 3;
    private static final int PICTURE_RESULT4 = 4;
    int CROP_PIC1;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_medicine);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                    (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }


        }
        token = FirebaseInstanceId.getInstance().getToken();
        homeTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));
        SharedPreferences sopre2 = getSharedPreferences("login_method", MODE_PRIVATE);
        Reg_id = sopre2.getString("user_id", null);
        final LayoutInflater inflator = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        custompopup_view = inflator.inflate(R.layout.room_popoup, null);
        custompopup_view1 = inflator.inflate(R.layout.thanks_popup, null);

    }


    @OnClick({R.id.back, R.id.order_two, R.id.handpick, R.id.deliver, R.id.proceed, R.id.lin_close, R.id.closebutton, R.id.ok,
            R.id.attach1, R.id.attach2, R.id.lin_close2, R.id.attach3, R.id.lin_close3, R.id.attach4, R.id.lin_close4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent(Order_medicine.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.lin_close:
                linClose.setVisibility(View.GONE);
                orderThree.setVisibility(View.GONE);
                orderFour.setVisibility(View.GONE);
                picture.setVisibility(View.GONE);
                list.remove(selectedFilePath1);
                System.out.println("list_image"+list);
                break;

            case R.id.attach2:
                Intent chooseImageIntent2 = ImagePicker.getPickImageIntent(this);
                startActivityForResult(chooseImageIntent2, PICTURE_RESULT2);
                break;
            case R.id.attach1:

                Intent chooseImageIntent1 = ImagePicker.getPickImageIntent(this);
                startActivityForResult(chooseImageIntent1, PICTURE_RESULT1);
                break;
            case R.id.lin_close2:
                linClose2.setVisibility(View.GONE);
                orderThree.setVisibility(View.GONE);
                orderFour.setVisibility(View.GONE);
                picture2.setVisibility(View.GONE);
                list.remove(selectedFilePath2);
                System.out.println("list_image"+list);
                break;
            case R.id.attach3:
                Intent chooseImageIntent3 = ImagePicker.getPickImageIntent(this);
                startActivityForResult(chooseImageIntent3, PICTURE_RESULT3);
                break;
            case R.id.lin_close3:
                linClose3.setVisibility(View.GONE);
                orderThree.setVisibility(View.GONE);
                orderFour.setVisibility(View.GONE);
                picture3.setVisibility(View.GONE);
                list.remove(selectedFilePath3);
                System.out.println("list_image"+list);
                break;
            case R.id.attach4:
                Intent chooseImageIntent4 = ImagePicker.getPickImageIntent(this);
                startActivityForResult(chooseImageIntent4, PICTURE_RESULT4);
                break;
            case R.id.lin_close4:
                linClose4.setVisibility(View.GONE);
                orderThree.setVisibility(View.GONE);
                orderFour.setVisibility(View.GONE);
                picture4.setVisibility(View.GONE);
                list.remove(selectedFilePath4);
                System.out.println("list_image"+list);
                break;
            case R.id.order_two:
                break;
            case R.id.handpick:
                uploadVideo2();
                progressWheel.setVisibility(View.VISIBLE);
                break;
            case R.id.deliver:

                orderTwo.setVisibility(View.GONE);
                orderThree.setVisibility(View.GONE);
                orderFour.setVisibility(View.VISIBLE);
                break;
            case R.id.proceed:
                if (list.size()==0){
                    SweetAlertDialog dialog = new SweetAlertDialog(Order_medicine.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    dialog.setTitleText("Please attach at least one prescription")
                            .setCustomImage(R.drawable.ic_launcher)
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

                orderTwo.setVisibility(View.GONE);
                orderThree.setVisibility(View.VISIBLE);
                orderFour.setVisibility(View.GONE);
                System.out.println("list_image"+list);
                break;
            case R.id.closebutton:

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == PICTURE_RESULT1&&resultCode == RESULT_OK) {

            Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
            getImageUri(getApplicationContext(), bitmap);
            startCropImageActivity(uri);
            CROP_PIC1=5;


        }
        if ( requestCode == PICTURE_RESULT2&&resultCode == RESULT_OK) {

            Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
            getImageUri(getApplicationContext(), bitmap);
            startCropImageActivity(uri);
            CROP_PIC1=6;


        }
        if ( requestCode == PICTURE_RESULT3&&resultCode == RESULT_OK) {

            Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
            getImageUri(getApplicationContext(), bitmap);
            startCropImageActivity(uri);
            CROP_PIC1=7;


        }
        if ( requestCode == PICTURE_RESULT4&&resultCode == RESULT_OK) {

            Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
            getImageUri(getApplicationContext(), bitmap);
            startCropImageActivity(uri);
            CROP_PIC1=8;


        }
        if (CROP_PIC1==5&&requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                linClose.setVisibility(View.VISIBLE);
                orderTwo.setVisibility(View.VISIBLE);
                picture.setVisibility(View.VISIBLE);
                orderThree.setVisibility(View.GONE);
                list.remove(selectedFilePath1);
                picture.setImageURI(result.getUri());
                selectedFilePath1 = Vis_FilePath.getPath(getApplicationContext(), result.getUri());
                list.add(selectedFilePath1);
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: ", Toast.LENGTH_LONG).show();
            }
        }
        if (CROP_PIC1==6&&requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                linClose2.setVisibility(View.VISIBLE);
                orderTwo.setVisibility(View.VISIBLE);
                picture2.setVisibility(View.VISIBLE);
                orderThree.setVisibility(View.GONE);
                list.remove(selectedFilePath2);
                picture2.setImageURI(result.getUri());
                selectedFilePath2 = Vis_FilePath.getPath(getApplicationContext(), result.getUri());
                list.add(selectedFilePath2);
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }

        }
        if (CROP_PIC1==7&&requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                linClose3.setVisibility(View.VISIBLE);
                orderTwo.setVisibility(View.VISIBLE);
                picture3.setVisibility(View.VISIBLE);
                orderThree.setVisibility(View.GONE);
                list.remove(selectedFilePath3);
                picture3.setImageURI(result.getUri());
                selectedFilePath3 = Vis_FilePath.getPath(getApplicationContext(), result.getUri());
                list.add(selectedFilePath3);
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed:", Toast.LENGTH_LONG).show();
            }

        }
        if (CROP_PIC1==8&&requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                linClose4.setVisibility(View.VISIBLE);
                orderTwo.setVisibility(View.VISIBLE);
                picture4.setVisibility(View.VISIBLE);
                orderThree.setVisibility(View.GONE);
                list.remove(selectedFilePath4);
                picture4.setImageURI(result.getUri());
                selectedFilePath4 = Vis_FilePath.getPath(getApplicationContext(), result.getUri());
                list.add(selectedFilePath4);
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " , Toast.LENGTH_LONG).show();
            }

        }

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        uri=Uri.parse(path);
        return uri;
    }
    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
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
                 /*   SweetAlertDialog dialog = new SweetAlertDialog(Order_medicine.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                    dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));*/

                  /*  Intent intent = new Intent(Order_medicine.this, Thnkyou.class);
                    intent.putExtra("msg", msg);
                    startActivity(intent);
                    finish();*/
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

                Vis_Upload multipart = new Vis_Upload(requestURL, charset);
                multipart.addHeaderField("User-Agent", "CodeJava");
                multipart.addHeaderField("Test-Header", "Header-Value");
                multipart.addFormField("user_id", Reg_id);
                multipart.addFormField("delivery_type", "room_delivery");
                multipart.addFormField("room_no", roomNo.getText().toString());
                multipart.addFormField("patient_type", "in_patient");
                multipart.addFormField("device_type", "android");
                multipart.addFormField("device_token", FirebaseInstanceId.getInstance().getToken());
                String file_uris[] = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    file_uris[i]= list.get(i).toString();
                    System.out.println("testtt_:" + Reg_id + "," + selectedFilePath1+ "," + roomNo.getText().toString());
                    Serverresponse = "";
                    System.out.println("file_uris[]"+file_uris[i]);
                    multipart.addFilePart("prescription[]",file_uris[i]);
                }
                List<String> response = Collections.singletonList(multipart.finish());
                for (String line : response) {
                    System.out.println(line);
                    Serverresponse += line;
                }
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
                        /*Intent intent = new Intent(Order_medicine.this, Thnkyou.class);
                        intent.putExtra("msg", msg);
                        startActivity(intent);
                        finish();*/

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
                    Vis_Upload multipart = new Vis_Upload(requestURL, charset);
                    multipart.addHeaderField("User-Agent", "CodeJava");
                    multipart.addHeaderField("Test-Header", "Header-Value");
                    multipart.addFormField("user_id", Reg_id);
                    multipart.addFormField("delivery_type", "hand pick");
                    multipart.addFormField("room_no", "NIL");
                    multipart.addFormField("patient_type", "out_patient");
                    multipart.addFormField("device_type", "android");
                    multipart.addFormField("device_token", FirebaseInstanceId.getInstance().getToken());
                    String file_uris[] = new String[list.size()];
                    for (int i =0; i < list.size(); i++) {
                        Serverresponse = "";
                        file_uris[i]= list.get(i).toString();
                        System.out.println("file_uris[]"+file_uris[i]);
                        multipart.addFilePart("prescription[]",file_uris[i]);

                    }
                    List<String> response = Collections.singletonList(multipart.finish());
                    for (String line : response) {
                        System.out.println(line);
                        Serverresponse += line;
                        System.out.println("SERVER REPLIED:" + Serverresponse);
                    }


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

