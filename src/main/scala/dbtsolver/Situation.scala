package dbtsolver

/**
 * A situation in a puzzle holds the current state of the puzzle.
 *
 * Implement this for your favorite puzzle game and give the initial
 * situation to the Solver :)
 */
trait Situation {

  /**The situation from which this situation was produced. */
  val predecessor: Situation

  /**If true, than this situation is a winning solution of the puzzle. */
  def isWinning(): Boolean

  /**Generates all successor situations for this situation. */
  def successors(): List[Situation]

  /**
   * The path from the initial situation to this situation.
   *
   * @return A list with the initial situation, any intermediate situations and this Situation.
   */
  def forwardPath(): List[Situation] = {
    return reversePath().reverse
  }

  /**
   * The path from this situation to the the initial situation.
   *
   * @return A list with this situation, any intermediate situations and the initial situation.
   */
  def reversePath(): List[Situation] = predecessor match {
    case null => List(this)
    case _ => this :: predecessor.reversePath()
  }

}
