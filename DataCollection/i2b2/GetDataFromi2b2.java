import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GetDataFromi2b2
{
  public static void main(String[] args) throws Exception
  {
    //edit directory to be the location of the file 'deid_surrogate_test_all_version2.xml'
    String directory = "";
    String filePath = directory + "/deid_surrogate_test_all_version2.xml";
    File file = new File(filePath);
    BufferedReader in = new BufferedReader(new FileReader(file));

    List<Record> records = new ArrayList<Record>();

    Record record = null;
    String line;
    int mode = -1;
    while ((line = in.readLine()) != null)
    {
      if (line.startsWith("<RECORD ID="))
      {
        record = new Record(line.split("=")[1].split("\"")[1]);
        mode = -1;
        records.add(record);
      } else if (line.equals("HISTORY OF PRESENT ILLNESS :"))
        mode = 0;
      else if (line.equals("PHYSICAL EXAMINATION :"))
        mode = 1;
      else if (line.equals("HOSPITAL COURSE :"))
        mode = 2;
      else if (line.equals("PAST MEDICAL HISTORY :"))
        mode = 3;
      else if (line.equals("ALLERGIES :"))
        mode = 4;
      else if (line.equals("LABORATORY DATA :"))
        mode = 5;
      else if (line.equals("SOCIAL HISTORY :"))
        mode = 6;
      else if (line.equals("DISPOSITION :"))
        mode = 7;
      else if (line.contains(" :"))
        mode = -1;
      else
        switch (mode)
        {
          case 0:
            record.setHistoryOfPresentIllness(
                record.getHistoryOfPresentIllness() + "\n" + line);
            break;
          case 1:
            record.setPhysicalExamination(
                record.getPhysicalExamination() + "\n" + line);
            break;
          case 2:
            record.setHospitalCourse(record.getHospitalCourse() + "\n" + line);
            break;
          case 3:
            record.setPastMedicalHistory(
                record.getPastMedicalHistory() + "\n" + line);
            break;
          case 4:
            record.setAllergies(record.getAllergies() + "\n" + line);
            break;
          case 5:
            record.setLaboratoryData(record.getLaboratoryData() + "\n" + line);
            break;
          case 6:
            record.setSocialHistory(record.getSocialHistory() + "\n" + line);
            break;
          case 7:
            record.setDisposition(record.getDisposition() + "\n" + line);
            break;
        }
    } // while

    System.out.println(records.size());

    in.close();

    for (Record printRecord : records)
    {
      String printString = printRecord.getHistoryOfPresentIllness() + " "
          + printRecord.getPhysicalExamination() + " "
          + printRecord.getHospitalCourse() + " "
          + printRecord.getPastMedicalHistory() + " "
          + printRecord.getAllergies() + " " + printRecord.getLaboratoryData()
          + " " + printRecord.getSocialHistory() + " "
          + printRecord.getDisposition() + " ";

      if (printString.trim().split("[ \n\t]").length > 10)
      {
        
          PrintWriter writer = new PrintWriter(
          new FileWriter(directory + "/" + printRecord.getID()
          + ".txt"));
          writer.println(printString.trim());
          writer.close();
         
      } // if
    } // for
  }// main
}// class