package AimTrainer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Statistics {
    private int hits = 0;
    private final PropertyChangeSupport supportHits = new PropertyChangeSupport(this);
    private int misses = 0;
    private final PropertyChangeSupport supportMisses = new PropertyChangeSupport(this);
    private int circlesDisplayed = 0;

    public Statistics() {
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        supportHits.firePropertyChange("hits", this.hits, hits);
        this.hits = hits;
    }

    public void addListenerHits(PropertyChangeListener listener) {
        supportHits.addPropertyChangeListener(listener);
    }

    public void removeListenerHits(PropertyChangeListener listener) {
        supportHits.removePropertyChangeListener(listener);
    }

    public int getMisses() {
        return misses;
    }

    public void setMisses(int misses) {
        supportMisses.firePropertyChange("misses", this.misses, misses);
        this.misses = misses;
    }

    public void addListenerMisses(PropertyChangeListener listener) {
        supportMisses.addPropertyChangeListener(listener);
    }

    public void removeListenerMisses(PropertyChangeListener listener) {
        supportMisses.removePropertyChangeListener(listener);
    }

    public int getCirclesDisplayed() {
        return circlesDisplayed;
    }

    public void setCirclesDisplayed(int circlesDisplayed) {
        this.circlesDisplayed = circlesDisplayed;
    }
}
