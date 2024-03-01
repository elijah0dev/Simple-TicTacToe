module tictactoe.simpletictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens tictactoe.simpletictactoe to javafx.fxml;
    exports tictactoe.simpletictactoe;
}