package homework1CIS263;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MyOlympicMedalistTestPhase1
 *
 * @author  Ana Posada
 * @version 1.0.0 - November 2021
 */
public class MyOlympicMedalistTestPhase1
{
    /******************************************************
     * Test OlympicMedalist constructor
     *****************************************************/
    @Test
    public void testConstructorMedalist()
    {
        OlympicMedalist medalist = new OlympicMedalist (
                "Atlanta,1996,Hockey,Hockey,hockey,POWELL  Lisa Josephine,Women,AUS,Gold");

        assertEquals("City should be equal to input parameter", "Atlanta", medalist.getCity());
        assertEquals("Year should be equal to input parameter",1996, medalist.getYear());
        assertEquals("Sport should be equal to input parameter", "Hockey", medalist.getSport());
        assertEquals("discipline should be equal to input parameter", "Hockey", medalist.getDiscipline());
        assertEquals("event should be equal to input parameter", "hockey", medalist.getEvent());
        assertEquals("athlete name should be equal to input parameter", "POWELL  Lisa Josephine", medalist.getName());
        assertEquals("gender should be equal to input parameter", "Women", medalist.getGender());
        assertEquals("country code should be equal to input parameter", "AUS", medalist.getCountryCode());
        assertEquals("medal should be equal to input parameter", "Gold", medalist.getMedal());

    }

    /******************************************************
     * Test OlympicCountryMedals constructor
     *****************************************************/
    @Test
    public void testConstructorOlympicCountryMedals()
    {
        OlympicCountryMedals countryTotals = new OlympicCountryMedals (
                2008 , "Beijing", "USA", 125, 109, 81);

        assertEquals("City should be equal to input parameter", "Beijing", countryTotals.getCity());
        assertEquals("Year should be equal to input parameter",2008, countryTotals.getYear());
        assertEquals("country code should be equal to input parameter", "USA", countryTotals.getCountryCode());
        assertEquals("gold medals should be equal to input parameter", 125, countryTotals.getGoldMedals());
        assertEquals("silver medals should be equal to input parameter", 109, countryTotals.getSilverMedals());
        assertEquals("bronze medals should be equal to input parameter", 81, countryTotals.getBronzeMedals());
    }
}

