package com.officialandroidexample.gl2cameraeye;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.officialandroidexample.gl2cameraeye.CamRenderer;

public class CamGLSurfaceView extends GLSurfaceView implements SensorEventListener {
    public CamGLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        mRenderer = new CamRenderer(context);
        setRenderer(mRenderer);

        mSensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        mAcceleration = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
    }

    public boolean onTouchEvent(final MotionEvent event) {
        queueEvent(new Runnable(){
                public void run() {
                mRenderer.setPosition(event.getX() / getWidth(),
                                      event.getY() / getHeight());
            }});
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        mCamera.stopPreview();
        mCamera.release();

        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        mCamera = Camera.open();
        Camera.Parameters p = mCamera.getParameters();
        // No changes to default camera parameters
        mCamera.setParameters(p);

        queueEvent(new Runnable(){
                public void run() {
                    mRenderer.setCamera(mCamera);
                }});

        mSensorManager.registerListener(this, mAcceleration, SensorManager.SENSOR_DELAY_GAME);
        super.onResume();
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            final float[] accelerationVector = event.values;
            queueEvent(new Runnable(){
                    public void run() {
                        mRenderer.setAcceleration(accelerationVector);
                    }});
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Ignoring sensor accuracy changes.
    }

    CamRenderer mRenderer;
    Camera mCamera;

    SensorManager mSensorManager;
    Sensor mAcceleration;
}

