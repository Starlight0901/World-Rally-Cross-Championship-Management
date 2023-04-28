package com.example.programming_cw_final;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VrlController implements Initializable {

    @FXML
    private TableView<VrlTableDetails> raceTable;

    @FXML
    private TableColumn<VrlTableDetails, String> vrlLocation;

    @FXML
    private TableColumn<VrlTableDetails, Integer> vrlPosition;

    @FXML
    private TableColumn<VrlTableDetails, Integer> vrlDate;

    @FXML
    private TableColumn<VrlTableDetails, String> vrlName;

    @FXML
    private TableColumn<VrlTableDetails, Integer> vrlScores;

    public void navigationVRLToMenu(ActionEvent actionEvent) throws Exception { // vrl to menu
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        newStage.setScene(new Scene(root, 650, 650));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void onVRLBackToMenuClick(ActionEvent actionEvent) throws Exception { // vrl to menu
        navigationVRLToMenu(actionEvent);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // pass the data to the table
        vrlDate.setCellValueFactory(new PropertyValueFactory<VrlTableDetails, Integer>("vrlDate"));
        vrlLocation.setCellValueFactory(new PropertyValueFactory<VrlTableDetails, String>("vrlLocation"));
        vrlName.setCellValueFactory(new PropertyValueFactory<VrlTableDetails, String>("vrlName"));
        vrlPosition.setCellValueFactory(new PropertyValueFactory<VrlTableDetails, Integer>("vrlPosition"));
        vrlScores.setCellValueFactory((new PropertyValueFactory<VrlTableDetails, Integer>("vrlScores")));

        ObservableList<VrlTableDetails> vrlList = FXCollections.observableArrayList(); // create the observable list


        int key;
        int j;
        ArrayList value = new ArrayList();

        for (int i = 1; i < RandomRaceDetails.allRacesDetails.size(); i++) { // sort according to the date (ascending order)
            key = (Integer) RandomRaceDetails.allRacesDetails.get(i).get(0);
            j = i - 1;

            while (j >= 0 && ((Integer) RandomRaceDetails.allRacesDetails.get(j).get(0)) > key) {
                //swapping values
                value = RandomRaceDetails.allRacesDetails.get(j + 1);
                RandomRaceDetails.allRacesDetails.set(j + 1, RandomRaceDetails.allRacesDetails.get(j));
                RandomRaceDetails.allRacesDetails.set(j, value);
                j -= 1;
            }
        }

        for (int i=0; i<RandomRaceDetails.allRacesDetails.size(); i++){ // add the data from the list to the observable list
            vrlList.addAll(new VrlTableDetails((Integer) RandomRaceDetails.allRacesDetails.get(i).get(0), (String) RandomRaceDetails.allRacesDetails.get(i).get(1), (String) RandomRaceDetails.allRacesDetails.get(i).get(2), (Integer) RandomRaceDetails.allRacesDetails.get(i).get(3), (Integer) RandomRaceDetails.allRacesDetails.get(i).get(4) ));
        }

        raceTable.setItems(vrlList); // set the values of the observable list to the race table
    }

}
