package com.example.cm1601_cw_2236750;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController{

    @FXML
    private TextField addDriverName;
    @FXML
    private TextField addDriverAge;
    @FXML
    private TextField addDriverCarType;
    @FXML
    private TextField addDriverTeamName;
    @FXML
    private TextField addDriverCurrentPoints;
    @FXML
    private Label prompt;
    @FXML
    private TextField newData;
    @FXML
    private TextField driverToUpdate;
    @FXML
    private Label promptUpdate;
    @FXML
    private Label promptDelete;
    @FXML
    private TextField driverToDelete;
    @FXML
    private TextArea position;
    @FXML
    private Button standingDisplay;
    @FXML
    private Label racesPrompt;


    private Stage stage;
    private Scene scene;
    private Parent root;


    
    public void mainScene(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("racing.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addDriver(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("addDriver.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void addDriverData(){
        prompt.setText("                        ");
        String driverName = "";
        String driverAge = "" ;
        String driverTeamName = "";
        String driverCarType = "";
        String driverCurrentPoints = "";

        try{
            if (addDriverName.getText().isEmpty() || addDriverAge.getText().isEmpty()
                    || addDriverTeamName.getText().isEmpty() || addDriverCarType.getText().isEmpty()
                    || addDriverCurrentPoints.getText().isEmpty()){
                throw new Exception();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Data Entry");
            alert.setContentText("Make sure all fields have been filled!");
            alert.showAndWait();
        }

        try{
            driverName = String.format("%-20s",addDriverName.getText());
            if (driverName.length() >20){
                throw new  Exception();}
            Driver driver = new Driver("Racing.txt",driverName);
            if(driver.driverExists()){
                throw new NullPointerException();
            }

        } catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Driver already exists!");
            alert.showAndWait();
            addDriverName.clear();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Make sure the driver's name length is within 20 letters!");
            alert.showAndWait();
            addDriverName.clear();

        }
        try{
            driverAge = String.format("%-4s", Integer.parseInt(addDriverAge.getText()));
            if (driverAge.length() >4){
                throw new  Exception();
            }
            if ((Integer.parseInt(driverAge.trim()) < 18) || (Integer.parseInt(driverAge.trim()) > 100)){
                throw new  Exception();
            }

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver age");
            alert.setContentText("Make sure the age is a number, and greater than 18!");

            alert.showAndWait();
            addDriverAge.clear();

        }
        try{
            driverTeamName = String.format("%-20s",addDriverTeamName.getText());
            if (driverTeamName.length() >20){
                throw new  Exception();}

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver's team name");
            alert.setContentText("Make sure the team name length is within 20 letters!");
            alert.showAndWait();
            addDriverTeamName.clear();

        }
        try{
            driverCarType = String.format("%-20s",addDriverCarType.getText());
            if (driverCarType.length() >20){
                throw new  Exception();}

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver's Car type");
            alert.setContentText("Make sure the car type length is within 20 letters!");
            alert.showAndWait();
            addDriverCarType.clear();

        }
        try{
            driverCurrentPoints = String.format("%-15s",Integer.parseInt(addDriverCurrentPoints.getText()));
            if (driverCurrentPoints.length() >15){
                throw new  Exception();}

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver's current points");
            alert.setContentText("Make sure entered current points is a number!");
            alert.showAndWait();
            addDriverCurrentPoints.clear();

        }


        if (!addDriverName.getText().isEmpty() && !addDriverAge.getText().isEmpty()
                && !addDriverTeamName.getText().isEmpty() && !addDriverCarType.getText().isEmpty()
                && !addDriverCurrentPoints.getText().isEmpty()){
            String driverData = driverName + driverAge + driverTeamName + driverCarType + driverCurrentPoints;
            CreateFile Racing = new CreateFile("Racing.txt",driverData);
            Racing.createAndWrite();
            prompt.setText("Driver added Successfully!");
            addDriverName.clear();
            addDriverAge.clear();
            addDriverTeamName.clear();
            addDriverCarType.clear();
            addDriverCurrentPoints.clear();

        }}



    public void updateDriver(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("updateDriver.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateDriverName(){
        promptUpdate.setText("                        ");
        String driverNameUpdate = "";
        String newDriverName = "";

        try{
            if (newData.getText().isEmpty() || driverToUpdate.getText().isEmpty()){
                throw new Exception();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Data Entry");
            alert.setContentText("Make sure all fields have been filled!");
            alert.showAndWait();
        }
        try{
            driverNameUpdate = String.format("%-20s",driverToUpdate.getText());
            if (driverNameUpdate.length() >20){
                throw new  Exception();}
            Driver driverUpdate = new Driver("Racing.txt",driverNameUpdate);
            if(!driverUpdate.driverExists()){
                throw new NullPointerException();
            }

        } catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Driver doesn't exists!");
            alert.showAndWait();
            driverToUpdate.clear();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Make sure the driver's name length is within 20 letters!");
            alert.showAndWait();
            driverToUpdate.clear();
        }

        try{
            newDriverName = String.format("%-20s",newData.getText());
            if (newDriverName.length() >20){
                throw new  Exception();}
            Driver driverUpdate = new Driver("Racing.txt",newDriverName);
            if(driverUpdate.driverExists()){
                throw new NullPointerException();
            }

        } catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Driver already exists!");
            alert.showAndWait();
            newData.clear();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Make sure the driver's name length is within 20 letters!");
            alert.showAndWait();
            newData.clear();
        }


        if (!newData.getText().isEmpty() && !driverToUpdate.getText().isEmpty()){
            Driver driverUpdate = new Driver("Racing.txt",newDriverName);
            driverUpdate.updateDriverData("driverNameUpdate",newDriverName,driverNameUpdate);
            promptUpdate.setText("Driver updated Successfully!");
            newData.clear();
            driverToUpdate.clear();
        }

    }

    public void updateDriverAge(){
        promptUpdate.setText("                        ");
        String driverNameUpdate = "";
        String newDriverAge = "";

        try{
            if (newData.getText().isEmpty() || driverToUpdate.getText().isEmpty()){
                throw new Exception();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Data Entry");
            alert.setContentText("Make sure all fields have been filled!");
            alert.showAndWait();
        }
        try{
            driverNameUpdate = String.format("%-20s",driverToUpdate.getText());
            if (driverNameUpdate.length() >20){
                throw new  Exception();}
            Driver driverUpdate = new Driver("Racing.txt",driverNameUpdate);
            if(!driverUpdate.driverExists()){
                throw new NullPointerException();
            }

        } catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Driver doesn't exists!");
            alert.showAndWait();
            driverToUpdate.clear();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Make sure the driver's name length is within 20 letters!");
            alert.showAndWait();
            driverToUpdate.clear();
        }
        try{
            newDriverAge = String.format("%-4s", Integer.parseInt(newData.getText()));
            if (newDriverAge.length() >4){
                throw new  Exception();
            }
            if ((Integer.parseInt(newDriverAge.trim()) < 18) || (Integer.parseInt(newDriverAge.trim()) > 100)){
                throw new  Exception();
            }

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver age");
            alert.setContentText("Make sure the age is a number, and greater than 18!");

            alert.showAndWait();
            newData.clear();

        }
        if (!newData.getText().isEmpty() && !driverToUpdate.getText().isEmpty()){
            Driver driverUpdate = new Driver("Racing.txt",newDriverAge);
            driverUpdate.updateDriverData("driverAgeUpdate",newDriverAge,driverNameUpdate);
            promptUpdate.setText("Driver updated Successfully!");
            newData.clear();
            driverToUpdate.clear();
        }


    }
    public void updateTeamName(){
        promptUpdate.setText("                        ");
        String driverNameUpdate = "";
        String newDriverTeamName = "";

        try{
            if (newData.getText().isEmpty() || driverToUpdate.getText().isEmpty()){
                throw new Exception();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Data Entry");
            alert.setContentText("Make sure all fields have been filled!");
            alert.showAndWait();
        }
        try{
            driverNameUpdate = String.format("%-20s",driverToUpdate.getText());
            if (driverNameUpdate.length() >20){
                throw new  Exception();}
            Driver driverUpdate = new Driver("Racing.txt",driverNameUpdate);
            if(!driverUpdate.driverExists()){
                throw new NullPointerException();
            }

        } catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Driver doesn't exists!");
            alert.showAndWait();
            driverToUpdate.clear();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Make sure the driver's name length is within 20 letters!");
            alert.showAndWait();
            driverToUpdate.clear();
        }
        try{
            newDriverTeamName = String.format("%-20s",newData.getText());
            if (newDriverTeamName.length() >20){
                throw new  Exception();}

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver's team name");
            alert.setContentText("Make sure the team name length is within 20 letters!");
            alert.showAndWait();
            newData.clear();

        }
        if (!newData.getText().isEmpty() && !driverToUpdate.getText().isEmpty()){
            Driver driverUpdate = new Driver("Racing.txt",newDriverTeamName);
            driverUpdate.updateDriverData("driverTeamNameUpdate",newDriverTeamName,driverNameUpdate);
            promptUpdate.setText("Driver updated Successfully!");
            newData.clear();
            driverToUpdate.clear();
        }

    }
    public void updateCarType(){
        promptUpdate.setText("                        ");
        String driverNameUpdate = "";
        String newCarType = "";

        try{
            if (newData.getText().isEmpty() || driverToUpdate.getText().isEmpty()){
                throw new Exception();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Data Entry");
            alert.setContentText("Make sure all fields have been filled!");
            alert.showAndWait();
        }
        try{
            driverNameUpdate = String.format("%-20s",driverToUpdate.getText());
            if (driverNameUpdate.length() >20){
                throw new  Exception();}
            Driver driverUpdate = new Driver("Racing.txt",driverNameUpdate);
            if(!driverUpdate.driverExists()){
                throw new NullPointerException();
            }

        } catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Driver doesn't exists!");
            alert.showAndWait();
            driverToUpdate.clear();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Make sure the driver's name length is within 20 letters!");
            alert.showAndWait();
            driverToUpdate.clear();
        }
        try{
            newCarType = String.format("%-20s",newData.getText());
            if (newCarType.length() >20){
                throw new  Exception();}

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver's team name");
            alert.setContentText("Make sure the car type length is within 20 letters!");
            alert.showAndWait();
            newData.clear();

        }
        if (!newData.getText().isEmpty() && !driverToUpdate.getText().isEmpty()){
            Driver driverUpdate = new Driver("Racing.txt",newCarType);
            driverUpdate.updateDriverData("driverCarTypeUpdate",newCarType,driverNameUpdate);
            promptUpdate.setText("Driver updated Successfully!");
            newData.clear();
            driverToUpdate.clear();
        }
    }
    public void updateCurrentPoints(){
        promptUpdate.setText("                        ");
        String driverNameUpdate = "";
        String newCurrentPoints = "";

        try{
            if (newData.getText().isEmpty() || driverToUpdate.getText().isEmpty()){
                throw new Exception();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Data Entry");
            alert.setContentText("Make sure all fields have been filled!");
            alert.showAndWait();
        }
        try{
            driverNameUpdate = String.format("%-20s",driverToUpdate.getText());
            if (driverNameUpdate.length() >20){
                throw new  Exception();}
            Driver driverUpdate = new Driver("Racing.txt",driverNameUpdate);
            if(!driverUpdate.driverExists()){
                throw new NullPointerException();
            }

        } catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Driver doesn't exists!");
            alert.showAndWait();
            driverToUpdate.clear();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Make sure the driver name length is within 20 letters!");
            alert.showAndWait();
            driverToUpdate.clear();
        }
        try{
            newCurrentPoints = String.format("%-15s",Integer.parseInt(newData.getText()));
            if (newCurrentPoints.length() >15){
                throw new  Exception();}

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Current Points");
            alert.setContentText("Make sure the current points length is an integer within 20 letters!");
            alert.showAndWait();
            newData.clear();

        }
        if (!newData.getText().isEmpty() && !driverToUpdate.getText().isEmpty()){
            Driver driverUpdate = new Driver("Racing.txt",newCurrentPoints);
            driverUpdate.updateDriverData("driverCurrentPointsUpdate",newCurrentPoints,driverNameUpdate);
            promptUpdate.setText("Driver updated Successfully!");
            newData.clear();
            driverToUpdate.clear();
        }
    }


    public void deleteDriver(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("deleteDriver.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void deleteADriver(){
        promptDelete.setText("                        ");
        String driverNameDelete = "";
        try{
            if (driverToDelete.getText().isEmpty()) {
                throw new IOException();
            }
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Input Driver name!");
            alert.showAndWait();
        }
        try {

            driverNameDelete = String.format("%-20s", driverToDelete.getText());
            if (driverNameDelete.length() > 20) {
                throw new Exception();
            }
            Driver driverDelete = new Driver("Racing.txt", driverNameDelete);
            if (!driverDelete.driverExists()) {
                throw new NullPointerException();
            }
        }
        catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Driver doesn't exist!");
            alert.showAndWait();
            driverToDelete.clear();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Driver name");
            alert.setContentText("Make sure the driver's name length is within 20 letters!");
            alert.showAndWait();
            driverToDelete.clear();
        }
        if (!driverToDelete.getText().isEmpty()){
            Driver deleteADriver = new Driver("Racing.txt",driverNameDelete);
            deleteADriver.deleteDriver();
            promptDelete.setText("Driver deleted!");
            driverToDelete.clear();
        }
    }
    public void displayStandings(ActionEvent event) throws  IOException{
        Parent root = FXMLLoader.load(getClass().getResource("displayStandings.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void displayPositions(){
        position.appendText("Driver | Age | Team Name | Car Type | Current Points | Position\n");
        DriverTableDisplay table = new DriverTableDisplay("Racing.txt");
        position.appendText(table.driverSort());
        position.setWrapText(true);
        standingDisplay.setDisable(true);
    }

    public void simulateRace(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("simulateRace.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void simulateRaces(){
        racesPrompt.setText("             Races were Simulated!");
        for (int numRaces = 0; numRaces < 3; numRaces++){
            Races simulateRaces = new Races();
            simulateRaces.simulateRace();
        }

    }

    public void racesSummary(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("racesSummary.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void saveToFile(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("saveToFile.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayFile(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("displayFile.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayFileScene(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("displayFileScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void closeProgram(ActionEvent event) throws  IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}