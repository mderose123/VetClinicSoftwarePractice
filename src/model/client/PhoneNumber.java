package model.client;

public class PhoneNumber {

    private int countryCode;
    private int areaCode;
    private int firstNum;
    private int lastNum;

    public PhoneNumber(int areaCode, int firstNum, int lastNum) {

        countryCode = 1;
        this.areaCode = areaCode;
        this.firstNum = firstNum;
        this.lastNum = lastNum;
    }

    public void setCountryCode(int x) {
        countryCode = x;
    }

    public String toString() {
        int x = countryCode + areaCode + firstNum + lastNum;
        return "+" + x;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public int getLastNum() {
        return lastNum;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public void setLastNum(int lastNum) {
        this.lastNum = lastNum;
    }
}
