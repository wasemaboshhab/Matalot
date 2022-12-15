import java.util.Scanner;

public class Matalh {

    public static void main(String[] args) {

        game();


    }

    public static void game() {
        char[] x0 = { '1' ,'2' , '3', '4', '5', '6', '7','8' ,'9'};
        char player1symbol = 'x';
        char player2symbol = '0';
        int turn = 1;
        System.out.println("first player with symbol X start the Game");
        printBoard(x0);
        boolean play  = true;
        int countMaxSteps = 9;


        while (play && countMaxSteps > 0) {
            int userPosition = getPositionFromUser(x0);
            if (isAvailable(x0, userPosition)) {
                if (turn == 1) {
                    turn = 2;
                    play = !placeSymbolInBoard(x0, userPosition, player1symbol);
                } else {
                    turn = 1;
                    play = !placeSymbolInBoard(x0, userPosition, player2symbol);
                }
                countMaxSteps--;
            }
        }
        if (countMaxSteps == 0) {
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
            if (winner == '-') {
                winner = checkWinnerBySlant(array);

            }
        }
        return winner;
    }

    private static char checkWinnerBySlant(char[] array) {
        char winner = '-';
        boolean xWinner = array[0] == 'x' && array[4] == 'x' && array[8] == 'x'
               ||array[2] == 'x' && array[4] == 'x' && array[6] == 'x';

        boolean oWinner = array[2] == '0' && array[4] == '0' && array[6] == '0'
                || array[0] == 'x' && array[4] == 'x' && array[8] == 'x';



        if (xWinner) {
            winner = 'x';
        } else if (oWinner) {
            winner = '0';
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
                if (symbols[cell] == 'x') {
                    countPointsPlayerX++;
                } else if (symbols[cell] == '0') {
                    countPointsPlayer0++;
                } else break;
            }
            if (countPointsPlayer0 == columns) {
                return '0';
            } else if (countPointsPlayerX == columns) {
                return 'x';
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
