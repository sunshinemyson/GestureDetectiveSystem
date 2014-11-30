package GestureDetectiveSystem.Detectors;

import android.util.Log;
import android.view.MotionEvent;
import GestureDetectiveSystem.ThreeFingerGestureData;
import GestureDetectiveSystem.Detectors.Math.MPoint;

public class ThreeFingerDragDetector extends AbstractGesture {
	static final private int FINGER_COUNT = 3;
	protected int ActiveShreshold = 10; // ! Minus distance to trigger Active
	protected float MovingShreshold = 40.0f; // ! Minus distance to trigger
												// Moving

	protected MPoint mLastValidPoint = MPoint.makeInvalidValue();
	protected int mDelta = -1; // ! record gesture's change
	protected int mDirection = -1; // ! -1 == invalid direction

	// Vertical : 0; Horizontal : 1

	public ThreeFingerDragDetector() {
		super(FINGER_COUNT, GestureDetectorType.trigger_detector,
				GestureCategory.THREE_FINGER_DRAG_GESTURE);
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

			// TODO: use center of three finger as Point
			MPoint currentPoint = new MPoint((int) e.getX(), (int) e.getY());
			float difference = MPoint.distance(currentPoint, mLastValidPoint);
			if (Math.abs(difference) < this.MovingShreshold) {
				Log.e("ThreeFingerSwipe", "gesture doesn't moving: enter idle status");
				bMeet = false;
			} else {
				Log.e("ThreeFingerSwipe", "Gesture meet moving condition");
				bMeet = true;
				int deltaX = currentPoint.x - mLastValidPoint.x;
				int deltaY = currentPoint.y - mLastValidPoint.y;

				if (-1 == mDirection) {
					mDirection = (Math.abs(deltaX) > Math.abs(deltaY)) ? 0 : 1;
				}

				mDelta = (1 == mDirection) ? deltaY : deltaX;
				
				//mLastValidPoint = currentPoint;
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
		mGestureInfo = new ThreeFingerGestureData(mDelta, mDirection);
	}

	@Override
	protected void cleanUp() {
		mGestureInfo = null;
		mDelta = -1;
		mDirection = -1;
	}
}
