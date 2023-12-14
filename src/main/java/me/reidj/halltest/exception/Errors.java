package me.reidj.halltest.exception;

import javafx.scene.control.Alert;
import lombok.AllArgsConstructor;
import me.reidj.halltest.App;

@AllArgsConstructor
public enum Errors implements Solid {
    FIELD_EMPTY(new FieldIsEmpty())
    ;

    private final Solid solid;

    @Override
    public boolean check(String... strings) {

        boolean bool = solid.check(strings);

        if (bool) {

            String[] message = solid.getMessage();

            App.getApp().getPrimaryStage().showAlert(
                    Alert.AlertType.ERROR,
                    message[0],
                    message[1]
            );
        }

        return bool;
    }

    @Override
    public String[] getMessage() {
        return solid.getMessage();
    }
}
