import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;

public class FilterFiles
{

  public static void main(String[] args) throws Exception
  {
    System.out.println("listing files");
    String directory = "";
    String path = directory + "/records/";

    File folder = new File(path);
    File[] files = folder.listFiles();

    System.out.println("sorting files");
    Arrays.parallelSort(files, new Comparator<File>()
    {
      @Override
      public int compare(File f1, File f2)
      {
        return (int) (f2.length() - f1.length());
      }
    });

    System.out.println("writing files");
    for (int i = 0; i < 150; i++)
      Files.copy(files[i].toPath(), new FileOutputStream(
          directory + "/records_filtered/" + files[i].getName()));
  }

}
