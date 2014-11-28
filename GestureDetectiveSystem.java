package GestureDetectiveSystem;

import GestureDetectiveSystem.Detectors.GestureCategory;
import GestureDetectiveSystem.Detectors.GestureDetectorStatus;
import GestureDetectiveSystem.Detectors.IGestureDetector;
import GestureDetectiveSystem.Detectors.TwoFingerGenericDetector;
import GestureDetectiveSystem.Detectors.TwoFingerPinchZoomIn;
import GestureDetectiveSystem.Detectors.TwoFingerPinchZoomOut;
import android.os.Parcelable;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class GestureDetectiveSystem {

	public GestureDetectiveSystem() {
		mDetectors = new ArrayList<IGestureDetector>();
		mHanlderMap = new HashMap<GestureCategory, ArrayList<IGestureHandler> >();
		pwrp();
	}

	public void finalize() {
		// TODO: function name may not right
		pwdn();
	}

	// @return true : event will be consumed by a gesture
	// false: no gesture will trigger by current event
	public boolean receieveMotionEvent(MotionEvent Evnt) {
		// TODO handle system's event
		boolean bConsumed = false;
		int cntDetectors = mDetectors.size();
		
		for( int idx = 0; idx < cntDetectors; ++idx ){
			IGestureDetector detector = mDetectors.get(idx);
			assert( detector != null );

			boolean gestureStart, gestureGoing, gestureEnd;
			gestureStart = gestureGoing = gestureEnd = false;
			switch( detector.detect(Evnt) ){
				case Gesture_listening:{//Current gesture doesn't trigged
					break;
				}
				case Gesture_active:{
					gestureStart = true;
					bConsumed = true;
					break;
				}
				case Gesture_moving:{
					gestureGoing = true;
					bConsumed = true;
					break;
				}
				case Gesture_dismiss:{
					gestureEnd = true;
					break;
				}
				default:
					break;
					
			}
			if( gestureStart || gestureGoing || gestureEnd ){
				// trigger handler
				GestureCategory cat = detector.getDetectorCategory();
				Parcelable gestureInfo = detector.getGestureInfo();
				ArrayList<IGestureHandler> gHandlers = mHanlderMap.get( cat );
				if( !gHandlers.isEmpty() ){
					IGestureHandler handler;
					for( int j = 0; j < gHandlers.size(); ++j ){
						handler = gHandlers.get(j);
						assert( handler != null );
						if( gestureStart ) handler.onGestureStarted(gestureInfo);	
						if( gestureGoing ) handler.onGestureMoving(gestureInfo);
						if( gestureEnd ) handler.onGestureEnd(gestureInfo);
					}
				}
			}
		}

		return bConsumed;
	}

	public void registGestureHandler(GestureCategory whichGesture,
			IGestureHandler ihandler) {
		ArrayList<IGestureHandler> handlers = mHanlderMap.get(whichGesture);
		if( handlers == null ){
			handlers = new ArrayList<IGestureHandler>();
		}
		handlers.add(ihandler);
		mHanlderMap.put(whichGesture, handlers);
		// TODO
	}

	private void pwrp() {
		// add all Gesture detector
		mDetectors.add( new TwoFingerGenericDetector() );
//		mDetectors.add(new TwoFingerPinchZoomIn() );
//		mDetectors.add(new TwoFingerPinchZoomOut() );
	}

	private void pwdn() {
		
	}

	private ArrayList<IGestureDetector> mDetectors;

	private HashMap<GestureCategory, ArrayList<IGestureHandler> > mHanlderMap;

}
