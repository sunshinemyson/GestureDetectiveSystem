package GestureDetectiveSystem;

import android.os.Parcel;
import android.os.Parcelable;

public class ThreeFingerSwipeLeftData implements Parcelable{
	public int x;

	public ThreeFingerSwipeLeftData(int deltaX) {
		x = deltaX;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(x);
	}

	public static final Parcelable.Creator<ThreeFingerSwipeLeftData> CREATOR = new Parcelable.Creator<ThreeFingerSwipeLeftData>() {

		public ThreeFingerSwipeLeftData createFromParcel(Parcel in) {
			return new ThreeFingerSwipeLeftData(in);
		}

		public ThreeFingerSwipeLeftData[] newArray(int size) {
			return new ThreeFingerSwipeLeftData[size];
		}
	};

	private ThreeFingerSwipeLeftData(Parcel in) {
		x = in.readInt();
	}

}
