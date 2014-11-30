package GestureDetectiveSystem;

import android.os.Parcel;
import android.os.Parcelable;

public class ThreeFingerGestureData implements Parcelable{
	
	public int mDirect;
	public int mDiff;

	public ThreeFingerGestureData(int deltaX, int direction) {
		mDiff= deltaX;
		mDirect = direction;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(mDiff);
		out.writeInt(mDirect);
	}

	public static final Parcelable.Creator<ThreeFingerGestureData> CREATOR = new Parcelable.Creator<ThreeFingerGestureData>() {

		public ThreeFingerGestureData createFromParcel(Parcel in) {
			return new ThreeFingerGestureData(in);
		}

		public ThreeFingerGestureData[] newArray(int size) {
			return new ThreeFingerGestureData[size];
		}
	};

	private ThreeFingerGestureData(Parcel in) {
		mDiff = in.readInt();
		mDirect = in.readInt();
	}

}
