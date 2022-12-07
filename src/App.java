import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        GridPane gPane = new GridPane();

        try {
            ChessBoard board = new ChessBoard(gPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(gPane, 800, 800));

        stage.setResizable(false);

        stage.show();

    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
