//package ooad.Database;
//
//import java.util.Scanner;
//
//public class MainMenu implements Menu{
//    boolean exit;
//    boolean ingelogd = false;
//
//    @Override
//    public void runMenu() {
//        printHeader();
//        while (!exit) {
//            printMenu();
//            int choice = getMenuChoice();
//            performAction(choice);
//        }
//    }
//
//    @Override
//    public void printHeader() {
//        System.out.println("+-----------------------------------+");
//        System.out.println("|          Welkom bij de            |");
//        System.out.println("|         Vagado Quiz App           |");
//        System.out.println("+-----------------------------------+");
//    }
//


//    @Override
//    public void printMenu() {
//        displayHeader("Maak alstublieft een keuze");
//        System.out.println("1) Registreren");
//        System.out.println("2) Inloggen");
//        System.out.println("3) Winkel");
//        System.out.println("4) Quiz spelen");
//        System.out.println("0) Exit");
//    }
//
//
//    @Override
//    public void performAction(int choice) {
//        switch (choice) {
//            case 1:
//                if(ingelogd){
//                    System.out.println("U bent reeds ingelogd, maak een andere keuze");
//                } else {
//                    //registreren();
//                }
//                break;
//            case 2:
//                if(ingelogd){
//                    System.out.println("U bent reeds ingelogd, maak een andere keuze");
//                } else {
//                    //inloggen();
//                }
//                break;
//            case 3:
//                if(ingelogd) {
//                    //vragenlijstKopen();
//                } else {
//                    System.out.println("U bent nog niet ingelogd, doe dit eerst.");
//                }
//                break;
//            case 4:
//                if(ingelogd) {
//                    //quizSpelen();
//                } else {
//                    System.out.println("U bent nog niet ingelogd, doe dit eerst.");
//                }
//                break;
//            case 0:
//                System.out.println("Bedankt voor het gebruiken van onze applicatie.");
//                System.exit(0);
//                break;
//            default:
//                System.out.println("Error");
//        }
//    }
//
//}
