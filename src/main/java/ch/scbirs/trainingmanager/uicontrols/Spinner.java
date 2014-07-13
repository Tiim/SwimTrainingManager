package ch.scbirs.trainingmanager.uicontrols;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class Spinner extends Region {

    final DoubleProperty value = new SimpleDoubleProperty(0);
    final DoubleProperty incrementAmount = new SimpleDoubleProperty(1);

    public Spinner(final double startVal, final double inc) {
        this();
        value.set(startVal);
        incrementAmount.set(inc);
    }

    public Spinner() {
        getStylesheets().add(getClass().getResource("Spinner.css").toExternalForm());
        getStyleClass().add("Spinner");

        final TextField textField = new TextField();
        final Button buttonIncrement = new Button();
        final Button buttonDecrement = new Button();

        textField.getStyleClass().add("text");
        buttonIncrement.getStyleClass().addAll("button", "increment");
        buttonDecrement.getStyleClass().addAll("button", "decrement");

        buttonDecrement.setMaxHeight(12);

        final HBox hBox = new HBox();

        hBox.getChildren().addAll(buttonDecrement, textField, buttonIncrement);

        value.addListener((observable, oldV, newV) -> textField.setText(Double.toString(value.get())));
        textField.textProperty().addListener((observable, oldValue, newValue) -> update(newValue));
        buttonIncrement.setOnMouseClicked((event) -> increment());
        buttonDecrement.setOnMouseClicked((event) -> decrement());

        getChildren().addAll(hBox);
        layout();

        setOnScroll((event) -> change(event.getDeltaY()));

    }

    private void change(final double direction) {
        if (direction > 0) increment();
        if (direction < 0) decrement();
    }

    private void update(final String str) {
        value.set(Double.parseDouble(str));
    }

    private void increment() {
        value.set(value.get() + incrementAmount.get());
    }

    private void decrement() {
        value.set(value.get() - incrementAmount.get());
    }

    ////// GETTER SETTER

    public double getValue() {
        return value.get();
    }

    public void setValue(final double val) {
        value.set(val);
    }

    public DoubleProperty valueProperty() {
        return value;
    }

    public double getIncrementAmount() {
        return incrementAmount.get();
    }

    public void setIncrementAmount(final double amount) {
        incrementAmount.set(amount);
    }

    public DoubleProperty incrementAmountProperty() {
        return incrementAmount;
    }

}
