import scala.collection.immutable.List
//import java.util.Date
import java.sql.Date
import scala.collection.mutable.ListBuffer


import scala.reflect.internal.util.TriState.{False, True}

object MainGestion extends App {
  var templistesalle = new ListBuffer[Salle]()
  var templistEtudiant = new ListBuffer[Etudiant]()
  var templistMatiere = new ListBuffer[Matiere]()
  var templistProfesseur = new ListBuffer[Professeur]()
  var templistNote = new ListBuffer[Note]()
  var templistExam = new ListBuffer[Exam]()
  var tempListEmploiduTemps = new ListBuffer[EmploiDuTemps]()



  def lesNotesEtudiant(etud:Etudiant,notes: List[Note]):List[Float] = {
    var l = new ListBuffer[Float]()
    for (note_etu <- notes) {
      if (note_etu.getEtudiant().getIdEtud() == etud.getIdEtud())
        l += note_etu.getLanote()
    }
    return l.toList
  }
    def lesNotesEtudiantMatiere(etud:Etudiant,matiere:Matiere, notes: List[Note]):List[Float] = {
      var l = new ListBuffer[Float]()
      for (note_etu <- notes) {
        if (note_etu.getEtudiant() == etud && note_etu.getMatiere() == matiere) {
          if (note_etu.getLanote()!=None)
              l+=note_etu.getLanote()
          else
             l+=0
        }
      }

    return l.toList
  }

  def lesNotesExamEtudiant(etud:Etudiant,notes: List[Exam]):List[Float] = {
    var l = new ListBuffer[Float]()
    for (note_etu <- notes) {
      if (note_etu.getEtudiant().getIdEtud() == etud.getIdEtud())
        l+=note_etu.getNoteExam()
    }

    return l.toList
  }

  def lesNotesExamEtudiantMatiere(etud:Etudiant,matiere:Matiere, notes: List[Exam]):List[Float] = {
    var l = new ListBuffer[Float]()
    for (note_etu <- notes) {
      if (note_etu.getEtudiant() == etud && note_etu.getMatiere() == matiere)
        l+=note_etu.getNoteExam()
    }

    return l.toList
  }

  def afficherNote(notes: List[Float]):Unit={
    for (note_etu <- notes){
      println(note_etu)
    }
  }

  def afficherNoteExam(exams: List[Float]):Unit={
    for (exam_etu <- exams){
      println(exam_etu)
    }
  }
  // Fonction qui calcul la moyenne sur chaque matiere
  def moyenneEtudiantMatiere(etu:Etudiant,matiere:Matiere,les_notes:List[Note],les_notes_exam:List[Exam]):Float={
    var l_n =  lesNotesEtudiantMatiere(etu,matiere,les_notes)
    var l_exa = lesNotesExamEtudiantMatiere(etu,matiere,les_notes_exam)

      return (l_n.sum/l_n.length+l_exa.sum/l_exa.length)/2
  }
  def estValideMatiere(idEdut:Etudiant,matiere:Matiere,les_notes:List[Note],les_notes_exam:List[Exam]):Boolean={
    return (moyenneEtudiantMatiere(idEdut,matiere,les_notes,les_notes_exam)>10)
  }


  // Methodes de calcul de la moyenne d'un  etudiant
  def moyenneEtudiant(idEdut:Etudiant,listematiere:List[Matiere],les_notes:List[Note],les_notes_exam:List[Exam]):Float={
    var moy_mat = new ListBuffer[Float]()
    var total_coef : Float=0
    for (matiere <- listematiere){
      moy_mat+=moyenneEtudiantMatiere(idEdut,matiere,les_notes,les_notes_exam)*matiere.getCoeffMatiere()
      total_coef+=matiere.getCoeffMatiere()
    }
    return moy_mat.toList.sum/total_coef
  }
  // A revoir si on mettra une liste pour l'emploi du temps contenant les cours ou non


  // Autres methodes a implementees
  /*
  // ok: de faire l'allocation des salles en fonction de l'emploi du temps.
  // ok: de déterminer les salles disponibles entre un intervalle de temps de la journée
  de trouver les salles occupées en précisant le nom du cours et le professeur
  de lister les notes d'un étudiant
  de calculer la moyenne de chaque étudiant
  d'afficher les statistiques de l'établissement (moyenne de chaque classe, moyenne de chaque matière, le taux de réussites et d'échecs,
   */



