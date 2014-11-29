package GestureDetectiveSystem.Detectors;

import GestureDetectiveSystem.ThreeFingerSwipeLeftData;
import GestureDetectiveSystem.Detectors.Math.MPoint;
import android.util.Log;
import android.view.MotionEvent;

public class ThreeFingerSwipeLeftDetector extends ThreeFingerGenericDetector {

	public ThreeFingerSwipeLeftDetector() {
		// TODO Auto-generated constructor stub
		super();
		mType = GestureDetectorType.continuous_detecotr;
		mCategory = GestureCategory.three_finger_swipe_left;
	}

	@Override
	protected boolean meetActiveCond(MotionEvent e) {
		boolean bMeet = super.meetActiveCond(e);
		if (bMeet) {
			// Basic condition meet!
			// We could add more check here, but i let it empty for now!
			// TODO: save center of three finger as Point
			mLastValidPoint = new MPoint((int) e.getX(), (int) e.getY());
			assert (mLastValidPoint != null);
		}
		return bMeet;
	}

	@Override
	protected boolean meetMovingCond(MotionEvent e) {
		boolean bMeet = super.meetMovingCond(e);
		if (bMeet) {
			// Basic condition meet!
			if (!this.mLastValidPoint.valid()) {
				// Didn't start moving under our rule
				// assert( false ); //TODO: we record first valid point when
				// gesture active
			}

			MPoint currentPoint = new MPoint((int) e.getX(), (int) e.getY());
			if (MPoint.distance(currentPoint, mLastValidPoint) < this.MovingShreshold) {
				Log.d("ThreeFingerSwipeLeft", "gesture doesn't moving");
				bMeet = false;
			}
		}
		return bMeet;
	}

	@Override
	protected boolean meetDismissCond(MotionEvent e) {
		return super.meetDismissCond(e);
	}

	@Override
	protected void buildGestureData() {
		mGestureInfo = new ThreeFingerSwipeLeftData(mDelta);
	}
}
