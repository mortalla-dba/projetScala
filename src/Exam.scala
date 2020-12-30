//import scala.collection.mutable.ArrayBuffer
class Exam(idExam : Long, etudiant: Etudiant, matiere : Matiere, noteExam : Float)  {
  def getIdExam(): Long = idExam
  def getNoteExam(): Float = noteExam
  def getEtudiant(): Etudiant = etudiant
  def getMatiere() : Matiere = matiere

  override def toString: String =
    s"($idExam,$etudiant,$matiere,$noteExam)"
}
