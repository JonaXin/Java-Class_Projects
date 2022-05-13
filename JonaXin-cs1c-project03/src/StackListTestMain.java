package stacks;

public class StackListTestMain {
    public static void main(String[] args)
    {
        StackList<String> stack1 = new StackList<>("testStack1");
        stack1.push("www.google.com");

        stack1.push("www.foothill.edu");
        stack1.push("stem center");
        System.out.println(stack1.pop());
        stack1.push("stem center2");
        stack1.pop();
        stack1.pop();

        System.out.println("stack size is: " + stack1.size());

        System.out.println("links in the stack: " + stack1);

        if (stack1.isEmpty())
            System.out.println("stack is empty");
        else{
            System.out.println("stack isn't empty");
        }

    }

}
