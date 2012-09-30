package dbtsolver

import org.junit._
import Assert._

import dbtsolver.situation.{BarlinSituation => BS}
import collection.mutable.{HashMap}

@Test
class SolverTest {

  val solver = new Solver();

  @Test
  def testDoNothingForInitialWinningSituation() {

    val s0 = BS(null, Array(3, 3, 3))

    assertTrue(s0.isWinning())
    assertEquals(List(s0), solver.findSolutionPath(s0))
  }

  @Test
  def testFindAdjacentWinningSituation() {

    val s0 = BS(null, Array(4, 3, 3))
    val s1 = BS(s0, Array(3, 3, 3))

    assertFalse(s0.isWinning())
    assertTrue(s1.isWinning())

    assertEquals(List(s0, s1), solver.findSolutionPath(s0))
  }

  @Test
  def testFindClosestSolution() {

    assertEquals(
      List(
        BS(null, Array(2))
      ),
      solver.findSolutionPath(BS(null, Array(2))))

    assertEquals(
      List(
        BS(null, Array(2, 3)),
        BS(null, Array(2, 2))
      ),
      solver.findSolutionPath(BS(null, Array(2, 3))))

    assertEquals(
      List(
        BS(null, Array(2, 3, 4)),
        BS(null, Array(2, 3, 3)),
        BS(null, Array(3, 3, 3))
      ),
      solver.findSolutionPath(BS(null, Array(2, 3, 4))))

    assertEquals(
      List(
        BS(null, Array(2, 3, 4, 5, 6)),
        BS(null, Array(2, 3, 4, 5, 5)),
        BS(null, Array(2, 3, 4, 5, 4)),
        BS(null, Array(2, 3, 4, 4, 4)),
        BS(null, Array(2, 4, 4, 4, 4)),
        BS(null, Array(3, 4, 4, 4, 4)),
        BS(null, Array(4, 4, 4, 4, 4))
      ),
      solver.findSolutionPath(BS(null, Array(2, 3, 4, 5, 6))))
  }

  @Test
  def testGraphCycles() {

    // traversing this graph breadth-first would run into a cycle (a, b, c, c)
    // and this would happen before the winning situation e is reached
    // the solver must detect the cycle and not process a again
    // a -> b
    // b -> c, d
    // c -> a (cycle)
    // d -> e
    // e -> winning

    // manual mocking :/
    var mocks = HashMap[String, Situation]()
    val calls = new StringBuilder()

    val a = new Situation {
      def successors(): List[Situation] = List(mocks("b"))

      def isWinning(): Boolean = {
        calls.append("a")
        return false
      }

      val predecessor: Situation = null
    }

    val b = new Situation {
      def successors(): List[Situation] = List(mocks("c"), mocks("d"))

      def isWinning(): Boolean = {
        calls.append("b")
        return false
      }

      val predecessor: Situation = a
    }

    val c = new Situation {
      def successors(): List[Situation] = List(mocks("a"))

      def isWinning(): Boolean = {
        calls.append("c")
        return false
      }

      val predecessor: Situation = b
    }

    val d = new Situation {
      def successors(): List[Situation] = List(mocks("e"))

      def isWinning(): Boolean = {
        calls.append("d")
        return false
      }

      val predecessor: Situation = b
    }

    val e = new Situation {
      def successors(): List[Situation] = return Nil

      def isWinning(): Boolean = {
        calls.append("e")
        return true
      }

      val predecessor: Situation = d
    }

    mocks = HashMap[String, Situation]("a" -> a, "b" -> b, "c" -> c, "d" -> d, "e" -> e)

    assertEquals(List(a, b, d, e), solver.findSolutionPath(a))

    assertTrue(calls.toString() == calls.toString().distinct)
    assertEquals("abcde", calls.toString())
  }

  @Test
  def testGraphDiamonds() {

    // traversing this graph breadth-first would run into a diamond
    // d is reached from (a, b) and from (a, c) and would be processed twice,
    // before the winning situation e is reached.
    // the solver must detect the diamond and not process d again
    // a -> b
    // a -> c
    // b -> d
    // c -> d (diamond)
    // d -> e
    // e -> winning

    // manual mocking :/
    var mocks = HashMap[String, Situation]()
    val calls = new StringBuilder()

    val a = new Situation {
      def successors(): List[Situation] = List(mocks("b"), mocks("c"))

      def isWinning(): Boolean = {
        calls.append("a")
        return false
      }

      val predecessor: Situation = null
    }

    val b = new Situation {
      def successors(): List[Situation] = List(mocks("d"))

      def isWinning(): Boolean = {
        calls.append("b")
        return false
      }

      val predecessor: Situation = a
    }

    val c = new Situation {
      def successors(): List[Situation] = List(mocks("d"))

      def isWinning(): Boolean = {
        calls.append("c")
        return false
      }

      val predecessor: Situation = a
    }

    val d = new Situation {
      def successors(): List[Situation] = List(mocks("e"))

      def isWinning(): Boolean = {
        calls.append("d")
        return false
      }

      val predecessor: Situation = b
    }

    val e = new Situation {
      def successors(): List[Situation] = return Nil

      def isWinning(): Boolean = {
        calls.append("e")
        return true
      }

      val predecessor: Situation = d
    }

    mocks = HashMap[String, Situation]("a" -> a, "b" -> b, "c" -> c, "d" -> d, "e" -> e)

    assertEquals(List(a, b, d, e), solver.findSolutionPath(a))

    assertTrue(calls.toString() == calls.toString().distinct)
    assertEquals("abcde", calls.toString())
  }


}
