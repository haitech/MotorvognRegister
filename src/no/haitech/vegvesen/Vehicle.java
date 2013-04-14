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

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class for Vehicle Object implementing Parcelable.
 * To prevent bad performance with Serializable, I'm using Parcelable for more
 * efficient to pass between Activities.
 * Contains all information about a vehicle.
 * 
 * @author Thomas Le
 *
 */
public class Vehicle implements Parcelable {
    private String merke;                   // E.g. Citroen
    private String modell;                  // E.g. Xantia Turbo D
    private String type;                    // E.g. VF7X14B00
    private String gruppe;                  // E.g. Personbil
    private String farge;                   // E.g. Gul
    private Boolean klasseB = false;        // E.g. True
    private int seter;                      // E.g. 5
    private String drivstofftype;           // E.g. Diesel
    private Slagvolum slagvolum;            // E.g. 1905, 1.9
    private Motorytelse motorytelse;        // E.g. { 66, KW, 90 }
    private int akslerMedDrift;             // E.g. 1
    private int egenvektMedForer;           // E.g. 1570
    private int tilhengervektMBrems;        // E.g. 1900
    private int tilhengervektUBrems;        // E.g. 750
    private int tilhengervektKopl;          // E.g. 76
    private int vontogvekt;                 // E.g. 3940
    private int taklast;                    // E.g. 100
    private String regnr;                   // E.g. RH 12345
    private String understellsnr;           // E.g. VF7X14B00024B0547
    private int registreringsaar;           // E.g. 1995
    private Date forstegangsreg;            // E.g. 29.06.1995
    private Date registrertEierDato;        // E.g. 02.01.2008
    private String registrertDistrikt;      // E.g. Stavanger
    private Date avregistrertDato;          // E.g. 31.12.2012
    private int egenvekt;                   // E.g. 1495
    private int totalvekt;                  // E.g. 2040
    private int lengde;                     // E.g. 480
    private int bredde;                     // E.g. 180
    private int nyttelast;                  // E.g. 470
    private int akseltrykkForan;            // E.g. 1000
    private int akseltrykkBak;              // E.g. 1080
    private String dekkdimensjonForan;      // E.g. 195/65 R 15
    private String dekkdimensjonBak;        // E.g. 195/65 R 15
    private String hastighetsindeksForan;   // E.g. T (190 km/t)
    private String hastighetsindeksBak;     // E.g. Ikke oppgitt
    private int lastindeksForan;            // E.g. 84
    private int lastindeksBak;              // E.g. 84
    private String innpressForan;           // E.g. Ikke oppgitt
    private String innpressBak;             // E.g. Ikke oppgitt
    private int antallAksler;               // E.g. Ikke oppgitt
    private Date eukontrollfrist;           // E.g. 31.01.2013
    private Date eukontrollSist;            // E.g. 10.12.2010
    
    public Vehicle() {
    }
    
    public Vehicle(Parcel in) {
        readFromParcel(in);
    }
    
