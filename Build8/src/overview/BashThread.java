package overview;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class BashThread implements Runnable {

	IOCommandObject ioco;
	Boolean threadStart = true;
	ProcessBuilder proc = null;
	Process myProcess = null;
	BufferedReader in;
	PrintWriter out;
	BufferedReader err;

	public BashThread(IOCommandObject ioco) {
		// needs all the process starting stuff
		this.ioco = ioco;

		// while (threadStart = true) {
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

		try {
			in.readLine();
		} catch (Exception e) {
		}

	}

}
