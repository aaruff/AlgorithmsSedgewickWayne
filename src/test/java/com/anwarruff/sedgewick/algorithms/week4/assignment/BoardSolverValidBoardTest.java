package com.anwarruff.sedgewick.algorithms.week4.assignment;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * Created by aruff on 11/28/16.
 */
public class BoardSolverValidBoardTest {
    @Test
    public void testSolutionBoard() {
        int[][] solutionTiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board solutionBoard = new Board(solutionTiles);
        Solver boardSolver = new Solver(solutionBoard);
        assertTrue(boardSolver.isSolvable());
    }

    @Test
    public void testSolvable() {
        ArrayList<ExampleBoard> tests = new ArrayList<>();
        for (int i = 0; i <= 5; ++i) {
            String filename;
            if (i < 10) {
               filename = "/week4/puzzle0" + i + ".txt";
            }
            else {
                filename = "/week4/puzzle" + i + ".txt";
            }
            tests.add(new ExampleBoard(filename, i));
        }

        for (ExampleBoard test : tests) {
            Board board = ExampleBoard.boardReader(test.fileName);
            Solver boardSolver = new Solver(board);
            assertEquals(test.fileName, test.moves, boardSolver.moves());
            assertTrue(boardSolver.isSolvable());
        }
    }

    @Test
    public void testHardProblems() {
        ArrayList<ExampleBoard> tests = new ArrayList<>();
        tests.add(new ExampleBoard("/week4/puzzle4x4-hard1.txt", 38));
        tests.add(new ExampleBoard("/week4/puzzle4x4-hard2.txt", 47));

        for (ExampleBoard test : tests) {
            Board board = ExampleBoard.boardReader(test.fileName);
            Solver boardSolver = new Solver(board);
            assertEquals(test.fileName, test.moves, boardSolver.moves());
            assertTrue(boardSolver.isSolvable());
        }
    }

    @Test
    public void testAltSizeProblems() {
        ArrayList<ExampleBoard> tests = new ArrayList<>();
        for (int i = 0; i <= 6; ++i) {
            String f1 = "/week4/puzzle2x2-0" + i + ".txt";
            tests.add(new ExampleBoard(f1, i));
        }
        for (int i = 0; i <= 31; ++i) {
            String filename;
            if (i < 10) {
                filename = "/week4/puzzle3x3-0" + i + ".txt";
            }
            else {
                filename = "/week4/puzzle3x3-" + i + ".txt";
            }
            tests.add(new ExampleBoard(filename, i));
        }
        for (int i = 0; i <= 50; ++i) {
            String filename;
            if (i < 10) {
                filename = "/week4/puzzle4x4-0" + i + ".txt";
            }
            else {
                filename = "/week4/puzzle4x4-" + i + ".txt";
            }
            tests.add(new ExampleBoard(filename, i));
        }

        for (ExampleBoard test : tests) {
            Board board = ExampleBoard.boardReader(test.fileName);
            Solver boardSolver = new Solver(board);
            assertEquals(test.fileName, test.moves, boardSolver.moves());
            assertTrue(boardSolver.isSolvable());
        }
    }
}