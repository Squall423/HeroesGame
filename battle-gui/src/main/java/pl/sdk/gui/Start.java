package pl.sdk.gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

 class Start extends Application {

    static private Start instance;
 Stage stage;

    private Start() {
    }

    @Override
    public void start(Stage aStage) throws Exception {
        stage = aStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/battleMap.fxml"));
        loader.setController(new BattleMapController());
        Scene scene = new Scene(loader.load());
        aStage.setScene(scene);
        aStage.setX(5);
        aStage.setY(5);
        aStage.show();
        aStage.setOnCloseRequest(e -> {
            e.consume();
            exit();
        });


    }

    static Start getInstance() {
        if (instance == null) {
            instance = new Start();
        }
        return instance;
    }


    public void exit() {
        stage.close();
    }
}
