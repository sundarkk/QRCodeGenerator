package com.apkglobal.qrcodegenerator;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class MainActivity extends AppCompatActivity {
Button btn_qrgenerate;
ImageView imageView;
EditText et_qrinput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_qrgenerate=findViewById(R.id.btn_generate);
        et_qrinput=findViewById(R.id.et_qrmsg);
        imageView=findViewById(R.id.qrimage);

        btn_qrgenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qrmsg=et_qrinput.getText().toString().trim();
                WindowManager windowManager=(WindowManager)
                        getSystemService(WINDOW_SERVICE);
                Display display= windowManager.getDefaultDisplay();
                Point point= new Point();
                display.getSize(point);
                int x=point.x;
                int y=point.y;
                int smallerDimension = x < y ? x : y;
                smallerDimension = smallerDimension * 3/4;
                QRCodeEncoder qrCodeEncoder=new
                        QRCodeEncoder(qrmsg,null,Contents.Type.TEXT,
                        BarcodeFormat.QR_CODE.toString(), smallerDimension);
                //convert qrcode into bitmap
                try {
                    Bitmap bitmap=qrCodeEncoder.encodeAsBitmap();
                    imageView.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
