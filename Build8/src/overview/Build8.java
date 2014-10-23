package overview;


/*
Build5_2

2 browsers and one command line
build observes (ObserverFile/SubjectFile) Browser - bro1 and bro2
build observes (ObserverTime/SubjectTime) Browser - bro1 and bro2
implements a system to allow double click to be implemented, whilst ignoring the single click fired at the same time (using ObserverTime)
L + mspd single click (TreeSelectionListener) - highlights and selects file/directory at
L + mspd double click (TreeSelectionListener) - opens/closes file/directory at given node and populates next node
R single click - highlights and selects node, brings up popup menu
R double click - selects node and highlights and dumps file name into command line

Done 7th Aug 2014


To do: 2. own build menu to come up on hover over dir/file?
need to get it refreshing properly, as when a removable drive removed from computer, it won't refresh properly.





--------------------------Gummphy notes to keep-----------------------------------------------------
Terms: FileVisitor FileTree, walking the filetree, visitor pattern
File Browser GUI
FileBro
JTree / ModelTree
youtube - java swing tutorial JTree
oracle documentation How to use trees

Tree nodes:
setup the very top node (here = "top")
then add nodes to it
can add nodes to those nodes
I will need to run through filesystem and count the number of directories/files etc
Then add that number of different nodes
Then add the directories/files etc to them auto

Listing a Directory's Contents

You can list all the contents of a directory by using the newDirectoryStream(Path) method. This method returns an object that implements the DirectoryStream interface. The class that implements the DirectoryStream interface also implements Iterable, so you can iterate through the directory stream, reading all of the objects. This approach scales well to very large directories.

Remember: The returned DirectoryStream is a stream. If you are not using a try-with-resources statement, don't forget to close the stream in the finally block. The try-with-resources statement takes care of this for you.
The following code snippet shows how to print the contents of a directory:

Path dir = ...;
try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
    for (Path file: stream) {

    }
} catch (IOException | DirectoryIteratorException x) {
    // IOException can never be thrown by the iteration.
    // In this snippet, it can only be thrown by newDirectoryStream.
    System.err.println(x);
}


 //------------------end of gumphy notes-------------------------------------*/
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Build8 extends JPanel implements ObserverFile, ActionListener//, ObserverTime
{

	//------------------------------------GUI graphics--------------------------------------

	JTextArea ta;
	CommandLine cl;
	Browser broBot, broTop;
	JButton favourites;

	public  Build8()
	{

		//-------------------------- layout --------------------------------------------------------

		super(new GridLayout(0,3));

		ta = new JTextArea(50,50);
		ta.setText("testing");
		cl = new CommandLine();

		broBot = new Browser("Browser Bottom");
		broTop = new Browser("Browser Top");
		
		favourites = new JButton("Add favourites");
		favourites.addActionListener(this);

		//add(ta);
		add(cl);
		add(broBot);
		add(broTop);
	//	add(favourites);
		
		
		
		



		//-------------------------Observer Patterns (ObserverFile)----------------------------------
		//-------------------------registering Build as an observer for both files and time on the Subjects (Browser and Textarea)---------

		broBot.registerObserverFile(this);
		broTop.registerObserverFile(this);
		//once textarea is a class register "this" (ie Build) as an observer on textarea

	}	

	//----------------------CREATE AND SHOW GUI method to be called when a new instance of Build is created-----------------
	public void createAndShowGUI()
	{

		//Create and set up the window.
		JFrame frame = new JFrame("Tali File Browser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add content to the window.
		frame.add(new Build8());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	//-----------------------Observer Pattern update methods different depending which subject coming in from--------------

//?? this puts cursor at start if just a file name, and at the end if a menuitem + filename??
	public void updateBrowserFile(Browser brows)
	{
		//cl.grabFocus();
		//ta.setText(brows.getMenuItem() + " " + brows.getCurFile().getName());
		cl.append(brows.getMenuItem() + " " + brows.getCurFile().getName());
		brows.resetCurMenuItem();
		
       // System.out.println(curMenuItem);
	}
	


	public void updateCommandLineFile()
	{

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Favourites fav = Favourites.getInstance();
		fav.addList("menuitem");
		fav.addList("annother menuitem");
		
		Favourites newFav = Favourites.getInstance();
		ArrayList<String> aList = fav.getList();
		
		for(String al : aList)
		{
			System.out.println("singleton " + al);
		}
		
		for(String s : fav.getList()){
			System.out.println("first singleton " + s);
		}
		
	}




}