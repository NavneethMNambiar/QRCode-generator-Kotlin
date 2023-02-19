package com.example.qrcode

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.createBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {

    private lateinit var qrtext : EditText
    private lateinit var qrbutton : Button
    private lateinit var qrcode : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        qrbutton=findViewById(R.id.qrbutton)

        qrtext=findViewById(R.id.qrtext)

        qrcode=findViewById(R.id.qrcode)

        qrbutton.setOnClickListener{
            val data = qrtext.text.toString().trim()

            if(data.isEmpty()){
                Toast.makeText(this ,"enter some data",Toast.LENGTH_SHORT).show()

            }else{
                val writer=QRCodeWriter()
                try{

                    val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
                    val width= bitMatrix.width
                    val height= bitMatrix.height
                    val bmp= Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                    for (x in 0 until width){
                        for (y in 0 until height){
                            bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                        }
                    }

                    qrcode.setImageBitmap(bmp)
                }catch (e:WriterException){
                    e.printStackTrace()
                }
            }
        }
    }
}