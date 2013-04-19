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

import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * A SurfaceView for camera.
 * 
 * @author Thomas Le
 *
 */
public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    private final String TAG = "ERROR";
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraView(Context context, Camera camera) {
        super(context);
        // Initiate the SurfaceHolder
        mCamera = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);
        
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {

        if (mHolder.getSurface() == null){
          // preview surface does not exist
          return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e){
          // ignore: tried to stop a non-existent preview
        }

        // Sets preview size.
        Camera.Parameters parameters = mCamera.getParameters();  
        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();  
        Camera.Size cs = sizes.get(0);  
        parameters.setPreviewSize(cs.width, cs.height);
        mCamera.setParameters(parameters);

        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
        Log.e(TAG, "SurfaceChanged.");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }
    
    
    /**
     * TODO: For later use, ANPR.
     * @return {@code Camera} object.
     */
    public Camera getCamera() {
        return mCamera;
    }

}
