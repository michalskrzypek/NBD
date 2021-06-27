object Main {

  def main(args: Array[String]): Unit = {
    println("1.\n")
    println(task1("Poniedziałek"))
    println(task1("Sobota"))
    println(task1("Something"))
    println(task1("Saturday"))
    println(task1("Wtorek"))
    println()

    println("2.\n")
    val kontoBankowe1 = new KontoBankowe(100)
    println(kontoBankowe1.stan)
    println(kontoBankowe1.wyplac(50))
    println(kontoBankowe1.wplac(20))

    val kontoBankowe2 = new KontoBankowe()
    println(kontoBankowe2.stan)
    println(kontoBankowe2.wplac(15))
    println(kontoBankowe2.wyplac(10))

    println()

    println("3.\n")
    val osoba1 = Osoba("Michal", "Skrzypek")
    greet(osoba1)

    val osoba2 = Osoba("Jan", "Nowak")
    greet(osoba2)

    val osoba3 = Osoba("Maria", "Kowalska")
    greet(osoba3)

    val osoba4 = Osoba("Marek", "Marecki")
    greet(osoba4)

    println()

    println("4.\n")
    val number = 48
    def minusOne(num: Int): Int = num - 1
    println(task4(number, minusOne))

    println()

    println("5.\n")

    val osoba5 = new Osoba("Ewa", "Nowak") with Pracownik
    println(s"Podatek pracownika jest równy ${osoba5.podatek}%")

    val osoba6 = new Osoba("Michał", "Skrzypek") with Student
    println(s"Podatek studenta jest równy ${osoba6.podatek}%")

    val osoba7 = new Osoba("jan", "Kowalski") with Nauczyciel
    println(s"Podatek nauczyciela jest równy ${osoba7.podatek}%")

    val osoba9 = new Osoba("ktos", "tam") with Pracownik with Student
    println(s"Podatek pracownika, który studiuje jest równy ${osoba9.podatek}%")

    val osoba8 = new Osoba("jakas", "osoba") with Student with Pracownik
    println(s"Podatek studenta, który pracuje wynosi ${osoba8.podatek}%")
  }

  def task1(str: String): String = {
    val workingDays = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek")
    val weekendDays = List("Sobota", "Niedziela")

    str match {
      case a if (workingDays.contains(a)) => "Praca"
      case b if (weekendDays.contains(b)) => "Weekend"
      case _ => "Nie ma takiego dnia"
    }
  }

  def greet(osoba: Osoba) = {
    val greeting = osoba.nazwisko match {
      case "Skrzypek" => "Cześć Michał"
      case "Nowak" => "dzień dobry Jan"
      case "Kowalska" => "Hej Maria"
      case _ => "Witam cię"
    }

    println(greeting)
    greeting
  }

  def task4(number: Int, fun: (Int) => Int): Int = {
    fun(fun(fun(number)))
  }
}
