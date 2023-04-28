package com.example.programming_cw_final;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class ApplicationController  {
    int Age, Points, newAge, newPoints;
    String foundDetails, updatedDetails, duplicateDetails;


//    //==================================================================================================================

    //ADD
    @FXML
    private Label dataInsertionMsg;
    @FXML
    private Label addedDetails;
    @FXML
    private TextField name;
    @FXML
    private TextField age;
    @FXML
    private TextField team;
    @FXML
    private TextField car;
    @FXML
    private TextField points;


    public void onAddButtonClick(ActionEvent actionEvent) throws Exception { //Menu to Add button
        navigationAdd(actionEvent);
    }

    public void navigationAdd(ActionEvent actionEvent) throws Exception { // Add button scene change
        Stage newstage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Add.fxml"));
        newstage.setScene(new Scene(root, 650, 650));
        newstage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
    public void navigationAddBackToMenu(ActionEvent actionEvent) throws Exception { // Add button to menu
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        newStage.setScene(new Scene(root, 650, 650));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void onButtonClick(ActionEvent actionEvent) throws Exception { // Add details to the list

        if (name.getText().isEmpty() && age.getText().isEmpty() && team.getText().isEmpty() && car.getText().isEmpty() && points.getText().isEmpty()) { // All fields are empty
            dataInsertionMsg.setText("Enter data!");
        } else if (name.getText().isEmpty() || age.getText().isEmpty() || team.getText().isEmpty() || car.getText().isEmpty() || points.getText().isEmpty()) { // Atleast one field is empty
            dataInsertionMsg.setText("Fill all Fields!");
        } else if (!(age.getText().isEmpty() || points.getText().isEmpty())) {
            try { // validating valid input for age and points
                Age = Integer.parseInt(age.getText());
                Points = Integer.parseInt(points.getText());

                if ((Age < 0) || (Points < 0)) {
                    throw new Exception("Negative input");
                }
                if (DuplicatedEntries(name.getText())) {
                    addedDetails.setText(duplicateDetails);
                    dataInsertionMsg.setText("Driver Name Already Exists");
                } else {
                    AddDriversDetails(); // call Add function
                }
            } catch (RuntimeException e) {
                dataInsertionMsg.setText("Enter integers for age and points");
            } catch (Exception exception) {
                dataInsertionMsg.setText("Enter positive values for age and points");
            }
        }
    }

    public void onAddToMenuButtonClick(ActionEvent actionEvent) throws Exception {
        navigationAddBackToMenu(actionEvent);
    }
    //================================

    public void AddDriversDetails() {
        ArrayList drivers = new ArrayList();

        drivers.add(name.getText());
        drivers.add(Age);
        drivers.add(team.getText());
        drivers.add(car.getText());
        drivers.add(Points);
        DriversDetails.driversDetails.add(drivers);
        System.out.println(DriversDetails.driversDetails);
        String details = name.getText() + ", " + age.getText() + ", " + team.getText() + ", " + car.getText() + ", " + points.getText();
        addedDetails.setText(details);
        dataInsertionMsg.setText("Added Successfully");
    }


    //==================================================================================================================
    //Delete

    @FXML
    private TextField DeleteName;

    @FXML
    private Label DetailsToDelete;

    @FXML
    Button DeleteToMenuButton;

    @FXML
    Button FindToDeleteButton;

    public void onDDDButtonClick(ActionEvent actionEvent) throws Exception { //Menu to delete
        navigationDelete(actionEvent);
    }

    public void navigationDelete(ActionEvent actionEvent) throws Exception { // Navigation delete
        Stage newstage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Delete.fxml"));
        newstage.setScene(new Scene(root, 650, 650));
        newstage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void navigationDeleteToMenu(ActionEvent actionEvent) throws Exception { // Delete to menu
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        newStage.setScene(new Scene(root, 650, 650));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void onFindToDeleteButtonClick(ActionEvent actionEvent) throws Exception { //find name the user entered to delete

        if (DeleteName.getText().isEmpty()) {
            DetailsToDelete.setText("Enter the name of the driver you wish to delete:");
        } else {
            if (FindDetails(DeleteName.getText())) {
                DetailsToDelete.setText(foundDetails);
            } else {
                DetailsToDelete.setText("Name not available. Please recheck!");
            }
        }
    }

    public void onDeleteButtonClick(ActionEvent actionEvent) throws Exception { // when user confirmed delete.. validating delete
        for (int i = 0; i < DriversDetails.driversDetails.size(); i++) {

            if (DeleteName.getText().equals(DriversDetails.driversDetails.get(i).get(0))) { // search the name
                DriversDetails.driversDetails.remove(i); // delete driver
                DetailsToDelete.setText("Deleted Successfully");
                System.out.println(DriversDetails.driversDetails);
            }
        }
    }

    public void onDeleteToMenuButtonClick(ActionEvent actionEvent) throws Exception {
        navigationDeleteToMenu(actionEvent);
    }

    //==================================================================================================================

    //Update

    @FXML
    private TextField updateName;

    @FXML
    private TextField updateAge;

    @FXML
    private TextField updateTeam;

    @FXML
    private TextField updateCar;

    @FXML
    private TextField updatePoints;

    @FXML
    private Label updateDetails;

    @FXML
    private Label updateMsg;

    @FXML
    private TextField UpdateName;


    public void onUDDButtonClick(ActionEvent actionEvent) throws Exception { // menu to update
        navigationUpdate(actionEvent);
    }

    public void navigationUpdate(ActionEvent actionEvent) throws Exception { // navigation update
        Stage newstage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Update.fxml"));
        newstage.setScene(new Scene(root, 650, 650));
        newstage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void navigationUpdateToMenu(ActionEvent actionEvent) throws Exception { // update to menu
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        newStage.setScene(new Scene(root, 650, 650));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }



    public void onFindToUpdateButtonClick(ActionEvent actionEvent) throws Exception { // find driver the user entered to update
        if (UpdateName.getText().isEmpty()) { // if the field is empty
            updateDetails.setText("Enter the name of the driver you wish to update:");
        } else {
            if (FindDetails(UpdateName.getText())) { // if found, the found details are displayed
                updateDetails.setText(foundDetails);
            } else {
                updateDetails.setText("Name not available. Please recheck!"); // if not found, the error message is displayed
            }
        }
    }

    public void updateDetails() {

        for (int i = 0; i < DriversDetails.driversDetails.size(); i++) { // update details
            if (UpdateName.getText().equals(DriversDetails.driversDetails.get(i).get(0))) {
                DriversDetails.driversDetails.get(i).set(0, updateName.getText());
                DriversDetails.driversDetails.get(i).set(1, newAge);
                DriversDetails.driversDetails.get(i).set(2, updateTeam.getText());
                DriversDetails.driversDetails.get(i).set(3, updateCar.getText());
                DriversDetails.driversDetails.get(i).set(4, newPoints);
                updateMsg.setText("Updated Successfully. \n New details are displayed above.");
                updatedDetails = updateName.getText() + ", " + newAge + ", " + updateTeam.getText() + ", " + updateCar.getText() + ", " + newPoints;
            }
        }
    }

    public void onUpdateButtonClick(ActionEvent actionEvent) throws Exception { // when the user confirmed update, the validations

        if (updateName.getText().isEmpty() && updateAge.getText().isEmpty() && updateTeam.getText().isEmpty() && updateCar.getText().isEmpty() && updatePoints.getText().isEmpty()) {
            updateMsg.setText("Enter data!"); // if all fields are empty
        } else if (updateName.getText().isEmpty() || updateAge.getText().isEmpty() || updateTeam.getText().isEmpty() || updateCar.getText().isEmpty() || updatePoints.getText().isEmpty()) {
            updateMsg.setText("Fill all Fields!"); // if atleast one field is empty
        } else if (!(updateAge.getText().isEmpty() || updatePoints.getText().isEmpty())) {
            try { // validating age for invalid input and negative inputs
                newAge = Integer.parseInt(updateAge.getText());
                newPoints = Integer.parseInt(updatePoints.getText());

                if ((newAge < 0) || (newPoints < 0)) {
                    throw new Exception("Negative input");
                } else {
                    updateDetails();
                    updateDetails.setText(updatedDetails);
                }
            } catch (RuntimeException e) {
                updateMsg.setText("Enter integers for age and points");
            } catch (Exception exception) {
                updateMsg.setText("Enter positive values for age and points");
            }
        }
    }
    public void onUpdateToMenuButtonClick(ActionEvent actionEvent) throws Exception { // update to menu
        navigationUpdateToMenu(actionEvent);
    }

    //==================================================================================================================
    //VCT

    public void onVCTButtonClick(ActionEvent actionEvent) throws Exception { // menu to vct
        navigationVCT(actionEvent);

    }


    public void navigationVCT(ActionEvent actionEvent) throws Exception { // vct navigation
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("vct.fxml"));
        newStage.setScene(new Scene(root, 650, 650));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();

    }




    //==================================================================================================================
    //SRR

    @FXML
    private Label enterDate;

    @FXML
    private Label enterLocation;
    public void onSRRButtonClick(ActionEvent actionEvent) throws Exception { // menu to random race
        navigationRandomRace(actionEvent);
    }

    public void navigationRandomRace(ActionEvent actionEvent) throws Exception { // random race navigation
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("S_RandomRace.fxml"));
        newStage.setScene(new Scene(root, 650, 650));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }


    //==================================================================================================================
    //VRL


    public void onVRLButtonClick(ActionEvent actionEvent) throws Exception { // menu to vrl
        navigationVRL(actionEvent);
    }

    public void navigationVRL(ActionEvent actionEvent) throws Exception { // vrl navigation
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("vrl.fxml"));
        newStage.setScene(new Scene(root, 650, 650));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }


    //==================================================================================================================
    //STF

    @FXML
    private Label saveDataMsg;
    @FXML
    private Label stfFileErrorMsg;

    public void onSTFButtonClick(ActionEvent actionEvent) throws Exception { // menu to stf
        navigationSTF(actionEvent);

    }

    public void navigationSTF(ActionEvent actionEvent) throws Exception { // stf navigation
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("stf.fxml"));
        newStage.setScene(new Scene(root, 650, 650));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void navigationSTFToMenu(ActionEvent actionEvent) throws Exception { // stf to menu navigation
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        newStage.setScene(new Scene(root, 650, 650));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void onSaveToButtonClick(ActionEvent actionEvent) throws Exception{ // save details to file

        File file = new File("driversDetails.txt"); // create file object

        try { // check if the file of the name entered is there.. if not there a file will be created
            if (file.createNewFile()){
                stfFileErrorMsg.setText("file created");
                System.out.println("file created");
            }
            else{
                stfFileErrorMsg.setText("loading file");
                System.out.println("loading file");
            }
        }
        catch (IOException exception){
            stfFileErrorMsg.setText("An error occurred");
            System.out.println("An error occurred");
        }

        try{

            FileWriter fileWriter = new FileWriter("driversDetails.txt"); // create fileWriter object

            fileWriter.write("\t \t \t \t \t Current Details of Drivers \n");

            String driverName, driverAge, driverTeam, driverCar, driverPoints;

            for (int i=0; i<DriversDetails.driversDetails.size(); i++){
                fileWriter.write("Name: "); // write the name to the file
                driverName =(String) DriversDetails.driversDetails.get(i).get(0);
                fileWriter.write(driverName);
                fileWriter.write("\t");

                fileWriter.write("\t");
                fileWriter.write("Age: "); // write the age to the file
                driverAge = String.valueOf(DriversDetails.driversDetails.get(i).get(1));
                fileWriter.write(driverAge);
                fileWriter.write("\t");

                fileWriter.write("\t");
                fileWriter.write("Team: "); // write the team to the file
                driverTeam =(String) DriversDetails.driversDetails.get(i).get(2);
                fileWriter.write(driverTeam);
                fileWriter.write("\t");

                fileWriter.write("\t");
                fileWriter.write("Car: "); // write the car to the file
                driverCar =(String) DriversDetails.driversDetails.get(i).get(3);
                fileWriter.write(driverCar);
                fileWriter.write("\t");

                fileWriter.write("\t");
                fileWriter.write("Points: "); // write the points to the file
                driverPoints = String.valueOf(DriversDetails.driversDetails.get(i).get(4));
                fileWriter.write(driverPoints);
                fileWriter.write("\n");

            }
            saveDataMsg.setText("Data Saved Successfully in the textfile 'driversDetails.txt'"); //successful message
            fileWriter.close();
        }
        catch (IOException exception){
            saveDataMsg.setText("An Error Occurred! ");
        }
    }

    public void onSTFBackToMenuClick(ActionEvent actionEvent) throws Exception {
        navigationSTFToMenu(actionEvent);
    }

    //==================================================================================================================

    @FXML
    private Label displayRFF;
    @FXML
    private Label rffFileErrorMsg;


    public void onRFFButtonClick(ActionEvent actionEvent) throws Exception { //menu to rff
        navigationRFF(actionEvent);
    }

    public void navigationRFF(ActionEvent actionEvent) throws Exception { // navigation rff
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("rff.fxml"));
        newStage.setScene(new Scene(root, 650, 650));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void navigationRFFToMenu(ActionEvent actionEvent) throws Exception { //navigation to menu
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        newStage.setScene(new Scene(root, 650, 650));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void onRFFLoadDataButtonClick(ActionEvent actionEvent) throws Exception{ // load data from the text file button

        File file = new File("driversDetails.txt");

        try { // check if the file is already created or not. if not created, a file will be created
            if (file.createNewFile()){
                rffFileErrorMsg.setText("file created");
                System.out.println("file created");
            }
            else{
                rffFileErrorMsg.setText("loading file");
                System.out.println("loading file");
            }
        }
        catch (IOException exception){
            rffFileErrorMsg.setText("An error occurred");
            System.out.println("An error occurred");
        }

        String allDetails = " Reading Details From File \n";
        try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)){
            String readline = br.readLine(); // creating fileReader and BufferedReader objects

            while (readline != null){ // until the reading line is null, the lines are read from file
                String details = (readline + "\n"); // the read line is saved to a temperory variable
                allDetails = allDetails + details; // the data saved in the temp variable is concatinated with previous read line data
                System.out.println(readline); // printed in the console for checking
                readline = br.readLine();
            }
        } catch (Exception exception){
            System.out.println(exception);
            rffFileErrorMsg.setText("An error occurred when reading from the file");
            System.out.println("An error occurred when reading from the file");
        }
        displayRFF.setText(allDetails); // displaying the data read from the file in the GUI
    }

    public void onRFFUpdateButtonClick(ActionEvent actionEvent) throws Exception{ // Navigation to update to update details
        navigationUpdate(actionEvent);
    }

    public void onUpdateToRFFButtonClick(ActionEvent actionEvent) throws Exception{ // navigation back to RFF from update
        navigationRFF(actionEvent);
    }

    public void onRFFDeleteButtonClick(ActionEvent actionEvent) throws Exception{ // navigation to delete to delete details
        navigationDelete(actionEvent);
    }

    public void onDeleteToRFFButtonClick(ActionEvent actionEvent) throws Exception{ // navigation from delete to rff
        navigationRFF(actionEvent);
    }

    public void onRFFSaveButtonClick(ActionEvent actionEvent) throws Exception{ // navigation rff to stf
        navigationSTF(actionEvent);
    }

    public void onSTFBackToRFFClick(ActionEvent actionEvent) throws Exception{ // navigation stf to rff
        navigationRFF(actionEvent);
    }
    public void onRFFBackToMenuClick(ActionEvent actionEvent) throws Exception { //navigation from rff to menu
        navigationRFFToMenu(actionEvent);
    }
    //==================================================================================================================


    public void onESCButtonClick(ActionEvent actionEvent) throws Exception { // menu to exit page
        navigationExit(actionEvent);
    }

    public void navigationExit(ActionEvent actionEvent) throws Exception { // navigation to exit
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Exit.fxml"));
        newStage.setScene(new Scene(root, 500, 500));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void navigationESCToMenu(ActionEvent actionEvent) throws Exception { // navigation exit to menu
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        newStage.setScene(new Scene(root, 550, 550));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void onBackToMenuButtonClick(ActionEvent actionEvent) throws Exception{ // exit to menu page
        navigationESCToMenu(actionEvent);
    }

    public void onExitButtonClick(ActionEvent actionEvent) throws Exception { // exit program if the user confirm exit
        System.exit(0);
    }
    //==================================================================================================================


    public boolean DuplicatedEntries(String duplicateName) { // check if the entered name is already in the list of drivers
        boolean ifDuplicated = false; // default false
        for (int index = 0; index < DriversDetails.driversDetails.size(); index++) {
            if (duplicateName.equals(DriversDetails.driversDetails.get(index).get(0))) {

                duplicateDetails = DriversDetails.driversDetails.get(index).get(0) + ", " + DriversDetails.driversDetails.get(index).get(1) + ", " + DriversDetails.driversDetails.get(index).get(2) + ", " + DriversDetails.driversDetails.get(index).get(3) + ", " + DriversDetails.driversDetails.get(index).get(4);

                ifDuplicated = true; // if the name is available, returns true
            }
        }
        return ifDuplicated;
    }

    //==================================== Finding details algorithm for DDD and UDD====================================

    public boolean FindDetails(String findName) { // search for the details of the passed name in the list
        boolean ifFound = false; // default false
        for (int i = 0; i < DriversDetails.driversDetails.size(); i++) {
            if (findName.equals(DriversDetails.driversDetails.get(i).get(0))) {
                foundDetails = DriversDetails.driversDetails.get(i).get(0) + ", " + DriversDetails.driversDetails.get(i).get(1) + ", " + DriversDetails.driversDetails.get(i).get(2) + ", " + DriversDetails.driversDetails.get(i).get(3) + ", " + DriversDetails.driversDetails.get(i).get(4);
                ifFound = true; // if name is found, returns true
            }
        }
        return ifFound;
    }

    //==================================================================================================================

}