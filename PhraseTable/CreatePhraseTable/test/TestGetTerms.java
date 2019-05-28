package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class TestGetTerms
{
  private static HashMap<String, Long> frequencies;

  public static void main(String[] args) throws Exception
  {
    String frequencyFilePath =
        "/Users/matthew/Documents/AugmentedReading/1gms/vocab";

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
    
    System.out.println(frequencies.get("hypertension"));
    System.out.println(frequencies.get("high"));
    System.out.println(frequencies.get("blood"));
    System.out.println(frequencies.get("pressure"));

    System.out.println(getFrequency("hypertension"));
    System.out.println(getFrequency("high blood pressure"));

    // validate term string
    System.out.println(validateTermString("hello"));
    System.out.println(validateTermString("Hello"));
    System.out.println(validateTermString("HE - Hello"));
    System.out.println(validateTermString("HE.llo"));
    System.out.println(validateTermString("hello hello"));
    System.out.println(validateTermString("hello hello hello"));
    System.out.println(validateTermString("hello hello hello hello"));
    System.out.println(validateTermString("hello hello hello hello hello"));
    System.out
        .println(validateTermString("hello hello hello hello hello hello"));
    

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
