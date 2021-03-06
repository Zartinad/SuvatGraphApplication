/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suvatgraphapplication;

import java.awt.BorderLayout;

/**
 * ExpandedGraphGUI
 * @author Zartin
 */
public class ExpandedGraphGUI extends javax.swing.JDialog {
private Graph graph;
private boolean isClosed = false;
java.awt.Frame parent;
    /**
     * Creates new form ExpandedGraphGUI with the graph passed from MainFrame
     * @param parent - MainFrame
     * @param modal - always true
     * @param graph - Graph to be used
     * @param isDrawEnabled - If drawing is enabled when opened
     */
    public ExpandedGraphGUI(java.awt.Frame parent, boolean modal, Graph graph, boolean isDrawEnabled) {
        super(parent, modal);
        initComponents();
        this.graph = graph;
        this.graphPanel.setLayout(new BorderLayout());
        this.graphPanel.add(graph.getChartPanel());
        this.graphPanel.validate();
        this.setDefaultCloseOperation(ExpandedGraphGUI.DO_NOTHING_ON_CLOSE);
        this.drawToggle.setSelected(isDrawEnabled);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        graphPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();
        hideToggle = new javax.swing.JToggleButton();
        drawToggle = new javax.swing.JToggleButton();
        clearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        graphPanel.setBackground(new java.awt.Color(0, 0, 255));
        graphPanel.setLayout(new javax.swing.BoxLayout(graphPanel, javax.swing.BoxLayout.LINE_AXIS));

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        exportButton.setText("Export");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        hideToggle.setText("Hide");
        hideToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideToggleActionPerformed(evt);
            }
        });

        drawToggle.setText("Draw");
        drawToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawToggleActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                .addComponent(drawToggle, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hideToggle, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(graphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hideToggle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(drawToggle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exports graph as PNG
     * @param evt - button click
     */
    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        //Saves graph as image
        getGraph().exportAsPNG("new graph");
    }//GEN-LAST:event_exportButtonActionPerformed

    /**
     * Clears the graph of all drawings
     * @param evt - button click
     */
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        //Removes all the graph drawings
        getGraph().removeAnnotations();
    }//GEN-LAST:event_clearButtonActionPerformed

    /**
     * Closes the JDialog
     * @param evt - button click
     */
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        //Closes the JDialog
         isClosed = true;
         this.setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed

    /**
     * Hides graph when toggled on
     * @param evt - button click
     */
    private void hideToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideToggleActionPerformed
        //Hides the graph
        getGraph().setLineVisible(hideToggle.isSelected());
    }//GEN-LAST:event_hideToggleActionPerformed

    /**
     * Enables draw function when toggled on
     * @param evt - button click
     */
    private void drawToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawToggleActionPerformed
        //Enables drawing function
        if(drawToggle.isSelected())  {       
            getGraph().setDrawingTool();}else
       {    getGraph().disableMouse();}
    }//GEN-LAST:event_drawToggleActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JToggleButton drawToggle;
    private javax.swing.JButton exportButton;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JToggleButton hideToggle;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @param graph the graph to set
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * @return the isClosed
     */
    public boolean isIsClosed() {
        return isClosed;
    }

    /**
     * @param isClosed the isClosed to set
     */
    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }
}
