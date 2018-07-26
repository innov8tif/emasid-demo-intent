package com.innov8tif.emassample;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;
import java.util.logging.Logger;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final int PERMISSION_RC = 134;
    private static final String EXTRA_VERIFY_FACE = "EXTRA_VERIFY_FACE";
    private static final String EXTRA_HIGH_THRESHOLD = "EXTRA_HIGH_THRESHOLD";
    TextView txtResult;
    ImageView imgDoc;
    ImageView imgFace;

    private static final String EXTRA_SCAN_TYPE = "EXTRA_SCAN_TYPE";
    private String EXTRA_RESULT = "result";
    private boolean mVerifiedFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgDoc = findViewById(R.id.img_full_doc);
        imgFace = findViewById(R.id.img_face);
        txtResult = findViewById(R.id.txt_result);

    }

    public void start(View view) {
        startScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {


            EmasModel result = new Gson().fromJson(data.getStringExtra(EXTRA_RESULT), EmasModel.class);
            Log.d("EMAS", "=>" + data.getStringExtra(EXTRA_RESULT));
            Glide.with(this)
                    .load(result.getDocumentPath())
                    .into(imgDoc);


            Glide.with(this)
                    .load(result.getDetectedFacePath())
                    .into(imgFace);

            txtResult.setText(result.toString());
        } else {

            txtResult.setText("Failed");

            imgDoc.setImageBitmap(null);
            imgFace.setImageBitmap(null);
        }
    }

    @AfterPermissionGranted(PERMISSION_RC)
    private void startScan() {
        if (isPermissionGranted(PERMISSION_RC)) {


         /*  available scan type
                1.mykad
              2. ikad
            3. passport
               4. mdr
              5.  sg
              6. ektp
              7. hk*/
            Intent intent = new Intent();
            intent.setAction("com.innov8tif.emas.scan");
            intent.putExtra(EXTRA_SCAN_TYPE, "mykad");
            intent.putExtra(EXTRA_VERIFY_FACE, mVerifiedFace);
            intent.putExtra(EXTRA_HIGH_THRESHOLD, true);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, 11);
            } else {
                showDownloadDialog();
            }


        }
    }

    private void showDownloadDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("EmasID not found")
                .setMessage("The latest version of Emas ID Demo app is required to continue.Please download from Playstore.")
                .setPositiveButton("OPEN PLAYSTORE", (dialogInterface, i) -> {
                    gotoPlaystore();

                }).setNegativeButton("CANCEL", null)
                .create();

        dialog.show();
    }

    private void gotoPlaystore() {
        final String appPackageName = "com.innov8tif.emas.id.demo";
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }


    private boolean isPermissionGranted(int requestCode) {
        String[] perms = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        boolean isGranted = EasyPermissions.hasPermissions(this, perms);
        if (!isGranted) {
            EasyPermissions.requestPermissions(this, "These permissions are required to use the app", requestCode, perms);
        }
        return isGranted;
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setRationale("Please allow the permission to use the app")
                    .setTitle(getString(R.string.app_name))
                    .setPositiveButton(getString(R.string.settings))
                    .setNegativeButton(getString(R.string.cancel))
                    .setRequestCode(requestCode)
                    .build()
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.verified) {
            mVerifiedFace = !item.isChecked();
            item.setChecked(!item.isChecked());

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

}
