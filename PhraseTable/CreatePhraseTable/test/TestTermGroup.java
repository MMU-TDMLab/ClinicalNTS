package test;

import phraseTable.Term;
import phraseTable.TermGroup;

public class TestTermGroup
{

  public static void main(String[] args) throws Exception
  {
    TermGroup group = new TermGroup("1");
    group.addTerm(new Term("1", "helloA", 100));
    group.addTerm(new Term("1", "helloB", 200));
    group.addTerm(new Term("1", "helloC", 300));
    group.addTerm(new Term("1", "helloD", 400));
    group.addTerm(new Term("1", "helloE", 500));
    group.addTerm(new Term("1", "helloE", 500));

    System.out.println(group.getMostSimpleTerm());
    
    System.out.println(group.getPairs());
  }

}
