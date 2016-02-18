package com.mazesolver.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

/** class for various helper functions */
public class Helpers {


	public static Color colorLuminance(Color color, float lum){
		/** returns the same color with luminance applied
		 *  e.g. lum = -0.1 returns a 10% darker color.
		 */
		Color tmp = new Color(color);
		return tmp.add(tmp.r * lum, tmp.g * lum, tmp.b * lum, 0);
	}

}
