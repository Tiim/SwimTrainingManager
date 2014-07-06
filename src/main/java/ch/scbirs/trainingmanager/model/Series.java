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

    public static class SeriesPart {
        private SimpleIntegerProperty nr = new SimpleIntegerProperty();
        private SimpleIntegerProperty totalDistance = new SimpleIntegerProperty();
        private SimpleIntegerProperty repetitions = new SimpleIntegerProperty();
        private SimpleIntegerProperty distance = new SimpleIntegerProperty();
        //TODO: Change stroke form String to Stroke
        private SimpleStringProperty stroke = new SimpleStringProperty();
        private SimpleStringProperty materials = new SimpleStringProperty();
        private SimpleStringProperty notes = new SimpleStringProperty();

        public int getTotalDistance() {
            return totalDistance.get();
        }

        public void setTotalDistance(int totalDistance) {
            this.totalDistance.set(totalDistance);
        }

        public int getRepetitions() {
            return repetitions.get();
        }

        public void setRepetitions(int repetitions) {
            this.repetitions.set(repetitions);
        }

        public int getDistance() {
            return distance.get();
        }

        public void setDistance(int distance) {
            this.distance.set(distance);
        }

        public String getStroke() {
            return stroke.get();
        }

        public void setStroke(String stroke) {
            this.stroke.set(stroke);
        }

        public String getMaterials() {
            return materials.get();
        }

        public void setMaterials(String materials) {
            this.materials.set(materials);
        }

        public String getNotes() {
            return notes.get();
        }

        public void setNotes(String notes) {
            this.notes.set(notes);
        }

        public int getNr() {
            return nr.get();
        }

        public void setNr(int nr) {
            this.nr.set(nr);
        }
    }

    public List<SeriesPart> seriesParts;

    public Series() {

    }

    public Series(SeriesPart... s) {
        seriesParts = new ArrayList<>();
        seriesParts.addAll(Arrays.asList(s));
    }

}
