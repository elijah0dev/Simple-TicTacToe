package tictactoe.simpletictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class TicTacToeApplication extends Application {
    private String playing = "X";
    private Label status = new Label("Turn: " + this.playing);
    private Button[][] ticTacToe = new Button[3][3];

    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }

    @Override
    public void start(Stage window) {
        BorderPane borders = new BorderPane();
        GridPane grid = new GridPane();

        this.status.setFont(Font.font("Monospaced", 40));
        borders.setPrefSize(200, 300);
        borders.setPadding(new Insets(10, 10, 10, 10));
        grid.setPadding(new Insets(15, 15, 15, 15));
        grid.setHgap(10);
        grid.setVgap(10);

        this.ticTacToe[0][0] = new Button(" ");
        this.ticTacToe[0][1] = new Button(" ");
        this.ticTacToe[0][2] = new Button(" ");
        this.ticTacToe[1][0] = new Button(" ");
        this.ticTacToe[1][1] = new Button(" ");
        this.ticTacToe[1][2] = new Button(" ");
        this.ticTacToe[2][0] = new Button(" ");
        this.ticTacToe[2][1] = new Button(" ");
        this.ticTacToe[2][2] = new Button(" ");

        for (int row = 0; row < this.ticTacToe.length; row++) {
            for (int column = 0; column < this.ticTacToe[row].length; column++) {
                Button currentSpace = this.ticTacToe[row][column];
                currentSpace.setFont(Font.font("Monospaced", 40));
                currentSpace.setOnAction((event) -> claimSpot(currentSpace));
                grid.add(currentSpace, row, column);
            }
        }

        borders.setTop(this.status);
        borders.setCenter(grid);

        Scene toe = new Scene(borders);
        window.setScene(toe);
        window.show();
    }

    public void claimSpot(Button button) {
        if (!(this.status.getText().contains("Turn"))) {
            return;
        }
        if (!(button.getText().equals(" "))) {
            return;
        }

        button.setText(this.playing);

        if (winChecker() == true) {
            this.status.setText("The end!");
            return;
        }

        if (this.playing.equals("X")) {
            this.playing = "O";
        } else {
            this.playing = "X";
        }
        this.status.setText("Turn: " + this.playing);
    }

    public boolean winChecker() {
        int horizontal = 0;
        int vertical = 0;
        int i = 0;

        while (i < 3) {
            horizontal = 0;
            vertical = 0;
            for (int row = 0; row < this.ticTacToe.length; row++) {
                for (int column = 0; column < this.ticTacToe[row].length; column++) {
                    if (!(this.ticTacToe[row][column].getText().equals(this.playing))) {
                        continue;
                    }
                    if (row == i) {
                        vertical++;
                    }
                    if (column == i) {
                        horizontal++;
                    }
                    if (horizontal >= 3 || vertical >= 3) {
                        return true;
                    }

                }
            }
            i++;
        }
        return checkDiagonals();
    }
    public boolean checkDiagonals(){
        if (!(this.playing.equals(this.ticTacToe[1][1].getText()))){
            return false;
        }
        if (this.ticTacToe[0][0].getText().equals(this.playing) && this.ticTacToe[2][2].getText().equals(this.playing)){
            return true;
        }
        if (this.ticTacToe[0][2].getText().equals(this.playing) && this.ticTacToe[2][0].getText().equals(this.playing)){
            return true;
        }
        return false;
    }

}
