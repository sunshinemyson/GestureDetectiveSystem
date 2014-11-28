package GestureDetectiveSystem;

import android.os.Parcel;
import android.os.Parcelable;

public class GenericTwoFingerInfoData implements Parcelable {
	public String message;
	public int deltaX;
	public int deltaY;

	public GenericTwoFingerInfoData(String gMessage, int i, int j) {
		// TODO Auto-generated constructor stub
		message = gMessage;
		deltaX = i;
		deltaY = j;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel out, int flags) {
		out.writeString(message);
		out.writeInt(deltaX);
		out.writeInt(deltaY);
	}

	public static final Parcelable.Creator<GenericTwoFingerInfoData> CREATOR = new Parcelable.Creator<GenericTwoFingerInfoData>() {

		public GenericTwoFingerInfoData createFromParcel(Parcel in) {
			return new GenericTwoFingerInfoData(in);
		}

		public GenericTwoFingerInfoData[] newArray(int size) {
			return new GenericTwoFingerInfoData[size];
		}
	};

	private GenericTwoFingerInfoData(Parcel in) {
		message = in.readString();
		deltaX = in.readInt();
		deltaY = in.readInt();
	}
}