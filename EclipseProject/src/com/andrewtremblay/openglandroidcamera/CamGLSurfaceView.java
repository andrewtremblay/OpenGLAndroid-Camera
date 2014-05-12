package com.andrewtremblay.openglandroidcamera;

import com.andrewtremblay.openglandroidcamera.helpers.CameraHelpers;
import com.andrewtremblay.openglandroidcamera.helpers.DebugHelpers;

import android.content.Context;
import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class CamGLSurfaceView extends GLSurfaceView  {
    CamRenderer mRenderer;
    Camera mCamera;

    public CamGLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        mRenderer = new CamRenderer(context);
        setRenderer(mRenderer);
        
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    queueEvent(new Runnable(){
                        public void run() {
                        mRenderer.setPosition(event.getX() / getWidth(),
                                              event.getY() / getHeight());
                    }});
                    return true;
                }
                return false;
            }
        });
    
    }

    
    @Override
    public void onPause() {
        super.onPause();
        mCamera.stopPreview();
        mCamera.release();
    }

    @Override
    public void onResume() {
        mCamera = Camera.open();
        Camera.Parameters p = mCamera.getParameters();
        // No changes to default camera parameters
        Log.d(DebugHelpers.TAG, CameraHelpers.cameraParamsToString(p));
        
        mCamera.setParameters(p);

        queueEvent(new Runnable(){
                public void run() {
                    mRenderer.setCamera(mCamera);
                }});

        super.onResume();
    }

}

