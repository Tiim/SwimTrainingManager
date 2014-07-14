package ch.scbirs.trainingmanager.uicontrols;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Control;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class Spinner extends Control {

    final DoubleProperty value = new SimpleDoubleProperty(0);
    final DoubleProperty incrementAmount = new SimpleDoubleProperty(1);
    final BooleanProperty invalidData = new SimpleBooleanProperty(false);

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
        try {
            value.set(Double.parseDouble(str));
            if (invalidData.get()) {
                invalidData.set(false);
            }
        } catch (final NumberFormatException e) {
            invalidData.set(true);
        }
    }

    void increment() {
        value.set(value.get() + incrementAmount.get());
    }

    void decrement() {
        value.set(value.get() - incrementAmount.get());
    }

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

    public boolean isInvalidData() {
        return invalidData.get();
    }

    public void setInvalidData(final boolean b) {
        invalidData.set(b);
    }

    public BooleanProperty invalidDataProperty() {
        return invalidData;
    }
}
