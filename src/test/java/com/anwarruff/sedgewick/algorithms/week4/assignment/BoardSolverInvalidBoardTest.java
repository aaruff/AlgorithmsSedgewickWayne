package com.anwarruff.sedgewick.algorithms.week4.assignment;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

/**
 * Created by aruff on 12/3/16.
 */
public class BoardSolverInvalidBoardTest {

    @Test
    public void testTwoByTwo() {
        ArrayList<ExampleBoard> tests = new ArrayList<>();
        tests.add(new ExampleBoard("/week4/puzzle2x2-unsolvable1.txt", -1));
        tests.add(new ExampleBoard("/week4/puzzle2x2-unsolvable2.txt", -1));
        tests.add(new ExampleBoard("/week4/puzzle2x2-unsolvable3.txt", -1));

        for (ExampleBoard test : tests) {
            Board board = ExampleBoard.boardReader(test.fileName);
            Solver boardSolver = new Solver(board);
            assertEquals(test.fileName, test.moves, -1);
            assertFalse(boardSolver.isSolvable());
        }
    }

    @Test
    public void testThreeByThree() {
        ArrayList<ExampleBoard> tests = new ArrayList<>();
        tests.add(new ExampleBoard("/week4/puzzle3x3-unsolvable.txt", -1));
        tests.add(new ExampleBoard("/week4/puzzle3x3-unsolvable1.txt", -1));
        tests.add(new ExampleBoard("/week4/puzzle3x3-unsolvable2.txt", -1));

        for (ExampleBoard test : tests) {
            Board board = ExampleBoard.boardReader(test.fileName);
            Solver boardSolver = new Solver(board);
            assertEquals(test.fileName, test.moves, -1);
            assertFalse(boardSolver.isSolvable());
        }
    }

//    @Test
//    public void testFourByFour() {
//        ArrayList<ExampleBoard> tests = new ArrayList<>();
//        tests.add(new ExampleBoard("/week4/puzzle4x4-unsolvable.txt", -1));
//
//        for (ExampleBoard test : tests) {
//            Board board = ExampleBoard.boardReader(test.fileName);
//            Solver boardSolver = new Solver(board);
//            assertEquals(test.fileName, test.moves, -1);
//            assertFalse(boardSolver.isSolvable());
//        }
//    }

}