  // Instancier les salles de classe disponible
  val salle1 = new Salle(
    1,
    "Salle 1 Master I BDA"
  )
  val salle2 = new Salle(
    2,
    "Salle 2 Master I BDA"
  )
  // Ajouter les salles dans une liste
  templistesalle+=salle1
  templistesalle+=salle2

  // convertir ListBuffer en List simple
  val salleList = templistesalle.toList

  // Instancier les professeurs
  val prof1 = new Professeur(
    idProfesseur = 1,
    nomProfesseur = "Dr Mboup",
    prenomPro = "Samba",
    grade = "Titulaire"
  )
  val prof2 = new Professeur(
    idProfesseur = 2,
    nomProfesseur = "Dr Gueye",
    prenomPro = "Mouhamed",
    grade = "Tuteur"
  )
  val prof3 = new Professeur(
    idProfesseur = 3,
    nomProfesseur = "Dr Ndiaye",
    prenomPro = "Mamadou",
    grade = "Tutilaire"
  )
  val prof4 = new Professeur(
    idProfesseur = 4,
    nomProfesseur = "Dr Diouf",
    prenomPro = "Babacar",
    grade = "Tutilaire"
  )

  // Ajouter les professeur dasn une liste buffer
  templistProfesseur+=prof1
  templistProfesseur+=prof2
  templistProfesseur+=prof3
  templistProfesseur+=prof4

  // Convertir la lsite buffer en liste simple de professeurs
  val listeProfesseur = templistProfesseur.toList

  // Instancier les matieres
  val matiere1 = new Matiere(
    codeMatiere = "MATH1",
    libelleMatiere = "Mathematiques",
    coeffMatiere = 4
  )
  val matiere2 = new Matiere(
    codeMatiere = "BD",
    libelleMatiere = "Base de donnees",
    coeffMatiere = 3
  )
  val matiere3 = new Matiere(
    codeMatiere = "INFO1",
    libelleMatiere = "Informatique",
    coeffMatiere = 2
  )
  val matiere4 = new Matiere(
    codeMatiere = "DROIT",
    libelleMatiere = "Droit administratif",
    coeffMatiere = 2
  )
  val matiere5 = new Matiere(
    codeMatiere = "FRAN",
    libelleMatiere = "Francais",
    coeffMatiere = 1
  )
 // Ajouter les matieres dasn une liste
  templistMatiere+=matiere1
  templistMatiere+=matiere2
  templistMatiere+=matiere3
  templistMatiere+=matiere4
  templistMatiere+=matiere5

  // Convertir ListeBuffer en Liste simple des matieres disponible
  val listeMatiere = templistMatiere.toList

  // Instancier les etudiants
  val etudiant1 = new Etudiant(
    idEtudiant = 1,
    nomEtudiant = "Gueye",
    prenomEtudiant = "Samba",
    niveauEtude = "M1"
  )
  val etudiant2 = new Etudiant(
    idEtudiant = 2,
    nomEtudiant = "Diop",
    prenomEtudiant = "Abdou",
    niveauEtude = "M1"
  )
  val etudiant3 = new Etudiant(
    idEtudiant = 3,
    nomEtudiant = "Sarr",
    prenomEtudiant = "Abdoulaye",
    niveauEtude = "M2"
  )


  // Ajouter les etudiants dans une liste d'etudiants
  templistEtudiant+=etudiant1
  templistEtudiant+=etudiant2
  templistEtudiant+=etudiant3

  // Convertir la liste buffder en list simple d'etudiant
  val listeEtudiant = templistEtudiant.toList

