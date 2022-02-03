package pl.sdk.gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.sdk.hero.Fraction;
import pl.sdk.hero.Player;

public class EconomyStart extends Application {

    public EconomyStart() {

    }

    @Override
    public void start(Stage aStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/eco.fxml"));
        loader.setController(new EcoController(new Player(Fraction.NECROPOLIS, 1000), new Player(Fraction.NECROPOLIS,
                1000
        )));
        Scene scene = new Scene(loader.load());
        aStage.setScene(scene);
        aStage.setX(5);
        aStage.setY(5);
        aStage.show();

    }

    static void main(String[] aArgs) {
        launch();
    }
}
