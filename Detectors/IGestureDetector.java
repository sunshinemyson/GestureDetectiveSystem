package GestureDetectiveSystem.Detectors;

import android.view.MotionEvent;
import android.os.Parcelable;

enum GestureDetectorType {
	trigger_detector, continuous_detecotr,

	cnt_detector
};

public interface IGestureDetector {

	abstract public GestureDetectorType getDetectorType();

	abstract public GestureCategory getDetectorCategory();

	abstract public GestureDetectorStatus detect(MotionEvent evnt);

	abstract public Parcelable getGestureInfo();

}

abstract class AbstractGesture implements IGestureDetector {
	public int trigger_finger_count;
	public GestureDetectorType mType;
	public GestureDetectorStatus mStatus;
	public GestureCategory mCategory;

	public boolean active_not_trig = true;

	AbstractGesture() {
		trigger_finger_count = -1;
		mType = GestureDetectorType.cnt_detector;
		mStatus = GestureDetectorStatus.Gesture_listening;
		mCategory = GestureCategory.cnt_gesture_category; // invalid category

	}

	AbstractGesture(int aCount, GestureDetectorType aType, GestureCategory aCat) {

		// assert( aCount >0 && aCount < 10);
		trigger_finger_count = aCount;
		mType = aType;
		mCategory = aCat;
		mStatus = GestureDetectorStatus.Gesture_listening;
	}

	@Override
	public GestureDetectorType getDetectorType() {
		return mType;
	}

	@Override
	public GestureCategory getDetectorCategory() {
		return mCategory;
	}

	@Override
	public GestureDetectorStatus detect(MotionEvent evnt) {
		return null;
	}

	@Override
	public Parcelable getGestureInfo() {
		return null;
	}

}