  // Instancier les notes des etudiants
  // note etudiant 1
  val note1 = new Note(
    idNote = 1,
    etudiant1,
    matiere1,
    lanote = 14
  )
  val note2 = new Note(
    idNote = 2,
    etudiant1,
    matiere1,
    lanote = 10
  )
  val note3 = new Note(
    idNote = 3,
    etudiant1,
    matiere2,
    lanote = 14
  )
  val note4 = new Note(
    idNote = 4,
    etudiant1,
    matiere3,
    lanote = 10
  )
  val note5 = new Note(
    idNote = 5,
    etudiant1,
    matiere4,
    lanote = 13
  )
  val note6 = new Note(
    idNote = 6,
    etudiant1,
    matiere5,
    lanote = 11
  )
// note etudaint 2
val note7 = new Note(
  idNote = 7,
  etudiant2,
  matiere1,
  lanote = 14
)
  val note8 = new Note(
    idNote = 8,
    etudiant2,
    matiere2,
    lanote = 10
  )
  val note9 = new Note(
    idNote = 9,
    etudiant2,
    matiere2,
    lanote = 14
  )
  val note10 = new Note(
    idNote = 10,
    etudiant2,
    matiere3,
    lanote = 10
  )
  val note11 = new Note(
    idNote = 11,
    etudiant2,
    matiere4,
    lanote = 11
  )
  val note12 = new Note(
    idNote = 12,
    etudiant2,
    matiere4,
    lanote = 10
  )
  val note13 = new Note(
    idNote = 13,
    etudiant2,
    matiere5,
    lanote = 10
  )

  // note etudaint 3
  val note14 = new Note(
    idNote = 14,
    etudiant3,
    matiere1,
    lanote = 14
  )
  val note15 = new Note(
    idNote = 15,
    etudiant3,
    matiere2,
    lanote = 10
  )
  val note16 = new Note(
    idNote = 16,
    etudiant3,
    matiere3,
    lanote = 11
  )
  val note17 = new Note(
    idNote = 17,
    etudiant3,
    matiere4,
    lanote = 9
  )
  val note18 = new Note(
    idNote = 18,
    etudiant3,
    matiere5,
    lanote = 7
  )


  // Ajouter les notes dans une liste de Notes
  templistNote+=note1
  templistNote+=note2
  templistNote+=note3
  templistNote+=note4
  templistNote+=note5
  templistNote+=note6
  templistNote+=note7
  templistNote+=note8
  templistNote+=note9
  templistNote+=note10
  templistNote+=note11
  templistNote+=note12
  templistNote+=note13
  templistNote+=note14
  templistNote+=note15
  templistNote+=note16
  templistNote+=note17
  templistNote+=note18
  // Convertir la liste buffer en liste simple
  val listNote = templistNote.toList

  //Instancier les notes des exams

  val exam1 = new Exam(
    idExam = 1,
    etudiant1,
    matiere1,
    noteExam = 15
  )
  val exam2 = new Exam(
    idExam = 2,
    etudiant1,
    matiere2,
    noteExam = 12
  )
  val exam3 = new Exam(
    idExam = 3,
    etudiant1,
    matiere3,
    noteExam = 15
  )
  val exam4 = new Exam(
    idExam = 4,
    etudiant1,
    matiere4,
    noteExam = 13
  )
  val exam5 = new Exam(
    idExam = 5,
    etudiant1,
    matiere5,
    noteExam = 17
  )
  // etudaint 2 exam
  val exam6 = new Exam(
    idExam = 6,
    etudiant2,
    matiere1,
    noteExam = 10
  )
  val exam7 = new Exam(
    idExam = 7,
    etudiant2,
    matiere2,
    noteExam = 12
  )
  val exam8 = new Exam(
    idExam = 8,
    etudiant2,
    matiere3,
    noteExam = 12
  )
  val exam9 = new Exam(
    idExam = 9,
    etudiant2,
    matiere4,
    noteExam = 12
  )
  val exam10 = new Exam(
    idExam = 10,
    etudiant2,
    matiere5,
    noteExam = 11
  )
  // etudaint 3 exam
  val exam11 = new Exam(
    idExam = 11,
    etudiant3,
    matiere1,
    noteExam = 10
  )
  val exam12 = new Exam(
    idExam = 12,
    etudiant3,
    matiere2,
    noteExam = 4
  )
  val exam13 = new Exam(
    idExam = 13,
    etudiant3,
    matiere3,
    noteExam = 9
  )
  val exam14 = new Exam(
    idExam = 14,
    etudiant3,
    matiere4,
    noteExam = 12
  )
  val exam15 = new Exam(
    idExam = 15,
    etudiant3,
    matiere5,
    noteExam = 11
  )


