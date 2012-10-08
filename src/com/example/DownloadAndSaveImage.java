package com.example;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DownloadAndSaveImage extends Activity
{

	 private Button b1,upload,crop;
	 private String url;
	 private ImageView myImage;
	 private Bitmap myBitmap;
	 private String PATH = Environment.getExternalStorageDirectory().toString();
	 
	  
	 @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.downloadactivity);
	        
	        b1=(Button)findViewById(R.id.button2);
	        upload=(Button)findViewById(R.id.button3);
	        crop=(Button)findViewById(R.id.crop);
		       
	        b1.setClickable(false);
	        new DownloadAndSaveTask().execute("http://karanbalkar.com/wp-content/uploads/2012/09/jellybean2.jpg");
	        
	        //upload an image from sdcard
	        upload.setOnClickListener(new OnClickListener() 
	        {
				@Override
				public void onClick(View v) 
				{
					// TODO Auto-generated method stub
					
					File imgFile = new  File(PATH,"sampleimage4.jpg");
					if(imgFile.exists())
					{

						myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
					    myImage = (ImageView) findViewById(R.id.uploadimage);
					    myImage.setImageBitmap(myBitmap);

					}

					
				}
			});
	        
	        
	        //crop the image uploaded
	        crop.setOnClickListener(new OnClickListener() 
		     {	
				@Override
				public void onClick(View v) 
				{
					// TODO Auto-generated method stub
					//remove 30 pixels
					 Bitmap croppedBmp = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight()-30);
					 myImage.setImageBitmap(croppedBmp);

				}
			});
	              
	    }
	 
	 
	    private class DownloadAndSaveTask extends AsyncTask<String, Void, Bitmap> 
	     {
		   
		    private final ProgressDialog d=new ProgressDialog(DownloadAndSaveImage.this);
		   
		    @Override
		    protected void onPreExecute() 
		    {
		      super.onPreExecute();
		      d.setMessage("Downloading image...Please wait!!");
		      d.setCancelable(false);
		      d.show();
		      
		   }

		   @Override
		   protected Bitmap doInBackground(String... params) 
		   {
			     url = params[0];
			   
		     	 try 
		    	 	{            
		    		 return BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream());
		    	 	} 
		     	 catch (MalformedURLException e) 
		     	 	{
		    		 e.printStackTrace();
		    		 return null;
		    		} 
		     	 catch (IOException e) 
		     	    {
		    		 e.printStackTrace();
		    		 return null;
		     	    }
		      	 
				     	
	         }
		   
		    @Override
		    protected void onPostExecute(final Bitmap result) 
		     {
		        super.onPostExecute(result); 
		        if(result!=null)
		        {	  
		        	d.dismiss();
		        	b1.setClickable(true);
		        	b1.setOnClickListener(new OnClickListener() 
		        		{
		        			@Override
		        			public void onClick(View v)
		        			{
								// TODO Auto-generated method stub
								OutputStream outStream = null;
								File file = new File(PATH, "sampleimage4.jpg");
								try 
								   {
								    outStream = new FileOutputStream(file);
								    result.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
								    outStream.flush();
								    outStream.close();   
								    Toast.makeText(DownloadAndSaveImage.this, "Saved", Toast.LENGTH_LONG).show();
								   } 
								   catch (FileNotFoundException e) 
								    {
								    	 // TODO Auto-generated catch block
								    	  e.printStackTrace();
								    } 
								    catch (IOException e)
								    {
								    	  // TODO Auto-generated catch block
								    	  e.printStackTrace();
								    }
									
						     }
				      
				         });
				         	
				      }
		        
		        } //end postexecute
   
	 } //end asynctask class
		
	
} //end main class
