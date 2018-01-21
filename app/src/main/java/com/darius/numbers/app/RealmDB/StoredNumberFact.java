package com.darius.numbers.app.RealmDB;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dariu on 1/21/2018.
 */

public class StoredNumberFact extends RealmObject {

    @PrimaryKey
    private int uId;

    private int storedNumber;

    private String storedNumberFact;


    public int getuId() {
        return uId;
    }

    public int getStoredNumber() {
        return storedNumber;
    }

    public void setStoredNumber(int storedNumber) {
        this.storedNumber = storedNumber;
    }

    public String getStoredNumberFact() {
        return storedNumberFact;
    }

    public void setStoredNumberFact(String storedNumberFact) {
        this.storedNumberFact = storedNumberFact;
    }
}
