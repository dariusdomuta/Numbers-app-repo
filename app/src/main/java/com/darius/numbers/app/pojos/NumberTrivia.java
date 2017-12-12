package com.darius.numbers.app.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dariu on 12/6/2017.
 */

public class NumberTrivia {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("found")
    @Expose
    private Boolean found;
    @SerializedName("type")
    @Expose
    private String type;

    public final static Parcelable.Creator<NumberTrivia> CREATOR = new Parcelable.Creator<NumberTrivia>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NumberTrivia createFromParcel(Parcel in) {
            return new NumberTrivia(in);
        }

        public NumberTrivia[] newArray(int size) {
            return (new NumberTrivia[size]);
        }

    }
            ;

    protected NumberTrivia(Parcel in) {
        this.text = ((String) in.readValue((String.class.getClassLoader())));
        this.number = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.found = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public NumberTrivia() {
    }

    /**
     *
     * @param text
     * @param number
     * @param type
     * @param found
     */
    public NumberTrivia(String text, Integer number, Boolean found, String type) {
        super();
        this.text = text;
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

    public NumberTrivia withText(String text) {
        this.text = text;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public NumberTrivia withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public Boolean getFound() {
        return found;
    }

    public void setFound(Boolean found) {
        this.found = found;
    }

    public NumberTrivia withFound(Boolean found) {
        this.found = found;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NumberTrivia withType(String type) {
        this.type = type;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(text);
        dest.writeValue(number);
        dest.writeValue(found);
        dest.writeValue(type);
    }

    public int describeContents() {
        return 0;
    }

}
