package ypsilon.digamma.core.test

import org.scalatest.FunSuite
import ypsilon.digamma.core._

class MiscTest extends FunSuite {

  test("Reader") {
    assert(1 == 1)
    assert(car(read("(\"hello\")")) === new String("hello"))
    assert(car(read("(world)")) === 'world)
    assert(car(read("(34541)")) === 34541)
    assert(read("""(1 2 (3 hello ("world" 5) 6) 'sym '() ())""") ===
      List(1, 2, List(3, 'hello, List("world", 5), 6), List('quote, 'sym), List('quote, List()), List()))
  }

}