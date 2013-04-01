package ypsilon.digamma

package object core {
  import scala.util.parsing.combinator._

  class LispParsers extends JavaTokenParsers {
    lazy val expr: Parser[Any] = symbol | number | string | list | quote
    lazy val number: Parser[Any] = wholeNumber ^^ (s => s.toInt)
    lazy val symbol: Parser[Any] = ident ^^ (s => Symbol(s))
    lazy val string: Parser[Any] = stringLiteral ^^ (s => s.substring(1, s.length() - 1))
    lazy val list: Parser[Any] = "(" ~> rep(expr) <~ ")"
    lazy val quote: Parser[Any] = ("'" ~> expr) ^^ (List('quote, _))
  }

  def read(s: String) = {
    val parsers = new LispParsers
    val res = parsers.parseAll(parsers.expr, s)
    if (res.successful) {
      res.get
    } else {
      throw new IllegalArgumentException(res.toString)
    }
  }

  def display(datum: Any): String = datum match {
    case Nil => "()"
    case List('quote, x) => "'" + display(x)
    case (x: ::[_]) => "(" + x.map(display).mkString(" ") + ")"
    case (x: String) => x
    case (x: Symbol) => x.toString.substring(1)
    case _ => datum.toString
  }

  def car(datum: Any) = datum match {
    case lst: List[Any] => lst.head
    case _ => throw new IllegalArgumentException("expected pair, but got " + datum.getClass())
  }

  def cdr(datum: Any) = datum match {
    case lst: List[Any] => lst.tail
    case _ => throw new IllegalArgumentException("expected pair, but got " + datum.getClass())
  }

  def caar(datum: Any) = car(car(datum))
  def cadr(datum: Any) = car(cdr(datum))
  def cddr(datum: Any) = cdr(cdr(datum))
}
