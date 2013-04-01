import ypsilon.digamma.core._

object Main {
  def main(args: Array[String]) {
    System.out.println("Hello Ypsilon Digamma!")
    val lst = read("(1 2 (3 hello (\"world\" 5) 6) 'symbol '() ())")
    println(cadr(lst))
    println(display(lst))
  }
}
