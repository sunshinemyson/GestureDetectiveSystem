package GestureDetectiveSystem;

import android.os.Parcel;
import android.os.Parcelable;

class TwoFingerSwipe implements Parcelable{
	private int direction;
	private int deltaX;
	private int deltaY;
	
	public int describeContents(){
		return 0;
	}
	
	public void writeToParcel( Parcel out, int flags){
		out.writeInt(direction);
		out.writeInt(deltaX);
		out.writeInt(deltaY);
	}
	
	public static final Parcelable.Creator<TwoFingerSwipe> CREATOR 
	= new Parcelable.Creator<TwoFingerSwipe>() {
		
		public TwoFingerSwipe createFromParcel( Parcel in ){
			return new TwoFingerSwipe(in);
		}
		
		public TwoFingerSwipe[] newArray(int size){
			return new TwoFingerSwipe[size];
		}
	};
	
	private TwoFingerSwipe( Parcel in ){
		direction = in.readInt();
		deltaX = in.readInt();
		deltaY = in.readInt();
	}
}