  // Ajouter les notes des exams dans une liste buffer
  templistExam+=exam1
  templistExam+=exam2
  templistExam+=exam3
  templistExam+=exam4
  templistExam+=exam5
  templistExam+=exam6
  templistExam+=exam7
  templistExam+=exam8
  templistExam+=exam9
  templistExam+=exam10
  templistExam+=exam11
  templistExam+=exam12
  templistExam+=exam13
  templistExam+=exam14
  templistExam+=exam15
// Convertir la liste buffer en liste simple
  val listeExam = templistExam.toList

  // Instancier les emplois du temps
  val emploi1 = new EmploiDuTemps(
    idEmploi = 1,
    salle1,
    matiere1,
    prof1,
    ladate = "26/12/2020",
    heure =  "10H00-13H00"
  )
  val emploi2 = new EmploiDuTemps(
    idEmploi = 2,
    salle2,
    matiere2,
    prof2,
    ladate = "26/12/2020",
    heure =  "14H00-17H00"
  )
  val emploi3 = new EmploiDuTemps(
    idEmploi = 3,
    salle1,
    matiere1,
    prof1,
    ladate = "26/12/2020",
    heure =  "14H00-17H00"
  )

  // Ajouter les horaires des emplois du temps dans une liste
  tempListEmploiduTemps+=emploi1
  tempListEmploiduTemps+=emploi2
  tempListEmploiduTemps+=emploi3
  // Convertir une lsite buffer en liste simple
  val listeemploiDuTemps = tempListEmploiduTemps.toList


  //Affichage des informations de l'application
  // Liste des salles de classes
  println("########################################################################################")
  println(" Affiche Liste des salles de classes")
  salleList.foreach { println }
  // Liste des professeurs
  println("########################################################################################")
  println("Liste des professeurs")
  listeProfesseur.foreach{println}
  // Liste des matieres
  println("########################################################################################")
  println("Liste des matieres")
  listeMatiere.foreach {println}
  // Liste des etudiants
  println("########################################################################################")
  println("Liste des etudiants")
  listeEtudiant.foreach{println}
  println("########################################################################################")
  // Liste des notes des etudiants
  println("Liste des notes des etudiants")
  listNote.foreach{println}
  println("########################################################################################")
  // Liste des notes des exams
  println("Liste des notes des exams")
  listeExam.foreach{println}
  println("########################################################################################")
  // Les emplois du temps
  println("Les emplois du temps")
  listeemploiDuTemps.foreach{println}
  println("########################################################################################")
  println("Les notes de l'etudiant: "+etudiant1)
  val lesnotesEtu1=lesNotesEtudiant(etudiant1,listNote)
  afficherNote(lesnotesEtu1)
  println("########################################################################################")
  println("Les notes exam de l'etudiant: "+etudiant1)
  val lesnotesexamEtu1=lesNotesExamEtudiant(etudiant1,listeExam)
  afficherNoteExam(lesnotesexamEtu1)
  println("########################################################################################")
  for (etudiant <- listeEtudiant) {
    println("##############  "+etudiant.getPrenomEtudiant()+" "+etudiant.getNomEtudanit()+ " ############")
    for (matiere <- listeMatiere) {
         println("L'etudiant " + etudiant + " a sur  la matiere " + matiere + " une moyenne de : " + moyenneEtudiantMatiere(etudiant1, matiere, listNote, listeExam))

    }
    println("###### La moyenne generale  de: "+etudiant.getPrenomEtudiant()+" "+etudiant.getNomEtudanit()+" est: "+moyenneEtudiant(etudiant,listeMatiere,listNote,listeExam))
    if (moyenneEtudiant(etudiant,listeMatiere,listNote,listeExam)>10)
      println("###### L'etudiant: "+etudiant.getPrenomEtudiant()+" "+etudiant.getNomEtudanit()+" passe en classe superieure")
    else
      println("###### L'etudiant: "+etudiant.getPrenomEtudiant()+" "+etudiant.getNomEtudanit()+" doit  reprendre la classe")


  }

}
