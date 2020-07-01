package com.example.pdfgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
Button button;
TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editText= findViewById(R.id.textSpace);
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        //createPDF();
    }

public void Onclick(View view){
        createPDF(editText.getText().toString());
}
    private void createPDF(final String text) {

        //here will be the new code
                PdfDocument myPdfDocument = new PdfDocument();
                Paint myPaint = new Paint();
                TextPaint myTextPaint=new TextPaint();


                PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
                PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo1);
                Canvas canvas = myPage1.getCanvas();
                myPaint.setColor(Color.BLACK);
                StaticLayout myTextLayout = new StaticLayout( text,myTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL,1.0f,1.0f,false);
                canvas.save();

                int textX =10;
                int textY=10;
                canvas.translate(textX,textY);
                myTextLayout.draw(canvas);
                canvas.restore();
                //canvas.drawText(String.valueOf(canvas),20,40,myPaint);

                myPdfDocument.finishPage(myPage1);




                File file= new File(Environment.getExternalStorageDirectory(),"/FirstPDF.pdf");
                try {
                    myPdfDocument.writeTo(new FileOutputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myPdfDocument.close();
            }

    }

