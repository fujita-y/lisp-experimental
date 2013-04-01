import ypsilon.digamma.core._
import ypsilon.digamma.core.Procedure._

object Main {
  def main(args: Array[String]) {
    System.out.println("Hello Ypsilon Digamma!")
    val lst = read("(1 2 (3 hello (\"world\" 5) 6) 'sym '() ())")
    println(cadr(lst))
    println(caddr(lst))
    println(display(lst))

    // TODO: move to test
    {
      val s = car(read("(\"hello\")"))
      println("String: " + s)
      println("isInstanceOf[String]?: " + s.isInstanceOf[String])
      println("length: " + s.asInstanceOf[String].length)
    }

    {
      val i = car(read("(34541)"))
      println("Int: " + i)
     println("isInstanceOf[Int]?: " + i.isInstanceOf[Int])
    }

    {
      val s = car(read("(world)"))
      println("Symbol: " + s)
      println("isInstanceOf[Symbol]?: " + s.isInstanceOf[Symbol])
    }

  }
}
