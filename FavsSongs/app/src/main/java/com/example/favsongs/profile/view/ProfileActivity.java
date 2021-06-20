package com.example.favsongs.profile.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.favsongs.FavSongsApplication;
import com.example.favsongs.search.MainActivity;
import com.example.favsongs.R;
import com.example.favsongs.login.view.LoginActivity;
import com.example.favsongs.profile.ImageProcessor;
import com.example.favsongs.profile.ProfileContract;
import com.example.favsongs.profile.ProfilePresenter;

import java.io.FileNotFoundException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View {

    public ImageProcessor imageProcessor;

    private ProfileContract.Presenter mPresenter;
    private ImageView mUserImageView;
    private EditText mUserNameEditText;
    private String mImagePath;

    private final int SELECT_PICTURE_REQUEST_CODE = 200;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle == null) {
            openLoginView();
        }

        int userId = bundle.getInt(LoginActivity.ID_PARAMETER);

        mPresenter = new ProfilePresenter(this, ((FavSongsApplication) getApplication()).database().userDao(), userId);
        imageProcessor = new ImageProcessor();

        mUserNameEditText = findViewById(R.id.usernameEditText);
        mUserImageView = findViewById(R.id.userImageView);
        mUserImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageSelectorDialog();
            }
        });

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        mPresenter.retrieveUserInformation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public void displayUserInformation(String name, String image) {
        mUserNameEditText.setText(name);
        loadImage(image);
    }

    @Override
    public void displayUserPlaylist() {
    }

    @Override
    public void openLoginView() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
    }

    private void openImageSelectorDialog() {
        Intent actionPickIntent = new Intent(Intent.ACTION_PICK);
        actionPickIntent.setType("image/*");
        startActivityForResult(actionPickIntent,SELECT_PICTURE_REQUEST_CODE);

    }

    private void loadImage(String localPath) {
        Bitmap bitmap = imageProcessor.getImageBitmap(localPath);
        if (bitmap != null){
            mUserImageView.setImageBitmap(bitmap);
        }
    }

    private void save(){
        if (validateUsernameInput() && validateImageInput()) {
            mPresenter.updateUserProfile(mUserNameEditText.getText().toString(), mImagePath);
        }
    }

    private boolean validateImageInput(){
        if (mImagePath == null) {
            Toast.makeText(this, R.string.message_wrong_username, Toast.LENGTH_SHORT);
            return false;
        }
        return true;
    }

    private boolean validateUsernameInput(){
        if (mUserNameEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.message_wrong_username, Toast.LENGTH_SHORT);
            return false;
        }
        return true;
    }

    private void logout(){
        mPresenter.logout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_PICTURE_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    getImageFromIntentData(data);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }

    private void getImageFromIntentData(Intent data) {
        try {
            Uri imageUri = data.getData();
            Bitmap bitmap = imageProcessor.decodeUri(this, imageUri, 300);
            if (bitmap == null){
                Toast.makeText(this, "Error while decoding image.", Toast.LENGTH_SHORT).show();
            }
            else {
                displayUserImage(bitmap);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "File not found.", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayUserImage(Bitmap bitmap) {
        mUserImageView.setImageBitmap(bitmap);
        mImagePath = imageProcessor.saveToInternalStorage(getApplicationContext(), bitmap, mUserNameEditText.getText().toString());
    }
}
