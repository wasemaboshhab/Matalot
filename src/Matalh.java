import java.util.EventListener;
import java.util.Scanner;

public class Matalh {
    public static final char X_SYMBOL = 'x';
    public static final char O_SYMBOL = '0';
    public static final int FIRST_PLAYER = 1;
    public static final int SECOND_PLAYER = 2;

    public static void main(String[] args) {




    }

    public static void game() {
        char[] x0 = { '1' ,'2' , '3', '4', '5', '6', '7','8' ,'9'};
        boolean play  = true;
        int turn = 1, countSteps = 9;
        System.out.println("first player with symbol X start the Game");
        printBoard(x0);

            while (play && countSteps > 0) {
                int userPosition = getPositionFromUser(x0);
                if (isAvailable(x0, userPosition)) {
                    if (turn == FIRST_PLAYER) {
                        turn = SECOND_PLAYER;
                        play = !placeSymbolInBoard(x0, userPosition, X_SYMBOL);
                    } else {
                        turn = FIRST_PLAYER;
                        play = !placeSymbolInBoard(x0, userPosition, O_SYMBOL);
                    }
                    countSteps--;
                }
            }
            if (countSteps == 0) {
                System.out.println(" NO Winners :)");
            }


    }

    public static boolean placeSymbolInBoard(char[] arr, int index, char playerSymbol) {
        boolean checkWinner = false;
        arr[index] = playerSymbol;
        printBoard(arr);
        char playerWinner = checkWinner(arr);
        if (playerWinner != '-') {
            checkWinner = true;
            System.out.println("the winner is : " + playerWinner);

        }

        return checkWinner;
    }

    public static void printBoard(char[] symbols) {
        int counter = 3;

        for (int i = 0; i < symbols.length; i++) {
            System.out.print(symbols[i] + " ");
            counter--;
            if (counter == 0) {
                System.out.println();
                counter = 3;
            }

        }
    }

    public static boolean isAvailable(char[] arr, int index) {
        boolean available = true;
        if (arr[index] == 'x' || arr[index] == '0') {
            available = false;
        }
        return available;
    }

    public static int getPositionFromUser(char[]x0) {
        Scanner scanner = new Scanner(System.in);
        int userPosition;
        boolean invalidPosition;
       /* boolean validPosition;
        System.out.println();*/
        do {
            System.out.println("choose a positions  1-9");
            userPosition = scanner.nextInt();
            invalidPosition = userPosition < 1 || userPosition > 9 ;

            if (!invalidPosition && x0[userPosition-1] == 'x' || x0[userPosition-1] == '0') {
                System.out.println("The place is occupied");
            }
           /* validPosition = userPosition >= 1 && userPosition <= 9;
            System.out.println();*/
        } while (invalidPosition );

        return --userPosition;

    }

    public static char checkWinner(char[] array) {
        char winner = '-';
        winner = checkWinnerByRow(array);
        if (winner == '-') {
            winner = checkWinnerByColumn(array);
            if (winner != '5') {
                if (array[4] == X_SYMBOL) {
                    winner = checkWinnerBySlant(array, X_SYMBOL);
                } else {
                    winner = checkWinnerBySlant(array,O_SYMBOL);
                }

            }
        }
        return winner;
    }

    private static char checkWinnerBySlant(char[] array , char symbol) {
        char winner = '-';
        boolean winnerBysSlant = array[0] == symbol  && array[8] == symbol
               ||   array[2] == symbol   && array[6] == symbol;
        if (winnerBysSlant) {
            winner = symbol;
        }

        return winner;
    }

    public static char checkWinnerByColumn(char[] symbols) {

        int columns = 3;
        char symbol = '-';
        int countPointsPlayerX = 0;
        int countPointsPlayer0 = 0;
        for (int i = 0; i < columns; i++) {
            for (int cell = i; cell < (i+1) + 6 ; cell += columns) {
                if (symbols[cell] == X_SYMBOL) {
                    countPointsPlayerX++;
                } else if (symbols[cell] == O_SYMBOL) {
                    countPointsPlayer0++;
                } else break;
            }
            if (countPointsPlayer0 == columns) {
                return X_SYMBOL;
            } else if (countPointsPlayerX == columns) {
                return O_SYMBOL;
            } else {
                countPointsPlayer0 = 0;
                countPointsPlayerX = 0;
            }
        }
        return symbol;
    }

    public static char checkWinnerByRow(char[] symbols) {
        int rows = 3;
        char winnerSymbol = '-';
        int countPointsPlayerX = 0;
        int countPointsPlayer0 = 0;
//        {
//            '0', '0', '0',
//            'x', 'x', 'x',
//            'x', '0', 'x'
//        };
        for (int i = 0; i < rows; i++) {
            for (int cell = i * 3; cell < rows *(i+1); cell++) {

                if (symbols[cell] == 'x') {
                    countPointsPlayerX++;
                } else if (symbols[cell] == '0') {
                    countPointsPlayer0++;
                } else break;
            }
            if (countPointsPlayer0 == rows) {
                return '0';
            } else if (countPointsPlayerX == rows) {
                return 'x';
            } else {
                countPointsPlayer0 = 0;
                countPointsPlayerX = 0;
            }

        }
        return winnerSymbol;
    }

    public static int checkIfArrayUpDown(int[] array) {

        int indexOfBesga = -1;
        int numberOfBesga = 0;

        for (int i = 0; i < array.length; i++) {
            if (i < array.length - 2) {
                numberOfBesga = array[i + 1];
                if (array[i] < numberOfBesga && array[i + 2] < numberOfBesga) {
                    indexOfBesga = i + 1;
                }
            }
        }

        return indexOfBesga;
    }


}
