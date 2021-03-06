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

public class CopyOfCommandLine extends JPanel implements KeyListener{
	
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
	
	
	public CopyOfCommandLine()
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
		//panel.add(tf);
		//panel.add(ta);
		//panel.add(ta);
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
		ta.setText("line83");
		int key = ke.getKeyCode();
	     if (key == KeyEvent.VK_ENTER) 
	     {
	    	 System.out.println("line87");
	    	ta.setText("line88");
	    	System.out.println("line89");
	    	 File wd = new File("/bin");
	    	 //using processbuilder so can retrieve the workingdir which can't from just a process
	    	 ProcessBuilder proc = null;
	    	 Process myProcess = null;
	    	 try
	    	 {
	    		// proc = new ProcessBuilder("Runtime.getRuntime().exec("/bin/bash", null, wd)",null, wd);
	    		 proc = new ProcessBuilder("/bin/bash");
	    		 proc.directory(wd);
	    		 myProcess = proc.start();
	    		// Process moveProc = Runtime.getRuntime().exec("/bin/bash", null, wd);
	    		 //myProcess = moveProc;
	    		 ta.setText("line95");
	    	 }
	    	 catch (IOException e)
	    	 {
	    		 e.printStackTrace();
	    	 }
	    	 
	    	 if (myProcess !=null)
	    	 {
	    		 BufferedReader in = new BufferedReader(new InputStreamReader(myProcess.getInputStream()));
		    	   PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(myProcess.getOutputStream())), true);
	    		//  BufferedWriter out = new BufferedWriter(proc.getOutputStream())), true);
	    		//  BufferedWriter out = new BufferedWriter(new OutputStreamWriter(proc.getOutputStream()));
		    	    ta.setText("line 106");
		    	    try
		    	    {
		    	    	//out.println(tf.getText());
		    	    //	out.println("cd ..");
			    	//  out.println("pwd");
			    	//   out.println("exit");
			    	//   out.println("pwd");
			    	 //  out.println("pwd");
			    	   out.println("ls");
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
		    	       for(int j=0; j<152; j++ )
		    	       {
		    	    	   if(in.readLine()!=null)
		    	    	   {
		    	    		   System.out.println("not null " + i++);
		    	    		   
		    	    	   }
		    	    	   else
		    	    	   {
		    	    		   System.out.println("null");
		    	    	   }
		    	    	   
		    	       }
		    	       
		    	     /*  while (in.readLine()!=null)
		    	       {
		    	    	  System.out.println(i++); 
		    	       }
		    	       System.out.println("here");
		    	       in.close();
		    	       out.close();
		    	       myProcess.destroy();
		    	     //  System.out.println(in.readLine());
		    	       //System.out.println(in.readLine());
			    	  // workDir.setText(proc.)
		    	     /*  String line = in.readLine();
		    	    while(line!=null)
		    	    {
		    	    	
		    	    	System.out.println(i++);
		    	    /*	if(in.readLine()==null)
		    	    	{
		    	    		System.out.println("edn");
		    	    	}*/
		    	     /*  String line = in.readLine();
		    	       
		    	          System.out.println(line);
		    	         ta.setText(line);
		    	          ta.append("\n" + proc.directory().toString());
		    	    }*/
		    	       
		    	    }
		    	    catch (Exception e) {
		    	       e.printStackTrace();
		    	    }
	    	 }
	    	 
	     }
	}
	
	
	    	 
	/*    	 String input = tf.getText();
	    	 tf.setText("");
	    	 ta.setText("testing");
	    	
	    	 ta.append("\n" + input);
	    	 //set working directory "wd"
	    	 File wd = new File("/bin");
	    	// System.out.println(wd);
	    	 Process proc = null;
	    	 ta.setText("line97");
	    	 try 
	    	 {
	    		 ta.setText("line100");
	    		 
	    	 //tell the OS to use bash shell, in the working direcotry wd, with a null environment variable
	    	    proc = Runtime.getRuntime().exec("/bin/bash", null, wd);
	    	 }
	    	 catch (IOException e) 
	    	 {
	    	    e.printStackTrace();
	    	 }
	    	 if (proc != null) 
	    	 {
	    		 ta.setText("line111");
	    		 //set "in" to be the inputstream from the process (ie the output to the command line)
	    		 //and set "out" to be the outputstream from the process (ie the input to the command line)
	    	 
	    	    BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
	    	    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
	    	    
	    	    out.println(input);
	    	   
	    	    try 
	    	    {
	    	    	ta.setText("line122");
	    	       String line;
	    	       while ((line = in.readLine()) != null) 
	    	       {
	    	    	  System.out.println("line 122" + line);
	    	          ta.setText(line);
	    	          
	    	          
	    	       }
	    	       ta.setText("line131");
	    	       //doesn't get to here
	    	       System.out.println("testing");
	    	       ta.setText("test");
	    	       proc.waitFor();
	    	       in.close();
	    	       out.close();
	    	       proc.destroy();
	    	    }
	    	    catch (Exception e) {
	    	       e.printStackTrace();
	    	    }
	    	 }
	    	 
	
	    	 
	     }
	}
	    	 
	    	 //Problems with this see: https://issues.apache.org/jira/browse/EXEC-45
	    	 /*
	    	 try
	    	 {
	    	 Process c = Runtime.getRuntime().exec("echo hello");
	         BufferedReader o = new BufferedReader(new InputStreamReader(c.getInputStream()));
	         ta.setText(o.readLine());
	         System.out.println(o.readLine());
	         System.out.println("done");
	    	 }
	    	 catch(IOException ioe){System.out.println("IO Thrown " +ioe);}
	    	 */
	    	 