    private void readFromParcel(Parcel in) {
        merke = in.readString();
        modell = in.readString();
        type = in.readString();
        gruppe = in.readString();
        farge = in.readString();
        klasseB = in.readByte() == 1;
        seter = in.readInt();
        drivstofftype = in.readString();
        slagvolum = in.readParcelable(
                Slagvolum.class.getClassLoader());
        motorytelse = in.readParcelable(
                Motorytelse.class.getClassLoader());
        akslerMedDrift = in.readInt();
        egenvektMedForer = in.readInt();
        tilhengervektMBrems = in.readInt();
        tilhengervektUBrems = in.readInt();
        tilhengervektKopl = in.readInt();
        vontogvekt = in.readInt();
        taklast = in.readInt();
        regnr = in.readString();
        understellsnr = in.readString();
        registreringsaar = in.readInt();
        forstegangsreg = new Date(in.readLong());
        registrertEierDato = new Date(in.readLong());
        registrertDistrikt = in.readString();
        avregistrertDato = new Date(in.readInt());
        egenvekt = in.readInt();
        totalvekt = in.readInt();
        lengde = in.readInt();
        bredde = in.readInt();
        nyttelast = in.readInt();
        akseltrykkForan = in.readInt();
        akseltrykkBak = in.readInt();
        dekkdimensjonForan = in.readString();
        dekkdimensjonBak = in.readString();
        hastighetsindeksForan = in.readString();
        hastighetsindeksBak = in.readString();
        lastindeksForan = in.readInt();
        lastindeksBak = in.readInt();
        innpressForan = in.readString();
        innpressBak = in.readString();
        antallAksler = in.readInt();
        eukontrollfrist = new Date(in.readLong());
        eukontrollSist = new Date(in.readLong());
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(merke);
        dest.writeString(modell);
        dest.writeString(type);
        dest.writeString(gruppe);
        dest.writeString(farge);
        dest.writeByte((byte) (klasseB ? 1 : 0));
        dest.writeInt(seter);
        dest.writeString(drivstofftype);
        dest.writeParcelable(slagvolum, flags);
        dest.writeParcelable(motorytelse, flags);
        dest.writeInt(akslerMedDrift);
        dest.writeInt(egenvektMedForer);
        dest.writeInt(tilhengervektMBrems);
        dest.writeInt(tilhengervektUBrems);
        dest.writeInt(tilhengervektKopl);
        dest.writeInt(vontogvekt);
        dest.writeInt(taklast);
        dest.writeString(regnr);
        dest.writeString(understellsnr);
        dest.writeInt(registreringsaar);
        dest.writeLong(forstegangsreg.getTime());
        dest.writeLong(registrertEierDato.getTime());
        dest.writeString(registrertDistrikt);
        dest.writeLong(avregistrertDato.getTime());
        dest.writeInt(egenvekt);
        dest.writeInt(totalvekt);
        dest.writeInt(lengde);
        dest.writeInt(bredde);
        dest.writeInt(nyttelast);
        dest.writeInt(akseltrykkForan);
        dest.writeInt(akseltrykkBak);
        dest.writeString(dekkdimensjonForan);
        dest.writeString(dekkdimensjonBak);
        dest.writeString(hastighetsindeksForan);
        dest.writeString(hastighetsindeksBak);
        dest.writeInt(lastindeksForan);
        dest.writeInt(lastindeksBak);
        dest.writeString(innpressForan);
        dest.writeString(innpressBak);
        dest.writeInt(antallAksler);
        dest.writeLong(eukontrollfrist.getTime());
        dest.writeLong(eukontrollSist.getTime());
    }
    
    /**
     * Mutator method for Brand.
     * 
     * @return String brand of vehicle.
     */
    public String getMerke() {
        return merke;
    }
    
