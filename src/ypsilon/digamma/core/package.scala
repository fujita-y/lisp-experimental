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

  def display(datum: Any): String = datum match {
    case Nil => "()"
    case List('quote, x) => "'" + display(x)
    case (x: ::[_]) => "(" + x.map(display).mkString(" ") + ")"
    case (x: String) => x
    case (x: Symbol) => x.toString.tail
    case _ => datum.toString
  }

  def write(datum: Any): String = datum match {
    case Nil => "()"
    case (x: ::[_]) => "(" + x.map(write).mkString(" ") + ")"
    case (x: String) => "\"" + x + "\""
    case (x: Symbol) => x.toString.tail
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
