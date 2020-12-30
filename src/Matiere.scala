class Matiere(codeMatiere : String, libelleMatiere: String, coeffMatiere:Int)  {
  def getLibelleMatiere(): String = libelleMatiere
  def getCodeMatiere(): String = codeMatiere
  def getCoeffMatiere(): Int = coeffMatiere

  override def toString: String =
    s"($libelleMatiere)"
}
