package com.mazesolver.util;

import com.badlogic.gdx.math.Interpolation;

public class Tween {
	
	private Interpolation interpolation;
	
	private float time, duration;
	private float startVal, currentVal, targetVal;

	public Tween(){
		this(2.5f, "linear");
	}
	
	public Tween(float duration, String interpolationType) {
		this.time = 0;
		this.startVal = 0; 
		this.currentVal = 0; 
		this.targetVal = 0;
		
		this.duration = duration;
		this.interpolation = getInterpolation(interpolationType);
	}

	/** Available interpolations are: 
	 * "bounce", "bounceIn", "bounceOut", "circle", "circleIn", "circleOut", "elastic", "elasticIn", 
	 * "elasticOut", "exp10", "exp10In", "exp10Out", "exp5", "exp5In", "exp5Out", "fade", "linear", 
	 * "pow2", "pow2In", "pow2Out", "pow3", "pow3In", "pow3Out", "pow4", "pow4In", "pow4Out", "pow5", 
	 * "pow5In", "pow5Out", "sine", "sineIn", "sineOut", "swing", "swingIn", "swingOut" */
	public void setInterPolation(String interpolationType){
		this.interpolation = getInterpolation(interpolationType);
	}
	
	public float update(float dt){
		
		
		if(this.currentVal != this.targetVal){
			this.time += dt;
			
			float percent = this.time / this.duration;
			float inter = this.interpolation.apply(percent);
			
			this.currentVal = this.startVal + (this.targetVal-this.startVal)*inter;
			
			if(this.time > this.duration){
				this.time = 0;
				this.currentVal = this.targetVal;
			}
		}
		return this.currentVal;
	}
	
	private Interpolation getInterpolation(String interpolationType) {
		try {
			return (Interpolation) Interpolation.class.getField(interpolationType).get(null);
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		} catch(NoSuchFieldException e) {
			e.printStackTrace();
		} catch(SecurityException e) {
			e.printStackTrace();
		}
		return Interpolation.linear;
	}

	public void setTargetValue(float targetVal) {
		this.startVal = this.currentVal;
		this.targetVal = targetVal;
	}

	public void setCurrentValue(float currentVal) {
		this.currentVal = currentVal;
	}

	public float getTargetValue() {
		return this.targetVal;
	}
	
}
