package phraseTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class GetTerms
{

  private static HashMap<String, Long> frequencies;

  public static void main(String[] args) throws Exception
  {
    
    //change to the directory where SNOMED has been stored
    String snomedDirectory = "";
    String termsFilePath = snomedDirectory
        + "SnomedCT_InternationalRF2_PRODUCTION_20180731T120000Z/Full/"
        + "Terminology/sct2_Description_Full-en_INT_20180731.txt";

    //change to a copy of the Google web 1T unigram frequencies (or other frequencies)
    String frequencyFilePath = "";
    String outFile = "phraseTable.txt";

    // read in frequencies
    frequencies = new HashMap<String, Long>();
    BufferedReader frequenciesReader =
        new BufferedReader(new FileReader(frequencyFilePath));
    String line;
    while ((line = frequenciesReader.readLine()) != null)
    {
      String[] splitLine = line.split("\t");
      splitLine[0] = splitLine[0].toLowerCase();
      if (splitLine[0].matches("[a-z]*"))
      {
        Long frequency = frequencies.get(splitLine[0]);
        if (frequency == null)
          frequency = 0l;
        frequencies.put(splitLine[0], frequency + Long.parseLong(splitLine[1]));
      }
    }
    frequenciesReader.close();

    // read in terms + process
    HashMap<String, TermGroup> termGroups = new HashMap<String, TermGroup>();

    BufferedReader termsReader =
        new BufferedReader(new FileReader(termsFilePath));
    termsReader.readLine();
    while ((line = termsReader.readLine()) != null)
    {
      String[] splitLine = line.split("\t");
      String conceptID = splitLine[4];
      String termString = splitLine[7];
      termString = validateTermString(termString);
      if (termString != null)
      {
        Long frequency = getFrequency(termString);
        Term term = new Term(conceptID, termString, frequency);
        TermGroup termGroup = termGroups.get(conceptID);
        if (termGroup == null)
        {
          termGroup = new TermGroup(conceptID);
          termGroups.put(conceptID, termGroup);
        }
        termGroup.addTerm(term);
      }
    }
    termsReader.close();

    // go through term groups
    List<TermPair> termPairs = new ArrayList<TermPair>();
    for (TermGroup termGroup : termGroups.values())
    {
      int groupSize = termGroup.size();
      if (groupSize >= 2)
      {
        termPairs.addAll(termGroup.getPairs());
      }
    }

    // final pass through the list to check for duplicates.
    // acronyms with duplicates are discarded, as this indicates polysemy.
    // terms with duplicates are filtered to select only the replacement with
    // the highest frequency (assuming no polysemy)
    HashMap<String, List<String>> termToAllTerms =
        new HashMap<String, List<String>>();
    for (TermPair pair : termPairs)
    {
      List<String> simpleWords = termToAllTerms.get(pair.getTerm1().getTerm());
      if (simpleWords == null)
        simpleWords = new ArrayList<String>();
      simpleWords.add(pair.getTerm2().getTerm());
      termToAllTerms.put(pair.getTerm1().getTerm(), simpleWords);
    }

    HashMap<String, String> termToSingleTerm = new HashMap<String, String>();
    for (Entry<String, List<String>> entry : termToAllTerms.entrySet())
    {
      String complexTerm = entry.getKey();
      List<String> simpleTerms = entry.getValue();

      // check if there is more than one possible replacement.
      // otherwise, select most frequent replacement (as long as its not an
      // acronym, where we want to avoid polysemy...).
      if (simpleTerms.size() == 1)
      {
        termToSingleTerm.put(complexTerm, simpleTerms.get(0));
      } else if (!complexTerm.matches("[A-Z]*"))
      {
        // if not then get the simplest term from the list and store
        String simpleTerm = simpleTerms.get(0);
        Long simpleFrequency = getFrequency(simpleTerm);
        for (String term : simpleTerms)
        {
          Long termFrequency = getFrequency(term);
          if (termFrequency > simpleFrequency)
          {
            simpleTerm = term;
            simpleFrequency = termFrequency;
          }
        }
        termToSingleTerm.put(complexTerm, simpleTerm);
      }
    }

    // write out list
    PrintWriter out = new PrintWriter(outFile);
    for (Entry<String, String> entry : termToSingleTerm.entrySet())
      out.println(entry.getKey() + "|||" + entry.getValue());
    out.close();

  }

  // validates a term and returns either valid term, or null if invalid.
  // Also converts String to lower case
  // - identifies abbreviations and removes definitions
  // - filters terms containing punctuation
  // - filters long terms (4 or more words)
  public static String validateTermString(String termString)
  {
    // check for abbreviations
    if (termString.matches("[A-Z]*\\s-\\s[A-Za-z ]*"))
    {
      termString = termString.split(" - ")[0];
    } else
    {
      termString = termString.toLowerCase();
    }

    // remove punctuation
    if (!termString.matches("[A-Za-z ]*"))
      termString = null;
    // remove terms with lots of words
    else if (termString.split(" ").length > 4)
      termString = null;

    return termString;
  }

  // gets frequency of a term, handling MWEs by taking the average of all words
  public static Long getFrequency(String term)
  {
    Long frequency = 0l;
    if (term.contains(" "))
    {
      String[] splitTerm = term.split(" ");
      for (String subterm : splitTerm)
      {
        Long frequencyToAdd = frequencies.get(subterm);
        if (frequencyToAdd == null)
          frequencyToAdd = 0l;
        frequency += frequencyToAdd;
      }
      frequency /= splitTerm.length;
    } else
    {
      frequency = frequencies.get(term);
      if (frequency == null)
        frequency = 0l;
    }
    return frequency;
  }

}
