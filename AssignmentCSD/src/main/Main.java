/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import List.MyList;
import List.Node;
import Object.Book;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinht
 */
public class Main {

    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static MyList l = new MyList();

    public static void main(String[] args) {

        Controller c = new Controller();
        File f = new File(c.pathFile + c.nameFile);

        while (true) {
            c.menu();
            int Option = 0;
            try {
                Option = Integer.parseInt(in.readLine());
            } catch (IOException | NumberFormatException ex) {
                System.err.println("You must input Number!!!");
            }
            switch (Option) {
                case 1:
                    if (c.checkFileIsEmpty(f.getPath())) {
                        System.out.println("Empty!!!!");
                        return;
                    }
                    c.getDataFromFile(l, f.getPath());
                    break;
                case 2:
                    Node n = c.getNode();
                    if (!l.checkNodeExist(n)) {//check book have exits yet in node
                        System.err.println("Book have exits!!!");
                        break;
                    }
                    if (c.checkAddBook(n)) {
                        c.addBookToLast(l, n);
                    }
                    break;
                case 3:
                    System.out.println("List Book: ");
                    if (l.isEmpty()) {
                        System.err.println("List is Empty!!!");
                        break;
                    }
                    System.out.println("bcode  |  title  |  quantity  |  lended  |  price  |   value");
                    System.out.println("-------------------------------------------------------------");
                    l.traverse();
                    break;
                case 4:
                    File file = null;
                    try {
                        System.out.print("Enter File: ");
                        c.nameFile = in.readLine().trim();//let user input name of file
                        file = getFileSelection(c.nameFile, c.pathFile);
                    } catch (IOException ex) {
                        System.err.println("Error: " + ex.getMessage());
                    }
                    c.saveListToFile(l, file);
                    break;
                case 5:
                    System.out.println("Search By Code");
                    String result = getResultXcode();
                    System.out.println("Result: " + result);

                    break;
                case 6:
                    System.out.println("Delete By xCode");
                    String xCode = getXCode();
                    c.removeList(l, xCode);
                    break;
                case 7:
                    System.out.println("Sort by BCode");
                    c.sortByBCode(l);
                    break;
                case 8:
                    Node nodeFirst = c.getNode();
                    if (!l.checkNodeExist(nodeFirst)) {//check book have exits yet
                        System.err.println("Book have exits!!!");
                        break;
                    }
                    if (c.checkAddBook(nodeFirst)) {
                        c.addBookFirst(l, nodeFirst);
                    }
                    break;
                case 9:
                    System.out.println("Add After Pos");
                    int Position = getPos();
                    Node nodes = c.getNode();
                    if (!l.checkNodeExist(nodes)) {
                        c.insertAfter(l, Position, nodes);
                    }
                    System.out.println("Insert Suceess");
                    break;

                case 10:
                    System.out.println("Remove in Position");
                    int Pos = getPos();
                    c.removeNodeByPos(l, Pos);
                    break;
                case 11:
                    System.exit(0);
                default:
                    System.err.println("You must choose in range 1 -> 11");
                    break;
            }
        }

    }

    private static File getFileSelection(String nameFile, String pathFile) {
        if (!nameFile.endsWith(".txt")) {
            nameFile = nameFile.concat(".txt");
            System.out.println(nameFile);
        }
        return new File(pathFile + nameFile);
    }

    private static String getResultXcode() {
        try {
            String xCode = in.readLine().trim();
            Book b = l.searchBook(xCode);//create book search
            if (b != null) {
                return "Found";
            } else {
                return "Not Found";
            }
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return "Fail!!!";
    }

    private static String getXCode() {
        try {
            String xCode = in.readLine().trim();
            return xCode;
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return null;
    }

    private static int getPos() {
        while (true) {
            try {
                System.out.print("Enter Position: ");
                String Position = in.readLine().trim();
                int pos = Integer.parseInt(Position);
                if (pos > 0) {
                    return pos;
                }
                System.err.println("Wrong format Position!!!");
            } catch (IOException | NumberFormatException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
