/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suvatgraphapplication;

import java.io.File;
import java.math.*;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Backend algorithms for application
 * @author Zartin
 */
public class Logic {
    
    //LinkedList used to store all generated leg of motions 
    private LinkedList<LegOfMotion> legOfMotion = new LinkedList<>();

    private String[] columnnames = {"Initial Displacement (m)", "Final Displacement(m)", "Initial Velocity(m/s)", "Final Velocity(m/s)", "Acceleration(m/s²)", "Time(s)"};
    FileHandler fh = new FileHandler();
    private LinkedList<double[]> domainHides = new LinkedList<>();
    private LinkedList<Integer> domainHideIndex = new LinkedList<>();
    
    private String errorMessage = "Error:";
    
    /**
     * Method is used to get a time range needed later to highlight/hide corresponding sections in the graphs
     * @param min - Index of the first selected range.
     * @param max - Index of the last selected range.
     * @return time range to be hidden or highlighted
     */
    double[] getTimeRange(int min, int max) {

        double start = 0;
        
        //Loops from 0 to the maximum leg to calculate total time elapsed
        for (int x = 0; x < max; x++) {
            
            //Start is changed to the start of the maximum index leg
            start = start + legOfMotion.get(x).getTime();
           
        }
        //End is the start plus the time for max index leg
        double end = start + legOfMotion.get(max).getTime();
        
        //Start is set to 0 if min index is 0;
        if (min == 0){
        start =0;
        }
        
        //returns as a double array;
        double[] timeStartandEnd = {start, end};
        
        return timeStartandEnd;
    }
    
    /**
     * Add a domain that is to be hidden on the graphs.
     * @param min - index of min table select
     * @param max - index of max table select
     */
    void addDomainHides(int min, int max){
        double[] domain = getTimeRange(min,max);
        if(getDomainHides().contains(domain)){
            getDomainHides().remove(domain);
        }else{
            getDomainHides().add(domain);}
    
    }
    
    /**
     * Removes the domain of the given index from hide list
     * @param min - index of min selected 
     * @param max - index of max table select 
     */
    void removeDomainHide(int min, int max){
        double[] domain = getTimeRange(min,max);
        if(getDomainHides().contains(domain)){
            System.out.println("check");
            getDomainHides().remove(domain);
            
        }
        else{
        }
     
    }
    
    /**
     * Clears the domain that should be hidden on the graph
     * @param hide - hides all domains
     */
    void removeAllDomainHides(boolean hide){
        if (hide==true){
        LinkedList<double[]> domainHide = new LinkedList<>();
        this.domainHides = domainHide;}
    
    }
    
  
    
    /**
     * returns LegOfMotion at the specified index
     * @param index - Index in the LegofMotion list
     * @return LegOfMotion at that index
     */
    LegOfMotion getLegOfMotion(int index) {
        return legOfMotion.get(index);
    }
    
    /**
     * Creates a new leg of motion and adds to the end list.
     * @param booleanList - checkboxe data from MainGUI
     * @param list - Suvat values from mainGUI
     * @param b - boolean that specifies if netDisplacement or initial and final displacement were given
     */
    void addLegOfMotion(boolean[] booleanList, double[] list, boolean b) {
        LegOfMotion leg = legOfMotionCreation(booleanList, list, b);
        legOfMotion.add(leg);

    }

    /**
     * Creates a new leg of motion and adds it to the list at index
     * Leg of motions after this are shifted with changes accounting to the new displacement 
     * @param booleanList - checkboxe data from MainGUI
     * @param list - Suvat values from MainGUI
     * @param index - index where new leg of motion is to be added.
     */
    void addLegOfMotionNext(boolean[] booleanList, double[] list, int index) {
        
        
        LegOfMotion leg = legOfMotionCreation(booleanList, list, true);
        index = index +1;
        legOfMotion.add(index, leg);
       
        //Loops through the list from index x such that consistency with displacement is perserved
        for (int x = index; x < legOfMotion.size(); x++) {
            LegOfMotion previous = legOfMotion.get(x - 1);
            LegOfMotion current = legOfMotion.get(x);

            current.setIdisplacement(previous.getFdisplacement());
            current.setFdisplacement(current.getIdisplacement() + current.getTotaldisplacement());
        }

    }

    /**
     * Removes all leg of motion from the list
     */
    void clearList(){
        legOfMotion = new LinkedList<>();
  
     
    }
    
    /*
   Function calls for the FileHandler Methods
    */
    
    /**
     * Calls the fileHandler read function.
     * @param filePath - file path of file
     */
    void load(String filePath) {
        legOfMotion = fh.read(filePath);
    }

    /**
     * Calls the fileHandler write function.
     * @param path - file path to save
     */
    void save(String path) {
        fh.setFILE_PATH(path);
        fh.write(legOfMotion);
    }
    
