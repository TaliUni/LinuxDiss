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

public class CL_23Oct_10_56WorkingVersion extends JPanel implements KeyListener{
	
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
	 *23rd Oct 11am
	 *Trying to solve this using InputStreamReader "in" (which reads in the output from the command)
	 *in.ready() this says whether there's anything in the InputStreamReader buffer.
	 *command doensn't immediately populate the buffer, so have to wait 10ms before check
	 *
	 *This works for Bring up GUI, User -> Enter, read from console, send to bash, retrieve
	 *output (even for something such as "dc" which requires more input from console.
	 *
	 *But GUI still hangs.
	 *
	 *CommandLine class attempting to resolve this.
	 *
	 *This version has working from comand line, with hanging GUI
	 */
	
	JTextArea ta;
	JTextField tf;
	JTextField workDir;
	JScrollPane panel;
	
	
	public CL_23Oct_10_56WorkingVersion()
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
			    	    //	ta.append("insdie second try");
			    	    	//System.out.println("testing");
			    	    	//String command = tf.getText();
			    	    	//tf.setText("");
//###### ta.setText will not
//action until the WHOLE of this try (perhaps more) is finished.
//therefore because something such as "dc" hangs the in.readLine() this then hangs the GUI, the textarea never populated, and
//can't get user to add in details....			    	    	
			    	    	//ta.setText(command + (myDate = new Date()).getTime());
			    	    	//System.out.println("line132 " + " Console date " + (consolDate = new Date()).getTime() + "  tarea date " + (myDate = new Date()).getTime());
			    	    	//int i = 0;
			    	    	/*while(i!=857433)
			    	    	{System.out.println(i);
			    	    	i++;}*/
			    	    	//out.println(command);// + "  command date " + (consDate = new Date()).getTime());
			    	    //	System.out.println("12");
			    	    	//System.out.println("command date " + (commandDate = new Date()).getTime());
			    	  //  	out.println("ls");
			    	    //	out.println("pwd");
			    	  //  	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			    	    //	String s = br.readLine();
			    	   // 	System.out.println("read in stuff " + s);
			    	    	
			    	    //	System.out.println("line 154");
			    	    //	out.println(s);
			    	    /*	out.println("dc");
			    	    	out.println("2");
			    	      // out.println(s);
			    	       out.println("f");
			    	       out.println("3");
			    	       out.println("f");
			    	       System.out.println("line 158");
			    	       out.println("q");
			    	       System.out.println("line 159");
			    	       out.println("ls");
			    	       System.out.println("line 160");
			    	    //   out.println("q");
			    	      // out.println("ls");*/
			    	       
			    	    //	out.println("pwd");
			    	      // System.out.println("line129");
				    	   //out.println("pwd");
				    	   
				    	   //out.println("exit");
				    	 //  tf.setText("");
				    	   
				    	   
			    	       int i=0;
			    	       String line;
			    	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			    	       String s;
			    	       
//SET up an infinite loop, so once started, just keeps going
//will need to close, when other stuff going on, such as using the file browser
//not sure how will set up without an infinite loop
//but probably just waiting for input from user, rather than on an infinite loop, so
//set up on enter, if it's on a waiting for input, then it'l just output an error
//as usual, and so user will see that.
// add in pwd once in.ready() == false as well			    	       
			    	      while(i ==0)
			    	      {
			    	    	  System.out.println("start of first while");
				    	    	s = br.readLine();
				    	    	out.println(s);
				    	    	System.out.println("println out s " + s);
			    	       
//doesnt set up in.ready() straight away
//perhaps need to wait for a few mms??
				    	    	
//with this code, I can now get ls to produce output (on second command - ie ls <enter> then say dc <enter> outputs ls
//and can get dc to work and quit and then use ls again after
//but have the same proble with the wait between

//either this is that in.ready() has not had time to populate in which case stick a pause in
//or in.ready() does not populate straight away
				    	    	
//this is because in.ready() has not yet populated.				    	    	
				    	    	
				    	    	try {
				    	    	    Thread.sleep(10);                 //1000 milliseconds is one second.
				    	    	} catch(InterruptedException ex) {
				    	    	    Thread.currentThread().interrupt();
				    	    	}
				    	    	
				    	    	if(in.ready()==true)
				    	    	{
				    	    		System.out.println("into ready == true");
					    	       while((line=in.readLine())!=null)
					    	    	//if((line=in.readLine())==null)
					    	       {
					    	    	   System.out.println("into second while");
					    	    		   //System.out.println(line);
					    	    	   //ta.append("test");
					    	    	// publish( ta.append(line + "\n"));
					    	    	   System.out.println(line);
					    	    	 //  System.out.println(i++);
//#####THIS LOOKS LIKE WHAT NEED
//I think tHE BREAK HERE IS BREAKING BOTH WHILE LOOOPS
//AND EVEN WITH BREAK THE GUI STILL HANGING				    	    	   
				    	    	   if(in.ready()==false)
				    	    	  {
				    	    		  System.out.println("break");
				    	    		  break;
				    	    	  }
				    	    	   
					    	       }
				    	    	}
				    	    	else{
				    	    	
				    	    		System.out.println("got here");
				    	    	}
				    	       
			    	      }
			    	      System.out.println("out of while");
				    	 /*      if(line==null)
				    	       {
				    	    	   System.out.println("output = null");
				    	       }*/
				    	       //?? check where to place this, as may be better within while loop
				    	       String errSt;
				    	       while((errSt=err.readLine())!=null)
				    	       {
				    	    	   System.out.println(errSt);
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
