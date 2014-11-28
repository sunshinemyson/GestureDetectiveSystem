package GestureDetectiveSystem.Detectors;

import GestureDetectiveSystem.GenericTwoFingerInfoData;
import GestureDetectiveSystem.Detectors.GestureDetectorType;
import android.os.Parcelable;
import android.view.MotionEvent;

public/* TODO abstract */class TwoFingerGenericDetector extends AbstractGesture {
	public TwoFingerGenericDetector() {
		super(2, GestureDetectorType.trigger_detector,
				GestureCategory.two_finger_generic);
	}

	@Override
	public GestureDetectorStatus detect(MotionEvent evnt) {
		if (trigger_finger_count == evnt.getPointerCount()) {
			// this event should impact this gesture
			if ((GestureDetectorStatus.Gesture_listening == mStatus || GestureDetectorStatus.Gesture_dismiss == mStatus)
					&& meetActiveThreshold(evnt)) {
				mStatus = GestureDetectorStatus.Gesture_active;
				active_not_trig = false;
			} else if ((GestureDetectorStatus.Gesture_active == mStatus || GestureDetectorStatus.Gesture_moving == mStatus)
					&& meetMovingThreshold(evnt)) {
				mStatus = GestureDetectorStatus.Gesture_moving;
			}
		} else {
			if (mStatus == GestureDetectorStatus.Gesture_active
					|| mStatus == GestureDetectorStatus.Gesture_moving) {
				mStatus = GestureDetectorStatus.Gesture_dismiss;
			} else if (mStatus == GestureDetectorStatus.Gesture_dismiss) {
				mStatus = GestureDetectorStatus.Gesture_listening;
			}
			active_not_trig = true;
		}

		if (GestureDetectorStatus.Gesture_active == mStatus) {
			return active_not_trig ? mStatus
					: GestureDetectorStatus.Gesture_idle;
		}

		return mStatus;
	}

	@Override
	public Parcelable getGestureInfo() {
		String gMessage = new String("TwoFingerGenericDetector");
		switch (mStatus) {
		case Gesture_active: {
			gMessage += "-> active";
			break;
		}
		case Gesture_moving: {
			gMessage += "-> moving";
			break;
		}
		case Gesture_dismiss: {
			gMessage += "-> dismissing";
			break;
		}
		default:
			gMessage += "-> Invalid status notificaiton";
			break;
		}
		GenericTwoFingerInfoData gInfo = new GenericTwoFingerInfoData(gMessage,
				1, 1);

		return gInfo;
	}

	// TODO: make this function virtual
	private boolean meetActiveThreshold(MotionEvent evnt) {
		return evnt.getPointerCount() == trigger_finger_count;
	}

	// TODO: make this function virtual
	private boolean meetMovingThreshold(MotionEvent evnt) {
		return evnt.getAction() == MotionEvent.ACTION_MOVE;
	}
}
