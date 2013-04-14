/*
 * Copyright (C) 2013 Thomas Le
 * 
 * This file is part of MotorvognRegister.
 *
 * MotorvognRegister is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MotorvognRegister is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public license
 * along with MotorvognRegister. If not, see <http://www.gnu.org/licenses/>.
 */
package no.haitech.vegvesen;

import android.os.Parcel;
import android.os.Parcelable;

public class Motorytelse implements Parcelable {
    private int supplied;       // F.ex. 66
    private String extension;   // F.ex. KW
    private int horsePower;     // F.ex. 90
    
    public Motorytelse(Parcel in) {
        readFromParcel(in);
    }
    

    public Motorytelse(int supplied, String extension, int horsePower) {
        this.supplied = supplied;
        this.extension = extension;
        this.horsePower = horsePower;
    }

    public int getSupplied() {
        return supplied;
    }

    public void setSupplied(int supplied) {
        this.supplied = supplied;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    private void readFromParcel(Parcel in) {
        supplied = in.readInt();
        extension = in.readString();
        horsePower = in.readInt();
    }
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(supplied);
        dest.writeString(extension);
        dest.writeInt(horsePower);
    }
    
    public static final Parcelable.Creator<Motorytelse> CREATOR = 
            new Parcelable.Creator<Motorytelse>() {
        @Override
        public Motorytelse createFromParcel(Parcel source) {
            return new Motorytelse(source);
        }

        @Override
        public Motorytelse[] newArray(int size) {
            return new Motorytelse[size];
        }
    };
}
