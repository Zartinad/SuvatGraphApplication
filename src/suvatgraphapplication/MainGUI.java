/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suvatgraphapplication;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.annotations.XYLineAnnotation;


/**
 * Main screen of the application
 * @author Zartin
 */
public class MainGUI extends javax.swing.JFrame {
    
    //Declares all needed field variables
    Logic logic;
    
    ListSelectionModel tabelListener;
    DefaultTableModel tableModel;
    
    Graph dispGraph;
    Graph velocityGraph;
    Graph accGraph;
    
    //List of annotations so that they are retained when graph is updated.
    LinkedList<XYLineAnnotation> oldDispAnnotations= new LinkedList<>();    
    LinkedList<XYLineAnnotation> oldVelocityAnnotations = new LinkedList<>();
    LinkedList<XYLineAnnotation> oldAccAnnotations = new LinkedList<>();   
    
    
    /**
     * MainGUI constructor
     * @param logic - instance of logic class
     */
    public MainGUI(Logic logic) {
        
        this.logic = logic;
        initComponents();
        
        
        //Creates new graphs
        this.dispGraph = new Graph("Time(s)", "Displacement(m)", logic.getDispData(), this.oldDispAnnotations);
        this.velocityGraph = new Graph("Time(s)", "Velocity(m/s)", logic.getVelocityData(), this.oldVelocityAnnotations);
        this.accGraph = new Graph("Time(s)", "Acceleration(m/sU+00B2)", logic.getAccData(), this.oldAccAnnotations);
        
        this.errorText.setVisible(false);
        
        //Sets JFrame custom settings
        setResizable(false);
        this.getContentPane().setBackground(Color.DARK_GRAY);

        //Instantiates custom table listener.
        this.tabelListener = table.getSelectionModel();
        tabelListener.addListSelectionListener(new SharedListSelectionHandler());
        
        //Sets table model pointer for any table changes to be made
        tableModel = (DefaultTableModel) table.getModel();
        table.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 16));
       
        //Instantiates custom table renderer
        table.setDefaultRenderer(table.getColumnClass(0), new MyTableCellRenderer());
        
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);

        /*Listens for when combobox menu is changed
         *Switches text fields accordingly
         */
        this.dispMenu.addActionListener((ActionEvent e) -> {
            if (dispMenu.getSelectedIndex() == 0) {
                iDispField.setVisible(true);
                fDispField.setVisible(true);
                netDispField.setVisible(false);
                if(!tabelListener.isSelectionEmpty()){addButton.setEnabled(false);}
                else{addButton.setEnabled(true);}
                
            } else if (dispMenu.getSelectedIndex() == 1) {
               
                netDispField.setLocation(iDispField.getLocation());
                netDispField.setVisible(true);
                iDispField.setVisible(false);
                fDispField.setVisible(false);
                addButton.setEnabled(true);
            }
        });
        
    }
    
    /**
     * Class used to hide certain rows on the table.
     * It is used as a custom renderer for the table.
     */
     class MyTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setForeground(Color.black);
        
        //Loops through list that stores which index needs to be hidden
        for(int x = 0; x<logic.getDomainHideIndex().size();x++){
            if (row == logic.getDomainHideIndex().get(x)){
            c.setForeground(Color.white);  
            return c;
            }
            else c.setForeground(Color.black);
        }
       
        return c;
    }
}
    /**
     * Custom table listener. 
     * Creates highlight when rows are selected and disables buttons based selection.
     */
    class SharedListSelectionHandler implements ListSelectionListener {
        //Listener for when a row is selected
        @Override
        public void valueChanged(ListSelectionEvent e) {
            
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            
            if (lsm.isSelectionEmpty()) {
                //Disables edit/delete button to prevent null pointer errors 
                //Eneables add button assuming user wants to add next leg of motion
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
                
            }
            else{
                //Disables add button to avoid consistency issues with leg of motions.
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
                addButton.setEnabled(true);
                //When a row is selected, values will copied into field so it can easily be editted
                int index = lsm.getLeadSelectionIndex();
                fillFields(index);        
                
                //Selected row leg of motion will be highlighted
                
                //Gets the domain for row to be highlighted
                double[] domain = logic.getTimeRange(lsm.getMinSelectionIndex(), lsm.getMaxSelectionIndex());
                
                //Sets row to be highlighted
                velocityGraph.createChartHighLight(domain, logic.getDomainHides());
                dispGraph.createChartHighLight(domain, logic.getDomainHides());                
                accGraph.createChartHighLight(domain, logic.getDomainHides());
               
               
                addButton.setEnabled(dispMenu.getSelectedIndex()==1);
                
     
               
                
            }
        }
    }
    
    
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        atGroup = new javax.swing.ButtonGroup();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jPanel10 = new javax.swing.JPanel();
        leftHalfPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        iVField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        fvBox = new javax.swing.JCheckBox();
        fVField = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        accBox = new javax.swing.JCheckBox();
        accField = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        timeField = new javax.swing.JTextField();
        timeBox = new javax.swing.JCheckBox();
        dispPanel = new javax.swing.JPanel();
        dispMenu = new javax.swing.JComboBox();
        iDispField = new javax.swing.JTextField();
        fDispField = new javax.swing.JTextField();
        netDispField = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        clearSelectionButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        showButton = new javax.swing.JButton();
        hideTable = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        clearTableButton = new javax.swing.JButton();
        hideOnGraph = new javax.swing.JButton();
        showOnGraph = new javax.swing.JButton();
        velocityPanel = new javax.swing.JPanel();
        accPanel = new javax.swing.JPanel();
        dispButtonsPanel = new javax.swing.JPanel();
        exportDispGraphButton = new javax.swing.JButton();
        hideDispToggleButton = new javax.swing.JToggleButton();
        sExpand = new javax.swing.JButton();
        drawToggle = new javax.swing.JToggleButton();
        clearButton = new javax.swing.JButton();
        velocityButtons = new javax.swing.JPanel();
        exportVelocityButton = new javax.swing.JButton();
        hideVelocityToggleButton = new javax.swing.JToggleButton();
        velocityExpand = new javax.swing.JButton();
        drawVelocityToggle = new javax.swing.JToggleButton();
        clearVelocityButton = new javax.swing.JButton();
        accButtonsPanle = new javax.swing.JPanel();
        exportAccButton = new javax.swing.JButton();
        hideAccToggleButton = new javax.swing.JToggleButton();
        accExpand = new javax.swing.JButton();
        drawAccToggle = new javax.swing.JToggleButton();
        clearAccButton = new javax.swing.JButton();
        displacementPanel1 = new javax.swing.JPanel();
        dispGraphPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        errorText = new javax.swing.JLabel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(255, 51, 51));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        leftHalfPanel.setBackground(new java.awt.Color(153, 153, 153));
        leftHalfPanel.setBorder(new javax.swing.border.MatteBorder(null));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        iVField.setText("Initial Velocity");

        jLabel1.setText("Initial Velocity");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iVField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iVField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        fvBox.setText("Final Velocity");

        fVField.setText("Final Veloctiy");
        fVField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fVFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(fvBox)
                .addGap(0, 34, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fVField)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fvBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fVField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        accBox.setText("Acceleration");

        accField.setText("Acceleration");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(accBox)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(accBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

        timeField.setText("Time");

        timeBox.setText("Time");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timeBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timeBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dispPanel.setBackground(new java.awt.Color(153, 153, 153));

        dispMenu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Initial/Final Displacement", "Net Displacement" }));
        dispMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispMenuActionPerformed(evt);
            }
        });

        iDispField.setText("iDisplacement");
        iDispField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iDispFieldActionPerformed(evt);
            }
        });

        fDispField.setText("fDisplacement");

        netDispField.setText("Net Displacement");
        netDispField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                netDispFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dispPanelLayout = new javax.swing.GroupLayout(dispPanel);
        dispPanel.setLayout(dispPanelLayout);
        dispPanelLayout.setHorizontalGroup(
            dispPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dispMenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dispPanelLayout.createSequentialGroup()
                .addGroup(dispPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(dispPanelLayout.createSequentialGroup()
                        .addComponent(iDispField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fDispField))
                    .addComponent(netDispField, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        dispPanelLayout.setVerticalGroup(
            dispPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dispPanelLayout.createSequentialGroup()
                .addComponent(dispMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(dispPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iDispField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fDispField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(netDispField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));
        jPanel9.setBorder(new javax.swing.border.MatteBorder(null));

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        clearSelectionButton.setText("Clear Selection");
        clearSelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearSelectionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearSelectionButton)
                .addGap(12, 12, 12)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearSelectionButton)))
        );

        table.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Initial Displacement (m)", "Final Displacement (m)", "Initial Velocity (m/s)", "Final Velocity (m/s)", "Acceleration (m/s^2)", "Time(s)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }

        });
        table.setRowHeight(28);
        jScrollPane1.setViewportView(table);

        showButton.setText("Show");
        showButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showButtonActionPerformed(evt);
            }
        });

        hideTable.setText("Hide");
        hideTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideTableActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        clearTableButton.setText("Clear");
        clearTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearTableButtonActionPerformed(evt);
            }
        });

        hideOnGraph.setText("Hide on Graph");
        hideOnGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideOnGraphActionPerformed(evt);
            }
        });

        showOnGraph.setText("Show");
        showOnGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showOnGraphActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftHalfPanelLayout = new javax.swing.GroupLayout(leftHalfPanel);
        leftHalfPanel.setLayout(leftHalfPanelLayout);
        leftHalfPanelLayout.setHorizontalGroup(
            leftHalfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftHalfPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(leftHalfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftHalfPanelLayout.createSequentialGroup()
                        .addGroup(leftHalfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leftHalfPanelLayout.createSequentialGroup()
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(showOnGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(hideOnGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(clearTableButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(showButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(hideTable, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leftHalfPanelLayout.createSequentialGroup()
                                .addComponent(dispPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(leftHalfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(leftHalfPanelLayout.createSequentialGroup()
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftHalfPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(6, 6, 6))
                    .addGroup(leftHalfPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(8, Short.MAX_VALUE))))
        );
        leftHalfPanelLayout.setVerticalGroup(
            leftHalfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftHalfPanelLayout.createSequentialGroup()
                .addGroup(leftHalfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftHalfPanelLayout.createSequentialGroup()
                        .addGroup(leftHalfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leftHalfPanelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(dispPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leftHalfPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(leftHalfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(leftHalfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftHalfPanelLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftHalfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(leftHalfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(showButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hideTable, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(clearTableButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(showOnGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hideOnGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel10.add(leftHalfPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 930, 530));

        velocityPanel.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout velocityPanelLayout = new javax.swing.GroupLayout(velocityPanel);
        velocityPanel.setLayout(velocityPanelLayout);
        velocityPanelLayout.setHorizontalGroup(
            velocityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        velocityPanelLayout.setVerticalGroup(
            velocityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 162, Short.MAX_VALUE)
        );

        accPanel.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout accPanelLayout = new javax.swing.GroupLayout(accPanel);
        accPanel.setLayout(accPanelLayout);
        accPanelLayout.setHorizontalGroup(
            accPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        accPanelLayout.setVerticalGroup(
            accPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        dispButtonsPanel.setBackground(new java.awt.Color(153, 153, 153));
        dispButtonsPanel.setBorder(new javax.swing.border.MatteBorder(null));

        exportDispGraphButton.setText("Export");
        exportDispGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportDispGraphButtonActionPerformed(evt);
            }
        });

        hideDispToggleButton.setText("Hide");
        hideDispToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideDispToggleButtonActionPerformed(evt);
            }
        });

        sExpand.setText("Expand");
        sExpand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sExpandActionPerformed(evt);
            }
        });

        drawToggle.setText("Draw");
        drawToggle.setToolTipText("Toggle to draw on graph");
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

        javax.swing.GroupLayout dispButtonsPanelLayout = new javax.swing.GroupLayout(dispButtonsPanel);
        dispButtonsPanel.setLayout(dispButtonsPanelLayout);
        dispButtonsPanelLayout.setHorizontalGroup(
            dispButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispButtonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(drawToggle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hideDispToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportDispGraphButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sExpand)
                .addContainerGap())
        );
        dispButtonsPanelLayout.setVerticalGroup(
            dispButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispButtonsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dispButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sExpand)
                    .addComponent(exportDispGraphButton)
                    .addComponent(hideDispToggleButton)
                    .addComponent(drawToggle)
                    .addComponent(clearButton))
                .addGap(34, 34, 34))
        );

        velocityButtons.setBackground(new java.awt.Color(153, 153, 153));
        velocityButtons.setBorder(new javax.swing.border.MatteBorder(null));

        exportVelocityButton.setText("Export");
        exportVelocityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportVelocityButtonActionPerformed(evt);
            }
        });

        hideVelocityToggleButton.setText("Hide");
        hideVelocityToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideVelocityToggleButtonActionPerformed(evt);
            }
        });

        velocityExpand.setText("Expand");
        velocityExpand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                velocityExpandActionPerformed(evt);
            }
        });

        drawVelocityToggle.setText("Draw");
        drawVelocityToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawVelocityToggleActionPerformed(evt);
            }
        });

        clearVelocityButton.setText("Clear");
        clearVelocityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearVelocityButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout velocityButtonsLayout = new javax.swing.GroupLayout(velocityButtons);
        velocityButtons.setLayout(velocityButtonsLayout);
        velocityButtonsLayout.setHorizontalGroup(
            velocityButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, velocityButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clearVelocityButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(drawVelocityToggle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hideVelocityToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportVelocityButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(velocityExpand)
                .addContainerGap())
        );
        velocityButtonsLayout.setVerticalGroup(
            velocityButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, velocityButtonsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(velocityButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(velocityExpand)
                    .addComponent(exportVelocityButton)
                    .addComponent(hideVelocityToggleButton)
                    .addComponent(drawVelocityToggle)
                    .addComponent(clearVelocityButton))
                .addGap(34, 34, 34))
        );

        accButtonsPanle.setBackground(new java.awt.Color(153, 153, 153));
        accButtonsPanle.setBorder(new javax.swing.border.MatteBorder(null));

        exportAccButton.setText("Export");
        exportAccButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportAccButtonActionPerformed(evt);
            }
        });

        hideAccToggleButton.setText("Hide");
        hideAccToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideAccToggleButtonActionPerformed(evt);
            }
        });

        accExpand.setText("Expand");
        accExpand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accExpandActionPerformed(evt);
            }
        });

        drawAccToggle.setText("Draw");
        drawAccToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawAccToggleActionPerformed(evt);
            }
        });

        clearAccButton.setText("Clear");
        clearAccButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAccButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout accButtonsPanleLayout = new javax.swing.GroupLayout(accButtonsPanle);
        accButtonsPanle.setLayout(accButtonsPanleLayout);
        accButtonsPanleLayout.setHorizontalGroup(
            accButtonsPanleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accButtonsPanleLayout.createSequentialGroup()
                .addComponent(clearAccButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(drawAccToggle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hideAccToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportAccButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accExpand)
                .addContainerGap())
        );
        accButtonsPanleLayout.setVerticalGroup(
            accButtonsPanleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accButtonsPanleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(accButtonsPanleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accExpand)
                    .addComponent(exportAccButton)
                    .addComponent(hideAccToggleButton)
                    .addComponent(drawAccToggle)
                    .addComponent(clearAccButton))
                .addGap(34, 34, 34))
        );

        displacementPanel1.setBackground(new java.awt.Color(51, 51, 255));
        displacementPanel1.setLayout(new java.awt.BorderLayout());

        dispGraphPanel.setBackground(new java.awt.Color(51, 51, 255));
        dispGraphPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        javax.swing.GroupLayout dispGraphPanelLayout = new javax.swing.GroupLayout(dispGraphPanel);
        dispGraphPanel.setLayout(dispGraphPanelLayout);
        dispGraphPanelLayout.setHorizontalGroup(
            dispGraphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dispGraphPanelLayout.setVerticalGroup(
            dispGraphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        titleLabel.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("Suvat Graph Application");

        errorText.setForeground(new java.awt.Color(255, 0, 0));
        errorText.setText("Error");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dispButtonsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(velocityPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(accPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dispGraphPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(velocityButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(accButtonsPanle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(1110, 1110, 1110)
                    .addComponent(displacementPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(420, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dispGraphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dispButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(velocityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(velocityButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(accPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(accButtonsPanle, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(errorText)
                        .addGap(16, 16, 16))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(419, 419, 419)
                    .addComponent(displacementPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(267, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
     
    }//GEN-LAST:event_formMouseClicked

    /**
     * When the save button is clicked, a JFilechooser will ask what the user wants to save the file as.
     * @param evt - button click
     */
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
       
       //Creates JFileChooser with txt filter 
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        JFileChooser jfc = new JFileChooser();
        
        jfc.setFileFilter(filter);
        int userSelection = jfc.showSaveDialog(jfc);
        
        //If press button is saved
        if(userSelection == JFileChooser.APPROVE_OPTION){
       
        //Get the file chosen
        File file = jfc.getSelectedFile();
        
        //Overwrite file
        logic.save(file.getAbsolutePath()+".txt");
        
        }
         
         
    }//GEN-LAST:event_saveButtonActionPerformed

    /**
     * When load button is clicked, a JFileChooser will ask what text file the user wants to open
     * @param evt - button click
     */
    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        //Limits the selectable files to only .txt files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        JFileChooser jfl = new JFileChooser();
        
        //passes filter to the JFileChooser
        jfl.setFileFilter(filter);
        jfl.showOpenDialog(null);
        
        //When the JFileChooser closes, selected file with be loaded to the appliaction
        logic.load(jfl.getSelectedFile().getAbsolutePath());
        updateAll(true);
    }//GEN-LAST:event_loadButtonActionPerformed

    /**
     * Opens a JDialog with the displacement graph
     * @param evt - button click
     */
    private void sExpandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sExpandActionPerformed
        expandGraph(this.dispGraph, this.drawToggle.isSelected());  
    }//GEN-LAST:event_sExpandActionPerformed

    /**
     * Implement changes to the leg of motion, and recalculate displacement for succeeding leg of motions.
     * @param evt - button click
     */
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        //Gets the selected checkbox and data field values
        boolean[] checkBoxData = {this.fvBox.isSelected(), this.accBox.isSelected(), this.timeBox.isSelected()};
        double[] fieldData = logic.textFieldList(this.fieldsStringData());
        
        if (logic.checkBoxCount(checkBoxData)) {
            // only run if 3 check boxes are ticked.
            logic.editLegOfMotion(tabelListener.getLeadSelectionIndex(), checkBoxData, fieldData, dispMenu.getSelectedIndex() == 1);
            
        }
        
        updateAll(true);
        
    }//GEN-LAST:event_editButtonActionPerformed

    /**
     * Creates a new leg of motion with data in text field
     * If table row and netDisp menu box is selected, users can add after selected leg of motion.
     * @param evt - button click
     */
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        //Gets the selected checkbox and data field values
        boolean[] checkBoxData = {this.fvBox.isSelected(), this.accBox.isSelected(), this.timeBox.isSelected()};
        double[] fieldData = logic.textFieldList(this.fieldsStringData());
        
        if (logic.checkBoxCount(checkBoxData) && tabelListener.isSelectionEmpty()) {
            //Function will only run if 3 check boxes are ticked.
            logic.addLegOfMotion(checkBoxData, fieldData, dispMenu.getSelectedIndex() == 1);
            this.updateTable();
            this.setNewIDV();
            this.resetCheckBoxes();
            this.clearFields();
            updateAll(true);   
        } else if (logic.checkBoxCount(checkBoxData) && (tabelListener.isSelectionEmpty() == false) && dispMenu.getSelectedIndex() == 1) {
            //Add a leg of motion after selected index
           
            logic.addLegOfMotionNext(checkBoxData, fieldData, tabelListener.getLeadSelectionIndex());
            this.updateTable();
            this.setNewIDV();
            this.resetCheckBoxes();
            this.clearFields();
            updateAll(true);   
            
        } 
    }//GEN-LAST:event_addButtonActionPerformed

    /**
     * Deletes selected leg of motion and reorders them to avoid abrupt changes in displacement
     * @param evt - button click
     */
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        //Deletes selected table row
        logic.deleteLegOfMotion(tabelListener.getLeadSelectionIndex());
        this.addButton.setEnabled(true);
        updateAll(true);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void netDispFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_netDispFieldActionPerformed

    }//GEN-LAST:event_netDispFieldActionPerformed

    private void iDispFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iDispFieldActionPerformed

    }//GEN-LAST:event_iDispFieldActionPerformed

    private void dispMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dispMenuActionPerformed

    }//GEN-LAST:event_dispMenuActionPerformed

    /**
     * Removes all drawings from the displacement graph
     * @param evt - button click
     */
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
       //Remove all drawings
        dispGraph.removeAnnotations();
        oldDispAnnotations= new LinkedList<>();    
        oldVelocityAnnotations = new LinkedList<>();
        oldAccAnnotations = new LinkedList<>();   
    
    }//GEN-LAST:event_clearButtonActionPerformed

    /**
     * Saves displacement graph as a png image
     * @param evt - button click
     */
    private void exportDispGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportDispGraphButtonActionPerformed
        //Save disp graph as image
        dispGraph.exportAsPNG("dispGraph.png");
    }//GEN-LAST:event_exportDispGraphButtonActionPerformed

    /**
     * Hides the displacement graph when button is clicked
     * @param evt - button click
     */
    private void hideDispToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideDispToggleButtonActionPerformed
        //Enables and disables drawing tool
        if (hideDispToggleButton.isSelected()) {
            dispGraph.setLineVisible(false);      
        } else {
            dispGraph.setLineVisible(true);
        }
    }//GEN-LAST:event_hideDispToggleButtonActionPerformed

    /**
     * Exports velocity graph as a png image.
     * @param evt - button click
     */
    private void exportVelocityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportVelocityButtonActionPerformed
        velocityGraph.exportAsPNG("velocityGraph.png");
    }//GEN-LAST:event_exportVelocityButtonActionPerformed

    /**
     * Hides the velocity graph when button is clicked
     * @param evt - button click
     */
    private void hideVelocityToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideVelocityToggleButtonActionPerformed
        velocityGraph.setLineVisible(!hideVelocityToggleButton.isSelected());
        
    }//GEN-LAST:event_hideVelocityToggleButtonActionPerformed

    /**
     * Opens velocity graph in a new JDialog
     * @param evt - button click
     */
    private void velocityExpandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_velocityExpandActionPerformed
       //Opens JDialogue
        this.expandGraph(velocityGraph, this.drawVelocityToggle.isSelected());
    }//GEN-LAST:event_velocityExpandActionPerformed

    /**
     * Exports acceleration graph as a png image
     * @param evt - button click
     */
    private void exportAccButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportAccButtonActionPerformed
        accGraph.exportAsPNG("accGraph.png");
    }//GEN-LAST:event_exportAccButtonActionPerformed

    /**
     * Hides the acceleration graph when button is clicked
     * @param evt - button click
     */
    private void hideAccToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideAccToggleButtonActionPerformed
        if (hideAccToggleButton.isSelected()) {
            accGraph.setLineVisible(false);
        } else {
            accGraph.setLineVisible(true);
        }
    }//GEN-LAST:event_hideAccToggleButtonActionPerformed

    /**
     * Opens acceleration graph in a new JDialog
     * @param evt - button click
     */
    private void accExpandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accExpandActionPerformed
        this.expandGraph(accGraph, this.drawAccToggle.isSelected());
    }//GEN-LAST:event_accExpandActionPerformed

    /**
     * Unhides all specific leg of motions
     * @param evt - button click
     */
    private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showButtonActionPerformed
        logic.setDomainHideIndex(new LinkedList<>());
         table.updateUI();
        
    }//GEN-LAST:event_showButtonActionPerformed

    /**
     * Hides selected table row when clicked
     * @param evt - button click
     */
    private void hideTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideTableActionPerformed
       
       logic.getDomainHideIndex().add(tabelListener.getLeadSelectionIndex());
       table.updateUI();
       
   
    }//GEN-LAST:event_hideTableActionPerformed

    /**
     * Enables drawing function for displacement graph
     * @param evt - button click
     */
    private void drawToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawToggleActionPerformed
        if (drawToggle.isSelected()) {            
            dispGraph.setDrawingTool();
            
        } else {
            dispGraph.disableMouse();
        }
        
        
    }//GEN-LAST:event_drawToggleActionPerformed

    /**
     * Enables drawing function for velocity graph
     * @param evt - button click
     */
    private void drawVelocityToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawVelocityToggleActionPerformed
        //Disables drawing tool if button is not selected
        if (drawVelocityToggle.isSelected()) {            
            velocityGraph.setDrawingTool();
            
        } else {
            velocityGraph.disableMouse();
        }
    }//GEN-LAST:event_drawVelocityToggleActionPerformed

    /**
     * Enables drawing function for acceleration graph
     * @param evt - button click
     */
    private void drawAccToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawAccToggleActionPerformed
         //Disables drawing tool if button is not selected
        if (drawAccToggle.isSelected()) {            
            accGraph.setDrawingTool();
        } else {
            accGraph.disableMouse();
        }
    }//GEN-LAST:event_drawAccToggleActionPerformed

    /**
     * Removes all drawings from velocity graph
     * @param evt - button click
     */
    private void clearVelocityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearVelocityButtonActionPerformed
        velocityGraph.removeAnnotations();
        oldDispAnnotations= new LinkedList<>();    
        oldVelocityAnnotations = new LinkedList<>();
        oldAccAnnotations = new LinkedList<>();  
    }//GEN-LAST:event_clearVelocityButtonActionPerformed

    /**
     * Removes all drawings from acceleration graph
     * @param evt - button click
     */
    private void clearAccButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAccButtonActionPerformed
        accGraph.removeAnnotations();
        oldDispAnnotations= new LinkedList<>();    
        oldVelocityAnnotations = new LinkedList<>();
        oldAccAnnotations = new LinkedList<>();  
    }//GEN-LAST:event_clearAccButtonActionPerformed

    /**
     * Removes all leg of motions
     * @param evt - button click
     */
    private void clearTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearTableButtonActionPerformed
       logic.clearList();
       updateAll(true);
       
       
    }//GEN-LAST:event_clearTableButtonActionPerformed

    private void fVFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fVFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fVFieldActionPerformed

    /**
     * Unselects Jtable
     * @param evt - button click
     */
    private void clearSelectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearSelectionButtonActionPerformed

            table.clearSelection();
            this.setNewIDV();
            this.addButton.setEnabled(true);
   
    }//GEN-LAST:event_clearSelectionButtonActionPerformed

    /**
     * Selected leg of motion is hidden on graph
     * @param evt - button click
     */
    private void hideOnGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideOnGraphActionPerformed
                
                logic.addDomainHides(this.tabelListener.getMinSelectionIndex(), this.tabelListener.getMaxSelectionIndex());
                double[]test={0,0};
                //Sets row to be hidden
                velocityGraph.createChartHighLight(test, logic.getDomainHides());
                dispGraph.createChartHighLight(test, logic.getDomainHides());                
                accGraph.createChartHighLight(test, logic.getDomainHides());
    }//GEN-LAST:event_hideOnGraphActionPerformed

    /**
     * shows all leg of motions
     * @param evt - button click
     */
    private void showOnGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showOnGraphActionPerformed
                 //logic.removeDomainHide(this.tabelListener.getMinSelectionIndex(), this.tabelListener.getMaxSelectionIndex());
                 logic.removeAllDomainHides(true);
                 double[]test={0,0};
                 velocityGraph.createChartHighLight(test, logic.getDomainHides());
                 dispGraph.createChartHighLight(test, logic.getDomainHides());                
                 accGraph.createChartHighLight(test, logic.getDomainHides());
    }//GEN-LAST:event_showOnGraphActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox accBox;
    private javax.swing.JPanel accButtonsPanle;
    private javax.swing.JButton accExpand;
    private javax.swing.JTextField accField;
    private javax.swing.JPanel accPanel;
    private javax.swing.JButton addButton;
    private javax.swing.ButtonGroup atGroup;
    private javax.swing.JButton clearAccButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton clearSelectionButton;
    private javax.swing.JButton clearTableButton;
    private javax.swing.JButton clearVelocityButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel dispButtonsPanel;
    private javax.swing.JPanel dispGraphPanel;
    private javax.swing.JComboBox dispMenu;
    private javax.swing.JPanel dispPanel;
    private javax.swing.JPanel displacementPanel1;
    private javax.swing.JToggleButton drawAccToggle;
    private javax.swing.JToggleButton drawToggle;
    private javax.swing.JToggleButton drawVelocityToggle;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel errorText;
    private javax.swing.JButton exportAccButton;
    private javax.swing.JButton exportDispGraphButton;
    private javax.swing.JButton exportVelocityButton;
    private javax.swing.JTextField fDispField;
    private javax.swing.JTextField fVField;
    private javax.swing.JCheckBox fvBox;
    private javax.swing.JToggleButton hideAccToggleButton;
    private javax.swing.JToggleButton hideDispToggleButton;
    private javax.swing.JButton hideOnGraph;
    private javax.swing.JButton hideTable;
    private javax.swing.JToggleButton hideVelocityToggleButton;
    private javax.swing.JTextField iDispField;
    private javax.swing.JTextField iVField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel leftHalfPanel;
    private javax.swing.JButton loadButton;
    private javax.swing.JTextField netDispField;
    private javax.swing.JButton sExpand;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton showButton;
    private javax.swing.JButton showOnGraph;
    private javax.swing.JTable table;
    private javax.swing.JCheckBox timeBox;
    private javax.swing.JTextField timeField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel velocityButtons;
    private javax.swing.JButton velocityExpand;
    private javax.swing.JPanel velocityPanel;
    // End of variables declaration//GEN-END:variables

    /**
     *Prevents the cells of the table to be edited
     * @return if cell is editable
     */
    public boolean isCellEditable() {
        return false;
    }
    
   
    /**
     * Instantiates the ExpandedGraphGUI JDialog. Any edits made to the graph (i.e drawings) will be carried over
     * @param g - graph
     * @param isDrawEnabled - boolean if draw is enabled
     */
    void expandGraph(Graph g, boolean isDrawEnabled) {
        ExpandedGraphGUI eGUI = new ExpandedGraphGUI(this, true, g, isDrawEnabled);
        eGUI.setVisible(true);
        
        //Saves annotations made on the graph when GUI is closed
        if (eGUI.isIsClosed()) {
            
            
           
            
            updateAll(false);
                double[] domain = {0,0};
               velocityGraph.createChartHighLight(domain, logic.getDomainHides());
               dispGraph.createChartHighLight(domain, logic.getDomainHides());                
               accGraph.createChartHighLight(domain, logic.getDomainHides());
            
        }
        
        eGUI.dispose();
        
    }

    /**
     * Updates the table with new leg of motion list
     */
    void updateTable() {
        //Converts into table data structure
        Object[][] data = logic.tableFormat();
        
        //Deletes all the rows
        int rowCount = tableModel.getRowCount();
        for (int x = rowCount - 1; x >= 0; x--) {
            tableModel.removeRow(x);
            
        }
        
        //Adds all leg of motion again
        for (int x = 0; x < logic.getLegOfMotionList().size(); x++) {
            tableModel.addRow(data[x]);
        }        
    }
    
    /**
     * Returns the list of all the text field values
     * @return - list of all text field values
     */
    String[] fieldsStringData() {
        String[] stringList = {this.iDispField.getText(), this.fDispField.getText(), this.getNetDispField().getText(), this.iVField.getText(), this.fVField.getText(), this.accField.getText(), this.timeField.getText()};
        return stringList;
    }
    
    /**
     * Updates all JComponents in the JFrame
     * @param hide - maintains if graph is hidden or not
     */
    void updateAll(boolean hide) {
        //Stores old annotations to be carriedd over
        this.oldDispAnnotations = dispGraph.getAnnotations();
        this.oldVelocityAnnotations = velocityGraph.getAnnotations();
        this.oldAccAnnotations = accGraph.getAnnotations();
         
        //Draw fucntion turned false
        this.drawAccToggle.setSelected(false);
        this.drawToggle.setSelected(false);
        this.drawVelocityToggle.setSelected(false);
        
        this.updateTable();
        this.resetCheckBoxes();
        this.clearFields();
        
        //Creates new instances of each graph with respective data
        this.dispGraph = new Graph("Time(s)", "Displacement(m)", logic.getDispData(), this.oldDispAnnotations);
        this.velocityGraph = new Graph("Time(s)", "Velocity(ms⁻¹)", logic.getVelocityData(), this.oldVelocityAnnotations);
        this.accGraph = new Graph("Time(s)", "Acceleration(ms⁻²)", logic.getAccData(), this.oldAccAnnotations);
        
        /*
        Everything below adds the graph to their respective panels.
        All annotations are re-added as well
        */
        
        dispGraphPanel.removeAll();
        dispGraphPanel.setLayout(new BorderLayout());
        dispGraphPanel.add(dispGraph.getChartPanel(), BorderLayout.CENTER);
        dispGraph.getChartPanel().setSize(dispGraphPanel.getSize());
        dispGraph.updateAnnotaitons(oldDispAnnotations);
        
        velocityPanel.removeAll();
        velocityPanel.setLayout(new BorderLayout());
        velocityPanel.add(velocityGraph.getChartPanel(), BorderLayout.CENTER);
        velocityGraph.getChartPanel().setSize(velocityPanel.getSize());
        velocityGraph.updateAnnotaitons(oldVelocityAnnotations);
        
        accPanel.removeAll();
        accPanel.setLayout(new BorderLayout());
        accPanel.add(accGraph.getChartPanel(), BorderLayout.CENTER);
        accGraph.getChartPanel().setSize(accPanel.getSize());
        accGraph.updateAnnotaitons(oldAccAnnotations);
        
        //Any hidden leg of motions will re appear
       
        logic.removeAllDomainHides(hide);
        
        if(logic.getLegOfMotionList().isEmpty()){
        this.iDispField.setText("");
        this.iDispField.setEnabled(true);
        this.addButton.setEnabled(true);
        }
    }
    
    
    
    


    /**
     * Resets all the check boxes to unselected.
     */
    void resetCheckBoxes() {
        //Resets all check boxes to unchecked
        accBox.setSelected(false);
        
        fvBox.setSelected(false);
        
        timeBox.setSelected(false);
    }

