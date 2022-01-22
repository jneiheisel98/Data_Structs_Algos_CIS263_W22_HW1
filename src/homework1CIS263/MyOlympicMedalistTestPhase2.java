package homework1CIS263;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * The test class MyOlympicMedalistTestPhase2.
 *
 * @author  Ana Posada
 * @version 1.0.0 - November 2021
 */
public class MyOlympicMedalistTestPhase2
{
    private OlympicMedalistsDatabase db;

    /******************************************************
     * Test constructor
     *****************************************************/
    @Test 
    public void testConstructor()
    {
        db = new OlympicMedalistsDatabase();
        assertEquals("ArrayList of medalist should not contain any medalists at this time",
            0, db.getMedalistsDatabase ().size());  
        assertEquals("ArrayList of country medals should not contain any medalists at this time",
            0, db.getCountryTotalMedalsDatabase ().size());
    } 

    /******************************************************
     * Test read file and counts
     *****************************************************/
    @Test
    public void testReadFileAndCounts()
    {
        db = new OlympicMedalistsDatabase();
        db.readOlympicMedalistData("SummerOlympicsMedals.csv");

        assertEquals("Should be 15,316 medalists",
            15316, db.countAllMedalists ());  

        assertEquals("Count of women athletes ",
            5928, db.countAllWomen());  

        assertEquals("Count of men athletes",
            9388, db.countAllMen()); 

        assertEquals("Year/country medal totals records",
            559, db.getCountryTotalMedalsDatabase().size()); 
    }

    /******************************************************
     * Test searchMedalistsByYear
     *****************************************************/
    @Test
    public void testsearchMedalistsByYear()
    {
        ArrayList <OlympicMedalist> list;
        db = new OlympicMedalistsDatabase();
        db.readOlympicMedalistData("SummerOlympicsMedals.csv");
        list = db.searchMedalistsByYear(2000);
        assertEquals("There should be 2,015 medalist for Olympics 2000",
            2015, list.size());  

        list = db.searchMedalistsByYear(1980);
        assertEquals("There should be 1,387 medalist for Olympics 1980",
            1387, list.size());    

        list = db.searchMedalistsByYear(1985);
        assertEquals("There should be 0 medalist for Olympics 1985",
            0, list.size());   
    }

    /******************************************************
     * Test searchMedalistsByCountry
     *****************************************************/
    @Test
    public void testsearchMedalistsByCountry()
    {
        ArrayList <OlympicMedalist> list;
        db = new OlympicMedalistsDatabase();
        db.readOlympicMedalistData("SummerOlympicsMedals.csv");

        list = db.searchMedalistsByCountry("COL");
        assertEquals("There should be 8 medalists for COL - Colombia",
            8, list.size());  

        assertEquals("1984 Olympics first medal for Colombia",
            1984, list.get(0).getYear());

        list = db.searchMedalistsByCountry("USA");
        assertEquals("There should be 1,992 medalists for USA",
            1992, list.size());  

        list = db.searchMedalistsByCountry("AAA");
        assertEquals("NO country AAA - no medalists",
            0, list.size()); 
    }

    /******************************************************
     * Test searchMedalistsByCountry
     *****************************************************/
    @Test
    public void testFindAthlete()
    {
        OlympicMedalist medalist;
        db = new OlympicMedalistsDatabase();
        db.readOlympicMedalistData("SummerOlympicsMedals.csv");

        medalist = db.findAthlete("PATTERSON  Carly");
        assertEquals("Carly participated in the 2004 Athens Olympics",
            2004, medalist.getYear());  

        medalist = db.findAthlete("CALLE WILLIAMS  Maria Luisa");
        assertEquals("Maria Luisa participated in the 2004 Athens Olympics",
            2004, medalist.getYear()); 

        medalist = db.findAthlete("Mickey Mouse");
        assertEquals("No Olympic medalas for Mickey Mouse ",
            null, medalist); 

    }

    /******************************************************
     * Test findCountryWithHighestBronzeMedalsByYear
     *****************************************************/
    @Test
    public void testfindCountryWithHighestBronzeMedalsByYear()
    {
        OlympicMedalist medalist;
        db = new OlympicMedalistsDatabase();
        db.readOlympicMedalistData("SummerOlympicsMedals.csv");

        String country = db.findCountryWithHighestBronzeMedalsByYear(1996);
        assertEquals("Country with highest number of bronze medals in 1996 is AUS ",
            "AUS", country);  

        country = db.findCountryWithHighestBronzeMedalsByYear(1988);
        assertEquals("Country with highest number of bronze medals in 1988 is URS ",
            "URS", country);  

        country = db.findCountryWithHighestBronzeMedalsByYear(2013);
        assertEquals("No Olympic games in 2013 ",
            null, country);     

    }

    /******************************************************
     * Test top ten
     *****************************************************/
    @Test
    public void testTopTen() {
        ArrayList <OlympicCountryMedals> list;
        db = new OlympicMedalistsDatabase();
        db.readOlympicMedalistData("SummerOlympicsMedals.csv");

        list = db.topTenCountriesGoldMedals(1976);
        assertEquals("Top ten for 1976 should return 10 elements",
            10, list.size());

        assertEquals("URS is the country with the most gold medals in 1976",
            "URS", list.get(0).getCountryCode());
        assertEquals("GDR is the second country with the most gold medals in 1976",
            "GDR", list.get(1).getCountryCode());
        assertEquals("URS is the 10th country with the most gold medals in 1976",
            "BUL", list.get(9).getCountryCode());

        assertEquals("USA is the third country with 63 gold medals in 1976",
            63, list.get(2).getGoldMedals());
        assertEquals("JPN is the 4th country with 6 silver medals in 1976",
            6, list.get(3).getSilverMedals());
        assertEquals("FRG is the 5th country with 30 bronze medals in 1976",
            30, list.get(4).getBronzeMedals());

        // year does not have Olympics
        list = db.topTenCountriesGoldMedals(1977);
        assertEquals("There should be no olympic medalist for 1977",
            0, list.size()); 
    }

    /******************************************************
     * Test searchCountryMedalsByYear
     *****************************************************/
    @Test
    public void testsearchCountryMedalsByYear() {
        ArrayList <OlympicCountryMedals> list;
        db = new OlympicMedalistsDatabase();
        db.readOlympicMedalistData("SummerOlympicsMedals.csv");

        list = db.searchCountryMedalsByYear(1992);
        assertEquals("64 entries for olympic country medals for 1992",
            64, list.size());

        assertEquals("ALG is the first country in the total medals for 1992",
            "ALG", list.get(0).getCountryCode());

        list = db.searchCountryMedalsByYear(1984);
        assertEquals("47 entries for olympic country medals for 1984",
            47, list.size());

        list = db.searchCountryMedalsByYear(1995);
        assertEquals("No Olympic games in 1995",
            0, list.size());

    }

}
