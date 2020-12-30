class Etudiant(idEtudiant : Long, nomEtudiant : String, prenomEtudiant : String, niveauEtude : String){

  def demarrer(){
    // ...
  }


  def getNomEtudanit(): String = nomEtudiant

  def getPrenomEtudiant(): String = prenomEtudiant

  def getNiveauEtude(): String = niveauEtude
  def getIdEtud(): Long = idEtudiant

  override def toString: String =
    s"($idEtudiant, $nomEtudiant, $prenomEtudiant, $niveauEtude)"


}