/**
 * Manually forces user to use initial displacement as the final velocity and displacement of the old leg of motion
 */
    void setNewIDV() {
        int prev = tableModel.getRowCount() - 1;
        this.iDispField.setText(Double.toString(logic.getLegOfMotion(prev).getFdisplacement()));
        this.iDispField.setEnabled(false);
        this.addButton.setText("Add");
        clearFields();
        
    }

/**
 * Clears all the input fields apart from initial displacement
 */
    void clearFields() {
        
        fDispField.setText("");
        fVField.setText("");
        iVField.setText("");
        accField.setText("");
        timeField.setText("");
        getNetDispField().setText("");
        
    }

/**
 * Fill all the fields with the leg of motion of index in list.
 * Used for when leg of motion is to be edited or deleted
 * @param index - index of leg of motion to be edited/deleted
 */
    void fillFields(int index) {
        //Fills the fields with the values from the last added leg of instruction
     //   DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.CEILING);
        
        
        getNetDispField().setText(df.format(logic.getLegOfMotion(index).getTotaldisplacement()));
        iDispField.setText(df.format(logic.getLegOfMotion(index).getIdisplacement()));
        fDispField.setText(df.format(logic.getLegOfMotion(index).getFdisplacement()));
        iVField.setText(df.format(logic.getLegOfMotion(index).getIvelocity()));
        fVField.setText(df.format(logic.getLegOfMotion(index).getFvelocity()));
        accField.setText(df.format(logic.getLegOfMotion(index).getAcceleration()));
        timeField.setText(df.format(logic.getLegOfMotion(index).getTime()));
        
    }

