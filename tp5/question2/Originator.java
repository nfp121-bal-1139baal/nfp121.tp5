package question2;
import java.util.List;

class Originator {
    private List<String> state;

    public void set(List<String> state) {

        this.state = state;
    }

    public Memento saveToMemento() {
        System.out.println("Originator: Saving to Memento.");
        return new Memento(this.state);
    }

    public List<String> restoreFromMemento(Memento memento) {
        this.state = memento.getSavedState();
        return state;
    }
}