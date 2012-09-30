package dbtsolver

import org.junit._
import Assert._

@Test
class SituationTest {

  @Test
  def testPredecessors() = {

    val s0 = new Situation {
      def successors(): List[Situation] = Nil

      def isWinning(): Boolean = false

      val predecessor: Situation = null
    }

    val s1 = new Situation() {
      def successors(): List[Situation] = Nil

      def isWinning(): Boolean = false

      val predecessor: Situation = s0
    }

    val s2 = new Situation() {
      def successors(): List[Situation] = Nil

      def isWinning(): Boolean = false

      val predecessor: Situation = s1
    }

    val s3 = new Situation() {
      def successors(): List[Situation] = Nil

      def isWinning(): Boolean = false

      val predecessor: Situation = s2
    }

    assertEquals(List(s0, s1, s2, s3), s3.forwardPath())

  }
}
