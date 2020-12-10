import org.junit.Test;

import java.util.EmptyStackException;
import org.junit.Test;
import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;

public class StackTests
{

    @Test
    public void firstTest() {
        Stack<Integer> stack = new MyStack<Integer>();
        stack.push(1);
        assertEquals(new Integer(1), stack.pop());
    }

    @Test
    public void secondTest()
    {
        // ... TODO ...
        Stack<String> urls = new MyStack<String>();
        assertEquals(urls.empty(), true);
        for(int i =0; i<10 ; i++)
        {
            urls.push(i +".com");
            assertEquals(urls.peek(),i +".com");
        }
        assertEquals(urls.pop(),"9.com");
        assertEquals(urls.peek(),"8.com");
    }

    @Test
    public void thirdTest()
    {
        // check if the implementation behaves correctly when it isn't used properly
        Stack<String> stack = new MyStack<>();
        try
        {
            stack.pop();
        }
        catch (EmptyStackException e)
        {
            System.out.println("Operation on an empty stack");
        }
    }
    @Test
    public void fourthTest()
    {
        Stack<String> stack = new MyStack<String>();
        try{
            stack.peek();
        }
        catch(EmptyStackException e)
        {
            System.out.println("Operation on an empty stack");
        }
    }

    @Test(expected = EmptyStackException.class)
    public void fifthTest() // test peek() method badly used
    {
        Stack<Integer> stack = new MyStack<>();
        for(int i=0; i<5; i++)
        {
            stack.push(i);
        }
        for(int i =0; i<5;i++)
        {
            stack.pop();
        }
        stack.peek();
    }

    @Test(expected = EmptyStackException.class)
    public void sixthTest() // test pop() method badly used
    {
        Stack<Integer> stack = new MyStack<>();
        for(int i=0; i<5; i++)
        {
            stack.push(i);
        }
        for(int i =0; i<6;i++)
        {
            stack.pop();
        }
    }


    // Feel free to add tests in this class to debug your program



}
