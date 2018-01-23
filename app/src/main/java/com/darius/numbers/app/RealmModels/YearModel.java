package com.darius.numbers.app.RealmModels;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dariu on 1/21/2018.
 */

public class YearModel extends RealmObject{
    @PrimaryKey
    private int uId;

    private int storedYear;

    private String storedYearFact;

    public int getuId() {
        return uId;
    }

    public int getStoredYear() {
        return storedYear;
    }

    public void setStoredYear(int storedYear) {
        this.storedYear = storedYear;
    }

    public String getStoredYearFact() {
        return storedYearFact;
    }

    public void setStoredYearFact(String storedYearFact) {
        this.storedYearFact = storedYearFact;
    }
}
