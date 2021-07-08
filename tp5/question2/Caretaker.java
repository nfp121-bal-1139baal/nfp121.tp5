package question2;
import java.util.Stack;
class Caretaker {
    private Stack<Memento> savedStates = new Stack<Memento>();

    public void addMemento(Memento m) {
        savedStates.push(m);
    }

    public Memento getMemento() {
        return savedStates.pop();
    }

    public Memento seeMemento() {
        return savedStates.peek();
    }

    public boolean isEmpty(){
        return savedStates.empty();
    }
}