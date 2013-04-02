import ypsilon.digamma.core._
import ypsilon.digamma.core.Evaluator

object Main {
  def main(args: Array[String]) {
    System.out.println("Hello Ypsilon Digamma!")

    println("cadr (1 (2 3) 4 5) => " + cadr(read("(1 (2 3) 4 5)")))
    val evaluator = new Evaluator
    println("eval (car '(1 2 3)) => " + evaluator.eval(read("(car '(1 2 3))")))

  }
}
