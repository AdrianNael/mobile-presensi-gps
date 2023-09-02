//package com.example.mahapanca;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.ContentResolver;
//import android.content.ContentValues;
//import android.content.DialogInterface;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.BitmapFactory;
//import androidx.appcompat.app.AppCompatActivity;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.Toast;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.core.content.FileProvider;
//
//import com.example.mahapanca.databinding.ActivityVerifikasifotoBinding;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//import kotlin.jvm.Throws;
//
//public class Verifikasifoto extends AppCompatActivity {
//
//    final static int CAPTURE_IMAGE_REQUEST = 1;
//    File photoFile;
//    String mCurrentPhotoPath;
//    ActivityVerifikasifotoBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityVerifikasifotoBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        binding.buttonCapture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                captureImage();
//            }
//        });
//        binding.vfKonfirmasiButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigateToVerifikasifoto2();
//            }
//        });
//        binding.vBackButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigateToVerifikasiLokasi();
//            }
//        });
//    }
//
//    private void captureImage() {
//        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//
//        if (ContextCompat.checkSelfPermission(
//                this, Manifest.permission.CAMERA
//        ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(this, perms, 0);
//        } else {
//            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                // Create the File where the photo should go
//                try {
//                    photoFile = createImageFile();
//                    // Continue only if the File was successfully created
//                    if (photoFile != null) {
//                        Uri photoURI = FileProvider.getUriForFile(
//                                this,
//                                BuildConfig.APPLICATION_ID,
//                                photoFile
//                        );
//                        takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                        startActivityForResult(takePictureIntent, CAPTURE_IMAGE_REQUEST);
//                    }
//                } catch (Exception ex) {
//                    // Error occurred while creating the File
//                    displayMessage(this, ex.getMessage());
//                }
//            } else {
//                displayMessage(this, "Null result");
//            }
//        }
//
//    }
//
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName, /* prefix */
//                ".jpg", /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = image.getAbsolutePath();
//        return image;
//    }
//
//    private void displayMessage(Context context, String message) {
//        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
//    }
//
//    private void saveImageToGallery(Bitmap bitmap) {
//        // Menyimpan gambar ke galeri menggunakan MediaStore
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
//        String imageFileName = "IMG_" + timeStamp + ".jpg";
//
//        ContentValues values = new ContentValues();
//        values.put(MediaStore.Images.Media.TITLE, imageFileName);
//        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//
//        // Menyimpan gambar sebagai file eksternal
//        ContentResolver contentResolver = getContentResolver();
//        Uri imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//
//        try {
//            OutputStream outputStream = contentResolver.openOutputStream(imageUri);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//            outputStream.close();
//
//            // Menampilkan pesan berhasil
//            displayMessage(this, "Gambar berhasil disimpan di galeri");
//        } catch (IOException e) {
//            e.printStackTrace();
//            displayMessage(this, "Gagal menyimpan gambar");
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAPTURE_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
//            Bitmap myBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
//            binding.imageview5.setImageBitmap(myBitmap);
//
//            // Simpan gambar ke galeri
//            saveImageToGallery(myBitmap);
//        } else {
//            displayMessage(this, "Request cancelled or something went wrong.");
//        }
//    }
//    private void navigateToVerifikasifoto2() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Konfirmasi");
//        builder.setMessage("Apa yakin data yang sudah diberikan sudah benar !?");
//        // Add "Confirm" button
//        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Perform actions for confirmation
//                Intent intent = new Intent(Verifikasifoto.this, Dashboard_user.class);
//                startActivity(intent);
//
//                displayMessage("Anda berhasil Absen");
//            }
//        });
//
//        // Add "Back" button
//        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Perform actions for going back
//            }
//        });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
//    private void navigateToVerifikasiLokasi() {
//        Intent intent = new Intent(this, Verifikasilokasi.class);
//        startActivity(intent);
//    }
//    private void displayMessage(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//}