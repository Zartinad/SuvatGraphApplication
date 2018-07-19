/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suvatgraphapplication;


/**
 *Leg of Motion Class Template
 * @author Zartin
 */
public class LegOfMotion {
    
    //Fields are encapsulated to prevent coupling issues in the future
    private double ivelocity;
    private double fvelocity;
    private double idisplacement;
    private double fdisplacement;
    private double totaldisplacement;
    private double acceleration;
    private double time;
    private boolean isPositive;
    
    /**
     *A constructor used to create a new leg of motion.
     * @param id Initial Displacement
     * @param fd Final Displacement
     * @param td Total/Net Displacement
     * @param iv Initial Velocity
     * @param fv Final Velocity
     * @param acc Acceleration
     * @param time Time
     */
        public LegOfMotion( double id, double fd, double td, double iv, double fv, double acc, double time){
   
        this.idisplacement = id;
        this.fdisplacement = fd;
        this.totaldisplacement = td;
        this.ivelocity = iv;
        this.fvelocity = fv;
        this.acceleration = acc;
        this.time = time;
        
    
    }

    /**
     * Method that returns the leg of motion as a new table row format
     * Row: idisplacement, fdisplacement, tdisplacement, ivelocity, fvelocity, acc, time;
     * @return the leg of motion as a new table row format
     */
    Object[] toRow(){
        //Creates new row of data
        Object[] rowFormat = {this.idisplacement, this.fdisplacement, this.totaldisplacement, this.ivelocity, this.fvelocity, this.acceleration, this.time};
        return rowFormat ;
    
    }
    
    @Override
    /**
     * Returns CSV format of Leg of Motion.
     * To be used in FileHandler when writing leg of motion into disk
     */
    public String toString(){
         String id = Double.toString(this.getIdisplacement());
         String fd = Double.toString(this.getFdisplacement());
         String nd = Double.toString(this.getTotaldisplacement());
         String iv = Double.toString(this.getIvelocity());
         String fv = Double.toString(this.getFvelocity());
         String acc = Double.toString(this.getAcceleration());
         String tm = Double.toString(this.getTime());
                
         return id + "," + fd + "," + nd + "," + iv+ "," + fv+ "," + acc+ "," + tm;
    
    }
    
    
    
    /*
    Below are all the setters and getters for the encapsulated fields above
    */
    
    /**
     * @return the ivelocity
     */
    public double getIvelocity() {
        return ivelocity;
    }

    /**
     * @param ivelocity the ivelocity to set
     */
    public void setIvelocity(double ivelocity) {
        this.ivelocity = ivelocity;
    }

    /**
     * @return the fvelocity
     */
    public double getFvelocity() {
        return fvelocity;
    }

    /**
     * @param fvelocity the fvelocity to set
     */
    public void setFvelocity(double fvelocity) {
        this.fvelocity = fvelocity;
    }

    /**
     * @return the idisplacement
     */
    public double getIdisplacement() {
        return idisplacement;
    }

    /**
     * @param idisplacement the idisplacement to set
     */
    public void setIdisplacement(double idisplacement) {
        this.idisplacement = idisplacement;
    }

    /**
     * @return the fdisplacement
     */
    public double getFdisplacement() {
        return fdisplacement;
    }

    /**
     * @param fdisplacement the fdisplacement to set
     */
    public void setFdisplacement(double fdisplacement) {
        this.fdisplacement = fdisplacement;
    }

    /**
     * @return the totaldisplacement
     */
    public double getTotaldisplacement() {
        return totaldisplacement;
    }

    /**
     * @param totaldisplacement the totaldisplacement to set
     */
    public void setTotaldisplacement(double totaldisplacement) {
        this.totaldisplacement = totaldisplacement;
    }

    /**
     * @return the acceleration
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * @param acceleration the acceleration to set
     */
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * @return the time
     */
    public double getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * @return the isPositive
     */
    public boolean isIsPositive() {
        return isPositive;
    }

    /**
     * @param isPositive the isPositive to set
     */
    public void setIsPositive(boolean isPositive) {
        this.isPositive = isPositive;
    }}
    
    
