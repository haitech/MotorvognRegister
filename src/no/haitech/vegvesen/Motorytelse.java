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
 * Has all the mutator methods for engine performance.
 * 
 * @author Thomas Le
 * @see Vehicle
 */
public class Motorytelse implements Parcelable {
    private int oppgitt;                // E.g. 66
    private String oppgittBenevning;    // E.g. KW
    private int hestekrefter;           // E.g. 90
    
    public Motorytelse(Parcel in) {
        readFromParcel(in);
    }
    

    public Motorytelse(int oppgitt, String oppgittBenevning, int hestekrefter) {
        this.oppgitt = oppgitt;
        this.oppgittBenevning = oppgittBenevning;
        this.hestekrefter = hestekrefter;
    }

    public int getOppgitt() {
        return oppgitt;
    }

    public void setOppgitt(int oppgitt) {
        this.oppgitt = oppgitt;
    }

    public String getOppgittBenevning() {
        return oppgittBenevning;
    }

    public void setOppgittBenevning(String oppgittBenevning) {
        this.oppgittBenevning = oppgittBenevning;
    }

    public int getHestekrefter() {
        return hestekrefter;
    }

    public void setHestekrefter(int hestekrefter) {
        this.hestekrefter = hestekrefter;
    }

    private void readFromParcel(Parcel in) {
        oppgitt = in.readInt();
        oppgittBenevning = in.readString();
        hestekrefter = in.readInt();
    }
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(oppgitt);
        dest.writeString(oppgittBenevning);
        dest.writeInt(hestekrefter);
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
