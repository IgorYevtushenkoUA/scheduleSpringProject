package com.example.faculty.database.enums;

public enum Speciality {

    Cultural_Studies("Cultural Studies"),
    English_Language("English Language"),
    General_and_Slavic_Linguistics("General and Slavic Linguistics"),
    History("History"),
    Literature("Literature"),
    Philosophy_and_Religious_Studies("Philosophy and Religious Studies"),
    Ukrainian_Language("Ukrainian Language"),
    Archaeology("Archaeology"),
    Economic_Theory("Economic Theory"),
    Finance("Finance"),
    Marketing_and_Business_Management("Marketing and Business Management"),
    Applied_Legal_Studies("Applied Legal Studies"),
    International_and_European_Law("International and European Law"),
    Legal_Philosophy_and_Constitutional_Law("Legal Philosophy and Constitutional Law"),
    Jean_Monnet_Chair_in_European_Union_Law("Jean Monnet Chair in European Union Law"),
    Computer_Sciences("Computer Sciences"),
    Mathematics("Mathematics"),
    Multimedia_Systems("Multimedia Systems"),
    Network_Technologies("Network Technologies");

    private final String speciality;

    Speciality(String speciality) {
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return this.speciality;
    }


}
