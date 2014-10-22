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
import java.util.Date;
import java.util.List;

public class CommandLine extends JPanel implements KeyListener{
	
	/*
	 * Problem with Process.
	 * 
	 * Process - can't retrieve / setup/ change working directory 
	 * - soln use ProcessBuilder, then start Process from that
	 * - then can query ProcessBuilder for working dir, and will give
	 * - working dir of current process
	 * - can also change the working directory to pass on to the new process
	 * 
	 * Problem - once process been started, if user changes directory "cd"
	 * 1. the work dir of the Process is not changed (as this is outwith the process)
	 * 2. there is no return output from "cd" and so can't pick it up to pass to
	 * 		process to change work dir
	 *
	 *Soln - need to check "pwd" of bash (which will change if "cd" used)
	 *
	 *output is provided through process.getInputStream
	 *however, this will only end (ie produce a "null" output at the end) once the process
	 *has ended.  NOT once the current "command" (ie printwriter.println - which sends the "command" the user
	 *has typed to the process.getOutputstream, sending the command to the process, to process) has finished.
	 *
	 *Therefore can only get a "null" back from the process.getOutputStream, once have called 
	 *out.println("exit"), which stops the process.
	 *
	 *However, this 
	 */
	
	JTextArea ta;
	JTextField tf;
	JTextField workDir;
	JScrollPane panel;
	
	
	public CommandLine()
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
	    	 ta.setText("now");
	    	 try
	    	 {
	    		ta.setText("instide first try");
	    		 proc = new ProcessBuilder("/bin/bash");
	    		 proc.directory(wd);
	    		 myProcess = proc.start();
	    		 
	    		 
	    		 if (myProcess !=null)
		    	 {
	    			 
	    			 
		    		 BufferedReader in = new BufferedReader(new InputStreamReader(myProcess.getInputStream()));
			    	   PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(myProcess.getOutputStream())), true);
			    	   BufferedReader err = new BufferedReader(new InputStreamReader(myProcess.getErrorStream()));
			    	   
			    	   Date myDate;
			    	   Date consolDate;
			    	   Date commandDate;
		    		
			    	    try
			    	    {
			    	    	ta.append("insdie second try");
			    	    	System.out.println("testing");
			    	    	String command = tf.getText();
			    	    	tf.setText("");
//###### ta.setText will not
//action until the WHOLE of this try (perhaps more) is finished.
//therefore because something such as "dc" hangs the in.readLine() this then hangs the GUI, the textarea never populated, and
//can't get user to add in details....			    	    	
			    	    	//ta.setText(command + (myDate = new Date()).getTime());
			    	    	System.out.println("line132 " + " Console date " + (consolDate = new Date()).getTime() + "  tarea date " + (myDate = new Date()).getTime());
			    	    	int i = 0;
			    	    	while(i!=857433)
			    	    	{System.out.println(i);
			    	    	i++;}
			    	    	//out.println(command);// + "  command date " + (consDate = new Date()).getTime());
			    	    	System.out.println("12");
			    	    	System.out.println("command date " + (commandDate = new Date()).getTime());
			    	    	//out.println("dc");
			    	       out.println("2");
			    	       out.println("f");
			    	       
			    	    	out.println("pwd");
			    	       System.out.println("line129");
				    	   out.println("pwd");
				    	   
				    	   out.println("exit");
				    	   tf.setText("");
				    	   
				    	   
			    	       i=0;
			    	       String line;
			    	       
			    	      
			    	       
				    	       while((line=in.readLine())!=null)
				    	       {
				    	    	   
				    	    		   //System.out.println(line);
				    	    	   //ta.append("test");
				    	    	 publish( ta.append(line + "\n"));
				    	    	   System.out.println(line);
				    	    	   System.out.println(i++);
				    	    		   
				    	    		   
				    	    		   
				    	       }
				    	       //?? check where to place this, as may be better within while loop
				    	       String errSt;
				    	       while((errSt=err.readLine())!=null)
				    	       {
				    	    	   //System.out.println(errSt);
				    	    	//   ta.append(errSt+ "\n");
				    	       }
				    	    	   
				    	    	   
				    	       
			    	       
			    	     
			    	       
			    	    }
			    	    catch (Exception e) 
			    	    {
			    	    	System.out.println("line179");
			    	       e.printStackTrace();
			    	    }
		    	 }
	    		
	    		 
	    	 }
	    	 catch (IOException e)
	    	 {
	    		 System.out.println("line188");
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
