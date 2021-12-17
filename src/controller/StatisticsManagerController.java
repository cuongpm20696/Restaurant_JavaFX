package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import view.Navigator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsManagerController implements Initializable {
     @FXML
    private JFXButton btnStatistic;

    @FXML
    private JFXButton btnAccount;

    @FXML
    private JFXButton btnCategory;

    @FXML
    private JFXButton btnMeal;

    @FXML
    private JFXButton btnDrink;

    @FXML
    private JFXButton btnTable;

    @FXML
    private JFXButton btnView;

    @FXML
    private Button btnLogout;


    @FXML
    void clickAccount(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAccountManager();
    }

    @FXML
    void clickDrink(ActionEvent event) throws IOException {
        Navigator.getInstance().goToDrinkManager();
    }

    @FXML
    void clickLogout(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLogin();
    }

    @FXML
    void clickCategory(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCategoryManager();
    }
    @FXML
    void clickMeal(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMealManager();
    }

    @FXML
    void clickStatistic(ActionEvent event) throws IOException {
        Navigator.getInstance().goToStatisticManager();
    }

    @FXML
    void clickTable(ActionEvent event) throws IOException {
        Navigator.getInstance().goToTableManager();
    }

    @FXML
    void clickView(ActionEvent event) throws IOException {
        Navigator.getInstance().goToViewManager();
    }
    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniLineChart();
    }

    private void iniLineChart(){
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("MON", 8));
        series.getData().add(new XYChart.Data("TUE", 50));
        series.getData().add(new XYChart.Data("WED", 40));
        series.getData().add(new XYChart.Data("THU", 100));
        series.getData().add(new XYChart.Data("FRI", 30));
        series.getData().add(new XYChart.Data("SAT", 20));
        series.getData().add(new XYChart.Data("SUN", 8));
        lineChart.getData().addAll(series);
    }
}
