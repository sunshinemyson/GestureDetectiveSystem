package GestureDetectiveSystem;

import GestureDetectiveSystem.Detectors.GestureCategory;
import GestureDetectiveSystem.Detectors.IGestureDetector;
import GestureDetectiveSystem.Detectors.ThreeFingerDragDetector;
import GestureDetectiveSystem.Detectors.TwoFingerDragDetector;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class GestureDetectiveSystem {

	private ArrayList<IGestureDetector> mDetectors;
	private HashMap<GestureCategory, ArrayList<IGestureHandler>> mHanlderMap;

	public GestureDetectiveSystem(boolean bCustomize) {
		mDetectors = new ArrayList<IGestureDetector>();
		mHanlderMap = new HashMap<GestureCategory, ArrayList<IGestureHandler>>();
		if (!bCustomize)
			pwrp();
	}

	public void finalize() {
		// TODO: function name may not right
		pwdn();
	}

	public GestureDetectiveSystem customize(GestureCategory gCat) {
		switch (gCat) {
		case THREE_FINGER_DRAG_GESTURE: {
			mDetectors.add(new ThreeFingerDragDetector());
			break;
		}
		case TWO_FINGER_DRAG_GESTURE: {
			mDetectors.add(new TwoFingerDragDetector());
			break;
		}
		// add new case here
		default: {
			// Not support this gesture
			// TODO: throw an exception
			break;
		}
		}
		return this;
	}

	// @return true : event will be consumed by a gesture
	// false: no gesture will trigger by current event
	public boolean receieveMotionEvent(MotionEvent Evnt) {
		// TODO handle system's event
		boolean bConsumed = false;
		int cntDetectors = mDetectors.size();

		for (int idx = 0; idx < cntDetectors; ++idx) {
			IGestureDetector detector = mDetectors.get(idx);
			assert (detector != null);

			boolean gestureStart, gestureGoing, gestureEnd;
			gestureStart = gestureGoing = gestureEnd = false;
			switch (detector.detect(Evnt)) {
			case Gesture_listening: {// Current gesture doesn't trigged
				break;
			}
			case Gesture_active: {
				gestureStart = true;
				bConsumed = true;
				break;
			}
			case Gesture_moving: {
				gestureGoing = true;
				bConsumed = true;
				break;
			}
			case Gesture_dismiss: {
				gestureEnd = true;
				break;
			}
			default:
				break;

			}
			if (gestureStart || gestureGoing || gestureEnd) {
				// trigger handler
				GestureCategory cat = detector.getDetectorCategory();
				Parcelable gestureInfo = detector.getGestureInfo();
				ArrayList<IGestureHandler> gHandlers = mHanlderMap.get(cat);
				if (null != gHandlers && !gHandlers.isEmpty()) {
					IGestureHandler handler;
					for (int j = 0; j < gHandlers.size(); ++j) {
						handler = gHandlers.get(j);
						assert (handler != null);
						if (gestureStart)
							handler.onGestureStarted(gestureInfo);
						if (gestureGoing)
							handler.onGestureMoving(gestureInfo);
						if (gestureEnd)
							handler.onGestureEnd(gestureInfo);
					}
				} else {
					Log.e("TODO",
							"Why your system could detect a gesture that you didn't install a handler?");
				}
			}
		}

		return bConsumed;
	}

	public void registGestureHandler(GestureCategory whichGesture,
			IGestureHandler ihandler) {
		ArrayList<IGestureHandler> handlers = mHanlderMap.get(whichGesture);
		if (handlers == null) {
			handlers = new ArrayList<IGestureHandler>();
		}
		handlers.add(ihandler);
		mHanlderMap.put(whichGesture, handlers);
		// TODO
	}

	private void pwrp() {
		// add all Gesture detector
		mDetectors.add(new TwoFingerDragDetector());
		mDetectors.add(new ThreeFingerDragDetector());
		// mDetectors.add(new TwoFingerPinchZoomIn() );
		// mDetectors.add(new TwoFingerPinchZoomOut() );
	}

	private void pwdn() {

	}

}
