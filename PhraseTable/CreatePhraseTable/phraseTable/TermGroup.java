package phraseTable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TermGroup
{
  private String     conceptID;
  private Set<Term> terms;

  public TermGroup(String conceptID)
  {
    this.conceptID = conceptID;
    terms = new HashSet<Term>();
  }

  public void addTerm(Term term) throws Exception
  {
    if(! term.getConceptID().equals(conceptID))
      throw new Exception("trying to add Term: " + term + " to group with id: " + conceptID);
        
    if(! terms.contains(term))
      terms.add(term);
  }

  public Iterator<Term> getTermIterator()
  {
    return terms.iterator();
  }

  public String getConceptID()
  {
    return conceptID;
  }
  
  public int size()
  {
    return terms.size();
  }
  
  public List<TermPair> getPairs()
  {
    List<TermPair> termPairs = new ArrayList<TermPair>();
    
    Term simpleTerm = getMostSimpleTerm();
    
    for(Term term : terms)
    {
      if(term != simpleTerm)
      {
        termPairs.add(new TermPair(term, simpleTerm));
      }
    }
    
    return termPairs;
  }
  
  public Term getMostSimpleTerm()
  {
    Term simpleTerm = terms.iterator().next();
    for(Term term : terms)
      if(term.getFrequency() > simpleTerm.getFrequency())
        simpleTerm = term;
    return simpleTerm;
  }
}
