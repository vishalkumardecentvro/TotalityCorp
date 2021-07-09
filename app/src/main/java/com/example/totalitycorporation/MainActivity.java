package com.example.totalitycorporation;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraXConfig;

import com.example.totalitycorporation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements CameraXConfig.Provider {
  static final int REQUEST_IMAGE_CAPTURE = 1;
  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    View view = binding.getRoot();
    setContentView(view);

    instantiate();
    initialize();
    listen();
    load();
  }

  @NonNull
  @org.jetbrains.annotations.NotNull
  @Override
  public CameraXConfig getCameraXConfig() {
    return null;
  }

  private void instantiate() {

  }

  private void initialize() {

  }

  private void listen() {
    binding.mcvSelfie.setOnClickListener(v -> openFrontCamera());

  }

  private void load() {

  }

  private void openFrontCamera() {
    Intent open = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    open.putExtra("android.intent.extras.CAMERA_FACING", 1);

    try {
      startActivityForResult(open, REQUEST_IMAGE_CAPTURE);
    } catch (ActivityNotFoundException exception) {
      Toast.makeText(MainActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      Bundle extras = data.getExtras();
      Bitmap imageBitmap = (Bitmap) extras.get("data");
      binding.ivPreview.setImageBitmap(imageBitmap);
    }
  }
}