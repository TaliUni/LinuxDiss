import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class testProcess
{  public static void main(String [] argv) throws IOException
   {  Process c = Runtime.getRuntime().exec("echo hello");
      BufferedReader o = new BufferedReader(new InputStreamReader(c.getInputStream()));
      System.out.println(o.readLine());
      try
      {
    	  System.out.println(c.exitValue());
      }
      catch(IllegalThreadStateException itse)
      {
    	  System.out.println("illegal");
      }
   }
}

