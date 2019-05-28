In these files you will find all of the code needed to replicate the experiments undertaken as part of the paper: "Neural Text Simplification of Clinical Letters with a Domain Specific Phrase Table". In addition to the resources supplied here, you will need to access the following resources:

 - The i2b2 dataset 1B: https://www.i2b2.org/NLP/DataSets/Download.php
 - The MIMIC-III dataset: https://mimic.physionet.org/gettingstarted/access/
 - SNOMED-CT (via the UMLS: https://uts.nlm.nih.gov//license.html
 - The Google Web 1T frequencies: https://catalog.ldc.upenn.edu/LDC2006T13
 - The NTS system: https://github.com/senisioi/NeuralTextSimplification

Once you have these resources licenced, downloaded and installed, you can proceed to replicate our experiments by following the steps below:

 - run the code in the data collection folder to generate the documents that we selected
 - run the code in the PhraseTable/CreatePhraseTable folder to create the phrase table from SNOMED-CT
 - run the NTS software using the following command to simplify documents using our phrase table:

th translate.lua -replace_unk -phrase_table <PATH_TO_PHRASE_TABLE> -beam_size 5 -gpuid 0 -n_best 4 -model <PATH_TO_MODEL> -src <ORIGINAL_DOCUMENT> -output <SIMPLIFIED_DOCUMENT> -log_file <LOG_FILE>

You can also replicate the phrase table baseline by running the code in the folder: PhraseTable/ApplyPhraseTable


A full listing of files, with descriptions is below:

- Supplementary Material/
  - ReadMe.txt - this readme
  - CrowdSourcing/
    - FigureEightTask.txt - The task description as it appeared to annotators
    - results.csv.zip - The full results of our crowdsourcing task
  - DataCollection/
    - MIMIC/
      - GetDataFromMIMIC.java - processes the original MIMIC files to get the sub-records
      - FilterFiles.java - selects the 150 sub-records with the most tokens
    - i2b2/
      - GetDataFromi2b2.java - extracts and filters records from i2b2
      - Record.java - internal data class
  - PhraseTable/
    - ApplyPhraseTable/
      - phraseTable/
        - ApplyPhraseTable.java - Applies the phrase table baseline to files
        - PhraseTable.java - internal data class
        - TestPhraseTable.java - Test class
    - CreatePhraseTable/
      - phraseTable/
        - GetTerms.java - creates the phrase table
        - Term.java - internal data class
        - TermGroup.java - internal data class
        - TermPair.java - internal data class
      - test/
        - TestGetTerms.java - Test class
        - TestTermGroup.java - Test class
