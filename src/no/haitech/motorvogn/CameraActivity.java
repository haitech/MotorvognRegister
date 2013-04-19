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
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

public class CameraActivity extends Activity {
    CameraView cameraView;
    Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        

        
        // Makes the window fullscreen, with titlebar.
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        // Sets the home button in titlebar.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        

        
        
        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content.
        cameraView = new CameraView(this, mCamera);
//        FrameLayout preview = (FrameLayout) findViewById(R.id.cameraView);
//        preview.addView(cameraView);
        setContentView(cameraView);
        Log.e("CameraActivity", "OnCreate.");
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed
                // in the Action Bar.
                Intent parentActivityIntent = new Intent(this, MainActivity.class);
                parentActivityIntent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(parentActivityIntent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        mCamera.release();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
    }
    
    
    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }
    
    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

}
