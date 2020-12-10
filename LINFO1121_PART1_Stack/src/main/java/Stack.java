import java.util.EmptyStackException;

public interface Stack<E> {

    public boolean empty();

    public E peek() throws EmptyStackException;

    public E pop() throws EmptyStackException;

    public void push(E item);

}