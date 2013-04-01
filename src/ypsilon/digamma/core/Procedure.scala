package ypsilon.digamma.core

object Procedure {
  def caddr(datum: Any) = cadr(cdr(datum))
}
