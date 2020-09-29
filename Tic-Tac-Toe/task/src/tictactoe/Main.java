package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.StrictMath.abs;

public class Main {
    public static void main(String[] args) {
        // write your code here

        /*Scanner sc = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String gameString = sc.nextLine().replaceAll(" ", "");
        char[] gameSymbols = new char[9];

        for (int i = 0; i < gameSymbols.length; i++) {
            gameSymbols[i] = gameString.charAt(i);
        }*/
        char[] gameSymbols;
        String preGameString = "_________";
        String gameString = preGameString.replaceAll("_", " ");
        gameSymbols = new char[9];
        for (int i = 0; i < gameSymbols.length; i++) {
            gameSymbols[i] = gameString.charAt(i);
        }


        boolean gameEnded = false;
        showGame(gameSymbols);
        while (!gameEnded) {

            //player X
            //functions play should contain try catch for unwanted symbols like: ´
            playX(gameSymbols);
            showGame(gameSymbols);
            if (makeDecision(gameSymbols)) {
                gameEnded = true;
                break;
            }


            //player O
            playO(gameSymbols);
            showGame(gameSymbols);
            if (makeDecision(gameSymbols)) {
                gameEnded = true;
                break;
            }
        }


    }

    public static void showGame(char[] gameSymbols) {


        System.out.println("---------");

        System.out.println("| " + gameSymbols[0] + " " + gameSymbols[1] + " " + gameSymbols[2] + " |");
        System.out.println("| " + gameSymbols[3] + " " + gameSymbols[4] + " " + gameSymbols[5] + " |");
        System.out.println("| " + gameSymbols[6] + " " + gameSymbols[7] + " " + gameSymbols[8] + " |");

        System.out.println("---------");


    }

    public static boolean makeDecision(char[] gameSymbols) {

        String gameState = " ";
        boolean end = false;
        int xCount = 0;
        int yCount = 0;
        int difference = 0;
        int nullCount = 0;

        for (char gameSymbol : gameSymbols) {
            if (gameSymbol == 'X') {
                xCount += 1;
            } else if (gameSymbol == 'O') {
                yCount += 1;
            } else if (gameSymbol == ' ' || gameSymbol == '_') {
                nullCount += 1;
            }
        }
        difference = xCount - yCount;

        boolean xRows = rowMatch('X', gameSymbols);
        boolean oRows = rowMatch('O', gameSymbols);

        boolean xColumns = columnMatch('X', gameSymbols);
        boolean oColumns = columnMatch('O', gameSymbols);

        boolean xDiagonal = diagonalMatch('X', gameSymbols);
        boolean oDiagonal = diagonalMatch('O', gameSymbols);

        //when no side has a three in a row but the field has empty cells;
        if (!xRows && !oRows && !xColumns && !oColumns && !xDiagonal && !oDiagonal && nullCount > 0) {
            // gameState = "Game not finished";
            return false;
        }
        //when no side has a three in a row and the field has no empty cells;
        if (!xRows && !oRows && !xColumns && !oColumns && !xDiagonal && !oDiagonal && nullCount == 0) {
            System.out.println("Draw");
            return true;
        }
        //when the field has three X in a row;
        if (xRows || xDiagonal || xColumns) {
            System.out.println("X wins");
            return true;
        }
        //  when the field has three O in a row;
        if (oRows || oDiagonal || oColumns) {
            System.out.println("O wins");
            return true;
        }
        // when the field has three X in a row as well as three O in a row. Or the field has a lot more X's than O's or vice versa (if the difference is 2 or more, should be 1 or 0).
        if (((xRows || xDiagonal || xColumns) && (oRows || oDiagonal || oColumns)) || abs(difference) > 1) {
            System.out.println("Impossible");
            return true;
        } else {
            return false;
        }

        // System.out.println(gameState);
    }

    public static boolean rowMatch(char xo, char[] gameSymbols) {
        boolean isSame = false;

        if (gameSymbols[0] == xo && gameSymbols[1] == xo && gameSymbols[2] == xo) {
            isSame = true;
        } else if (gameSymbols[3] == xo && gameSymbols[4] == xo && gameSymbols[5] == xo) {
            isSame = true;
        } else if (gameSymbols[6] == xo && gameSymbols[7] == xo && gameSymbols[8] == xo) {
            isSame = true;
        }

        return isSame;
    }

