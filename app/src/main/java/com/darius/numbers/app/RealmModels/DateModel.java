package com.darius.numbers.app.RealmModels;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dariu on 1/21/2018.
 */

public class DateModel extends RealmObject{

    @PrimaryKey
    public int uId;

    public int storedDate;

    public String storedDateFact;

    public int getuId() {
        return uId;
    }

    public int getStoredDate() {
        return storedDate;
    }

    public void setStoredDate(int storedDate) {
        this.storedDate = storedDate;
    }

    public String getStoredDateFact() {
        return storedDateFact;
    }

    public void setStoredDateFact(String storedDateFact) {
        this.storedDateFact = storedDateFact;
    }
}
