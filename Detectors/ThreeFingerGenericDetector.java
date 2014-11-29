package GestureDetectiveSystem.Detectors;

import GestureDetectiveSystem.Detectors.Math.MPoint;

public abstract class ThreeFingerGenericDetector extends AbstractGesture {
	static final private int FINGER_COUNT = 3;
	protected int ActiveShreshold = 10;		//! Minus distance to trigger Active
	protected int MovingShreshold = 40;		//! Minus distance to trigger Moving
	
	protected MPoint mLastValidPoint = MPoint.makeInvalidValue();
	protected int mDelta = -1;	//! record gesture's change
	
	public ThreeFingerGenericDetector(){
		super(FINGER_COUNT, GestureDetectorType.trigger_detector, GestureCategory.three_finger_swipe_left);
	}
}
