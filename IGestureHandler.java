package GestureDetectiveSystem;

import android.os.Parcelable; 

public interface IGestureHandler {
	
	public void onGestureStarted( Parcelable gestureInfo );
	
	public void onGestureMoving( Parcelable gestureInfo );
	
	public void onGestureEnd( Parcelable gestureInfo);
}
