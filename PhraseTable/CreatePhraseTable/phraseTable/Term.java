package phraseTable;

public class Term
{
  private String conceptID;
  private String term;
  private long   frequency;

  public Term(String conceptID, String term, long frequency)
  {
    super();
    this.conceptID = conceptID;
    this.term = term;
    this.frequency = frequency;
  }

  public String getConceptID()
  {
    return conceptID;
  }

  public void setConceptID(String conceptID)
  {
    this.conceptID = conceptID;
  }

  public String getTerm()
  {
    return term;
  }

  public void setTerm(String term)
  {
    this.term = term;
  }

  public long getFrequency()
  {
    return frequency;
  }

  public void setFrequency(int frequency)
  {
    this.frequency = frequency;
  }

  public String toString()
  {
    return "(" + conceptID + ", " + term + ", " + frequency + ")";
  }

  public boolean equals(Object anObject)
  {
    Term otherTerm = (Term) anObject;
        
    return this.conceptID.equals(otherTerm.conceptID)
        && this.term.equals(otherTerm.term);
  }
  
  public int hashCode()
  {
    return 37 * conceptID.hashCode() + 53 * term.hashCode() + 91 * (int)frequency;
  }

}
