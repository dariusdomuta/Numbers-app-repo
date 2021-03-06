package com.darius.numbers.app.RealmModels;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dariu on 1/21/2018.
 */

public class MathModel extends RealmObject {

    @PrimaryKey
    private int uId;

    private int storedMath;

    private String storedMathFact;

    public int getuId() {
        return uId;
    }

    public int getStoredMath() {
        return storedMath;
    }

    public void setStoredMath(int storedMath) {
        this.storedMath = storedMath;
    }

    public String getStoredMathFact() {
        return storedMathFact;
    }

    public void setStoredMathFact(String storedMathFact) {
        this.storedMathFact = storedMathFact;
    }
}
