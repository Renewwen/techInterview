import java.util.Stack;

public class Solution {

    // Stack: FIFO; Queue: FILO
    // [1,2,3,4]
    // stack1: 每次push的时候，直接放入到stack1中
    // stack2: 每次pop的时候，case 1: stack2不为空，那就直接pop stack2
    //                      case 2: stack2为空，则将stack1中全部倒入stack2中之后，再pop
    // queue的实现形式: queue.pop() <--[stack2 --->][<--- stack1]<-- queue.push()

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            pushAllNodesFromStack1ToStack2();
        }
        return stack2.pop();
    }

    private void pushAllNodesFromStack1ToStack2() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }
}
