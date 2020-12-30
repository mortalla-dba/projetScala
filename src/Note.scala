//import scala.collection.mutable.ArrayBuffer
class Note(idNote : Long, etudiant: Etudiant, matiere : Matiere, lanote : Float)  {
  def getIdNote(): Long = idNote
  def getLanote(): Float = lanote
  //def getNoteEtudiant(): Float = lanote
  def getEtudiant(): Etudiant = etudiant
  def getMatiere() : Matiere = matiere


  override def toString: String =
    s"($idNote, $lanote,$etudiant,$matiere)"
}
