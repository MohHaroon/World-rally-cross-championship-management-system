package com.example.cm1601_cw_2236750;
import java.io.*;

public class CreateFile {

    public String fileName;
    public String driverData;

    CreateFile(String fileName,String driverData){
        this.fileName = fileName;
        this.driverData = driverData;
    }
    public void createAndWrite(){
        try {
            FileWriter writer = new FileWriter(fileName,true);
            PrintWriter writeFile = new PrintWriter(writer);
            writeFile.println(driverData);
            writeFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
