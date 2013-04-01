package ypsilon.digamma

/**
 * Created with IntelliJ IDEA.
 * User: yfujita
 * Date: 4/1/13
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */

import scala.util.parsing.combinator._

class LispParsers extends JavaTokenParsers {
  lazy val expr: Parser[Any] = atom | list | quot
  lazy val atom: Parser[Any] = ident | wholeNumber | stringLiteral
  lazy val list: Parser[Any] = "(" ~> rep(expr) <~ ")"
  lazy val quot: Parser[Any] = ("'" ~> expr) ^^ (List('quote, _))
}

package object core {
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
