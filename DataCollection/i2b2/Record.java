/*
 * HISTORY OF PRESENT ILLNESS :
 * PHYSICAL EXAMINATION :
 * HOSPITAL COURSE :
 * PAST MEDICAL HISTORY :
 * ALLERGIES :
 * LABORATORY DATA :
 * SOCIAL HISTORY :
 * DISPOSITION :
 */

public class Record
{
  private String id;
  private String historyOfPresentIllness;
  private String physicalExamination;
  private String hospitalCourse;
  private String pastMedicalHistory;
  private String allergies;
  private String laboratoryData;
  private String socialHistory;
  private String disposition;

  public Record(String id)
  {
    this.id = id;
    historyOfPresentIllness = "";
    physicalExamination = "";
    hospitalCourse = "";
    pastMedicalHistory = "";
    allergies = "";
    laboratoryData = "";
    socialHistory = "";
    disposition = "";
  }
  
  public String getID()
  {
    return id;
  }

  public String getHistoryOfPresentIllness()
  {
    return historyOfPresentIllness;
  }

  public void setHistoryOfPresentIllness(String historyOfPresentIllness)
  {
    this.historyOfPresentIllness = historyOfPresentIllness;
  }

  public String getPhysicalExamination()
  {
    return physicalExamination;
  }

  public void setPhysicalExamination(String physicalExamination)
  {
    this.physicalExamination = physicalExamination;
  }

  public String getHospitalCourse()
  {
    return hospitalCourse;
  }

  public void setHospitalCourse(String hospitalCourse)
  {
    this.hospitalCourse = hospitalCourse;
  }

  public String getPastMedicalHistory()
  {
    return pastMedicalHistory;
  }

  public void setPastMedicalHistory(String pastMedicalHistory)
  {
    this.pastMedicalHistory = pastMedicalHistory;
  }

  public String getAllergies()
  {
    return allergies;
  }

  public void setAllergies(String allergies)
  {
    this.allergies = allergies;
  }

  public String getLaboratoryData()
  {
    return laboratoryData;
  }

  public void setLaboratoryData(String laboratoryData)
  {
    this.laboratoryData = laboratoryData;
  }

  public String getSocialHistory()
  {
    return socialHistory;
  }

  public void setSocialHistory(String socialHistory)
  {
    this.socialHistory = socialHistory;
  }

  public String getDisposition()
  {
    return disposition;
  }

  public void setDisposition(String disposition)
  {
    this.disposition = disposition;
  }

  public String toString()
  {
    return "id: " + id + "\nHistory of Present Illness: " + historyOfPresentIllness
        + "\nPhysical Examination: " + physicalExamination + "\nHospital Care: "
        + hospitalCourse + "\nPast Medical History: " + pastMedicalHistory
        + "\nAllergies: " + allergies + "\nLab Data: " + laboratoryData
        + "\nSocial History: " + socialHistory + "\nDisposition: "
        + disposition;
  }
}