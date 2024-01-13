package com.example.cm1601_cw_2236750;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Races {

    public List<String> getDriver(){
        List<String> driverList = new ArrayList<String>();
        try {
            BufferedReader driverInfo = new BufferedReader(new FileReader("Racing.txt"));
            String line;
            while((line = driverInfo.readLine()) != null) {
                driverList.add(line.substring(0,20));
            }
            driverInfo.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return driverList;
    }

    public List<String> driverInfoRace(List <String> drivers){
        List<String> pointsList = new ArrayList<String>();
        for (int position = 0; position < drivers.size(); position++){
            try {
                BufferedReader driverInfo = new BufferedReader(new FileReader("Racing.txt"));
                String line;
                while((line = driverInfo.readLine()) != null) {
                    if (drivers.get(position).equals(line.substring(0,20))){
                        pointsList.add(line.substring(64,79));
                    }
                }
                driverInfo.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return pointsList;
    }

    public void simulateRace(){
        String [] locations = {"Nyirad","Holjes","Montalegre","Barcelona","Riga","Norway"};
        Random raceInfo = new Random();
        int date = raceInfo.nextInt(1,28);
        int month = raceInfo.nextInt(1,12);
        int year = raceInfo.nextInt(2023,2024);
        int locationCode = raceInfo.nextInt(0, locations.length);
        LocalDate dateRace = LocalDate.of(year,month,date);
        System.out.println(dateRace+ "  "+locations[locationCode]+"\n");
        List <String> drivers = getDriver();
        System.out.println(drivers);
        Collections.shuffle(drivers);
        System.out.println(drivers);
        List <String> driverPoints = driverInfoRace(drivers);
        System.out.println(driverPoints);

        String position1points = driverPoints.get(0);
        String position2points = driverPoints.get(1);
        String position3points = driverPoints.get(2);
        int position1 = Integer.parseInt(position1points.trim()) + 10;
        int position2 = Integer.parseInt(position2points.trim()) + 7;
        int position3 = Integer.parseInt(position3points.trim()) + 5;
        driverPoints.set(0, String.valueOf(position1));
        driverPoints.set(1, String.valueOf(position2));
        driverPoints.set(2, String.valueOf(position3));

        System.out.println(driverPoints);
        for (int position = 0; position < 3;position++){
            Driver updateDriverPoints = new Driver("Racing.txt",drivers.get(position));
            updateDriverPoints.updateDriverData("driverCurrentPointsUpdate",driverPoints.get(position),drivers.get(position));
        }

        String writeToFile = "Date" + "              " + "Location"+"\n";
        writeToFile += dateRace + "        " + locations[locationCode]+"\n\n";
        writeToFile += "Driver Name" + "     " + "Current Points"+ "        " + "Position" + "\n\n";
        int pos = 1;
        for (int positionCount = 0; positionCount < drivers.size();positionCount++){
            String currentPoints = String.format("%-15s",driverPoints.get(positionCount));
            writeToFile += drivers.get(positionCount) + currentPoints + "      " +pos+"\n";
            pos++;
        }
        CreateFile racesSummary = new CreateFile("RaceSummary",writeToFile);
        racesSummary.createAndWrite();

    }

}