     /*
    Fnction calls for the FileHandler Methods
    */

    /**
     * Returns a boolean list of all the checkBox selection
     * @param fv - boolean of final velocity check box
     * @param acc - boolean of acceleration check box
     * @param time - boolean of time check box
     * @return Boolean list of parameters
     */
    boolean[] checkBox(boolean fv, boolean acc, boolean time) {
        boolean[] list = new boolean[3];
       
        list[0] = fv;
        list[1] = acc;
        list[2] = time;

        return list;
    }


    /**
     * Converts textfield values into doubles.
     * @return list of all input fields as doubles list
     * @param stringList - of id, fd, netd, iv, fv, acc, time
     */
    double[] textFieldList(String[] stringList) {

        double[] inputData = new double[stringList.length];
        
        for (int x = 0; x < stringList.length; x++) {
            try {
                //Converts string into double
                inputData[x] = Double.parseDouble(stringList[x]);

            } catch (NumberFormatException e) {
                //if input is anything but a number, automatically set as 0;
                inputData[x] = 0;
            }
        }

        return inputData;

    }

    

    /**
     * Function that returns true if only 1 check box is selected
     * @param list - check box values
     * @return - returns true if only 1 is selected
     */
    boolean checkBoxCount(boolean[] list) {
        int count = 0;
        for (int x = 0; x < list.length; x++) {
            if (list[x] == true) {
                count = count + 1;
            }

        }

        return count == 1;
    }

    /**
     * Creates a new leg of motion.
     * @param booleanList - check box values
     * @param list - suvat values 
     * @param isNetDisp - boolean that checks if user is using NetDip text field
     * @return legOfMotion with specified values
     */
    LegOfMotion legOfMotionCreation(boolean[] booleanList, double[] list, boolean isNetDisp) {
       
        boolean fvB = booleanList[0];
        boolean accB = booleanList[1];
        boolean timeB = booleanList[2];

        double iDisp = list[0];
        double fDisp = list[1];
        double netDisp = list[2];
        double iv = list[3];
        double fv = list[4];
        double acc = list[5];
        double time = list[6];

        //if netDisp is given calculate final displacement first
        if (isNetDisp) {
            fDisp = netDisp + iDisp;

        }

        LegOfMotion leg = null;

        //Creates leg of motion according to check box
        if (fvB) {

            leg = legCreationDVF(iDisp, fDisp, iv, fv);

        } else if (accB) {

            leg = legCreationDIA(iDisp, fDisp, iv, acc);

        } else if (timeB) {

            leg = legCreationDIVT(iDisp, fDisp, iv, time);

        }
         else {
        }

        return leg;

    }

    /**
     * Edits the leg of motion at index as new leg of motion.
     * Leg of motion list is changed for displacement changes.
     * @param index - index of leg of motion to be edited
     * @param booleanList - check box data
     * @param list - suvat values
     * @param b  - if netDisp is given
     */
    void editLegOfMotion(int index, boolean[] booleanList, double[] list, boolean b) {

        LegOfMotion edit = legOfMotionCreation(booleanList, list, b);
        legOfMotion.remove(index);
        legOfMotion.add(index, edit);
        int countFromIndex = index + 1;

        //Abrupt changes in motion avoided by shifting displacement values appropriately
        for (int x = countFromIndex; x < legOfMotion.size(); x++) {

            LegOfMotion previous = legOfMotion.get(x - 1);
            LegOfMotion current = legOfMotion.get(x);

            current.setIdisplacement(previous.getFdisplacement());
            current.setFdisplacement(current.getIdisplacement() + current.getTotaldisplacement());

        }

    }


    /**
     * Returns a double array to be used for the MainGUI table
     * @return double array of leg of motion list
     */
    Object[][] tableFormat() {

        Object[][] table = new Object[getLegOfMotionList().size()][6];
        
        //Rounds value to 2 decimal places
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.CEILING);
     
