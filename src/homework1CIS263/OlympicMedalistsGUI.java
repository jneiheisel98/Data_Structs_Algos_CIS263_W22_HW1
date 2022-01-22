package homework1CIS263;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

/*************************************************************
 * GUI for the Olympic Medalists project
 * 
 * @author Ana Posada
 * @version November 2021
 ************************************************************/
public class OlympicMedalistsGUI
 extends JFrame implements ActionListener{

    OlympicMedalistsDatabase database;

    // Define buttons
    JButton yearMedalistsButton;
    JButton countryMedalistsButton;
    JButton yearTotalMedalsButton;
    JButton athleteButton;
    JButton topTenButton;
    JButton countryHighestBronzeButton;

    // Define text fields
    JTextField yearTextField;
    JTextField countryCodeTextField;
    JTextField nameTextField;

    /** Results text area */
    JTextArea resultsArea;

    /** menu items */
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem quitItem;
    JMenuItem openItem;
    JMenuItem countItem;

    /*****************************************************************
     * Main Method
     ****************************************************************/
    public static void main(String args[]){
        OlympicMedalistsGUI gui = new OlympicMedalistsGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Olympic Medalists");
        gui.pack();
        gui.setVisible(true);
    }

    /*****************************************************************
     * constructor installs all of the GUI components
     ****************************************************************/
    public OlympicMedalistsGUI(){
        database = new OlympicMedalistsDatabase();
        setupGUI();
        setupMenus();
    }

    /*****************************************************************
     * instantiates and adds the GUI components to the JFrame
     ****************************************************************/ 
    public void setupGUI() {
        // instantiating the JButtons
        yearMedalistsButton = new JButton ("By Year");
        countryMedalistsButton = new JButton ("By Country");
        topTenButton = new JButton ("Top Ten Year Gold");
        yearTotalMedalsButton = new JButton ("By Year");
        athleteButton = new JButton("Find Athlete");
        countryHighestBronzeButton = new JButton("Country Highest Bronze");

        // instantiate JTextFields
        yearTextField = new JTextField (4);
        countryCodeTextField = new JTextField (3);
        nameTextField = new JTextField(40);

        // set the layout to GridBag
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();

        // create results area
        resultsArea = new JTextArea(20,40);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        loc.gridx = 0;
        loc.gridy = 1;
        loc.insets = new Insets(20,20,20,20);
        loc.gridheight = 20;
        add(scrollPane, loc);

        // create Results label
        loc = new GridBagConstraints();
        loc.gridy = 0;
        loc.insets = new Insets(20,0,20,0);
        add(new JLabel("Results"), loc);

        // create JLabels
        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 0;
        loc.gridwidth = 2;
        loc.insets = new Insets(20,20,20,20);
        add(new JLabel("Medalists"), loc);

        loc.gridy = 4;
        loc.gridwidth = 2;
        add(new JLabel("Country Total Medals"), loc);

        // add Medalist JButtons
        loc.anchor = GridBagConstraints.LINE_START;
        loc.insets = new Insets(5,5,5,5);
        loc.gridx = 1;
        loc.gridy = 1;

        add(yearMedalistsButton, loc);
        loc.gridy++;
        add(countryMedalistsButton, loc);
        loc.gridy++;
        add(athleteButton, loc);

        // add country total JButtons
        loc.gridx = 1;
        loc.gridy = 5;
        add(yearTotalMedalsButton, loc);

        loc.gridy++;
        add(topTenButton, loc);

        loc.gridy++;
        add(countryHighestBronzeButton, loc);

        // JLabels and JTextFields to enter input parameters 
        loc = new GridBagConstraints();
        loc.insets = new Insets(0,5,5,5);
        loc.anchor = GridBagConstraints.LINE_START;

        loc.gridx = 0;
        loc.gridy = 22;
        add(new JLabel("Year"),loc);
        loc.gridy++;
        add(yearTextField, loc);

        loc.gridy = 24;
        add(new JLabel("Country Code"), loc);
        loc.gridy++;
        add(countryCodeTextField, loc);

        loc.gridy++;
        add(new JLabel ("Name"), loc);
        loc.gridy++;
        add(nameTextField, loc);

        // associating buttons with actionListener
        yearMedalistsButton.addActionListener(this);
        countryMedalistsButton.addActionListener(this); 
        topTenButton.addActionListener(this);
        countryHighestBronzeButton.addActionListener(this);
        athleteButton.addActionListener(this);
        yearTotalMedalsButton.addActionListener(this);
    }

    /*****************************************************************
     * This method is called when any button is clicked.  The proper
     * internal method is called as needed.
     * 
     * @param e the event that was fired
     ****************************************************************/       
    public void actionPerformed(ActionEvent e){
       // extract the button that was clicked
        JComponent buttonPressed = (JComponent) e.getSource();

        // open user selected file
        if (buttonPressed == openItem){
            openFile();
        }  
        if (buttonPressed == quitItem){
            System.exit(1);
        }  
        if (database.countAllMedalists() == 0)
            JOptionPane.showMessageDialog(this, "Forgot to read the file?");
        else { 
            if (buttonPressed == countItem){
                displayCounts();
            }  
            else if (buttonPressed == yearMedalistsButton){
                displayMedalistsByYear();
            }  
            else if (buttonPressed == countryMedalistsButton){
                displayByCountry();
            }  
            else if (buttonPressed == topTenButton){
                displayTopTen();
            } 
            else if (buttonPressed == yearTotalMedalsButton){
                displayYearTotalMedals();
            }
            else if (buttonPressed == athleteButton){
                displayByName();
            }
            else if (buttonPressed == countryHighestBronzeButton ){
                displayHighestBronzeMedals();
            }
        }
    }

    private void displayByCountry() {
        if (countryCodeTextField.getText().length() > 0)
            displayMedalists (database.searchMedalistsByCountry(countryCodeTextField.getText()));
        else
            JOptionPane.showMessageDialog(this, "Enter country code");
    }

    private void displayMedalists (ArrayList <OlympicMedalist> list) {
        DecimalFormat fmt = new DecimalFormat ("###,###,###");
        resultsArea.setText("Medalists info \n===========\n");
        for(OlympicMedalist b : list){
            resultsArea.append("\n" + b.toString());
        }
        resultsArea.append("\n Items Listed: "  + fmt.format(list.size()));
    }

    private void displayTotalMedals (ArrayList <OlympicCountryMedals> list) {
        DecimalFormat fmt = new DecimalFormat ("###,###,###");
        resultsArea.setText("Country Total medals info \n=====================\n");
        for(OlympicCountryMedals b : list){
            resultsArea.append("\n" + b.toString());
        }
        resultsArea.append("\n Items Listed: "  + fmt.format(list.size()));
    }

    private void displayHighestBronzeMedals (){
        try{
            int number = Integer.parseInt(yearTextField.getText());
            resultsArea.setText ("Country with most bronze medals for year: " + number);
            resultsArea.append ("\n" + database.findCountryWithHighestBronzeMedalsByYear(number));
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Enter a valid year");
        }

    }

    private void displayYearTotalMedals () {
        try{
            int number = Integer.parseInt(yearTextField.getText());
            displayTotalMedals (database.searchCountryMedalsByYear (number));
        }      
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Enter a valid year");
        }
    }

    private void displayMedalistsByYear () {
        try{
            int number = Integer.parseInt(yearTextField.getText());
            displayMedalists (database.searchMedalistsByYear (number));
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Enter a valid year");
        }
    }
    private void displayTopTen () {
        try{
            int number = Integer.parseInt(yearTextField.getText());
            displayTotalMedals (database.topTenCountriesGoldMedals
            (number));
        }      
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Enter a valid year");
        }

    }

    private void displayByName () {
        if (nameTextField.getText().length() > 0 )
            if (database.findAthlete (nameTextField.getText()) != null)
                resultsArea.setText (database.findAthlete (
                        nameTextField.getText()).toString());
            else
                resultsArea.setText("Athlete not found");
        else
            JOptionPane.showMessageDialog(this, "Enter name");
    }

    private void displayCounts () {
        DecimalFormat fmt = new DecimalFormat ("###,###");
        resultsArea.setText("Total medalists: " + fmt.format(database.countAllMedalists()));
        resultsArea.append("\nTotal country medals: " +
                fmt.format(database.getCountryTotalMedalsDatabase().size()));
        resultsArea.append("\nTotal women athletes : " + fmt.format(database.countAllWomen()));
        resultsArea.append("\nTotal men athletes: " + fmt.format(database.countAllMen()));
    }

    /*****************************************************************
     * open a data file with the name selected by the user
     ****************************************************************/ 
    private void openFile(){

        // create File Chooser so that it starts at the current directory
        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(userDir);

        // show File Chooser and wait for user selection
        int returnVal = fc.showOpenDialog(this);

        // did the user select a file?
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();

            // TO DO: change the following line as needed
            // to use your instance variable name
            database.readOlympicMedalistData(filename);          
        }
    }

    /*******************************************************
    Creates the menu items
     *******************************************************/    
    private void setupMenus(){
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        countItem = new JMenuItem("Counts");
        openItem = new JMenuItem("Open...");
        fileMenu.add(countItem);
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu);

        // TO DO: register the menu items with the action listener
        countItem.addActionListener(this); 
        openItem.addActionListener(this); 
        quitItem.addActionListener(this); 
    }
}