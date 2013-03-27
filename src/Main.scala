object Main {

  def foo(x: Array[String]) = x.foldLeft("") {
    (a, b) => a + b
  }

  def main(args: Array[String]) {
    System.out.println("Hello, Scala World!")
    println("concat arguments = " + foo(args))
  }
}
