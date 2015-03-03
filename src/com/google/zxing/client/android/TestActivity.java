package com.google.zxing.client.android;

import java.util.Hashtable;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.test.RGBLuminanceSource;

public class TestActivity extends Activity {
	private static final String TAG = "TestActivity";
	
	String path;
	private final static String pFormat = "png";

	    

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testactivity);
		//set
		Button btn2 = (Button)findViewById(R.id.Button03);
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				ImageView imgView = (ImageView)findViewById(R.id.ImageView01);
				QRCodeReader reader = new QRCodeReader(); 
		        int width = 200, height = 200;  
		        QRCodeWriter writer = new QRCodeWriter();
		        try {
		        	EditText edit = (EditText)findViewById(R.id.EditText01);
		        	//edit.getText();
		        	Log.i(TAG, "编辑框中的内容： " + edit.getText().toString());
//		        	System.out.println(edit.getText().toString());
		        	if(edit.getText().toString().length()<1)
		        	{
		        		return;
		        	}
		        	
		        	BitMatrix martix = writer.encode(edit.getText().toString(), BarcodeFormat.QR_CODE, width, height);
		        	System.out.println("w:"+martix.width+"h:"+martix.height); 
		        	String imageFormat = "png"; 
		        	Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		        	hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		        BitMatrix bitMatrix = new QRCodeWriter().encode(edit.getText().toString(),BarcodeFormat.QR_CODE, width, height,hints);
		        	 int[] pixels = new int[width * height];    
		             for (int y = 0; y < height; y++) {    
		                 for (int x = 0; x < width; x++) {    
		                     if(bitMatrix.get(x, y)){    
		                         pixels[y * width + x] = 0xff000000;    
		                     }else{
		                    	 pixels[y * width + x] = 0xffffffff;
		                     }    
		                         
		                 }    
		             }    
		                 
		             Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);    
		                
		             bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		             imgView.setImageBitmap(bitmap);
//		             RGBLuminanceSource source = new RGBLuminanceSource(bitmap);
//		            LuminanceSource source = new RGBLuminanceSource(path);  
//		            BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));  
//		             QRCodeReader reader2= new QRCodeReader();
//		             Result result = reader2.decode(bitmap1);
//		             System.out.println("res"+result.getText());
		             
				} catch (WriterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Button btn1 = (Button)findViewById(R.id.Button01);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ImageView imgView =(ImageView)findViewById(R.id.ImageView01);
				Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
				hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
				Bitmap bitmap = ((BitmapDrawable)imgView.getDrawable()).getBitmap();
				RGBLuminanceSource source = new RGBLuminanceSource(bitmap);
//		            LuminanceSource source = new RGBLuminanceSource(path);  
		            BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));  
		             QRCodeReader reader2= new QRCodeReader();
		             Result result;
		           try {
		             result = reader2.decode(bitmap1,hints);
		             System.out.println("res"+result.getText());
		             TextView text  = (TextView)findViewById(R.id.TextView01);
		            text.setText(result.getText());
					} catch (NotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ChecksumException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            // System.out.println("res"+result.getText());
			}
		});
	}
}
