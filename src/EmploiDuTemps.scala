import java.sql.Date

class EmploiDuTemps(idEmploi : Long, salle: Salle, matiere : Matiere, prof : Professeur, ladate: String, heure: String)  {
  def getIdEmploi(): Long = idEmploi
  def getSalle(): Salle = salle
  def getMatier(): Matiere=matiere
  def getProf(): Professeur=prof
  def getDate(): String=ladate
  def getHeure(): String=heure


  def estDisponibleSalleEmploi(idSalle:Long,emploiDuTemps: List[EmploiDuTemps],ladate:String, heure:String):Boolean={
    var ok=false
    for (salleemploye <- emploiDuTemps){
      if (idSalle==salleemploye.getSalle().getNumeroSalle() && ladate==salleemploye.getDate() && heure!=salleemploye.getHeure())
        return true
    }
    return ok
  }

  def estSalleOccupee(idSalle:Long,salleemploye: EmploiDuTemps,ladate:String, heure:String):Boolean={
    return (idSalle==salleemploye.getSalle().getNumeroSalle() && ladate==salleemploye.getDate() && heure==salleemploye.getHeure())
  }

  def afficherSalleOccupees(idSalle:Long,emploiDuTemps: List[EmploiDuTemps],ladate:String, heure:String):Unit={
    for (emploi <- emploiDuTemps){
      if (estSalleOccupee(idSalle,emploi,ladate,heure))
        println(emploi.getProf()," ", emploi.getMatier())
    }
  }
  override def toString: String =
    s"($idEmploi, $salle, $matiere, $prof, $ladate, $heure)"
}
