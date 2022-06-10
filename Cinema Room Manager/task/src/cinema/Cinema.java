package cinema;

import java.util.Objects;
import java.util.Scanner;

public class Cinema {
    private static Scanner sc = new Scanner(System.in);
    private static int rowsNum;
    private static int seatNum;
    private static String[][] str;
    private static double percentage = 0.001d;
    private static int currentIncome;
    private static int totalIncome;
    private static int purchasedT;
    private static int totalSeats;

    public static void Menu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int temp = sc.nextInt();
        boolean b = true;
        if (temp == 1) {
            CinemaScheme(str);
        } else if (temp == 2) {
            BuyATicket();
        } else if (temp == 3) {
            Statistics();
        } else {
            return;
        }
    }

    public static void Statistics() {
        System.out.println("\nNumber of purchased tickets: " + purchasedT);
        System.out.print("Percentage: ");
        System.out.printf("%.2f", percentage);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
        Menu();
    }

    public static void CinemaScheme(String[][] str) {
        System.out.println("\nCinema:");
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str[i].length; j++) {
                System.out.print(str[i][j] + " ");
            }
            System.out.println();
        }
        Menu();
    }

    public static void BuyATicket() {
        System.out.println("Enter a row number:");
        int rowNum = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = sc.nextInt();
        if (rowNum > str.length-1 || seat > str[rowsNum].length-1 || rowNum <= 0 || seat <= 0) {
            System.out.println("\nWrong input!\n");
            BuyATicket();
        } else {
            if (Objects.equals(str[rowNum][seat], "B")) {
                System.out.println("\nThat ticket has already been purchased!\n");
                BuyATicket();
            }
            int price = 0;
            if (totalSeats <= 60) {
                price = 10;
            } else if (totalSeats > 60 && rowsNum % 2 == 1) {
                if (rowNum <= str.length/2-1) {
                    price = 10;
                } else {
                    price = 8;
                }
            } else if (totalSeats > 60 && rowsNum % 2 == 0) {
                if (rowNum <= str.length/2-1) {
                    price = 10;
                } else {
                    price = 8;
                }
//            income = ((numOfRows / 2) * numOfSeats) * 10 + ((numOfRows / 2) * numOfSeats) * 8;
            }
            System.out.println("\nTicket price: $" + price);
            str[rowNum][seat] = "B";
            currentIncome += price;
            purchasedT++;
            percentage = (purchasedT*100)/(double)totalSeats;
            rowNum = 0;
            seat = 0;
            Menu();
        }

    }

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        rowsNum = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatNum = sc.nextInt();
        str = new String[rowsNum + 1][seatNum + 1];
        int i1 = 0;
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str[i].length; j++) {
                str[i][j] = i1 + "";
                i1++;
            }
            i1 = i + 1;
        }
        str[0][0] = " ";
        for (int i = 1; i < str.length; i++) {
            for (int j = 1; j < str[i].length; j++) {
                str[i][j] = "S";
            }
        }
        totalSeats = seatNum * rowsNum;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else if (totalSeats > 60 && rowsNum % 2 == 1) {
            int first = rowsNum / 2;
            int second = rowsNum - first;
            totalIncome = (second * seatNum) * 8 + (first * seatNum) * 10;
        } else if (totalSeats > 60 && rowsNum % 2 == 0) {
            totalIncome = ((rowsNum / 2) * seatNum) * 10 + ((rowsNum / 2) * seatNum) * 8;
        }
        Menu();
        sc.close();
    }
}