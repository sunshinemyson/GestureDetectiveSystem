package GestureDetectiveSystem.Detectors;

import GestureDetectiveSystem.GenericTwoFingerInfoData;
import GestureDetectiveSystem.Detectors.GestureDetectorType;
import android.os.Parcelable;

public/* TODO abstract */class TwoFingerDragDetector extends AbstractGesture {
	public TwoFingerDragDetector() {
		super(2, GestureDetectorType.trigger_detector,
				GestureCategory.TWO_FINGER_DRAG_GESTURE);
	}

	@Override
	public Parcelable getGestureInfo() {
		// TODO: Client won't want get this. Need rewrite
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
}