//Encapsulation Methods Below:    
    /**
     * @return the aField
     */
    public javax.swing.JTextField getaField() {
        return accField;
    }

    /**
     * @param aField the aField to set
     *
     */
    public void setaField(javax.swing.JTextField aField) {
        this.accField = aField;
    }

    /**
     * @return the accelerationBox
     */
    public javax.swing.JCheckBox getAccelerationBox() {
        return accBox;
    }

    /**
     * @param accelerationBox the accelerationBox to set
     */
    public void setAccelerationBox(javax.swing.JCheckBox accelerationBox) {
        this.accBox = accelerationBox;
    }

    /**
     * @return the addButton
     */
    public javax.swing.JButton getAddButton() {
        return addButton;
    }

    /**
     * @param addButton the addButton to set
     */
    public void setAddButton(javax.swing.JButton addButton) {
        this.addButton = addButton;
    }

    /**
     * @return the atGroup
     */
    public javax.swing.ButtonGroup getAtGroup() {
        return atGroup;
    }

    /**
     * @param atGroup the atGroup to set
     */
    public void setAtGroup(javax.swing.ButtonGroup atGroup) {
        this.atGroup = atGroup;
    }

    /**
     * @return the deleteButton
     */
    public javax.swing.JButton getDeleteButton() {
        return deleteButton;
    }

    /**
     * @param deleteButton the deleteButton to set
     */
    public void setDeleteButton(javax.swing.JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    /**
     * @return the directionMenu
     */
    /**
     * @return the editButton
     */
    public javax.swing.JButton getEditButton() {
        return editButton;
    }

    /**
     * @param editButton the editButton to set
     */
    public void setEditButton(javax.swing.JButton editButton) {
        this.editButton = editButton;
    }

    /**
     * @return the fVField
     */
    public javax.swing.JTextField getfVField() {
        return fVField;
    }

    /**
     * @param fVField the fVField to set
     */
    public void setfVField(javax.swing.JTextField fVField) {
        this.fVField = fVField;
    }

    /**
     * @return the fvBox
     */
    public javax.swing.JCheckBox getFvBox() {
        return fvBox;
    }

    /**
     * @param fvBox the fvBox to set
     */
    public void setFvBox(javax.swing.JCheckBox fvBox) {
        this.fvBox = fvBox;
    }

    /**
     * @return the iVField
     */
    public javax.swing.JTextField getiVField() {
        return iVField;
    }

    /**
     * @param iVField the iVField to set
     */
    public void setiVField(javax.swing.JTextField iVField) {
        this.iVField = iVField;
    }

    /**
     * @return the ivBox
     */
    /**
     * @return the jPanel1
     */
    /**
     * @return the jScrollPane1
     */
    public javax.swing.JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    /**
     * @param jScrollPane1 the jScrollPane1 to set
     */
    public void setjScrollPane1(javax.swing.JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    /**
     * @return the jTextField1
     */
    public javax.swing.JTextField getjTextField1() {
        return fDispField;
    }

    /**
     * @param jTextField1 the jTextField1 to set
     */
    public void setjTextField1(javax.swing.JTextField jTextField1) {
        this.fDispField = jTextField1;
    }

    /**
     * @return the table
     */
    public javax.swing.JTable getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(javax.swing.JTable table) {
        this.table = table;
    }

    /**
     * @return the timeBox
     */
    public javax.swing.JCheckBox getTimeBox() {
        return timeBox;
    }

    /**
     * @param timeBox the timeBox to set
     */
    public void setTimeBox(javax.swing.JCheckBox timeBox) {
        this.timeBox = timeBox;
    }

    /**
     * @return the timeField
     */
    public javax.swing.JTextField getTimeField() {
        return timeField;
    }

    /**
     * @param timeField the timeField to set
     */
    public void setTimeField(javax.swing.JTextField timeField) {
        this.timeField = timeField;
    }

    /**
     * @return the iDispField
     */
    public javax.swing.JTextField getiDispField() {
        return iDispField;
    }

    /**
     * @param iDispField the iDispField to set
     */
    public void setiDispField(javax.swing.JTextField iDispField) {
        this.iDispField = iDispField;
    }
    
        /**
     * @return the netDispField
     */
    public javax.swing.JTextField getNetDispField() {
        return netDispField;
    }

    /**
     * @param netDispField the netDispField to set
     */
    public void setNetDispField(javax.swing.JTextField netDispField) {
        this.netDispField = netDispField;
    }
    
    
}
