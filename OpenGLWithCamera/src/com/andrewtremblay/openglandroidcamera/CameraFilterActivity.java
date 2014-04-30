/**
 * 
 */
package com.andrewtremblay.openglandroidcamera;

import com.officialandroidexample.gl2cameraeye.CamGLSurfaceView;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * @author andrewtremblay
 *
 */
public class CameraFilterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new CamGLSurfaceView(this);
        setContentView(mGLView);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }

    private GLSurfaceView mGLView;
}
