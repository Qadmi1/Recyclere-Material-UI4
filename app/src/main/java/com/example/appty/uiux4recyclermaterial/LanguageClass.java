package com.example.appty.uiux4recyclermaterial;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by appty on 07/04/18.
 */

public class LanguageClass  implements Parcelable{

    private String name;
    private int img;

    public LanguageClass(String name, int img) {
        this.name = name;
        this.img = img;
    }


    protected LanguageClass(Parcel in) {
        name = in.readString();
        img = in.readInt();
    }

    public static final Creator<LanguageClass> CREATOR = new Creator<LanguageClass>() {
        @Override
        public LanguageClass createFromParcel(Parcel in) {
            return new LanguageClass(in);
        }

        @Override
        public LanguageClass[] newArray(int size) {
            return new LanguageClass[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(img);
    }
}
