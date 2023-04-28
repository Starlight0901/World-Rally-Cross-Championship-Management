package com.example.programming_cw_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;
//
public class vctStandingTableController implements Initializable{

    // define the columns and table
    @FXML
    private TableColumn<VctTableDetails, String> vctCar;

    @FXML
    private TableColumn<VctTableDetails, String> vctName;

    @FXML
    private TableColumn<VctTableDetails, Integer> vctScores;

    @FXML
    private TableView<VctTableDetails> vctTable;

    @FXML
    private TableColumn<VctTableDetails, String> vctTeam;

    @FXML
    private TableColumn<VctTableDetails, Integer> vctAge;

    @FXML
    private Button vctBackToMenu;






    public void navigationVCTToMenu(ActionEvent actionEvent) throws Exception { // navigation menu
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        newStage.setScene(new Scene(root, 550, 550));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    public void onVCTBackToMenuClick(ActionEvent actionEvent) throws Exception { // back to menu from vct
        navigationVCTToMenu(actionEvent);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // pass values from the list to the table

        vctName.setCellValueFactory(new PropertyValueFactory<VctTableDetails, String>("vctName"));
        vctAge.setCellValueFactory(new PropertyValueFactory<VctTableDetails, Integer>("vctAge"));
        vctTeam.setCellValueFactory(new PropertyValueFactory<VctTableDetails, String>("vctTeam"));
        vctCar.setCellValueFactory(new PropertyValueFactory<VctTableDetails, String>("vctCar"));
        vctScores.setCellValueFactory(new PropertyValueFactory<VctTableDetails, Integer>("vctScores"));

        ObservableList<VctTableDetails> list = FXCollections.observableArrayList(); // create the observable list

        int key;
        int j;
        ArrayList value = new ArrayList();

        for (int i = 1; i < DriversDetails.driversDetails.size(); i++) { // insertion sort on the drivers list. sort in the ascending order
            key = (Integer) DriversDetails.driversDetails.get(i).get(4);
            j = i - 1;

            while (j >= 0 && ((Integer) DriversDetails.driversDetails.get(j).get(4)) > key) {
                value = DriversDetails.driversDetails.get(j + 1); // saves the value in j+1
                DriversDetails.driversDetails.set(j + 1, DriversDetails.driversDetails.get(j)); // set the values in j to j+1
                DriversDetails.driversDetails.set(j, value); // sets the saved j+1 values to j
                j -= 1;
            }
        }
        System.out.println(DriversDetails.driversDetails); // prints details in the console to check
        Collections.reverse(DriversDetails.driversDetails); // reverse the list to get from the descending order

        for (int i=0; i<DriversDetails.driversDetails.size(); i++){ // add the list details to the observable list
            list.addAll( new VctTableDetails((String)DriversDetails.driversDetails.get(i).get(0), (Integer) DriversDetails.driversDetails.get(i).get(1), (String) DriversDetails.driversDetails.get(i).get(2), (String) DriversDetails.driversDetails.get(i).get(3), (Integer) DriversDetails.driversDetails.get(i).get(4)));
        }


        vctTable.setItems(list); // set the observable list data to the table

    }

}
