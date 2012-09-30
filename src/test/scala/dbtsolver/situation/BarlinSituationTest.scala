package dbtsolver.situation

import org.junit._
import Assert._

import dbtsolver.situation.{BarlinSituation => BS}

@Test
class BarlinSituationTest {

  @Test
  def testEmptySituation() {

    def a = BS(null, Array[Int]())
    assertEquals("[]", a.toString())
    assertFalse(a.isWinning())

    def b = BS(null, Array[Int]())

    assertEquals(a, a)

    assertEquals(a, b)
    assertEquals(b, a)

    assertEquals(a.hashCode(), b.hashCode())
  }

  @Test
  def testOneBarSituation() {

    def a = BS(null, Array[Int](1))
    assertEquals("[1]", a.toString())
    assertTrue(a.isWinning())

    def b = BS(null, Array[Int](1))

    assertEquals(a, b)
    assertEquals(b, a)

    assertEquals(a.hashCode(), b.hashCode())
  }

  @Test
  def testTwoBarSituation() {

    def a = BS(null, Array[Int](1, 2))
    assertEquals("[1, 2]", a.toString())
    assertFalse(a.isWinning())

    def b = BS(null, Array[Int](1, 2))

    assertEquals(a, b)
    assertEquals(b, a)

    assertEquals(a.hashCode(), b.hashCode())
  }

  @Test
  def testThreeBarSituation() {

    def a = new BS(null, Array[Int](1, 2, 3))
    assertFalse(a.isWinning())

    def b = new BS(null, Array[Int](3, 2, 1))
    assertFalse(b.isWinning())

    def c = new BS(null, Array[Int](2, 2, 2))
    assertTrue(c.isWinning())

    def d = BS(null, Array[Int](2, 2, 2))

    assertTrue(a != b)
    assertTrue(b != a)
    assertTrue(c == c)
    assertTrue(c == d)
  }

  @Test
  def testAdjacency() {
    def s0 = BS(null, Array(2, 3))
    val s1 = BS(s0, Array(3, 3))
    val s2 = BS(s0, Array(1, 3))
    val s3 = BS(s0, Array(2, 4))
    val s4 = BS(s0, Array(2, 2))

    def successors = s0.successors();

    assertEquals(4, successors.size)
    assertTrue(successors.contains(s1))
    assertTrue(successors.contains(s2))
    assertTrue(successors.contains(s3))
    assertTrue(successors.contains(s4))

    assertTrue(successors.forall(_.predecessor == s0));
  }


}
