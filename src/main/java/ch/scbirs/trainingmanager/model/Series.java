package ch.scbirs.trainingmanager.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Tim
 * @since 06 - 2014
 */
public class Series {

    private List<SeriesPart> seriesParts;

    public Series() {

    }

    public Series(final SeriesPart... s) {
        seriesParts = new ArrayList<>();
        seriesParts.addAll(Arrays.asList(s));
    }

    public static class SeriesPart {
        private final SimpleIntegerProperty nr = new SimpleIntegerProperty();
        private final SimpleIntegerProperty totalDistance = new SimpleIntegerProperty();
        private final SimpleIntegerProperty repetitions = new SimpleIntegerProperty();
        private final SimpleIntegerProperty distance = new SimpleIntegerProperty();
        //TODO: Change stroke form String to Stroke
        private final SimpleStringProperty stroke = new SimpleStringProperty();
        private final SimpleStringProperty materials = new SimpleStringProperty();
        private final SimpleStringProperty notes = new SimpleStringProperty();

        public int getTotalDistance() {
            return totalDistance.get();
        }

        public void setTotalDistance(final int totalDistance) {
            this.totalDistance.set(totalDistance);
        }

        public int getRepetitions() {
            return repetitions.get();
        }

        public void setRepetitions(final int repetitions) {
            this.repetitions.set(repetitions);
        }

        public int getDistance() {
            return distance.get();
        }

        public void setDistance(final int distance) {
            this.distance.set(distance);
        }

        public String getStroke() {
            return stroke.get();
        }

        public void setStroke(final String stroke) {
            this.stroke.set(stroke);
        }

        public String getMaterials() {
            return materials.get();
        }

        public void setMaterials(final String materials) {
            this.materials.set(materials);
        }

        public String getNotes() {
            return notes.get();
        }

        public void setNotes(final String notes) {
            this.notes.set(notes);
        }

        public int getNr() {
            return nr.get();
        }

        public void setNr(final int nr) {
            this.nr.set(nr);
        }
    }

}
