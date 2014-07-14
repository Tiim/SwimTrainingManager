package ch.scbirs.trainingmanager.uicontrols;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class Spinner extends Control {

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



    }

    void change(final double direction) {
        if (direction > 0) increment();
        if (direction < 0) decrement();
    }

    void update(final String str) {
        value.set(Double.parseDouble(str));
    }

    void increment() {
        value.set(value.get() + incrementAmount.get());
    }

    void decrement() {
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
