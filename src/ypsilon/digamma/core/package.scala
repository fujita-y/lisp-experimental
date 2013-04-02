package ypsilon.digamma

package object core {
  import scala.util.parsing.combinator.JavaTokenParsers

  class LispParsers extends JavaTokenParsers {
    lazy val expr: Parser[Any] = symbol | list | quote | number | string
    lazy val list: Parser[Any] = "(" ~> rep(expr) <~ ")"
    lazy val quote: Parser[Any] = ("'" ~> expr) ^^ (List('quote, _))
    lazy val symbol: Parser[Any] = ident ^^ (Symbol(_))
    lazy val number: Parser[Any] = wholeNumber ^^ (_.toInt)
    lazy val string: Parser[Any] = stringLiteral ^^ (x => x.substring(1, x.length() - 1))
  }

  def read(s: String) = {
    val parsers = new LispParsers
    val res = parsers.parseAll(parsers.expr, s)
    if (res.successful) res.get
    else throw new IllegalArgumentException(res.toString)
  }

  def display(e: Any): String = e match {
    case Nil => "()"
    case List('quote, x) => "'" + display(x)
    case x: ::[_] => "(" + x.map(display).mkString(" ") + ")"
    case x: String => x
    case x: Symbol => x.toString.tail
    case _ => e.toString
  }

  def write(e: Any): String = e match {
    case Nil => "()"
    case x: ::[_] => "(" + x.map(write).mkString(" ") + ")"
    case x: String => "\"" + x + "\""
    case x: Symbol => x.toString.tail
    case _ => e.toString
  }

  def cons(e1: Any, e2: Any) = e2 match {
    case x: List[Any] => e1::x
    case _ => throw new RuntimeException("const expected pair for second argument, but got " + e2.getClass())
  }

  def car(e: Any) = e match {
  case x: List[Any] => x.head
  case _ => throw new RuntimeException("car expected pair, but got " + e.getClass())
  }

  def cdr(e: Any) = e match {
    case x: List[Any] => x.tail
    case _ => throw new RuntimeException("cdr expected pair, but got " + e.getClass())
  }

}
