package homework1CIS263;

public class OlympicCountryMedals implements Comparable
{
    private String city;
    private int year;
    private String countryCode;
    private int goldMedals;
    private int silverMedals;
    private int bronzeMedals;

    public OlympicCountryMedals(
    int year,String city,String countryCode,
    int goldMedals,int silverMedals,int bronzeMedals) {
        this.city = city;
        this.year = year;
        this.countryCode = countryCode;
        this.goldMedals = goldMedals;
        this.silverMedals = silverMedals;
        this.bronzeMedals = bronzeMedals;
    }

    public String getCity() {
        return city;
    }

    public int getYear() {
        return year;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getGoldMedals() {
        return goldMedals;
    }

    public int getSilverMedals() {
        return silverMedals;
    }

    public int getBronzeMedals() {
        return bronzeMedals;
    }

    public String toString()  {
        return year+" "+city+", "+countryCode+", "+
        "Gold: "+goldMedals+", Silver: "+silverMedals+
        "Bronze: "+bronzeMedals;
    }

    public int compareTo(Object other){
        OlympicCountryMedals m = (OlympicCountryMedals) other;
        return m.goldMedals - goldMedals;
    }

}
