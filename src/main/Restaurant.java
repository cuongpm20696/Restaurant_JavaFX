package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Navigator;

public class Restaurant extends Application
{
    public static final String CURRENCY = "$";
    public static final String TABLENAME = "Table";

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Navigator.getInstance().setStage(stage);
        Navigator.getInstance().goToLogin();
    }

}