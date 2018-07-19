/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suvatgraphapplication;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.util.LinkedList;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

/**
 * CustomRenderer for graph application for code highlight. Overrides JFreeChart Renderer Methods in order to create highlight and hide function 
 * @author Zartin
 */
public class CustomRenderer extends XYLineAndShapeRenderer {

    private XYDataset data;
    private double[] domainHighlights;
    
    LinkedList<double[]> domainHide = new LinkedList<>();
    
    
    /**
     * Constructor for custom renderer
     * @param data - Data points
     * @param domain  - domain to be highlighted
     * @param domainHide - domain to be hidden
     */
    CustomRenderer(XYDataset data, double[] domain, LinkedList<double[]> domainHide ) {
        this.data = data;
        this.domainHighlights = domain;
        this.domainHide = domainHide;
        
    
    }

    /**
     * Gets the paint color of given plot
     * @param row - x coordinate
     * @param col - y coordinate
     * @return paint 
     */
    @Override
    public Paint getItemPaint(int row, int col) {
        Paint cpaint = getItemColor(row, col);
        if (cpaint == null) {
            cpaint = super.getItemPaint(row, col);
        }
        return cpaint;
    }

    /**
     * Set color of specified plot. Domain highlight is colored yellow
     * @param row - x coordinate
     * @param col - y coordinate
     * @return paint color
     */
    public Color getItemColor(int row, int col) {

        double x = getData().getXValue(row, col);
        //Changes color to red if there is nothing in the graph
        if(getDomainHighlights().length==0){
        return Color.RED;
        }
        ///Changes item to color yellow if it is within a given domain
        if (getDomainHighlights()[0] < x && x <= getDomainHighlights()[1]) {
            return Color.YELLOW;
        //Changes item to color red if it is out of a given domain
        } else {
            return Color.RED;
        }
 
    }
    
    
    /**
     * Sets the line item invisible if it is within a given domain
     * @param row - x value
     * @param col - y value
     * @return if item should be visible
     */
    @Override
    public boolean getItemLineVisible(int row , int col) {
       
        
        double x = data.getXValue(row, col);
        
        if(domainHide.isEmpty()){
        return true;
        }
        //Loops through all the domains that needs to be hidden
        for(int count = 0; count < domainHide.size();count++){
        double min = domainHide.get(count)[0];
        double max = domainHide.get(count)[1];
        //Hides point if it is within the domain
        if (min < x && x <= max) {
            return false;
        
        } 
        }
        return true;
        
    }
    
    
    /**
     * Paints the lines accordingly.
     * @param g2 - Java graphics
     * @param pass - pass
     * @param series - Plot series
     * @param item - Series item
     * @param shape - Shape of item
     */
    @Override
    protected void drawFirstPassShape(Graphics2D g2, int pass, int series,
        int item, Shape shape) {
        g2.setStroke(getItemStroke(series, item));
        
        //Gets the color of the first and last item
        Color c1 = getItemColor(series, item);
        Color c2 = getItemColor(series, item - 1);

        //Paints the lines accordingly
        GradientPaint linePaint = new GradientPaint(0, 300, c1, 0, 300, c2);

        g2.setPaint(linePaint);
        g2.draw(shape);
    }
    
 
    /**
     * @return the data
     */
    public XYDataset getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(XYDataset data) {
        this.data = data;
    }

    /**
     * @return the domainHighlights
     */
    public double[] getDomainHighlights() {
        return domainHighlights;
    }

    /**
     * @param domainHighlights the domainHighlights to set
     */
    public void setDomainHighlights(double[] domainHighlights) {
        this.domainHighlights = domainHighlights;
    }

 

  
}
