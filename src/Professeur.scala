class Professeur(idProfesseur : Long, nomProfesseur : String, prenomPro : String, grade : String){

  def demarrer(){
    // ...
  }

  def getNoProfesseur(): String = nomProfesseur
  def getNomProfesseur(): String = nomProfesseur

  def getPrenomProfesseur(): String = prenomPro

  def getGrade(): String = grade

  override def toString: String =
    s"($idProfesseur, $nomProfesseur, $prenomPro,$grade)"

}