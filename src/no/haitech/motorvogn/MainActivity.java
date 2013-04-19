/*
 * Copyright (C) 2013 Thomas Le
 * 
 * This file is part of MotorvognRegister.
 *
 * MotorvognRegister is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MotorvognRegister is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public license
 * along with MotorvognRegister. If not, see <http://www.gnu.org/licenses/>.
 */
package no.haitech.motorvogn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * An application for checking vehicle identification number against Norwegian
 * Public Roads Administrator, also known as Staten vegvesen.
 * 
 * @author Thomas Le
 *
 */
public class MainActivity extends Activity {
    private Context context;
    private Button bCamera;
    private Button bManual;
    private Button bHistory;
    private Button bSetup;

    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        
        // Makes the window fullscreen, with titlebar.
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        int buttonAlphaValue = 230;
        
        // Setting alpha value on buttons.
        bCamera = (Button) findViewById(R.id.bCamera);
        bManual = (Button) findViewById(R.id.bManual);
        bHistory = (Button) findViewById(R.id.bHistory);
        bSetup = (Button) findViewById(R.id.bPreferences);
        bCamera.getBackground().setAlpha(buttonAlphaValue);
        bManual.getBackground().setAlpha(buttonAlphaValue);
        bHistory.getBackground().setAlpha(buttonAlphaValue);
        bSetup.getBackground().setAlpha(buttonAlphaValue);
    }

    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    
    /**
     * OnClick listener for Camera button.
     * 
     * @param view a basic building block for user interface components, 
     *        {@code View}.
     */
    public void cameraOnClick(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        
//        // Demo demo
//        Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
//        imagesFolder.mkdirs(); // <----
//        File image = new File(imagesFolder, "image_001.jpg");
//        Uri uriSavedImage = Uri.fromFile(image);
//        imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
//        startActivityForResult(imageIntent,0);
//        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    
    
    
    /**
     * OnClick listener for Manual button.
     * Opens a new activity for {@code ManualActivity}
     * 
     * @param view a basic building block for user interface components, 
     *        {@code View}.
     */
    public void manualOnClick(View view) {
        Intent intent = new Intent(this, ManualActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    
    
    
    /**
     * OnClick listener for History button.
     * 
     * @param view
     */
    public void historyOnClick(View view) {
        Toast.makeText(context, "Kommer snart, Historikk", Toast.LENGTH_SHORT).show();
    }
    
    
    
    /**
     * OnClick listener for Preferences button.
     * 
     * @param view a basic building block for user interface components, 
     *        {@code View}.
     */
    public void preferencesOnClick(View view) {
        Toast.makeText(context, "Kommer snart, Innstillinger", Toast.LENGTH_SHORT).show();
    }
    
    
    
    /**
     * OnClick listener for GitHub button.
     * 
     * @param view a basic building block for user interface components, 
     *        {@code View}.
     */
    public void githubOnClick(View view) {
        Toast.makeText(context, "Kommer snart, GitHub", Toast.LENGTH_SHORT).show();
    }
    
    
    
    /**
     * OnClick listener for E-mail button.
     * 
     * @param view a basic building block for user interface components, 
     *        {@code View}.
     */
    public void emailOnClick(View view) {
        Toast.makeText(context, "Kommer snart, E-post", Toast.LENGTH_SHORT).show();
    }

}
