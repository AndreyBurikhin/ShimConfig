package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import util.ShimValues;

import java.io.IOException;

/**
 * Created by Ihar_Chekan on 10/13/2016.
 */
public class ShimConfigMain extends Application {

    final static Logger logger = Logger.getLogger(ShimConfigMain.class);

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Shims Setup");

        stage.setScene(
                createScene(
                        loadMainPane()
                )
        );

        stage.show();
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        Navigator.MAIN
                )
        );

        MainController mainController = loader.getController();

        Navigator.setMainController(mainController);
        Navigator.loadScene(Navigator.SCENE_1);

        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
                mainPane
        );

        return scene;
    }


    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else if (args.length == 1) {
            ShimValues.populateValues( args[0]);
            main.ShimConfigRun shimConfigRun = new main.ShimConfigRun();
            shimConfigRun.shimConfigRun();
            System.exit(0);
        } else if (args.length == 8 || args.length == 9) {
            ShimValues.populateValues( args );
        }
        else {
            logger.error("Use UI version or: " +
                    "\n usage: java -jar thisJar [SomeConfigFile]] " + "\n" +
                    " java -jar [pathToShim] [sshHost] [sshUser] [sshPassword] [restUser] [restPassword] [restHost] [dfsInstalDir] [optional:pathToTestProperties]");
            System.exit(-1);
        }
    }
}