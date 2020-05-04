package com.enyata.camdiary.ui.aggregations.aggregatorEditProfile;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cloudinary.Transformation;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.request.EditProfileRequest;
import com.enyata.camdiary.ui.collections.collectorEditProfile.CollectorEditProfileViewModel;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;

public class AggregatorEditProfileFragment extends Fragment {
    Button submit;
    EditText firstName,lastName,email,phoneNumber,address;
    String cloudinaryImage, cloudinaryID;
    ProgressDialog dialog;

    String firstNameText, lastNameText, emailText, phoneNumberText, addressText,userImage,userType, userFirstName, userLastName, userEmail,userPhoneNumber, userAddress;
    AggregatorEditProfileViewModel aggregatorEditProfileViewModel;
    ImageView userPix;
    private static final int REQUEST_PERMISSION = 1;
    private static int PICK_FROM_GALLERY = 1;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aggregatorEditProfileViewModel = ViewModelProviders.of(requireActivity()).get(AggregatorEditProfileViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_profile_layout,container,false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission() && checkExternalPermission()) {


            } else {
                requestPermission();
            }
        }


        userPix = view.findViewById(R.id.userImage);
        submit = view.findViewById(R.id.submitEditProfile);
        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        address = view.findViewById(R.id.address);
        userImage =  aggregatorEditProfileViewModel.getCollectorImage();
        Picasso.get().load(userImage).into(userPix);
        userEmail = aggregatorEditProfileViewModel.getUserEmail();
        userFirstName = aggregatorEditProfileViewModel.getUserFirstName();
        userLastName = aggregatorEditProfileViewModel.getUserLastName();
        userPhoneNumber = aggregatorEditProfileViewModel.getUserPhoneNuber();
        userAddress = aggregatorEditProfileViewModel.getUserAddress();
        userType = aggregatorEditProfileViewModel.getUserTpe();
        firstName.setText(userFirstName);
        lastName.setText(userLastName);
        phoneNumber.setText(userPhoneNumber);
        address .setText(userAddress);
        dialog = new ProgressDialog(getActivity(),R.style.MyAlertDialogStyle);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("please wait");





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("onnnn","BUttonclick");
                firstNameText = firstName.getText().toString();
                lastNameText = lastName.getText().toString();
                phoneNumberText = phoneNumber.getText().toString();
                addressText = address.getText().toString();
                EditProfileRequest.Request request = new EditProfileRequest.Request(firstNameText,lastNameText,addressText,phoneNumberText,userImage);
                if (InternetConnection.getInstance(getActivity()).isOnline()) {
                    aggregatorEditProfileViewModel.userEditProfile(request);
                }else {
                    Alert.showFailed(getActivity(),"Please Check your Internet and try again");
                }

            }
        });

        userPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                }catch(Exception exp){
                    Log.i("Error",exp.toString());
                }

            }
        });
    }


    private boolean checkExternalPermission() {
        return (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0) {
                    boolean readExternalAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeExternalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (readExternalAccepted && writeExternalStorage) {
                        Alert.showSuccess(getActivity(), "Permission Granted, Now you can access access the gallery");
                    } else {
                        Alert.showSuccess(getActivity(), "Permission Denied, You cannot access gallery");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA) && shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
                                showMessageOKCancel("You need to allow access to both permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},
                                                            REQUEST_PERMISSION);
                                                }
                                            }
                                        });
                                return;
                            }
                        }


                    }

                }
                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK){
            Uri uri = data.getData();

            try {
                dialog.show();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                uploadImagetoCloudinary(bitmap);

                userPix.setImageBitmap(bitmap);
                Log.i("BITMAP", String.valueOf(bitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public void uploadImagetoCloudinary(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG , 20, byteArrayOutputStream);
        byte[] partImage = byteArrayOutputStream.toByteArray();
        MediaManager.get().upload(partImage)
                .option("resource_type", "image")
                .unsigned("ht7lodiw")
                .callback(new UploadCallback() {

                    @Override
                    public void onStart(String requestId) {
                        Log.i("START", "STARTTTTT");
                        dialog.show();

                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        Double progress = (double) bytes / totalBytes;
                        dialog.show();
                        Log.i("PROGRESS", "PROGRESS");

                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        if (resultData != null) {
                            Log.i("SUCCESS", "SUCCESS");
                            dialog.dismiss();
                            userImage = (String) resultData.get("url");
                            cloudinaryID = (String) resultData.get("public_id").toString();
                            cloudinaryImage = MediaManager.get().url().transformation(new Transformation()).resourceType("image").generate(cloudinaryID + ".jpg");
                            Alert.showSuccess(getActivity(),"Image uploaded successfully");
                            Log.i("userImage", userImage);
                            Log.i("cloudinaryID", cloudinaryID);

                        }

                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Log.i("ERROR", "ERROR");
                        dialog.dismiss();
                        Alert.showFailed(getActivity(), "Error Uploading Result, Please try again later ");
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        Log.i("SCHEDULE", "SCHEDULE");
                        dialog.dismiss();
                        Alert.showFailed(getActivity(), "Uploading is taking time,please take picture again");

                    }
                })
                .dispatch();


    }


}
