package GestureDetectiveSystem.Detectors;

public enum GestureDetectorStatus {
	Gesture_listening,	//! default status
	Gesture_active,			//! gesture active
	Gesture_idle,			//! middle status between active and moving
	Gesture_moving,			//! gesture should at this status at it's lifetime
	Gesture_dismiss /*= Gesture_listening*/,		//! gesture disappear
	
	Gesture_Error
}
