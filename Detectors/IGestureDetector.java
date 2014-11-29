package GestureDetectiveSystem.Detectors;

import android.util.Log;
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
	protected int trigger_finger_count;
	protected GestureDetectorType mType;
	protected GestureDetectorStatus mStatus;
	protected GestureCategory mCategory;
	protected Parcelable mGestureInfo;

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
		// Implement Gesture procedure template
		if (inActivePreStatus() && meetActiveCond(evnt)) {
			mStatus = GestureDetectorStatus.Gesture_active;
			buildGestureData();
		} else if (inIdlePreStatus() && !meetMovingCond(evnt)) {
			mStatus = GestureDetectorStatus.Gesture_idle;
		} else if (inMovingPreStatus() && meetMovingCond(evnt)) {
			mStatus = GestureDetectorStatus.Gesture_moving;
			buildGestureData();
		} else if (inDismissPreStatus() && meetDismissCond(evnt)) {
			mStatus = GestureDetectorStatus.Gesture_dismiss;
			buildGestureData();
		} else {
			mStatus = GestureDetectorStatus.Gesture_listening;
		}
		return mStatus;
	}

	@Override
	public Parcelable getGestureInfo() {
		return mGestureInfo;
	}

	protected boolean meetActiveCond(MotionEvent e) {
		return (e.getPointerCount() == trigger_finger_count);
	}

	protected boolean meetMovingCond(MotionEvent e) {
		int action = e.getAction();
		return (e.getPointerCount() == trigger_finger_count && MotionEvent.ACTION_MOVE == (MotionEvent.ACTION_MOVE & action));
	}

	protected boolean meetDismissCond(MotionEvent e) {
		// TODO: check how ACTION_CANCEL will impact the result of pointer count
		int action = e.getAction();
		return (e.getPointerCount() != trigger_finger_count
				|| MotionEvent.ACTION_UP == (MotionEvent.ACTION_UP & action) || MotionEvent.ACTION_CANCEL == (MotionEvent.ACTION_CANCEL & action));
	}

	private boolean inActivePreStatus() {
		return (mStatus == GestureDetectorStatus.Gesture_listening);
	}

	private boolean inIdlePreStatus() {
		return (mStatus == GestureDetectorStatus.Gesture_active);
	}

	private boolean inMovingPreStatus() {
		return (mStatus == GestureDetectorStatus.Gesture_idle
				|| mStatus == GestureDetectorStatus.Gesture_moving || inIdlePreStatus());
	}

	private boolean inDismissPreStatus() {
		return (mStatus == GestureDetectorStatus.Gesture_moving
				|| mStatus == GestureDetectorStatus.Gesture_active || mStatus == GestureDetectorStatus.Gesture_idle);
	}

	protected void buildGestureData() {
	}
}
