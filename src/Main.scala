import ypsilon.digamma.core._
import ypsilon.digamma.core.Evaluator

object Main {

  def test_eval(s: String) {
    val evaluator = new Evaluator
    val env = List(('var100, 100),('var200, 200),('var300, 300))
    println("eval " + s + " => " + write(evaluator.eval(read(s), env)))
  }

  def main(args: Array[String]) {
    System.out.println("Hello Ypsilon Digamma!")

    println("cadr (1 (2 3) 4 5) => " + car(cdr(read("(1 (2 3) 4 5)"))))
    test_eval("""'4""")
    test_eval("""'(1 2 3)""")
    test_eval("""4888""")
    test_eval("""var200""")
    test_eval(""""string"""")
    test_eval("""(car '((1 2 3) (4 5 6)))""")
    test_eval("""(cdr '((1 2 3) (4 5 6)))""")
    test_eval( """(cons '(1 2 3) '(4 5 6))""")
    test_eval("""(eq '(1 2 3) '(4 5 6))""")
    test_eval( """(eq '(1 2 3) '(1 2 3))""")
    test_eval("""(eq 'hello 'hello)""")
    test_eval("""(eq 33 33)""")
  }
}
