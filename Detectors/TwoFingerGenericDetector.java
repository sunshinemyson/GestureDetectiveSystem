package GestureDetectiveSystem.Detectors;

import GestureDetectiveSystem.GenericTwoFingerInfoData;
import GestureDetectiveSystem.Detectors.GestureDetectorType;
import android.os.Parcelable;

public/* TODO abstract */class TwoFingerGenericDetector extends AbstractGesture {
	public TwoFingerGenericDetector() {
		super(2, GestureDetectorType.trigger_detector,
				GestureCategory.two_finger_generic);
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
}
