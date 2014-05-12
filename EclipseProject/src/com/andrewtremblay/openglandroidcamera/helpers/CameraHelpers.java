package com.andrewtremblay.openglandroidcamera.helpers;

import java.util.List;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class CameraHelpers {
	/** Check if this device has a camera */
	public static boolean hasCamera(Context context) {
	    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
	        // this device has a camera
	        return true;
	    } else {
	        // no camera on this device
	        return false;
	    }
	}

	public static boolean cameraHasColorEffect(Camera c, String colorEffect) {
		List<String> listOfColorEffects = c.getParameters().getSupportedColorEffects();
		if(listOfColorEffects.indexOf(colorEffect) != -1)
		{
			return true;
		}
		return false;
	}

	
	public static String cameraParamsToString(Parameters p) {
		String toRet = "ColorEffects: " + getConcatenatedList(p.getSupportedColorEffects());
		toRet = toRet + "  SupportedFocusModes: " + getConcatenatedList(p.getSupportedFocusModes());
		toRet = toRet + "  SupportedFlashModes: " + getConcatenatedList(p.getSupportedFlashModes());		
		return toRet;
	}

	private static String getConcatenatedList(List<String> list) {
		String toRet = "";
		for (String entry : list) {
	    	toRet = toRet + " " + entry;				
		}
		return toRet;
	}
	
	
	
}
