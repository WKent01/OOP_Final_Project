import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        GridPane gPane = new GridPane();

        ChessBoard board = new ChessBoard(gPane);

        stage.setScene(new Scene(gPane, 800, 800));

        stage.setResizable(false);

        stage.show();

    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
