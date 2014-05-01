package com.andrewtremblay.openglandroidcamera.helpers;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ImageHelpers {
	public enum Direction { VERTICAL, HORIZONTAL };

	/**
	    Creates a new bitmap by flipping the specified bitmap
	    vertically or horizontally.
	    @param src        Bitmap to flip
	    @param type       Flip direction (horizontal or vertical)
	    @return           New bitmap created by flipping the given one
	                      vertically or horizontally as specified by
	                      the <code>type</code> parameter or
	                      the original bitmap if an unknown type
	                      is specified.
	**/
	public static Bitmap flip(Bitmap src, Direction type) {
	    Matrix matrix = new Matrix();

	    if(type == Direction.VERTICAL) {
	        matrix.preScale(1.0f, -1.0f);
	    }
	    else if(type == Direction.HORIZONTAL) {
	        matrix.preScale(-1.0f, 1.0f);
	    } else {
	        return src;
	    }

	    return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
	}

}
