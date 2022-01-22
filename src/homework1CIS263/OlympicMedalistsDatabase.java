package homework1CIS263;

import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class OlympicMedalistsDatabase
{
    private ArrayList < OlympicMedalist > om;
    private ArrayList < OlympicCountryMedals > ocm;

    public OlympicMedalistsDatabase() {
        om = new ArrayList < OlympicMedalist > ();
        ocm = new ArrayList < OlympicCountryMedals > ();
    }

    public void readOlympicMedalistData(String filename) {
        // Read the full set of data from a text file
        try{
            // open the text file and use a Scanner to read the text
            FileInputStream fileByteStream = new FileInputStream(filename);
            Scanner scnr = new Scanner(fileByteStream);
            scnr.nextLine(); // reads the column headers

            // keep reading as long as there is more data
            while(scnr.hasNext()) {
                // reads each record of the file
                String data = scnr.nextLine();
                OlympicMedalist olympicMedalist = new OlympicMedalist(data);
                om.add(olympicMedalist);
            }
            generateCountryTotalMedals(); 
            fileByteStream.close();
        }
        catch(IOException e) {
            System.out.println("Failed to read the data file: " + filename);
        }
    }

    public void generateCountryTotalMedals() {
        Map<String, int[]> medalsByCountry = new HashMap<String, int[]>();
        for (int counter = 0; counter< om.size(); counter++){
            String nation = om.get(counter).getCountryCode();
            String city = om.get(counter).getCity();
            String year = Integer.toString(om.get(counter).getYear());
            String medalColor = om.get(counter).getMedal();
            int[] medalLevels;
            String completeKey = year + "," + city + "," + nation;
            if(medalsByCountry.containsKey(completeKey)){
                medalLevels = medalsByCountry.get(completeKey);
                switch (medalColor) {
                    case "Bronze":
                        medalLevels[2]+=1;
                        break;
                    case "Gold":
                        medalLevels[0]+=1;
                        break;
                    case "Silver":
                        medalLevels[1]+=1;
                        break;
                    default:
                        break;
                }
                medalsByCountry.replace(completeKey, medalLevels);

            }
            else{
                medalLevels = new int[3];
                switch (medalColor) {
                    case "Bronze":
                        medalLevels[2]+=1;
                        break;
                    case "Gold":
                        medalLevels[0]+=1;
                        break;
                    case "Silver":
                        medalLevels[1]+=1;
                        break;
                    default:
                        break;
                }
                medalsByCountry.put(completeKey, medalLevels);
            }
        }
        TreeMap<String, int[]> countryViewMedals= new TreeMap<String, int[]>();
        countryViewMedals.putAll(medalsByCountry);
        for(Map.Entry<String,int[]> entry: countryViewMedals.entrySet()){
            String dataPoints[] = entry.getKey().split(",");
            int[] medalLevels = entry.getValue();
            ocm.add(new OlympicCountryMedals(Integer.parseInt(dataPoints[0]), dataPoints[1], dataPoints[2], medalLevels[0], medalLevels[1], medalLevels[2]));
        }
    }




    public int countAllMedalists() {
        return om.size();
    }

    public int countAllWomen() {
        int count = 0;
        for(OlympicMedalist o : om) {
            if (o.getGender().equals("Women")) {
                count++;
            }
        }
        return count;
    }

    public int countAllMen() {
        int count = 0;
        for(OlympicMedalist o : om) {
            if (o.getGender().equals("Men")) {
                count++;
            }
        }
        return count;
    }

    public ArrayList < OlympicMedalist > getMedalistsDatabase() {
        return om;
    }

    public ArrayList < OlympicCountryMedals > getCountryTotalMedalsDatabase() {
        return ocm;
    }

    public ArrayList < OlympicMedalist > searchMedalistsByYear(int year) {
        ArrayList < OlympicMedalist > result = 
            new ArrayList < OlympicMedalist >();
        for(OlympicMedalist o : om) {
            if (o.getYear() == year) {
                result.add(o);
            }
        }
        return result;
    }

    public ArrayList < OlympicMedalist > searchMedalistsByCountry
    (String country) {
        ArrayList < OlympicMedalist > result = 
            new ArrayList < OlympicMedalist >();
        for(OlympicMedalist o : om) {
            if (o.getCountryCode().equals(country)) {
                result.add(o);
            }
        }
        return result;
    }

    public  OlympicMedalist  findAthlete    (String name) {
        OlympicMedalist result = null;
        for(OlympicMedalist o : om) {
            if (o.getName().equalsIgnoreCase(name)) {
                result = o;
            }
        }
        return result;
    }

    public ArrayList < OlympicCountryMedals > searchCountryMedalsByYear(int year) {
        ArrayList < OlympicCountryMedals > result = 
            new ArrayList < OlympicCountryMedals >();
        for(OlympicCountryMedals o : ocm) {
            if (o.getYear() == year) {
                result.add(o);
            }
        }
        return result;
    }

    public ArrayList < OlympicCountryMedals > topTenCountriesGoldMedals
    (int year) {
       
        ArrayList < OlympicCountryMedals > result = 
            new ArrayList < OlympicCountryMedals >();
        for(OlympicCountryMedals o : ocm) {
            if (o.getYear() == year) {
                result.add(o);
            }
        }
        Collections.sort(result);
        ArrayList < OlympicCountryMedals > complete;
            if(result.size() > 10){
             complete = new ArrayList < OlympicCountryMedals >(result.subList(0,10));
        }
            else{
                complete = result;
            }

        return complete;
    }

    public String findCountryWithHighestBronzeMedalsByYear(int year)
    {
        // One implementation:
        // String result = null;
        // int highestMedalCountSoFar = 0;
        // for (OlympicCountryMedals o : ocm) {
        // if (o.getYear() == year) {
        // if (o.getBronzeMedals() > highestMedalCountSoFar) {
        // result = o.getCountryCode();
        // highestMedalCountSoFar = o.getBronzeMedals();
        // }
        // }
        // }
        // return result;
        // A second implementation:
        ArrayList < OlympicCountryMedals > filteredByYear =          
        ocm.stream().filter(s -> s.getYear() == year).
        collect(Collectors.toCollection(ArrayList::new));
        if (filteredByYear.size() != 0) {
            OlympicCountryMedals r = Collections.max(filteredByYear,
                    Comparator.comparing(o->o.getBronzeMedals()));
            return r.getCountryCode();
        }
        else {
            return null;
        }

    }
}
