package com.example.thenamequizapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thenamequizapp.Classes.Database;
import com.example.thenamequizapp.Classes.Person;
import com.example.thenamequizapp.R;


public class NewPersonActivity extends AppCompatActivity {

    static final int REQUEST_CAMERA_CODE = 101;
    static final int CAMERA_REQUEST_CODE = 102;
    static final int REQUEST_GALLERY_CODE = 103;
    static final int GALLERY_REQUEST_CODE = 104;

    //BUTTONS
    Button takePic;
    Button libraryPic;

    EditText name;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        // Name and Image
        name = findViewById(R.id.editTextName);
        image = findViewById(R.id.imagePreview);

        //Getting buttons
        takePic = findViewById(R.id.takePicture);
        libraryPic = findViewById(R.id.library);

        //Bind function to buttons
        takePic.setOnClickListener(v -> checkAccessCamera());
        libraryPic.setOnClickListener(v -> checkAccessLibrary());

    }

    //Check permission to access camera, if good open camera
    public void checkAccessCamera() {
        name.onEditorAction(EditorInfo.IME_ACTION_DONE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_CODE);
        } else{
            openCamera();
        }
    }

    // Open Camera
    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    //Check permission to open library, if good open library
    public void checkAccessLibrary() {
        name.onEditorAction(EditorInfo.IME_ACTION_DONE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALLERY_CODE);
        } else {
            openLibrary();
        }
    }

    //Open library
    public void openLibrary() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    // Add person to database and go to database activity if name and image != null
    public void addPerson(View view) {

        if (!name.toString().equals("") && image.getDrawable() != null) {
            ((Database) this.getApplication()).addPerson(new Person(name.getText().toString(), image.getDrawable()));
            Toast.makeText(this, "Person added", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, DatabaseActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Something went wrong while adding a new person", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE) {
            Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(capturedImage);

        }
        if (requestCode == GALLERY_REQUEST_CODE){
            image.setImageURI(data.getData());
        } else {
            Toast.makeText(this, "Something went wrong when trying to get the image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Access to camera is required for the application to use it.", Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode == REQUEST_GALLERY_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openLibrary();
            } else {
                Toast.makeText(this, "Access to library is required for the application to use it.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}