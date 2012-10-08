package com.example;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainImageActivity extends Activity 
{
	private ImageView iv;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageactivity_main);
    
        //get the ImageView
        iv=(ImageView)findViewById(R.id.myimage);
        
        //display the image
        iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.sample1));
        
        iv.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent i= new Intent(MainImageActivity.this,DownloadAndSaveImage.class);
				startActivity(i);
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.imageactivity_main, menu);
        return true;
    }
}
