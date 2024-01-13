package com.example.cm1601_cw_2236750;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DriverTableDisplay {
    public String fileName;
    DriverTableDisplay(String fileName){
        this.fileName = fileName;
    }

    public String driverSort(){
        String ndn = "";
        List<Integer> orderedPoints = new ArrayList<Integer>();
        List<String> orderedPointsDriver = new ArrayList<String>();
        try {
            BufferedReader readFile = new BufferedReader(new FileReader("Racing.txt"));
            String line;

            while((line = readFile.readLine()) != null) {

                String currentPoints = line.substring(64,79);
                String driverName = line.substring(0,20);
                orderedPoints.add(Integer.parseInt(currentPoints.trim()));
                orderedPointsDriver.add(driverName);

            }
            sortPoints(orderedPoints,orderedPointsDriver);
            readFile.close();
            int standing = 0;
            for (int x =0;x < orderedPoints.size(); x++){
                BufferedReader readFileFind = new BufferedReader(new FileReader("Racing.txt"));
                String lineNum;
                while((lineNum = readFileFind.readLine()) != null) {
                    String name = lineNum.substring(0,20);
                    String age = lineNum.substring(20,24);
                    String teamName = lineNum.substring(24,44);
                    String carType = lineNum.substring(44,64);
                    String currentPoints = lineNum.substring(64,79);
                    int currentPoint = orderedPoints.get(x);
                    String currentDriver = orderedPointsDriver.get(x);
                    if ((String.valueOf(currentPoint)).equals(currentPoints.trim()) && (currentDriver.equals(name))){
                        standing++;
                        ndn = ndn + "\n" + name.trim() + " | " + age.trim() + " | " + teamName.trim() + " | "
                                + carType.trim() + " | " + currentPoints.trim() + " | " + standing +"\n";
                    }
                }
            }
            readFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ndn;
    }

    public void sortPoints(List <Integer> orderedPoints, List <String> orderedPointsDriver){
            for (int i =0; i < orderedPoints.size();i++){
                for (int z = i +1; z < orderedPoints.size();z++){
                    int tempI = orderedPoints.get(i);
                    int tempZ = orderedPoints.get(z);

                    if (tempI  < tempZ){

                        int tmp = orderedPoints.get(i);
                        String temp = orderedPointsDriver.get(i);
                        orderedPoints.set(i,orderedPoints.get(z));
                        orderedPoints.set(z,tmp);
                        orderedPointsDriver.set(i, orderedPointsDriver.get(z));
                        orderedPointsDriver.set(z,temp);
                    }
                }
            }
    }
}
