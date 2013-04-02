package ypsilon.digamma.core

class Evaluator {

  type LocalEnv = List[(Symbol, Any)]      // Association List

  def lookup(sym: Symbol, env: LocalEnv) = (env find (_._1 eq sym)) match {
    case None => throw new RuntimeException("unbound variable: " + sym.toString.tail)
    case x => x.get._2
  }

  def eval(e: Any, env: LocalEnv): Any = {
    //println("eval:" + e)
    e match {
      case x: Symbol => lookup(x, env)
      case 'quote::e1 => e1.head
      case e1::e2 => e1 match {
       // case 'quote => e2.head
        case 'cond => 'not_implemented_yet
        case 'define => 'not_implemented_yet
        case 'lambda => 'not_implemented_yet
        case 'car => car(eval(car(e2), env))
        case 'cdr => cdr(eval(car(e2), env))
        case 'cons => cons(eval(car(e2), env), eval(car(cdr(e2)), env))
        case 'eq => (eval(car(e2), env), eval(car(cdr(e2)), env)) match {
          case (x: List[_], y: List[_]) => x eq y
          case (x, y) => x == y
        }
        case _ => throw new RuntimeException("not an application: " + e)
      }
      case _ => e
    }
  }
}


