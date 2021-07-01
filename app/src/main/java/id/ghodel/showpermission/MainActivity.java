package id.ghodel.showpermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, PermissionUtils.PermissionResultCallback {

    private Button cekPermission;

    private ArrayList<String> permissions=new ArrayList<>();
    private PermissionUtils permissionUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionUtils = new PermissionUtils(MainActivity.this);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        cekPermission = findViewById(R.id.cek_permission);
        cekPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionUtils.check_permission(permissions,"\n" +
                        "Jelaskan di sini mengapa aplikasi memerlukan izin",1);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        permissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);

    }

    @Override
    public void PermissionGranted(int request_code) {
        Log.i("PERMISSION","DI IZINKAN " + request_code);
    }

    @Override
    public void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions) {
        Log.i("PERMISSION SEBAGIAN","DI IZINKAN SEBAGIAN : " + request_code + granted_permissions.toString());
    }

    @Override
    public void PermissionDenied(int request_code) {
        Log.i("PERMISSION","TIDAK DI IZINKAN  : " + request_code);
    }

    @Override
    public void NeverAskAgain(int request_code) {
        Log.i("PERMISSION","JANGAN PERNAH TANYA LAGI : " + request_code);
    }
}