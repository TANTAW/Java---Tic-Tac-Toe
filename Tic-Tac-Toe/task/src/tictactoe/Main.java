package tictactoe;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = null;
    private static String line = null;
    private static String line1 = null;
    private static String line2 = null;
    private static String line3 = null;

    public static void main(String[] args) {
        // write your code here
        scanner = new Scanner(System.in);
        line = "         ";
        line1 = line.substring(0, 3);
        line2 = line.substring(3, 6);
        line3 = line.substring(6);
        draw(line1, line2, line3);
        String state = null;
        int counter = 1;
        do {
            int[] coordinates = getCoordinates();

            int index = getIndex(coordinates[0], coordinates[1]);
            char[] chars = line.toCharArray();
            if (counter % 2 == 0) {
                chars[index] = 'O';
            } else {
                chars[index] = 'X';
            }

            line = String.valueOf(chars);
            line1 = line.substring(0, 3);
            line2 = line.substring(3, 6);
            line3 = line.substring(6);
            draw(line1, line2, line3);
            state = checkState(line);
            if (state.contains("wins") || state.equals("Draw")) {
                break;
            }
            counter++;
        } while (true);

        System.out.println(state);
    }

    static int[] getCoordinates() {
        int[] coordinates = new int[2];
        int coordinate1 = 0;
        int coordinate2 = 0;

        System.out.println("Enter the coordinates: ");
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                System.out.println("Enter the coordinates: ");
                scanner.next();
            }
            coordinate1 = scanner.nextInt();
            coordinate2 = scanner.nextInt();
            if (coordinate1 < 1 || coordinate1 > 3 && coordinate2 < 1 || coordinate2 > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (isOccupied(coordinate1, coordinate2)) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                break;
            }

        } while (true);
        coordinates[0] = coordinate1;
        coordinates[1] = coordinate2;
        return coordinates;
    }

    private static boolean isOccupied(int coordinate1, int coordinate2) {
        int index = getIndex(coordinate1, coordinate2);
        return line.charAt(index) != ' ';
    }

    static int getIndex(int coordinate1, int coordinate2) {
        int index = 0;
        if (coordinate1 == 1) {
            index = Math.abs(coordinate1 - coordinate2);
        } else if (coordinate1 == 2) {
            index = coordinate1 + coordinate2;
        } else if (coordinate1 == 3) {
            index = coordinate1 + coordinate2 + 2;
        }
        return index;
    }

    static void draw(String line1, String line2, String line3) {
        System.out.println("---------");
        System.out.println(modifyLine(line1));
        System.out.println(modifyLine(line2));
        System.out.println(modifyLine(line3));
        System.out.println("---------");
    }

    static String modifyLine(String line) {
        StringBuilder newLine = new StringBuilder("| ");
        for (int i = 0; i < line.length(); i++) {
            newLine.append("").append(line.charAt(i)).append(" ");
        }
        newLine.append("|");
        return newLine.toString();
    }

    static boolean isWin(String line) {
        return line.equals("XXX") || line.equals("OOO");
    }

    static String checkState(String line) {
        String state = null;
        String winResult = checkWinState();
        if (winResult != null) {
            state = winResult;
        } else if (count(line, ' ') >= 1) {
            state = "Game not finished";
        } else {
            state = "Draw";
        }
        return state;
    }


    static String checkWinState() {
        String column1 = line1.substring(0, 1) + line2.substring(0, 1) + line3.substring(0, 1);
        String column2 = line1.substring(1, 2) + line2.substring(1, 2) + line3.substring(1, 2);
        String column3 = line1.substring(2, 3) + line2.substring(2, 3) + line3.substring(2, 3);
        String dig1 = line1.substring(0, 1) + line2.substring(1, 2) + line3.substring(2, 3);
        String dig2 = line1.substring(2, 3) + line2.substring(1, 2) + line3.substring(0, 1);

        String result = null;
        if (isWin(line1) && !(isWin(line2) || isWin(line3) || isWin(column1) || isWin(column2) ||
                isWin(column3) || isWin(dig1) || isWin(dig2))) {
            result = line1.substring(0, 1) + " wins";
        } else if (isWin(line2) && !(isWin(line1) || isWin(line3) || isWin(column1) || isWin(column2) ||
                isWin(column3) || isWin(dig1) || isWin(dig2))) {
            result = line2.substring(0, 1) + " wins";
        } else if (isWin(line3) && !(isWin(line1) || isWin(line2) || isWin(column1) || isWin(column2) ||
                isWin(column3) || isWin(dig1) || isWin(dig2))) {
            result = line3.substring(0, 1) + " wins";
        } else if (isWin(column1) && !(isWin(line1) || isWin(line3) || isWin(line2) || isWin(column2) ||
                isWin(column3) || isWin(dig1) || isWin(dig2))) {
            result = column1.substring(0, 1) + " wins";
        } else if (isWin(column2) && !(isWin(line1) || isWin(line3) || isWin(column1) || isWin(line2) ||
                isWin(column3) || isWin(dig1) || isWin(dig2))) {
            result = column2.substring(0, 1) + " wins";
        } else if (isWin(column3) && !(isWin(line1) || isWin(line3) || isWin(column1) || isWin(column2) ||
                isWin(line2) || isWin(dig1) || isWin(dig2))) {
            result = column3.substring(0, 1) + " wins";
        } else if (isWin(dig1) && !(isWin(line1) || isWin(line3) || isWin(column1) || isWin(column2) ||
                isWin(column3) || isWin(line2) || isWin(dig2))) {
            result = dig1.substring(0, 1) + " wins";
        } else if (isWin(dig2) && !(isWin(line1) || isWin(line3) || isWin(column1) || isWin(column2) ||
                isWin(column3) || isWin(dig1) || isWin(line2))) {
            result = dig2.substring(0, 1) + " wins";
        }
        return result;
    }

    static int count(String line, char ch) {
        int len = line.length();
        int counter = 0;
        for (int i = 0; i < len; i++) {
            if (line.charAt(i) == ch) {
                counter++;
            }
        }
        return counter;
    }


}
