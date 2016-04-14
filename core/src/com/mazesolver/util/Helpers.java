package com.mazesolver.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

/** class for various helper functions */
public class Helpers {

	public static int getTitleFontSize(float cameraHeight){
		return getFontSize(36, 720, cameraHeight);
	}

	public static int getFontSize(int referenceFontSize, float referenceScreenHeight, float cameraHeight){
		return (int) (referenceFontSize/referenceScreenHeight *  cameraHeight);
	}


	public static Color colorLuminance(Color color, float lum){
		/** returns the same color with luminance applied
		 *  e.g. lum = -0.1 returns a 10% darker color.
		 */
		Color tmp = new Color(color);
		return tmp.add(tmp.r * lum, tmp.g * lum, tmp.b * lum, 0);
	}

}
