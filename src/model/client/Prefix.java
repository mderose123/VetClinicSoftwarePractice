package model.client;

public enum Prefix {
    MS("MS."),
    MRS("MRS."),
    MR("MR."),
    MX("MX.");

    private String abbreviation;

    Prefix(String abbreviation) {this.abbreviation = abbreviation;}

    public String getAbbreviation() { return abbreviation;}

    @Override
    public String toString() {return abbreviation;}
}
