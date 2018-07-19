/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suvatgraphapplication;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

/**
 * Graph Class that deals with most of the graphics and plots
 *
 * @author Zartin
 */
public class Graph {

    private LinkedList<XYLineAnnotation> annotations = new LinkedList<>();
    private JFreeChart chart;
    private ChartPanel chartPanel;
    private XYDataset data;
    private XYPlot plot;
    private Color lineColor;
    private Color backGroundColor;
    private String yLabel;
    private String xLabel;

    DrawPen dP = new DrawPen();

    int width = 640; /* Width of the image */
    int height = 480; /* Height of the image */


    /**
     * Constructor of graph class. Creates the plots and panels automatically.
     *
     * @param xLabel - x-axis label
     * @param yLabel - y-axis label
     * @param data - dataset to be used as plot
     * @param list - annotations list
     */
    Graph(String xLabel, String yLabel, XYDataset data, LinkedList<XYLineAnnotation> list) {
        
        
        JFreeChart newXY = ChartFactory.createXYLineChart(
                (yLabel + " over " + xLabel), xLabel, yLabel, data, PlotOrientation.VERTICAL, false, false, false);

        this.annotations = list;
        this.data = data;
        this.chart = newXY;
        this.chartPanel = new ChartPanel(chart);
        this.plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.DARK_GRAY);
        renderer.setSeriesStroke(0, new BasicStroke(5.0f));
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setBaseShapesVisible(false);

    }
    


    /**
     * Updates annotations (DrawPen lines)
     *
     * @param list - list of annotations
     */
    void updateAnnotaitons(LinkedList<XYLineAnnotation> list) {
        if (list.isEmpty()) {
        } else {
            for (int x = 0; x < list.size(); x++) {
                plot.addAnnotation(list.get(x));
            }
        }
    }

    /**
     * Clears annotations from graph
     */
    void removeAnnotations() {
        annotations = new LinkedList<>();
        plot.clearAnnotations();
    }

    /**
     * Hides the entire graph
     *
     * @param isVisible - checks if line is invisible
     */
    void setLineVisible(Boolean isVisible) {
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.DARK_GRAY);
        renderer.setSeriesStroke(0, new BasicStroke(5.0f));
        renderer.setSeriesPaint(0, Color.RED);

        //hides the shape
        renderer.setBaseShapesVisible(false);
        renderer.setBaseLinesVisible(isVisible);

    }

    /**
     * Creates chart highlight in a given domain
     *
     * @param domain - time domain to be highlighted
     * @param domainHide - time domain to be hidden
     */
    void createChartHighLight(double[] domain, LinkedList<double[]> domainHide ) {
        //Instatiates custom renderer for plot
        CustomRenderer cr = new CustomRenderer(this.data, domain, domainHide );
        getPlot().setRenderer(cr);
        getPlot().setBackgroundPaint(Color.DARK_GRAY);
        cr.setSeriesStroke(0, new BasicStroke(4.0f));
        cr.setSeriesPaint(0, Color.WHITE);
        cr.setBaseShapesVisible(false);
        cr.setBaseLinesVisible(true);

    }

    /**
     * Activates drawing tool for graph and disables zoom function
     */
    void setDrawingTool() {

        this.chartPanel.addMouseMotionListener(dP);
        dP.setIsActive(true);
        this.chartPanel.removeMouseListener(chartPanel);
        this.chartPanel.setFillZoomRectangle(false);

    }

    /**
     * Disables drawing tool and enables zoom function again
     */
    void disableMouse() {
        this.chartPanel.addMouseListener(chartPanel);
        this.chartPanel.removeMouseMotionListener(dP);
        this.chartPanel.setFillZoomRectangle(true);
        dP.setIsActive(false);
    }

    /**
     * Inner class for drawing tool. 
     * It calculates mouse coordinates relative the the size of the graph
     * Has isActive method to enable and disable when needed
     */
    class DrawPen implements MouseMotionListener {

        private boolean isActive;

    
        public void mouseDragged(MouseEvent e) {
            if (!isActive) {

                return;
            } else {
                
               //Gets mouse coordinates
                Point p = e.getPoint();
                System.out.println("");
                System.out.println("ScreenXCoordinate: " + e.getX() + " ScreenYCoordinate: " + e.getY());
                //Gets the chart area
                Rectangle2D plotArea = getChartPanel().getScreenDataArea();
                //calculates mouse coordinates based off chart area
                double x = getPlot().getDomainAxis().java2DToValue(p.getX(), plotArea, plot.getDomainAxisEdge());
                double y = getPlot().getRangeAxis().java2DToValue(p.getY(), plotArea, plot.getRangeAxisEdge());
                
                System.out.println("GraphXCoordinate: " + x + " GraphYCoordinate:  " + y);
                
                //Plots the mouse coordinates at the x and y coordinates
                createPoint(x, y);
            }

        }

        /**
         * @return the isActive
         */
        public boolean isIsActive() {
            return isActive;
        }

        /**
         * @param isActive the isActive to set
         */
        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        @Override
        //Does nothing if mouse is moved
        public void mouseMoved(MouseEvent e) {

        }

    }

    /**
     * Plots an annotation at the x and y coordinate
     * @param x - x coordinate
     * @param y - y coordinate
     */
    void createPoint(double x, double y) {
        XYLineAnnotation point = new XYLineAnnotation(x, y, x, y, new BasicStroke(5.0f), Color.CYAN);
        getPlot().addAnnotation(point);
        this.annotations.add(point);
    }

    /**
     * Saves the graph as designated string name
     * @param name  - name of file
     */
    void exportAsPNG(String name) {

        File XYChart = new File(name);
        try {
            ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            ChartUtilities.saveChartAsPNG(XYChart, this.chart, width, height, info);
        } catch (IOException ex) {

        }
    }
    
    /*
    Below are all the encapsulation fields
    */

    /**
     * @return the yLabel
     */
    public String getyLabel() {
        return yLabel;
    }

    /**
     * @param yLabel the yLabel to set
     */
    public void setyLabel(String yLabel) {
        this.yLabel = yLabel;
    }

    /**
     * @return the xLabel
     */
    public String getxLabel() {
        return xLabel;
    }

    /**
     * @param xLabel the xLabel to set
     */
    public void setxLabel(String xLabel) {
        this.xLabel = xLabel;
    }

    /**
     * @return the chart
     */
    public JFreeChart getChart() {
        return chart;
    }

    /**
     * @param chart the chart to set
     */
    public void setChart(JFreeChart chart) {
        this.chart = chart;
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
     * @return the plot
     */
    public XYPlot getPlot() {
        return plot;
    }

    /**
     * @param plot the plot to set
     */
    public void setPlot(XYPlot plot) {
        this.plot = plot;
    }

    /**
     * @return the lineColor
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * @param lineColor the lineColor to set
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * @return the backGroundColor
     */
    public Color getBackGroundColor() {
        return backGroundColor;
    }

    /**
     * @param backGroundColor the backGroundColor to set
     */
    public void setBackGroundColor(Color backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    /**
     * @return the chartPanel
     */
    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    /**
     * @param chartPanel the chartPanel to set
     */
    public void setChartPanel(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    /**
     * @return the annotations
     */
    public LinkedList<XYLineAnnotation> getAnnotations() {
        return annotations;
    }
    
       /**
     * @param old - old annotations
     * @return the annotations
     */
    public LinkedList<XYLineAnnotation> getAnnotations(LinkedList<XYLineAnnotation> old) {
        annotations.addAll(old);
        return annotations;
    }

    /**
     * @param annotations the annotations to set
     */
    public void setAnnotations(LinkedList<XYLineAnnotation> annotations) {
        this.annotations = annotations;
    }

}