//From http://www.webmasterworld.com/linux/3613813.htm
	    	 //start
	   /* 	 File wd = new File("/bin");
	    	 System.out.println(wd);
	    	 Process proc = null;
	    	 try {
	    	    proc = Runtime.getRuntime().exec("/bin/bash", null, wd);
	    	 }
	    	 catch (IOException e) {
	    	    e.printStackTrace();
	    	 }
	    	 if (proc != null) {
	    	    BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
	    	    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
	    	    out.println("cd ..");
	    	    out.println("pwd");
	    	    out.println("exit");
	    	    try {
	    	       String line;
	    	       while ((line = in.readLine()) != null) {
	    	          System.out.println(line);
	    	       }
	    	       proc.waitFor();
	    	       in.close();
	    	       out.close();
	    	       proc.destroy();
	    	    }
	    	    catch (Exception e) {
	    	       e.printStackTrace();
	    	    }
	    	 }
	    	 
	    	 try {
	    	    
	    		 Process c = Runtime.getRuntime().exec("ls");
	    	      BufferedReader o = new BufferedReader(new InputStreamReader(c.getInputStream()));
	    	      System.out.println(o.readLine());
	    	 }
	    	 catch (IOException e) {
	    	    e.printStackTrace();
	    	 }
	    	 //ENd
	    	
	     }*/
	    	 

	
	//HASHED OUT FOR COMPLETELY NEW CODE
	
	    	// ta.append("\n" + tf.getText());
	    	// Runtime runt = Runtime.getRuntime();
	    	//try{
	    	//	String command = tf.getText();
	    		
	    		//if(command.
	    	//	String sendCommand = command + " > test.txt";
	    		/*get command from tf
	    		 * attach >test.txt
	    		 * delete all from myScript.bat
	    		 * write command plus >test.txt to myScript.bat
	    		 * then run myScript.bat
	    		 * then read from myScript.bat to ta
	    		 */
	    	/*	try{
	    		FileWriter fw = new FileWriter("C://Users/User/Documents/uni/Dissertation/CurEclipse/myScript.bat", false);
	    		BufferedWriter bw = new BufferedWriter(fw);
	    		bw.write(sendCommand);
	    		bw.close();
	    		
	    		}
	    		catch(IOException ioe){}
	    		
	    		try{
	    			ProcessBuilder myCmdLine = new ProcessBuilder("cmd.exe");//.start();
	    			List<String> cmds = new ArrayList<String>();
	    			cmds.add("cmd.exe");
	    			cmds.add("cd..");
	    			cmds.add("> test.txt");*/
	    			//myCmdLine.command(cmds).start();
	    			//myCmdLine.command(cmds).start();
	    			
	    		//	Process returnCmdLine = myCmdLine.command(cmds).start();*/
	    			//ta.setText(.getOutputStream().toString());
	    			//ta.setText(myCmdLine.command(cmds).start().getOutputStream().toString());
	    			
	    			//Process returnCmdLine = myCmdLine;*/
	    		
	     /*
	    			File dir = new File("C://Users/User");
	    			   Runtime.getRuntime().exec("C://Users/User/Documents/uni/Dissertation/CurEclipse/myScript.bat");//, null, dir);
	    			//Runtime.getRuntime().exec("cd.. >test.txt");//sendCommand);//, null, dir);
	    			//Runtime.getRuntime().exec("cmd.exe /c" + sendCommand, null, dir);
	    			  //programme running on before test.txt is populated
	    			   //so need to get programme to wait for this to finish
	    			   //so force a wait*/
	    	/*		 try{ Thread.sleep(500);}
	    			   catch(InterruptedException ie){}
	    		}
	    		catch(IOException ioe){System.out.println("IO Thrown " +ioe);
	    		
	    		}
	    		
	    		try{
	    			ta.append(tf.getText()+ "\n");
	    			tf.setText("");
	    		FileReader fr = new FileReader("C://Users/User/test.txt");
	    		BufferedReader br = new BufferedReader(fr);
	    		String s;// = br.readLine();
	    		while((s = br.readLine())!=null)
	    		{
	    			ta.append(s+"\n");
	    		}
	    		br.close();
	    		
	    		
	    		
	    		}
	    		catch(IOException ioe){}
	    		
	    		try{
	    			//System.out.println("got here line 103 command line");
		    		FileWriter fwr = new FileWriter("C://Users/User/test.txt", false);
		    		BufferedWriter bre = new BufferedWriter(fwr);
		    		bre.write("cleared");
		    		bre.close();
		    		}
		    		catch(IOException ioe){}
	    			
	    	}*/
	    	/*	//String t = "echo dir c:\ � cmd";
	    		String[] star = {"cmd.exe", "dir /?"};
	    		Process p = runt.exec(star);//"cmd.exe dir /?");
	    				//cmd.exe");//dir /?");//dir /?");
	    		ta.setText("Null");
	    		InputStream is = p.getInputStream();
	    		BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    		
	    		StringBuilder stringBuild = new StringBuilder();
	    		String str = br.readLine();
	    		
	    		while(str !=null)
	    		{
	    			stringBuild.append(str);
	    			str=br.readLine();
	    			stringBuild.append("\n");
	    		}
	    		if(str == null)
	    		{
	    			
	    		}

	    		
	    		ta.setText(stringBuild.toString());
	    	}
	    	catch(IOException ioe){System.out.println("IO Thrown " +ioe);}
	    	
	    	 tf.setText("");
	         
	       // System.out.println("ENTER pressed");
	        }*/
		
	//}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
