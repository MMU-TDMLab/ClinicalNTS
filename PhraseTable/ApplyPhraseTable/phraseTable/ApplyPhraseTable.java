package phraseTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class ApplyPhraseTable
{
  public static void main(String [] args) throws Exception
  {
    PhraseTable phraseTable = new PhraseTable();
    
    //the directory of files to apply the phrase table to
    String originalDirectory = "";
    
    //the directory to store the results
    String outputDirectory = "";
    
    File originalDirectoryFile = new File(originalDirectory);
    
    File [] files = originalDirectoryFile.listFiles();
    
    int counter = 0;
    
    for(File file : files)
    {
      System.out.println(++counter + "/" + files.length);
      BufferedReader in = new BufferedReader(new FileReader(file));
      PrintWriter out = new PrintWriter(outputDirectory + file.getName());

      String line;
      while((line = in.readLine()) != null)
        out.println(phraseTable.applyPhraseTable(line));
      
      in.close();
      out.close();
    }//for file    
  }
}