    public void setMerke(String merke) {
        this.merke = merke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGruppe() {
        return gruppe;
    }

    public void setGruppe(String gruppe) {
        this.gruppe = gruppe;
    }

    public String getFarge() {
        return farge;
    }

    public void setFarge(String farge) {
        this.farge = farge;
    }

    public Boolean getKlasseB() {
        return klasseB;
    }

    public void setKlasseB(Boolean klasseB) {
        this.klasseB = klasseB;
    }

    public int getSeter() {
        return seter;
    }

    public void setSeter(int seter) {
        this.seter = seter;
    }

    public String getDrivstofftype() {
        return drivstofftype;
    }

    public void setDrivstofftype(String drivstofftype) {
        this.drivstofftype = drivstofftype;
    }

    public Slagvolum getSlagvolum() {
        return slagvolum;
    }

    public void setSlagvolum(Slagvolum slagvolum) {
        this.slagvolum = slagvolum;
    }

    public Motorytelse getMotorytelse() {
        return motorytelse;
    }

    public void setMotorytelse(Motorytelse motorytelse) {
        this.motorytelse = motorytelse;
    }

    public int getAkslerMedDrift() {
        return akslerMedDrift;
    }

    public void setAkslerMedDrift(int akslerMedDrift) {
        this.akslerMedDrift = akslerMedDrift;
    }

    public int getEgenvektMedForer() {
        return egenvektMedForer;
    }

    public void setEgenvektMedForer(int egenvektMedForer) {
        this.egenvektMedForer = egenvektMedForer;
    }

    public int getTilhengervektMBrems() {
        return tilhengervektMBrems;
    }

    public void setTilhengervektMBrems(int tilhengervektMBrems) {
        this.tilhengervektMBrems = tilhengervektMBrems;
    }

    public int getTilhengervektUBrems() {
        return tilhengervektUBrems;
    }

    public void setTilhengervektUBrems(int tilhengervektUBrems) {
        this.tilhengervektUBrems = tilhengervektUBrems;
    }

    public int getTilhengervektKopl() {
        return tilhengervektKopl;
    }

    public void setTilhengervektKopl(int tilhengervektKopl) {
        this.tilhengervektKopl = tilhengervektKopl;
    }

    public int getVontogvekt() {
        return vontogvekt;
    }

    public void setVontogvekt(int vontogvekt) {
        this.vontogvekt = vontogvekt;
    }

    public int getTaklast() {
        return taklast;
    }

    public void setTaklast(int taklast) {
        this.taklast = taklast;
    }

    public String getRegnr() {
        return regnr;
    }

    public void setRegnr(String regnr) {
        this.regnr = regnr;
    }

    public String getUnderstellsnr() {
        return understellsnr;
    }

    public void setUnderstellsnr(String understellsnr) {
        this.understellsnr = understellsnr;
    }

    public int getRegistreringsaar() {
        return registreringsaar;
    }

    public void setRegistreringsaar(int registreringsaar) {
        this.registreringsaar = registreringsaar;
    }

    public Date getForstegangsreg() {
        return forstegangsreg;
    }

    public void setForstegangsreg(Date forstegangsreg) {
        this.forstegangsreg = forstegangsreg;
    }

    public Date getRegistrertEierDato() {
        return registrertEierDato;
    }

    public void setRegistrertEierDato(Date registrertEierDato) {
        this.registrertEierDato = registrertEierDato;
    }

    public String getRegistrertDistrikt() {
        return registrertDistrikt;
    }

    public void setRegistrertDistrikt(String registrertDistrikt) {
        this.registrertDistrikt = registrertDistrikt;
    }

    public Date getAvregistrertDato() {
        return avregistrertDato;
    }

    public void setAvregistrertDato(Date avregistrertDato) {
        this.avregistrertDato = avregistrertDato;
    }

    public int getEgenvekt() {
        return egenvekt;
    }

    public void setEgenvekt(int egenvekt) {
        this.egenvekt = egenvekt;
    }

    public int getTotalvekt() {
        return totalvekt;
    }

    public void setTotalvekt(int totalvekt) {
        this.totalvekt = totalvekt;
    }

    public int getLengde() {
        return lengde;
    }

    public void setLengde(int lengde) {
        this.lengde = lengde;
    }

    public int getBredde() {
        return bredde;
    }

    public void setBredde(int bredde) {
        this.bredde = bredde;
    }

    public int getNyttelast() {
        return nyttelast;
    }

    public void setNyttelast(int nyttelast) {
        this.nyttelast = nyttelast;
    }

    public int getAkseltrykkForan() {
        return akseltrykkForan;
    }

    public void setAkseltrykkForan(int akseltrykkForan) {
        this.akseltrykkForan = akseltrykkForan;
    }

    public int getAkseltrykkBak() {
        return akseltrykkBak;
    }

    public void setAkseltrykkBak(int akseltrykkBak) {
        this.akseltrykkBak = akseltrykkBak;
    }

    public String getDekkdimensjonForan() {
        return dekkdimensjonForan;
    }

    public void setDekkdimensjonForan(String dekkdimensjonForan) {
        this.dekkdimensjonForan = dekkdimensjonForan;
    }

    public String getDekkdimensjonBak() {
        return dekkdimensjonBak;
    }

    public void setDekkdimensjonBak(String dekkdimensjonBak) {
        this.dekkdimensjonBak = dekkdimensjonBak;
    }

    public String getHastighetsindeksForan() {
        return hastighetsindeksForan;
    }

    public void setHastighetsindeksForan(String hastighetsindeksForan) {
        this.hastighetsindeksForan = hastighetsindeksForan;
    }

    public String getHastighetsindeksBak() {
        return hastighetsindeksBak;
    }

    public void setHastighetsindeksBak(String hastighetsindeksBak) {
        this.hastighetsindeksBak = hastighetsindeksBak;
    }

    public int getLastindeksForan() {
        return lastindeksForan;
    }

    public void setLastindeksForan(int lastindeksForan) {
        this.lastindeksForan = lastindeksForan;
    }

    public int getLastindeksBak() {
        return lastindeksBak;
    }

    public void setLastindeksBak(int lastindeksBak) {
        this.lastindeksBak = lastindeksBak;
    }

    public String getInnpressForan() {
        return innpressForan;
    }

    public void setInnpressForan(String innpressForan) {
        this.innpressForan = innpressForan;
    }

    public String getInnpressBak() {
        return innpressBak;
    }

    public void setInnpressBak(String innpressBak) {
        this.innpressBak = innpressBak;
    }

    public int getAntallAksler() {
        return antallAksler;
    }

    public void setAntallAksler(int antallAksler) {
        this.antallAksler = antallAksler;
    }

    public Date getEukontrollfrist() {
        return eukontrollfrist;
    }

    public void setEukontrollfrist(Date eukontrollfrist) {
        this.eukontrollfrist = eukontrollfrist;
    }

    public Date getEukontrollSist() {
        return eukontrollSist;
    }

    public void setEukontrollSist(Date eukontrollSist) {
        this.eukontrollSist = eukontrollSist;
    }


    public static final Parcelable.Creator<Vehicle> CREATOR = 
            new Parcelable.Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel source) {
            return new Vehicle(source);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };
}
