dbt-solver
==========

A dumb, but tough puzzle solver.

A generic puzzle solver that can solve all kinds of puzzles where starting from
an initial situation you have to do various moves in order to reach a final situation
which solves the puzzle. The solver models the situations as nodes in a graph and uses
brute-force breadth-first search to find the solution with the shortest distance from
the initial situation.
