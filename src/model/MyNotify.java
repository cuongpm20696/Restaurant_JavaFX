package model;


import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;




public class MyNotify {
    public static void MyNotifyAlert(String content){
        Notifications notify = Notifications.create().title("").graphic(null).text(content).hideAfter(Duration.seconds(2)).position(Pos.CENTER).darkStyle();
        notify.show();
    }
    public static void MyNotifyAlertError(String content){

        Notifications notify = Notifications.create().title("").graphic(null).text(content).hideAfter(Duration.seconds(2)).position(Pos.CENTER).darkStyle();
        notify.showError();
    }

}
