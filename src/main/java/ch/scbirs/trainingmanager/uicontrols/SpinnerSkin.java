package ch.scbirs.trainingmanager.uicontrols;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class SpinnerSkin extends SkinBase<Spinner> {

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    public SpinnerSkin(final Spinner control) {
        super(control);

        final TextField textField = new TextField();
        final Button buttonIncrement = new Button();
        final Button buttonDecrement = new Button();

        textField.setMaxWidth(Double.MAX_VALUE);

        textField.getStyleClass().add("text");
        buttonIncrement.getStyleClass().addAll("button", "increment");
        buttonDecrement.getStyleClass().addAll("button", "decrement");

//        final BorderPane border = new BorderPane();
//
//        border.setLeft(buttonDecrement);
//        border.setCenter(textField);
//        border.setRight(buttonIncrement);
//
        final HBox hbox = new HBox(buttonDecrement, textField, buttonIncrement);
        HBox.setHgrow(textField, Priority.ALWAYS);
        control.valueProperty().addListener((observable, oldV, newV) ->
                textField.setText(Double.toString(control.getValue())));
        textField.textProperty().addListener((observable, oldValue, newValue) -> control.update(newValue));
        buttonIncrement.setOnMouseClicked((event) -> control.increment());
        buttonDecrement.setOnMouseClicked((event) -> control.decrement());

//        getChildren().add(border);
        getChildren().add(hbox);
        control.setOnScroll((event) -> control.change(event.getDeltaY()));

        textField.setText(Double.toString(control.getValue()));
    }
}
