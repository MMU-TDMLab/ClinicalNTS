package phraseTable;

public class TermPair
{
  private Term complexTerm;
  private Term simpleTerm;

  public TermPair(Term complexTerm, Term simpleTerm)
  {
    this.complexTerm = complexTerm;
    this.simpleTerm = simpleTerm;
  }

  public Term getTerm1()
  {
    return complexTerm;
  }

  public void setTerm1(Term term1)
  {
    this.complexTerm = term1;
  }

  public Term getTerm2()
  {
    return simpleTerm;
  }

  public void setTerm2(Term term2)
  {
    this.simpleTerm = term2;
  }
  
  public String toString()
  {
    return complexTerm.getTerm() + "|||" +  simpleTerm.getTerm();
  }
  
}
