/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suvatgraphapplication;

/**
 * Main class that instantiates GUIS and logic class
 * @author Zartin
 */
public class SuvatGraphApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Creates new instance of logic class
        Logic logic = new Logic();
        //Passes logic class to instructor of main GUI
        MainGUI gui = new MainGUI(logic);
      
        //shows GUI
        gui.setVisible(true);
        
        //Sets GUI to be ready for use
        gui.updateAll(true);
        
        //Initially makes netDisp invisibel to prevent it from appearing with initial and final displacement
        gui.getNetDispField().setVisible(false);
       double x = (10.0/4.0);
        System.out.println(x);
    }
    
}
