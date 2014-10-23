package overview;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CommandLine_15oct_1435 extends JPanel implements KeyListener{
	
	/*
	 *First thing needs to do on being opened, is "open" to a given directory 
	 *show that in command line?
	 *how to prevent user from overtyping?
	 *on enter do something (ie send to OS and return to CommandLine)
	 */
	/*
	 * Command line "opens" to directory this eclipse file is in.
	 * cd does nothing and doesn't change it, naturally, I assume because it's sending
	 * commands to command line "fresh" each time (ie with no memory of what's gone before)
	 * 
	 * command sent to test.txt (written as per needed for command line)
	 * then myScript.bat inputs that command and then runs the command.  Command line
	 * working directory is the same directory as myScript.bat is in (I assume) test this. 
	 */
	
	JTextArea ta;
	JTextField tf;
	JTextField workDir;
	JScrollPane panel;
	
	
	public CommandLine_15oct_1435()
	{
		//super(new GridLayout(1,0));
		workDir = new JTextField(35);
		tf = new JTextField(35);
		ta = new JTextArea(30,35);
		ta.setText("");
		
		tf.addKeyListener(this);
		ta.addKeyListener(this);
		workDir.addKeyListener(this);
		panel = new JScrollPane(ta);
		
		add(workDir);
		add(tf);
		//add(ta);
		add(panel);
	}
	
	public void setText(String s)
	{
		tf.setText(s);
	}
	public void append(String s)
	{
		tf.setText(tf.getText() + s);
		grabFocus();
	}
	
	public void grabFocus()
	{
		tf.grabFocus();
		}

	@Override
	public void keyPressed(KeyEvent ke) 
	{
		
		int key = ke.getKeyCode();
	     if (key == KeyEvent.VK_ENTER) 
	     {
	    	 
	    	 File wd = new File("/bin");
	    	 //using processbuilder so can retrieve the workingdir which can't from just a process
	    	 ProcessBuilder proc = null;
	    	 Process myProcess = null;
	    	 try
	    	 {
	    		
	    		 proc = new ProcessBuilder("/bin/bash");
	    		 proc.directory(wd);
	    		 myProcess = proc.start();
	    		 
	    		 if (myProcess !=null)
		    	 {
		    		 BufferedReader in = new BufferedReader(new InputStreamReader(myProcess.getInputStream()));
			    	   PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(myProcess.getOutputStream())), true);
		    		
			    	    try
			    	    {
			    	    	
			    	    //	out.println("cd ..");
				    	//  out.println("pwd");
				    	//   out.println("exit");
				    	//   out.println("pwd");
				    	 //  out.println("pwd");
				    	   out.println("ls");
				    	   out.println("pwd");
				    	   out.println("exit");
			    	       int i=0;
			    	       String line;
			    	       //http://stackoverflow.com/questions/3643939/java-process-with-input-output-stream
			    	       //problem appears to be that in.readLine will not return null, but is waiting for more
			    	       //input from out, as process is still running.
			    	       //one soln, would be for "line" to equal "" to stop the while loop
			    	       //but this causes problems if a return does return a blank line........
			    	       //I can't exit the process after doing each bit, as then it won't retain its position
			    	       //or I could exit the process and pass the work directory across, whilst retaining the output
			    	       //myself............
			    	       for(int j=0; j<155; j++ )
			    	       {
			    	    	   if((line=in.readLine())!=null)
			    	    	   {
			    	    		  // System.out.println("not null " + i++);
			    	    		   System.out.println(line);
			    	    	   }
			    	    	   else
			    	    	   {
			    	    		   System.out.println("null");
			    	    	   }
			    	    	   
			    	       }
			    	       
			    	     
			    	       
			    	    }
			    	    catch (Exception e) {
			    	       e.printStackTrace();
			    	    }
		    	 }
	    		
	    		 
	    	 }
	    	 catch (IOException e)
	    	 {
	    		 e.printStackTrace();
	    	 }
	    	 
	    	 
	    	 
	     }
	}
	
	
	    	 
	

	   
	
	
	    	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
