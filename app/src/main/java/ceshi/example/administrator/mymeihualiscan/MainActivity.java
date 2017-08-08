package ceshi.example.administrator.mymeihualiscan;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.mymeihualiscan.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.zbar.lib.CaptureActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /*
    * 初始化控件
    * */
    private void initView() {
        scan = findViewById(R.id.scan);
        scan.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scan:
                DynamicAuthorization();
                break;
        }
    }

    /*
    *  6.0动态授权相机
    * */
    private void DynamicAuthorization() {
        AndPermission.with(getApplicationContext())
        .requestCode(200)
                .permission(
                        Manifest.permission.CAMERA)
                .callback(listener)
                .start();
    }

    /*
    * 权限回调
    * */
    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // Successfully.
            if(requestCode == 200) {
                Toast.makeText(getApplicationContext(),"相机授权成功",Toast.LENGTH_SHORT).show();
                //这里启动扫描二维码
               startActivity(new Intent(MainActivity.this, CaptureActivity.class));
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // Failure.
            if(requestCode == 200) {
                Toast.makeText(getApplicationContext(),"相机授权失败",Toast.LENGTH_SHORT).show();
            }
        }
    };
}
