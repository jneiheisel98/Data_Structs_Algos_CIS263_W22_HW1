package homework1CIS263;

import java.util.Comparator;
public class OlympicMedalist implements Comparable
{
    private String city;
    private int year;
    private String sport;
    private String discipline;
    private String event;
    private String athleteName;
    private String gender;
    private String countryCode;
    private String medal;

    public OlympicMedalist(String data) {
        String parts[] = data.split(",");
        city = parts[0];
        year = Integer.parseInt(parts[1]);
        sport = parts[2];
        discipline = parts[3];
        event = parts[4];
        athleteName = parts[5];
        gender = parts[6];
        countryCode = parts[7];
        medal = parts[8];
    }

    public String getCity() {
        return city;
    }

    public int getYear() {
        return year;
    }

    public String getSport(){
        return sport;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getEvent(){
        return event;
    }

    public String getName(){
        return athleteName;
    }

    public String getGender() {
        return gender;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getMedal() {
        return medal;
    }

    public String toString() {
        return city+" "+year+" Country: "+countryCode+
        " Sports: "+sport+" Event: "+event+
        " Name: "+athleteName+" "+medal;
    }

    public int compareTo(Object other) {
        OlympicMedalist medalist = (OlympicMedalist) other;
        return Comparator.comparing(OlympicMedalist::getYear)
        .thenComparing(OlympicMedalist::getCountryCode)
        .compare(this, medalist);
    }

}
