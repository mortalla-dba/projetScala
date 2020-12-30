class Salle(numeroSalle: Long, libelleSalle: String){

  def getLibelleSalle(): String = libelleSalle
  def getNumeroSalle(): Long = numeroSalle

  override def toString: String =
    s"($numeroSalle, $libelleSalle)"
}