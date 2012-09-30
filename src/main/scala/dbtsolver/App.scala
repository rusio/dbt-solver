package dbtsolver

import java.util.Arrays
import situation.FastBarlinSituation

/**
 * Main class.
 */
object App {

  def getInitialSituation(args: Array[String]): Situation = {
    val bars = if (args.length > 0) args.map(Integer.parseInt) else Array(1, 2, 3, 4, 5)
    return FastBarlinSituation(null, bars)
  }

  def main(args: Array[String]) {
    println("====================================================================")
    println("")

    val s0 = getInitialSituation(args)

    println("Solving Barlin Puzzle for initial situation " + s0 + " ...")
    println("")

    val solver = new Solver()
    val solutionPath: List[Situation] = solver.findSolutionPath(s0)

    solutionPath.foreach(println)

    println("")
    println("====================================================================")
  }

}
