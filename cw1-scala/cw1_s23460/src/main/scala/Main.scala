package pl.michalskrzypek

import scala.annotation.tailrec
import scala.collection.convert.ImplicitConversions.`seq AsJavaList`

object Main {
  def main(args: Array[String]) = {
    val weekdays = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")

    println("1a.\n")
    task1a(weekdays)
    println("")

    println("1b.\n")
    task1b(weekdays)
    println("")

    println("1c.\n")
    task1c(weekdays)
    println("")

    println("2a.\n")
    println(task2a(weekdays))
    println("")

    println("2b.\n")
    println(task2b(weekdays))
    println("")

    println("3.\n")
    println(task3(weekdays))
    println("")

    println("4a.\n")
    println(task4a(weekdays))
    println("")

    println("4b.\n")
    println(task4b(weekdays))
    println("")

    println("4c.\n")
    println(task4c(weekdays))
    println("")

    println("5.\n")
    val products = Map("Woda" -> 1, "Sok" -> 3, "Cytryny" -> 5)
    task5(products)
    println("")

    println("6.\n")
    task6(101, weekdays, "hello")
    println("")

    println("7.\n")
    val waterBottlePrice = products.get("Woda")
    println(waterBottlePrice)
    println("")

    val JuiceBottlePrice = products.get("Sok")
    println(JuiceBottlePrice)
    println("")

    println("8.\n")
    val intList = List(0, 2, 3, 0, -1, 4, 9, 7, 1, 0, -2)
    println(task8(intList))
    println("")

    println("9.\n")
    println(task9(intList))
    println("")

    println("10.\n")
    val biggerIntList = List(-999, 62, -5, 0, 2, 5, 1, 4, -3, 4, 5, -6, 82, 1, 20, 999)
    print(task10(biggerIntList))
  }

  def task1a(weekdays: List[String]): Unit = {
    var result = "";
    for (i <- weekdays.indices) {
      if (result != "")
        result += ", " + weekdays.get(i)
      else
        result += weekdays.get(i)
    }
    println(result)
  }

  def task1b(weekdays: List[String]): Unit = {
    var result = "";
    for (i <- weekdays.indices) {
      if (weekdays.get(i).startsWith("P")) {
        if (result != "") {
          result += ", " + weekdays.get(i)
        } else {
          result += weekdays.get(i)
        }
      }
    }
    println(result);
  }

  def task1c(weekdays: List[String]): Unit = {
    var result = ""
    var index = 0;
    while (index < weekdays.length) {
      if (result != "") {
        result += ", " + weekdays.get(index)
      } else {
        result += weekdays.get(index)
      }
      index = index + 1;
    }
    println(result);
  }

  def task2a(weekdays: List[String]): String = {
    def appendDay(i: Int): String = {
      if (i == weekdays.length) return ""
      val currentDay = if (i == weekdays.length - 1) {
        weekdays.get(i)
      } else {
        weekdays.get(i) + ", "
      }
      currentDay + appendDay(i + 1)
    }

    appendDay(0);
  }

  def task2b(weekdays: List[String]): String = {
    def appendDay(i: Int): String = {
      if (i == -1) return ""
      val currentDay = if (i == 0) {
        weekdays.get(i)
      } else {
        weekdays.get(i) + ", "
      }
      currentDay + appendDay(i - 1)
    }

    appendDay(weekdays.length - 1)
  }

  def task3(weekdays: List[String]): String = {
    @tailrec
    def appendDay(i: Int, result: String): String = {
      if (i == weekdays.length) return result
      val currentDay = if (i == weekdays.length - 1) {
        weekdays.get(i)
      } else {
        weekdays.get(i) + ", "
      }
      appendDay(i + 1, result + currentDay)
    }

    appendDay(0, "")
  }

  def task4a(weekdays: List[String]): String = {
    weekdays.fold("") { (sum, curr) => {
      sum + curr + ", "
    }
    }.dropRight(2)
  }

  def task4b(weekdays: List[String]): String = {
    weekdays.foldRight("") { (sum, curr) => {
      sum + ", " + curr
    }
    }.dropRight(2)
  }

  def task4c(weekdays: List[String]): String = {
    weekdays.fold("") { (sum, curr) => {
      if (curr.startsWith("P")) {
        sum + curr + ", "
      } else {
        sum
      }
    }
    }.dropRight(2)
  }

  def task5(products: Map[String, Int]): Unit = {
    val discountedProducts = products map { case (key, value) => (key, value * 0.9) }
    println(discountedProducts)
  }

  def task6(tup: (Int, Any, String)): Unit = {
    println(tup._1)
    println(tup._2)
    println(tup._3)
  }

  def task8(numbers: List[Int]): List[Int] = {
    @tailrec
    def iter(index: Int, currentList: List[Int]): List[Int] = {
      if (index >= currentList.length) return currentList

      val (part1, part2) = currentList.splitAt(index)

      if (currentList.get(index) == 0) {
        iter(index + 1, part1 ++ part2.tail)
      } else {
        iter(index + 1, currentList)
      }
    }

    iter(0, numbers)
  }

  def task9(numbers: List[Int]): List[Int] = {
    numbers map (el => el + 1)
  }

  def task10(numbers: List[Int]): List[Int] = {
    val filteredNumbers = numbers filter (el => el >= -5 && el <= 12)

    filteredNumbers map (el => el.abs)
  }
}