import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class GetDataFromMIMIC
{

  public static void main(String[] args) throws Exception
  {
    // edit directory to be a folder which contains the MIMIC data in the folder 'raw', output will go to the same directory in a subdirectory called 'records'
    String directory = "";
    String outPath = directory + "/records/";
    String rawDataPath = directory + "/raw/";
    File rawFolder = new File(rawDataPath);

    File[] files = rawFolder.listFiles();
    Arrays.sort(files);

    for (File file : files)
    {
      System.out.println(file);
      if (file.length() != 0)
      {
        String fileID = file.getName().split(".txt")[0];
        BufferedReader in = new BufferedReader(new FileReader(file));

        // header
        in.readLine();

        String fileLines = "";
        String line;
        int quoteCount = 0;
        int counter = 0;
        while ((line = in.readLine()) != null)
        {
          fileLines += line + " ";

          for (int i = 0; i < line.length(); i++)
            if (line.charAt(i) == '"')
              quoteCount++;

          if (quoteCount % 2 == 0)
          {
            String[] splitLine =
                fileLines.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            String printOut = splitLine[11];
            printOut = printOut.replace("_", "");
            printOut = printOut.substring(1, printOut.length() - 1);
            printOut = printOut.trim();

            if (printOut.split("[ \n\t]").length > 10)
            {
              PrintWriter out =
                  new PrintWriter(outPath + fileID + "-" + counter + ".txt");
              out.println(printOut);
              out.close();
              counter++;
            }

            quoteCount = 0;
            fileLines = "";
          }
        }
        in.close();
      }
    }
  }

}