    public static boolean columnMatch(char xo, char[] gameSymbols) {
        boolean isSame = false;

        if (gameSymbols[0] == xo && gameSymbols[3] == xo && gameSymbols[6] == xo) {
            isSame = true;
        } else if (gameSymbols[1] == xo && gameSymbols[4] == xo && gameSymbols[7] == xo) {
            isSame = true;
        } else if (gameSymbols[2] == xo && gameSymbols[5] == xo && gameSymbols[8] == xo) {
            isSame = true;
        }

        return isSame;
    }

    public static boolean diagonalMatch(char xo, char[] gameSymbols) {
        boolean isSame = false;

        if (gameSymbols[0] == xo && gameSymbols[4] == xo && gameSymbols[8] == xo) {
            isSame = true;
        } else if (gameSymbols[2] == xo && gameSymbols[4] == xo && gameSymbols[6] == xo) {
            isSame = true;
        }

        return isSame;
    }

    public static void playX(char[] gameSymbols) {

        Scanner sc = new Scanner(System.in);
        boolean finished = false;
        while (!finished) {
            System.out.print("Enter cells: ");

            int c1 = sc.nextInt();
            int c2 = sc.nextInt();

            //Coordinates 1 and 1 -> 6th char at symbol array
            if (c1 == 1 && c2 == 1) {
                if (gameSymbols[6] == ' ' || gameSymbols[6] == '_') {
                    gameSymbols[6] = 'X';
                    finished = true;
                } else if (gameSymbols[6] == 'X' || gameSymbols[6] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
                //Coordinates 1 and 2 -> 3rd char at symbol array
            } else if (c1 == 1 && c2 == 2) {
                if (gameSymbols[3] == ' ' || gameSymbols[3] == '_') {
                    gameSymbols[3] = 'X';
                    finished = true;
                } else if (gameSymbols[3] == 'X' || gameSymbols[3] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
                //Coordinates 1 and 3 -> 0 char at symbol array
            } else if (c1 == 1 && c2 == 3) {
                if (gameSymbols[0] == ' ' || gameSymbols[0] == '_') {
                    gameSymbols[0] = 'X';
                    finished = true;
                } else if (gameSymbols[0] == 'X' || gameSymbols[0] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;
                }
            }
            //Coordinates 2 and 1 -> 7th char at symbol array
            else if (c1 == 2 && c2 == 1) {
                if (gameSymbols[7] == ' ' || gameSymbols[7] == '_') {
                    gameSymbols[7] = 'X';
                    finished = true;
                } else if (gameSymbols[7] == 'X' || gameSymbols[7] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
            }
            //Coordinates 2 and 2 -> 4th char at symbol array
            else if (c1 == 2 && c2 == 2) {
                if (gameSymbols[4] == ' ' || gameSymbols[4] == '_') {
                    gameSymbols[4] = 'X';
                    finished = true;
                } else if (gameSymbols[4] == 'X' || gameSymbols[4] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
            }
            //Coordinates 2 and 3 -> 1st char at symbol array
            else if (c1 == 2 && c2 == 3) {
                if (gameSymbols[1] == ' ' || gameSymbols[1] == '_') {
                    gameSymbols[1] = 'X';
                    finished = true;
                } else if (gameSymbols[1] == 'X' || gameSymbols[1] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
            }
            //Coordinates 3 and 1 -> 8th char at symbol array
            else if (c1 == 3 && c2 == 1) {
                if (gameSymbols[8] == ' ' || gameSymbols[8] == '_') {
                    gameSymbols[8] = 'X';
                    finished = true;
                } else if (gameSymbols[8] == 'X' || gameSymbols[8] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
            }
            //Coordinates 3 and 2 -> 5 char at symbol array
            else if (c1 == 3 && c2 == 2) {
                if (gameSymbols[5] == ' ' || gameSymbols[5] == '_') {
                    gameSymbols[5] = 'X';
                    finished = true;
                } else if (gameSymbols[5] == 'X' || gameSymbols[5] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
            }
            //Coordinates 3 and 3 -> 2nd char at symbol array
            else if (c1 == 3 && c2 == 3) {
                if (gameSymbols[2] == ' ' || gameSymbols[2] == '_') {
                    gameSymbols[2] = 'X';
                    finished = true;
                } else if (gameSymbols[2] == 'X' || gameSymbols[2] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
            } else if ((c1 < 1 || c1 > 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if ((c2 < 1 || c2 > 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                System.out.println("You should enter numbers!");
            }


        }


    }

    public static void playO(char[] gameSymbols) {

        Scanner sc = new Scanner(System.in);
        boolean finished = false;
        while (!finished) {
            System.out.print("Enter cells: ");
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();


            //Coordinates 1 and 1 -> 6th char at symbol array
            if (c1 == 1 && c2 == 1) {
                if (gameSymbols[6] == ' ' || gameSymbols[6] == '_') {
                    gameSymbols[6] = 'O';
                    finished = true;
                } else if (gameSymbols[6] == 'X' || gameSymbols[6] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
                //Coordinates 1 and 2 -> 3rd char at symbol array
            } else if (c1 == 1 && c2 == 2) {
                if (gameSymbols[3] == ' ' || gameSymbols[3] == '_') {
                    gameSymbols[3] = 'O';
                    finished = true;
                } else if (gameSymbols[3] == 'X' || gameSymbols[3] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
                //Coordinates 1 and 3 -> 0 char at symbol array
            } else if (c1 == 1 && c2 == 3) {
                if (gameSymbols[0] == ' ' || gameSymbols[0] == '_') {
                    gameSymbols[0] = 'O';
                    finished = true;
                } else if (gameSymbols[0] == 'X' || gameSymbols[0] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
            }
            //Coordinates 2 and 1 -> 7th char at symbol array
            else if (c1 == 2 && c2 == 1) {
                if (gameSymbols[7] == ' ' || gameSymbols[7] == '_') {
                    gameSymbols[7] = 'O';
                    finished = true;
                } else if (gameSymbols[7] == 'X' || gameSymbols[7] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
            }
            //Coordinates 2 and 2 -> 4th char at symbol array
            else if (c1 == 2 && c2 == 2) {
                if (gameSymbols[4] == ' ' || gameSymbols[4] == '_') {
                    gameSymbols[4] = 'O';
                    finished = true;
                } else if (gameSymbols[4] == 'X' || gameSymbols[4] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
            }
            //Coordinates 2 and 3 -> 1st char at symbol array
            else if (c1 == 2 && c2 == 3) {
                if (gameSymbols[1] == ' ' || gameSymbols[1] == '_') {
                    gameSymbols[1] = 'O';
                    finished = true;
                } else if (gameSymbols[1] == 'X' || gameSymbols[1] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
            }
            //Coordinates 3 and 1 -> 8th char at symbol array
            else if (c1 == 3 && c2 == 1) {
                if (gameSymbols[8] == ' ' || gameSymbols[8] == '_') {
                    gameSymbols[8] = 'O';
                    finished = true;
                } else if (gameSymbols[8] == 'X' || gameSymbols[8] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
            }
            //Coordinates 3 and 2 -> 5 char at symbol array
            else if (c1 == 3 && c2 == 2) {
                if (gameSymbols[5] == ' ' || gameSymbols[5] == '_') {
                    gameSymbols[5] = 'O';
                    finished = true;
                } else if (gameSymbols[5] == 'X' || gameSymbols[5] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
            }
            //Coordinates 3 and 3 -> 2nd char at symbol array
            else if (c1 == 3 && c2 == 3) {
                if (gameSymbols[2] == ' ' || gameSymbols[2] == '_') {
                    gameSymbols[2] = 'O';
                    finished = true;
                } else if (gameSymbols[2] == 'X' || gameSymbols[2] == 'Y') {
                    System.out.println("This cell is occupied! Choose another one!");
                    finished = false;

                }
            } else if ((c1 < 1 || c1 > 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if ((c2 < 1 || c2 > 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                System.out.println("You should enter numbers!");
            }


        }


    }

}
