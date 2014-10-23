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

public class CommandLine extends JPanel implements KeyListener, ObserverOfIOCommandObject{

	/*
	 * Problem with Process.
	 * 
	 * Process - can't retrieve / setup/ change working directory - soln use
	 * ProcessBuilder, then start Process from that - then can query
	 * ProcessBuilder for working dir, and will give - working dir of current
	 * process - can also change the working directory to pass on to the new
	 * process
	 * 
	 * Problem - once process been started, if user changes directory "cd" 1.
	 * the work dir of the Process is not changed (as this is outwith the
	 * process) 2. there is no return output from "cd" and so can't pick it up
	 * to pass to process to change work dir
	 * 
	 * Soln - need to check "pwd" of bash (which will change if "cd" used)
	 * 
	 * output is provided through process.getInputStreamhowever, this will only
	 * end (ie produce a "null" output at the end) once the processhas ended.
	 * NOT once the current "command" (ie printwriter.println - which sends the
	 * "command" the userhas typed to the process.getOutputstream, sending the
	 * command to the process, to process) has finished.
	 * 
	 * Therefore can only get a "null" back from the process.getOutputStream,
	 * once have calledout.println("exit"), which stops the process.
	 * 
	 * 23rd Oct 11amTrying to solve this using InputStreamReader "in" (which
	 * reads in the output from the command)in.ready() this says whether there's
	 * anything in the InputStreamReader buffer.command doensn't immediately
	 * populate the buffer, so have to wait 10ms before check
	 * 
	 * This works for Bring up GUI, User -> Enter, read from console, send to
	 * bash, retrieveoutput (even for something such as "dc" which requires more
	 * input from console.
	 * 
	 * But GUI still hangs.
	 * 
	 * Try 1.
	 * 
	 * Start process/processbuilder on opening Class - see if this hangs GUIthen
	 * simplyon Enterget string from tfout.println(string from tf)wait 10mswhile
	 * (in.ready()!=false){set ta = in.readLine()}
	 */

	JTextArea ta;
	JTextField tf;
	JTextField workDir;
	JScrollPane panel;
	
	IOCommandObject ioco;

	public CommandLine(IOCommandObject ioco) {
		
		this.ioco = ioco;
		ioco.registerObserverOfIOCommandObject(this);
		
		
		// super(new GridLayout(1,0));
		workDir = new JTextField(35);
		tf = new JTextField(35);
		ta = new JTextArea(30, 35);
		ta.setText("");

		tf.addKeyListener(this);
		ta.addKeyListener(this);
		workDir.addKeyListener(this);
		panel = new JScrollPane(ta);

		add(workDir);
		add(tf);
		// add(ta);
		add(panel);

		File wd = new File("/bin");
		// using processbuilder so can retrieve the workingdir which can't from
		// just a process
		ProcessBuilder proc = null;
		Process myProcess = null;

		proc = new ProcessBuilder("/bin/bash");
		proc.directory(wd);
		try {
			myProcess = proc.start();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (myProcess != null) {

			BufferedReader in = new BufferedReader(new InputStreamReader(
					myProcess.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(myProcess.getOutputStream())), true);
			BufferedReader err = new BufferedReader(new InputStreamReader(
					myProcess.getErrorStream()));
		}
	}

	public void setText(String s) {
		tf.setText(s);
	}

	public void append(String s) {
		tf.setText(tf.getText() + s);
		grabFocus();
	}

	public void grabFocus() {
		tf.grabFocus();
	}

	@Override
	public void keyPressed(KeyEvent ke) {

		int key = ke.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			
			/*
			 * Synchronise method to get and put - tf.getText variable
			 * synchronise method to get and put - in.readLine() list variable
			 * 
			 * On pressing Enter
			 * retrieve text entered in tf - string
			 * send that to THREAD
			 * THREAD - 
			 * 		out.println(string)
			 * 	
					try {
						Thread.sleep(10); // 1000 milliseconds is one second.
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
		
					while (in.ready() == true) {
						SomeString = in.readLine() 
					}
		
					String errSt;
					while ((errSt = err.readLine()) != null) {
						Same? SomeString = err.readLine
						??err.ready()!=false??
					}
			 *
			 *
			 * Then pass out SomeString to programme
			 * set ta = SomeString
			 * set tf = blank
			 * 
			 * Query - does GUI have to wait on thread doing it's thing? - I think yes, to prevent user
			 * typing in further stuff and confusing things
			 * Can I do that, probably by getting THREAD to take up priority and tie in with GUI, so that
			 * GUI can't go if that thread is doing stuff (but only the stuff needed, not the general running the process
			 * which it will be doing all the time)
			 * So ie when in.ready()!=false tie up GUI
			 * when in.ready()==false GUI works
			 */

		

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

	@Override
	public void updateFromIOCommandObject() {
		// TODO Auto-generated method stub
		
	}



	

	

}
