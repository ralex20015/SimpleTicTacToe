import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char currentChar = 'X';
        char [][] game = {{'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}};
        printTicTacToe(game);
        String input= getGameOnString(game);
        String stateOfTheGame = "Game not finished";
        while (stateOfTheGame.equals("Game not finished")){
            String x = scanner.next();
            String y = scanner.next();
            scanner.nextLine();
            while(!getIncorrectMessage(x,y,game).equals("")){
                System.out.println(getIncorrectMessage(x,y,game));
                x = scanner.next();
                y = scanner.next();
                scanner.nextLine();
            }

            int numberX = Integer.parseInt(x) -1;
            int numberY = Integer.parseInt(y) -1;
            game[numberX][numberY] = currentChar;
            printTicTacToe(game);
            input = getGameOnString(game);
            currentChar = (currentChar == 'X') ? 'O':'X';
            stateOfTheGame = getStateOfTheGame(input);
        }
        System.out.println(getStateOfTheGame(input));
    }

    private static String getGameOnString(char [][]game){
        String input = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < game[i].length; j++) {
                input += game[i][j];
            }
        }
        return input;
    }

    private static void printTicTacToe(String input){
        String startRow = "| ";
        String finalOfTheRow = "|";
        System.out.println("---------");
        String printRow = startRow + changeUnderlineCharacter(0,input) +" "
                + changeUnderlineCharacter(1,input) +" "+
                changeUnderlineCharacter(2,input) +" "+ finalOfTheRow;
        System.out.println(printRow);
        printRow = startRow + changeUnderlineCharacter(3,input) +" "
                + changeUnderlineCharacter(4,input) +" "+
                changeUnderlineCharacter(5,input) +" "+ finalOfTheRow;
        System.out.println(printRow);
        printRow = startRow + changeUnderlineCharacter(6,input) +" "
                + changeUnderlineCharacter(7,input) +" "+
                changeUnderlineCharacter(8,input) +" "+ finalOfTheRow;
        System.out.println(printRow);
        System.out.println("---------");
    }
    private static char changeUnderlineCharacter(int position, String c){
        if (c.charAt(position) == '_'){
            return ' ';
        }
        return c.charAt(position);
    }

    private static void printTicTacToe(char[][] input){
        String startRow = "| ";
        String finalOfTheRow = "|";
        System.out.println("---------");
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == '_'){
                    input[i][j] = ' ';
                }
            }
        }
        String printRow = startRow + input[0][0] +" "
                + input[0][1] +" "+
                input[0][2] +" "+ finalOfTheRow;
        System.out.println(printRow);
        printRow = startRow + input[1][0] +" "
                + input[1][1] +" "+
                input[1][2] +" "+ finalOfTheRow;
        System.out.println(printRow);
        printRow = startRow + input[2][0] +" "
                + input[2][1] +" "+
                input[2][2] +" "+ finalOfTheRow;
        System.out.println(printRow);
        System.out.println("---------");
    }

    private static String getIncorrectMessage(String x, String y, char[][]game){
        String message = "";

        if(!Character.isDigit(x.charAt(0)) || !Character.isDigit(y.charAt(0))){
            message = "You should enter numbers!";
        }else {
            int numberX = Integer.parseInt(x);
            int numberY = Integer.parseInt(y);

            if (!(numberX >= 1 && numberX <= 3) || !(numberY >= 1 && numberY <= 3)){
                message = "Coordinates should be from 1 to 3!";
            }
            if (numberX-1 < 3 && numberY-1 < 3){
                if (game[numberX-1][numberY-1] == 'X' || game[numberX-1][numberY-1] == 'O'){
                    message = "This cell is occupied! Choose another one!";
                }
            }
        }

        return message;
    }


    private static String getStateOfTheGame(String input){
        boolean OWon = false;
        boolean xWon = false;
        String result = "";
        if (isSomeoneWinning(input,'X')){
            result = "X wins";
            xWon = true;
        }
        if (isSomeoneWinning(input,'O')) {
            result = "O wins";
            OWon = true;
        }
        if ((OWon && xWon) || isMoreOfXOrY(input) ){
            result = "Impossible";
        }
        if ((!OWon && !xWon) && !isThereEmptyCells(input)){
            result = "Draw";
        }
        if (!isMoreOfXOrY(input) && isThereEmptyCells(input) && !xWon && !OWon){
            result = "Game not finished";
        }
        return result;
    }

    private static boolean isSomeoneWinning(String input, char c){
        //Revisa filas
        if (input.charAt(0) == c && input.charAt(1) == c && input.charAt(2) == c){
            return true;
        } else if (input.charAt(3) == c && input.charAt(4) == c && input.charAt(5) == c) {
            return true;
        } else if (input.charAt(6) == c && input.charAt(7) == c && input.charAt(8) == c) {
            return true;
        }
        //Revisa Columnas
        if (input.charAt(0) == c && input.charAt(3) == c && input.charAt(6) == c){
            return true;
        } else if (input.charAt(1) == c && input.charAt(4) == c && input.charAt(7) == c) {
            return true;
        } else if (input.charAt(2) == c && input.charAt(5) == c && input.charAt(8) == c) {
            return true;
        }

        //Revisamos diagonales
        if (input.charAt(0) == c && input.charAt(4) == c && input.charAt(8) == c){
            return true;
        } else if (input.charAt(2) == c && input.charAt(4) == c && input.charAt(6) == c) {
            return true;
        }
        return false;
    }

    private static boolean isMoreOfXOrY(String input){
        int counterOfX = 0;
        int counterOfO = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'X'){
                counterOfX++;
            }
            if (input.charAt(i) == 'O'){
                counterOfO++;
            }
        }
        return (counterOfX - counterOfO >= 2) || (counterOfO - counterOfX >= 2);
    }

    private static boolean isThereEmptyCells(String input){
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '_' || input.charAt(i) == ' '){
                return true;
            }
        }
        return false;
    }
}