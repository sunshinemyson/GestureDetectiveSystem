package GestureDetectiveSystem;

import android.os.Parcelable;

//TODO: for trigger type gesture: moving equal start event, they should get the same gesture information
public interface IGestureHandler {

	public void onGestureStarted(Parcelable gestureInfo);

	public void onGestureMoving(Parcelable gestureInfo);

	public void onGestureEnd(Parcelable gestureInfo);
}
