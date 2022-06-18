package org.rodneyparshall.rightrx.enumeration;

public enum Dosage {
    FIVE("5"),
    TEN("10"),
    TWENTY("20"),
    THIRTY("30"),
    FIFTY("50"),
    ONE_HUNDRED("100"),
    TWO_HUNDRED("200");

    private final String dosage;

    Dosage(String dosage){
        this.dosage = dosage;
    }

    public String getDosage(){
        return this.dosage;
    }
}
