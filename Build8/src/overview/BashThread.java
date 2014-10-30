package overview;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class BashThread implements Runnable {

	IOCommandObject ioco;
	ProcessBuilder proc = null;
	Process myProcess = null;
	BufferedReader in;
	PrintWriter out;
	BufferedReader err;
	String cmd;
	String output;
	String errSt;
	String workDir;
	Boolean stop = false;

	// #### Question - change working directory, once up and running, away from
	// bin/bash
	// at moment yes - change to C - query in future, set up so that user can
	// set up and
	// it is remembered and can be changed. That requires files to be saved etc.

	public BashThread(IOCommandObject ioco) {

		// pass in the shared object between BashThread and CommandLine
		this.ioco = ioco;
		// needs all the process starting stuff

		File wd = new File("/bin");
		// using processbuilder so can retrieve the workingdir which can't
		// from
		// just a process

		proc = new ProcessBuilder("/bin/bash");
		proc.directory(wd);
		try {
			myProcess = proc.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (myProcess != null) {

			in = new BufferedReader(new InputStreamReader(
					myProcess.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					myProcess.getOutputStream())), true);
			err = new BufferedReader(new InputStreamReader(
					myProcess.getErrorStream()));

		}

	}

	@Override
	public void run() {

		while (!stop) {
			System.out.println("start of run bashthread");
			cmd = ioco.getCmd();
			System.out.println("BashThread cmd " + cmd);
			out.println(cmd);

			// wait until cmd has sent something to the buffer
			// make busy wait - ???? not sure about this, as only want one wait
			// at
			// start??

			try {
				Thread.sleep(10); // 1000 milliseconds is one second.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			try {
				if (in.ready() == true) {

					while ((output = in.readLine()) != null)

					{
						System.out.println("BashThread output " + output);
						ioco.putOutput(output);

						if (in.ready() == false) {

							break;
						}

					}
				}
				if (err.ready() == true) {
					while ((output = err.readLine()) != null) {
						ioco.putOutput(output);

						if (err.ready() == false) {
							break;
						}

					}
				}
			/*	try{
				System.out.println("myproc exitvalue  " + myProcess.exitValue());}
				catch(IllegalThreadStateException itse){System.out.println ("not exited " + itse.getMessage());}
				System.out.println("bashthread 109");
				//keep working directory updated
				out.println("pwd");
				workDir = in.readLine();
				ioco.setWorkDir(workDir);*/

			} catch (Exception e) {
			}
		}
	}

}
