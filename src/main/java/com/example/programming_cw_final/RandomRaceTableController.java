package com.example.programming_cw_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RandomRaceTableController implements Initializable{

    @FXML
    private Label enterDate;

    @FXML
    private Label enterLocation;

    @FXML
    private TableColumn<RandomRaceTable, String> srrName;

    @FXML
    private TableColumn<RandomRaceTable, Integer> srrPoints;

    @FXML
    private TableColumn<RandomRaceTable, Integer> srrPosition;

    @FXML
    private TableView<RandomRaceTable> srrTable;

    public void navigationSRRToMenu(ActionEvent actionEvent) throws Exception { // from srr to menu navigation
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        newStage.setScene(new Scene(root, 650, 650));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public static void generatingDate(){ // add the date to the list
        RandomRaceDetails.raceDate.clear();
        for (int i=1; i<32; i++){
            RandomRaceDetails.raceDate.add(i);
        }
        System.out.println(RandomRaceDetails.raceDate);
    }

    public void generateRace(){ //create the race
        if(!(RandomRaceDetails.allRacesDetails.isEmpty()) && RandomRaceDetails.raceDate.isEmpty()) { // if 31 races are taken place.
            enterDate.setText("No more races can take place.");
        }
        else if(RandomRaceDetails.raceDate.isEmpty()){ // if the date list is empty by initial
            generatingDate();
        }
        RandomRaceDetails.raceDriversDetails.clear(); // empty the list used to store details of a single race

        Random random = new Random(); //create random object

        System.out.println(RandomRaceDetails.raceDate.size());

        int randomIndexDate = random.nextInt(RandomRaceDetails.raceDate.size()); // take a random index to get a random date
        int date = (Integer) RandomRaceDetails.raceDate.get(randomIndexDate); // random date
        System.out.println(date);
        RandomRaceDetails.raceDate.remove(randomIndexDate); // remove the date from the date list
        String dateOfRace = ("Date: 2023 May " + date);
        enterDate.setText(dateOfRace); // print date in the interface

        int randomIndexLoc = random.nextInt(RandomRaceDetails.raceLocations.length); // random index to get the random location
        String location = RandomRaceDetails.raceLocations[randomIndexLoc];
        System.out.println(location);
        String locationOfRace = ("Location: " + location);
        enterLocation.setText(locationOfRace); // display location in the GUI

        Collections.shuffle(DriversDetails.driversDetails); // Shuffel drivers list

        //Giving positions to racers
        int totalPoints;
        for (int i=0; i<DriversDetails.driversDetails.size(); i++){
            ArrayList driversPosition = new ArrayList();
            if (i==0){ // first position to the 0th index
                driversPosition.add(1);
                driversPosition.add(10);
                driversPosition.add(DriversDetails.driversDetails.get(i).get(0));
                totalPoints = (Integer)DriversDetails.driversDetails.get(i).get(4) + 10;
                DriversDetails.driversDetails.get(i).set(4, totalPoints);

                RandomRaceDetails.raceDriversDetails.add(driversPosition); // save the current race details to the list

            }
            else if (i==1){ // second position to the 1th index
                driversPosition.add(2);
                driversPosition.add(07);
                driversPosition.add(DriversDetails.driversDetails.get(i).get(0));
                totalPoints = (Integer)DriversDetails.driversDetails.get(i).get(4) + 7;
                DriversDetails.driversDetails.get(i).set(4, totalPoints);

                RandomRaceDetails.raceDriversDetails.add(driversPosition); // save the current race details to the list

            }
            else if (i==2){ // third position to the 2th index
                driversPosition.add(3);
                driversPosition.add(05);
                driversPosition.add(DriversDetails.driversDetails.get(i).get(0));
                totalPoints = (Integer)DriversDetails.driversDetails.get(i).get(4) + 5;
                DriversDetails.driversDetails.get(i).set(4, totalPoints);

                RandomRaceDetails.raceDriversDetails.add(driversPosition); // save the current race details to the list

            }
            else{ // other positions to the order
                driversPosition.add(i+1);
                driversPosition.add(00);
                driversPosition.add(DriversDetails.driversDetails.get(i).get(0));

                RandomRaceDetails.raceDriversDetails.add(driversPosition); // save the current race details to the list

            }
        }
        System.out.println(RandomRaceDetails.raceDriversDetails);


        //Add details to the allRaceDetails list to access in VRL.
        for(int i=0; i<RandomRaceDetails.raceDriversDetails.size(); i++){
            ArrayList tempData = new ArrayList();
            tempData.add(date);
            tempData.add(location);
            tempData.add(RandomRaceDetails.raceDriversDetails.get(i).get(2));
            tempData.add(RandomRaceDetails.raceDriversDetails.get(i).get(0));
            tempData.add(RandomRaceDetails.raceDriversDetails.get(i).get(1));
            RandomRaceDetails.allRacesDetails.add(tempData);
        }
        System.out.println("All details: " + RandomRaceDetails.allRacesDetails);

        try{ // write the details to the list
            FileWriter fileWriter = new FileWriter("RandomRace.txt", true);

            fileWriter.write("\t \t \t \t \t Random Race for the month of May \n");
            fileWriter.write("\t Date: ");
            fileWriter.write("2023 May " + date);
            fileWriter.write("\t \t \t Location: " + location + "\n");

            for (int i=0; i<RandomRaceDetails.raceDriversDetails.size(); i++){
                fileWriter.write("Position: " + RandomRaceDetails.raceDriversDetails.get(i).get(0) + "\t \t");
                fileWriter.write("Scores: " + RandomRaceDetails.raceDriversDetails.get(i).get(1) + "\t \t");
                fileWriter.write("Name: " + RandomRaceDetails.raceDriversDetails.get(i).get(2) + "\n");
            }
            fileWriter.close();
        }
        catch (IOException exception) {
            System.out.println(" ");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // passing data to the table
        srrPosition.setCellValueFactory(new PropertyValueFactory<RandomRaceTable, Integer>("srrPosition"));
        srrName.setCellValueFactory(new PropertyValueFactory<RandomRaceTable, String>("srrName"));
        srrPoints.setCellValueFactory(new PropertyValueFactory<RandomRaceTable, Integer>("srrPoints"));

        generateRace();


        ObservableList<RandomRaceTable> srrList = FXCollections.observableArrayList(); //create the observable lisy

        for (int i = 0; i < RandomRaceDetails.raceDriversDetails.size(); i++) { // add data to the observable list
            srrList.addAll(new RandomRaceTable((Integer) RandomRaceDetails.raceDriversDetails.get(i).get(0), (String) RandomRaceDetails.raceDriversDetails.get(i).get(2), (Integer) RandomRaceDetails.raceDriversDetails.get(i).get(1)));
        }

        System.out.println(srrList);
        srrTable.setItems(srrList); //set data to the table from observable list
    }


    public void onSRRBackToMenuClick(ActionEvent actionEvent) throws Exception { // srr to menu
        navigationSRRToMenu(actionEvent);
    }

}
