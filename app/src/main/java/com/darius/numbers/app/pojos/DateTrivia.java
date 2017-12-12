package com.darius.numbers.app.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dariu on 12/6/2017.
 */

public class DateTrivia {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("found")
    @Expose
    private Boolean found;
    @SerializedName("type")
    @Expose
    private String type;
    public final static Parcelable.Creator<DateTrivia> CREATOR = new Parcelable.Creator<DateTrivia>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DateTrivia createFromParcel(Parcel in) {
            return new DateTrivia(in);
        }

        public DateTrivia[] newArray(int size) {
            return (new DateTrivia[size]);
        }

    }
            ;

    protected DateTrivia(Parcel in) {
        this.text = ((String) in.readValue((String.class.getClassLoader())));
        this.year = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.number = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.found = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public DateTrivia() {
    }

    /**
     *
     * @param text
     * @param number
     * @param year
     * @param type
     * @param found
     */
    public DateTrivia(String text, Integer year, Integer number, Boolean found, String type) {
        super();
        this.text = text;
        this.year = year;
        this.number = number;
        this.found = found;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DateTrivia withText(String text) {
        this.text = text;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public DateTrivia withYear(Integer year) {
        this.year = year;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public DateTrivia withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public Boolean getFound() {
        return found;
    }

    public void setFound(Boolean found) {
        this.found = found;
    }

    public DateTrivia withFound(Boolean found) {
        this.found = found;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DateTrivia withType(String type) {
        this.type = type;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(text);
        dest.writeValue(year);
        dest.writeValue(number);
        dest.writeValue(found);
        dest.writeValue(type);
    }

    public int describeContents() {
        return 0;
    }

}
