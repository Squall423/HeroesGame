package pl.sdk.gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

class Start extends Application {

    @Override
    public void start(Stage aStage) throws Exception {


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/battleMap.fxml"));
        loader.setController(new BattleMapController());
        Scene scene = new Scene(loader.load());
        aStage.setScene(scene);
        aStage.setX(5);
        aStage.setY(5);
        aStage.show();


    }


}
