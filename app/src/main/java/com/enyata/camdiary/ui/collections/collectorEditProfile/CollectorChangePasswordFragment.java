package com.enyata.camdiary.ui.collections.collectorEditProfile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.request.ChangePasswordRequest;
import com.enyata.camdiary.ui.editProfile.EditProfileFragment.ChangePassword;
import com.enyata.camdiary.utils.Alert;
import com.enyata.camdiary.utils.InternetConnection;

public class CollectorChangePasswordFragment extends Fragment {

    EditText oldPassword,newPassword,confirmPassword;
    Button submitChangePassword;
    CollectorEditProfileViewModel collectorEditProfileViewModel;
    String oldPasswordText,newPasswordText,confirmPasswordText;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collectorEditProfileViewModel = ViewModelProviders.of(requireActivity()).get(CollectorEditProfileViewModel.class);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_password_layout,container,false);

        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        oldPassword = view.findViewById(R.id.oldPassword);
        newPassword = view.findViewById(R.id.newPassword);
        confirmPassword = view.findViewById(R.id.confirmPassword);
        submitChangePassword = view.findViewById(R.id.submitChangePassword);
        submitChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("SUBMIT","SUNMITFORGOTPASSWord");
                oldPasswordText = oldPassword.getText().toString();
              newPasswordText = newPassword.getText().toString();
              confirmPasswordText = confirmPassword.getText().toString();
                if (!collectorEditProfileViewModel.isOldPasswordEmpty(oldPasswordText)){
                    Alert.showFailed(getActivity(),"Old password is required");
                }

              if (!collectorEditProfileViewModel.isLengthEqualsTothree(newPasswordText)){
                  Alert.showFailed(getActivity(), "New password must be at least 7 characters");
                  return;
              }if (!newPasswordText.equals(confirmPasswordText)){
                  confirmPassword.setError("Password does not match");
                  confirmPassword.requestFocus();
                }

              else {
                    ChangePasswordRequest.Request request = new ChangePasswordRequest.Request(oldPasswordText,newPasswordText);
                    if (InternetConnection.getInstance(getActivity()).isOnline()){
                        collectorEditProfileViewModel.userChangePassword(request);
                    }else {
                        Alert.showFailed(getActivity(), "Please Check your Internet Connection and try again");
                    }

              }


            }
        });



    }


}
