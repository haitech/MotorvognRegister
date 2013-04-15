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

/**
 * A helper class for {@code Vehicle}
 * Has all the mutator methods for engine displacement.
 * 
 * @author Thomas Le
 * @see Vehicle
 */
public class Slagvolum implements Parcelable {
    private int oppgitt;   // E.g. 1905
    private double liter;  // E.g. 1.9
    
    public Slagvolum(Parcel in) {
        readFromParcel(in);
    }

    public Slagvolum(int oppgitt, double liter) {
        this.oppgitt = oppgitt;
        this.liter = liter;
    }

    public int getOppgitt() {
        return oppgitt;
    }

    public void setOppgitt(int oppgitt) {
        this.oppgitt = oppgitt;
    }

    public double getLiter() {
        return liter;
    }

    public void setLiter(double liter) {
        this.liter = liter;
    }

    private void readFromParcel(Parcel in) {
        oppgitt = in.readInt();
        liter = in.readDouble();
    }
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(oppgitt);
        dest.writeDouble(liter);
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
