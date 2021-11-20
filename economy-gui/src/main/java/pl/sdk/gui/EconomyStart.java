package pl.sdk.gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.sdk.hero.EconomyHero;

public class EconomyStart extends Application {

    public EconomyStart() {

    }

    @Override
    public void start(Stage aStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/eco.fxml"));
        loader.setController(new EcoController(new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 3000),
                new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 3000)));
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
