package dbtsolver

import collection.mutable.{LinkedHashSet, HashSet, ListBuffer}

/**
 * A dumb, but tough puzzle solver. Generates a situation graph, starting
 * from the initial situation and expands the graph with the successor situations.
 * The Solver builds the graph breadth-first and stops when he detects a
 * winning situation, that is a solution to the puzzle.
 */
class Solver {

  /**
   * Finds the first solution, starting from the initial situation s0.
   * A solution the path of the closest winning situation.
   *
   * @return A list of situations where the first element is the
   * initial sitiation s0 and the last element is a winning situation.
   */
  def findSolutionPath(s0: Situation): List[Situation] = {

    // a queue for generating the graph breadth-first
    // also a set for fast lookup when detecting diamonds in the graph
    val pending = LinkedHashSet[Situation](s0)

    // a set for fast lookup when detecting cycles in the graph
    val visited = new HashSet[Situation]

    // generate the graph breadth-first and detect the first winning situation
    while (!pending.isEmpty) {
      val situation = pending.head
      pending.remove(situation)
      visited.add(situation)
      if (situation.isWinning()) {
        return situation.forwardPath()
      }
      else {
        // add the successor situations to the queue,
        // but only if they appear for the first time
        val seen = (s: Situation) => visited.contains(s) || pending.contains(s)
        pending ++= situation.successors().filterNot(seen)
      }
    }
    return Nil
  }

  // TODO: introduce class Solution with winning situation and path

  // TODO: there can be more than one solutions to the puzzle
  // maybe add a method that finds all distinct solutions

}
