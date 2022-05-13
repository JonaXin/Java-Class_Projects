package stacks;

import java.util.NoSuchElementException;

public class Navigator {
    private String currentLink;
    private StackList<String> backLinks;
    private StackList<String> forwardLinks;

    public Navigator()
    {
        currentLink = "";
        backLinks = new StackList<>("backLinks");
        forwardLinks = new StackList<>("forwardLinks");
    }

    public void setCurrentLink(String linkToSet)
    {
        if (!forwardLinks.isEmpty()) {
            forwardLinks.clear();
        }

        if (backLinks.isEmpty()) {

            if (currentLink.equals(""))
                currentLink = linkToSet;

            else{
                backLinks.push(currentLink);
                currentLink = linkToSet;
            }
        }

        else{
            backLinks.push(currentLink);
            currentLink = linkToSet;
        }
    }

    public String getCurrentLink()
    {
        return currentLink;
    }

    public StackList<String> getBackLinks()
    {
        return backLinks;
    }

    public StackList<String> getForwardLinks()
    {
        return forwardLinks;
    }

    public void goBack()
    {
        try {
            forwardLinks.push(currentLink);
            currentLink = backLinks.pop();
        }

        catch(NoSuchElementException e){
            forwardLinks.pop();
            System.out.println("\nWARNING! No back links left.");
        }

    }

    public void goForward()
    {
        try {
            backLinks.push(currentLink);
            currentLink = forwardLinks.pop();
        }

        catch(NoSuchElementException e){
            backLinks.pop();
            System.out.println("\nWARNING! No forward links left.");
        }
    }
}
