package phraseTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhraseTable
{
  private Map<String, String> phraseTable;

  public PhraseTable() throws Exception
  {
    // load Phrase Table
    phraseTable = new HashMap<String, String>();
    
    //the phraseTable.txt file produced by createPhraseTable
    String phraseTableFile = "phraseTable.txt";
    BufferedReader reader = new BufferedReader(new FileReader(phraseTableFile));
    String line = "";
    while ((line = reader.readLine()) != null)
    {
      String[] splitLine = line.split("\\|\\|\\|");
      phraseTable.put(splitLine[0], splitLine[1]);
    }
    reader.close();
  }

  public String applyPhraseTable(String input)
  {
    StringBuilder stringBuilder = new StringBuilder(input);
    for (String phrase : phraseTable.keySet())
    {      
      Pattern pattern = Pattern.compile(phrase);
      Matcher matcher = pattern.matcher(stringBuilder.toString());
      while(matcher.find())
      {
        int begin = matcher.start();
        int end = matcher.end();
        
        boolean wholeWord = true;
        
        if(begin > 0 && stringBuilder.charAt(begin - 1) != ' ')
          wholeWord = false;
        
        if(end < stringBuilder.length() - 1 && stringBuilder.charAt(end) != ' ')
          wholeWord = false;

        if(wholeWord)
          stringBuilder.replace(begin, end, phraseTable.get(phrase));
      }
    }
    return stringBuilder.toString();
  }
}
