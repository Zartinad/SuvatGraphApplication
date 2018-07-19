/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suvatgraphapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;


/**
 * FileHandler to read from and write to disk.
 * @author Zartin
 */
public class FileHandler {


    BufferedReader buf;
    PrintWriter writer;
    
    //Final is used to name a constant variable
    private String FILE_PATH = "files/file.txt";

   /**
    * 
    * @param filePath - address of file
    * @return Leg of motion list from disk
    */
    LinkedList<LegOfMotion> read(String filePath) {

        LinkedList<LegOfMotion> list = new  LinkedList<>();
        String[] line;
        String nextline;
        
        //Try catch method
        try {

            buf = new BufferedReader(new FileReader(filePath));
            nextline = buf.readLine();

            while (nextline != null) {
                //Uses CSV to identify different information
                line = nextline.split(",");
                
                //Converts string to double
                double id = Double.parseDouble(line[0]);
                double fd = Double.parseDouble(line[1]);
                double nd = Double.parseDouble(line[2]);
                double iv = Double.parseDouble(line[3]);
                double fv = Double.parseDouble(line[4]);
                double acc = Double.parseDouble(line[5]);
                double time = Double.parseDouble(line[6]);
                
                //Creates information with values
                LegOfMotion leg = new LegOfMotion(id,fd,nd,iv,fv,acc,time);
                list.add(leg);
                
                //Reads next line
                nextline = buf.readLine();
            }
            buf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Writes list into hard disk.
     * @param list - list to be written to file
     */
    void write(LinkedList<LegOfMotion> list) {
        try {
            writer = new PrintWriter(new FileWriter(FILE_PATH));            
            for (int x =0; x < list.size();x++){
                //Writes LegOfMotion into list method
                writer.println(list.get(x).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.close();

    }

    /**
     * @return the FILE_PATH
     */
    public String getFILE_PATH() {
        return FILE_PATH;
    }

    /**
     * @param FILE_PATH the FILE_PATH to set
     */
    public void setFILE_PATH(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }

}

