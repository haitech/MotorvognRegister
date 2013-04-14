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

public class Slagvolum implements Parcelable {
    private int supplied;   // F.ex. 1905
    private double liters;  // F.ex. 1.9
    
    public Slagvolum(Parcel in) {
        readFromParcel(in);
    }

    public Slagvolum(int supplied, double liters) {
        this.supplied = supplied;
        this.liters = liters;
    }

    public int getSupplied() {
        return supplied;
    }

    public void setSupplied(int supplied) {
        this.supplied = supplied;
    }

    public double getLiters() {
        return liters;
    }

    public void setLiters(double liters) {
        this.liters = liters;
    }

    private void readFromParcel(Parcel in) {
        supplied = in.readInt();
        liters = in.readDouble();
    }
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(supplied);
        dest.writeDouble(liters);
    }
    
    public static final Parcelable.Creator<Slagvolum> CREATOR = 
            new Parcelable.Creator<Slagvolum>() {
        @Override
        public Slagvolum createFromParcel(Parcel source) {
            return new Slagvolum(source);
        }

        @Override
        public Slagvolum[] newArray(int size) {
            return new Slagvolum[size];
        }
    };
}