        for (int x = 0; x < getLegOfMotionList().size(); x++) {
           
            table[x][0] = df.format(getLegOfMotionList().get(x).getIdisplacement());
            table[x][1] = df.format(getLegOfMotionList().get(x).getFdisplacement());
            table[x][2] = df.format(getLegOfMotionList().get(x).getIvelocity());
            table[x][3] = df.format(getLegOfMotionList().get(x).getFvelocity());
            table[x][4] = df.format(getLegOfMotionList().get(x).getAcceleration());
            table[x][5] = df.format(getLegOfMotionList().get(x).getTime());

        }
        return table;
    }

    /**
     * Deletes leg of motion at index. Shifts all leg of motion by displacement accordingly.
     * @param index - index to be deleted
     */
    void deleteLegOfMotion(int index) {
        getLegOfMotionList().remove(index);
        if (legOfMotion.size() == 1) {

        } else {
            for (int x = index; x < legOfMotion.size(); x++) {
                //Displacement consistency is achieved by setting them accordingly.
                LegOfMotion previous = legOfMotion.get(x - 1);
                LegOfMotion current = legOfMotion.get(x);

                current.setIdisplacement(previous.getFdisplacement());
                current.setFdisplacement(current.getIdisplacement() + current.getTotaldisplacement());
            }
        }
    }

  
    /**
     * Creates new leg of motion with displacement, initial velocity, and final velocity given
     * @param iDisp - initial displacement
     * @param fDisp - final displacement
     * @param iv - initial velocity
     * @param fv - final velocity
     * @return leg of motion
     */
    LegOfMotion legCreationDVF(double iDisp, double fDisp, double iv, double fv) {
        double netDisp = this.calculateTotalDisplacement(iDisp, fDisp);
        double acc = this.calculateAcceleration(netDisp, iv, fv);
        double time = this.calcualteTime(netDisp, iv, fv);

        LegOfMotion leg = new LegOfMotion(iDisp, fDisp, netDisp, iv, fv, acc, time);
        return leg;

    }

    /**
     * Creates new leg of motion with displacement, initial velocity, and acceleration give.
     * @param iDisp - initial displacement
     * @param fDisp - final displacement
     * @param iv - initial velocity
     * @param acc - acceleration
     * @return leg of motion
     */
    LegOfMotion legCreationDIA(double iDisp, double fDisp, double iv, double acc) {
        double netDisp = this.calculateTotalDisplacement(iDisp, fDisp);
        double fv = this.calculatefV(netDisp, iv, acc);
        double time = this.calcualteTime(netDisp, iv, fv);

        LegOfMotion leg = new LegOfMotion(iDisp, fDisp, netDisp, iv, fv, acc, time);
        return leg;

    }

    /**
     * Creates new leg of motion with displacement, initial velocity, and time given
     * @param iDisp - initial displacement
     * @param fDisp - final displacement 
     * @param iv - initial velocity
     * @param time - time
     * @return leg of motion
     */
    LegOfMotion legCreationDIVT(double iDisp, double fDisp, double iv, double time) {
        double netDisp = this.calculateTotalDisplacement(iDisp, fDisp);
        double fv = this.calculatefVt(netDisp, iv, time);
        double acc = this.calculateAcceleration(netDisp, iv, fv);

        LegOfMotion leg = new LegOfMotion(iDisp, fDisp, netDisp, iv, fv, acc, time);
        return leg;
    }

   
    /**
     *  Calculates the total displacement from 2 known displacement values
     * @param idisp - initial velocity
     * @param fdisp - final velocity
     * @return total displacement
     */
    double calculateTotalDisplacement(double idisp, double fdisp) {
        return (fdisp - idisp);
    }

    /**
     * Calculates the acceleration from total displacement, initial velocity, and final velocity
     * @param netDisp - net displacement
     * @param iv - initial velocity
     * @param fv - final velocity
     * @return acceleration
     */
    double calculateAcceleration(double netDisp, double iv, double fv) {
        double acc;
        
        acc = (Math.pow(fv, 2) - Math.pow(iv, 2)) / (2 * netDisp);
        
        if (iv == fv) {
            acc = 0;
            return acc;
        } else if (iv > fv) {
            //Ensures that values make sense with given input
            return Math.abs(acc) * -1;

        } else {
            return acc;

        }

    }
    
 
   /**
    * Calculates the time from the total displacement, initial velocity, and final velocity
    * @param disp - net displacement
    * @param iv - initial velocity
    * @param fv - final velocity
    * @return time
    */
    double calcualteTime(double disp, double iv, double fv) {
        double time = (2 * disp) / (iv + fv);
        if (time < 0) {
            //Avoids negative time
            return time * -1;

        } else {
            return time;
        }

    }


    
   /**
    * calculates the final velocity from total displacement, initial velocity and acceleration
    * @param disp - net displacement
    * @param iv - initial velocity
    * @param acc - acceleration
    * @return final velocity
    */
    double calculatefV(double disp, double iv, double acc) {
        double fv;
        if(acc==0){
            
       return fv = iv;
       }
        
      fv = Math.sqrt(Math.pow(iv, 2) + (2 * disp * acc));
        
        if (acc < 0) {
            return fv * -1;
        }
        return fv;

    }

    /**
     * calculates final velocity from total displacement, initial velocity, and time
     * @param disp - net displacement
     * @param iv - initial velocity
     * @param time - time
     * @return final velocity
     */
    double calculatefVt(double disp, double iv, double time) {
        double fv = ((2 * disp) / time) + iv;
        return fv;

    }

    /*----------
     Graph Logic:
     Functions below are used to derive points to be used for their respective graphs
     ------------*/
    
    /**
     * Creates all the data points for the displacement graph
     * @return XYDataset of displacement values
     */
    XYDataset getDispData() {
        final XYSeries data = new XYSeries("Displacement(m)", false);
        
        //Number of data points per leg of motion
        double count = 1;
        double dividen = 100;
        
        double time = 0;
        double totalTime = 0;
        
        //Returns an empty dataset if there are no leg of motion in the list
        if(legOfMotion.isEmpty()){
            final XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(data);
            return dataset;
        }
        
        data.add(time, legOfMotion.get(0).getIdisplacement());

        //loops through all leg of motion
        for (int x = 0; x < legOfMotion.size(); x++) {
            LegOfMotion leg = legOfMotion.get(x);
            data.add(totalTime, leg.getIdisplacement());
            
            //loops through time intervals of leg of motion
            while (count < dividen) {
                
                //calculates displacement at fraction of the time
                time = leg.getTime();
                double tmpTime = time * (count / dividen);
                double s = (leg.getIvelocity() * tmpTime) + (0.5 * leg.getAcceleration() * Math.pow(tmpTime, 2));
                double disp = s + leg.getIdisplacement();
                tmpTime = tmpTime + totalTime;

                //Adds point to the dataset
                data.add(tmpTime, disp);
                count = count + 1;
            }

            //Resets counter for next leg of motion
            count = 0;
            //Updates total time elapsed accordingly
            totalTime = totalTime + leg.getTime();
            data.add(totalTime, leg.getFdisplacement());

        }

        //Adds disp dataset to the dataset collection to be used in the final graph
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(data);
        return dataset;

    }

    /**
     * Creates all data points for velocity graph
     * @return velocity dataset
     */
    XYDataset getVelocityData() {
        final XYSeries velocity = new XYSeries("Velocity", false);
        
        //if leg of motion list is empty create an empty data set
         if(legOfMotion.isEmpty()){
            final XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(velocity);
            return dataset;
        }
         
        //Plots the first point on the graph
        double time = 0;
        velocity.add(time, legOfMotion.get(0).getIvelocity());

        //Loops through all the leg of motion on the list
        for (int x = 0; x < legOfMotion.size(); x++) {
            //Plots initial and final velocity
            LegOfMotion leg = legOfMotion.get(x);
            velocity.add(time, leg.getIvelocity());

            time = time + leg.getTime();
            velocity.add(time, leg.getFvelocity());
        }

        //Adds velocity dataset to the dataset collection to be used in the final graph
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(velocity);

        return dataset;

    }

    /**
     * Creates all data points for acceleration graph
     * @return acceleration data
     */
    XYDataset getAccData() {
        final XYSeries acc = new XYSeries("Accleration", false);

        //time plot the first point on the graph
        double time = 0;
        
        //if list is empty create empty dataset
         if(legOfMotion.isEmpty()){
            final XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(acc);
            return dataset;
        }

        //Loops through all the leg of motion on the list
        for (int x = 0; x < legOfMotion.size(); x++) {
            //plots accleration at the beginning and end of time interval
            LegOfMotion leg = legOfMotion.get(x);
            acc.add(time, leg.getAcceleration());
            time = time + leg.getTime();
            acc.add(time, leg.getAcceleration());
        }

        //Adds accleration dataset to the dataset collection to be used in the final graph
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(acc);

        return dataset;
    }
    
    
  

    
   /*--------
    Below are all the encapsulation setters and getters
    ----------*/
    
    /**
     * @return the columnnames
     */
    public String[] getColumnnames() {
        return columnnames;
    }

    /**
     * @param columnnames the columnnames to set
     */
    public void setColumnnames(String[] columnnames) {
        this.columnnames = columnnames;
    }

    /**
     * @return the leg of motion list
     */
    public LinkedList<LegOfMotion> getLegOfMotionList() {
        return legOfMotion;
    }

    /**
     * @param legOfMotion the leg of motion list to set
     */
    public void setLegOfMotionList(LinkedList<LegOfMotion> legOfMotion) {
        this.legOfMotion = legOfMotion;
    }

    /**
     * @return the domainHides
     */
    public LinkedList<double[]> getDomainHides() {
        return domainHides;
    }

    /**
     * @param domainHides the domainHides to set
     */
    public void setDomainHides(LinkedList<double[]> domainHides) {
        this.domainHides = domainHides;
    }

    /**
     * @return the domainHideIndex
     */
    public LinkedList<Integer> getDomainHideIndex() {
        return domainHideIndex;
    }

    /**
     * @param domainHideIndex the domainHideIndex to set
     */
    public void setDomainHideIndex(LinkedList<Integer> domainHideIndex) {
        this.domainHideIndex = domainHideIndex;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    

}
