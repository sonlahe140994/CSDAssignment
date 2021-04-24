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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author dinht
 */
public class Controller {

    public String nameFile = "book.txt";
    public String pathFile = "C:\\Users\\dinht\\Desktop\\AssignmentCSD\\";
    private static boolean isSaved = true;

    public void menu() {
        System.out.println("1.1: Load data from file");
        System.out.println("1.2: Input & add to the end");
        System.out.println("1.3: Display data");
        System.out.println("1.4: Save book list to file");
        System.out.println("1.5: Search by bcode");
        System.out.println("1.6: Delete by bcode");
        System.out.println("1.7: Sort by bcode");
        System.out.println("1.8: Input & add to begining");
        System.out.println("1.9: Add after position k");
        System.out.println("10: Delete position k");
        System.out.println("11: Exit");
    }

    public Node getNode() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            //create a book and then create a book
            try {
                System.out.print("Enter BCode: ");
                String bcode = in.readLine().trim();
                System.out.println("");
                System.out.print("Enter Title: ");
                String title = in.readLine().trim();
                System.out.println("");
                System.out.print("Enter Quantity: ");
                int quantity = Integer.parseInt(in.readLine().trim());
                System.out.println("");
                System.out.println("Enter Lender: ");
                int lended = Integer.parseInt(in.readLine().trim());
                System.out.println("");
                System.out.print("Enter Price: ");
                double price = Double.parseDouble(in.readLine().trim());
                Book b = new Book(bcode, title, quantity, lended, price);
                Node node = new Node(b);
                return node;
            } catch (IOException | NumberFormatException e) {
                //catch mistake if user input wrong format
                System.err.println(e.getMessage());
            }
        }
    }

    public boolean checkAddBook(Node n) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                //check if user want to add this book
                System.out.println("Do you want to add this book: ");
                System.out.println(n.info);
                String o = in.readLine().trim();
                if (o.equalsIgnoreCase("Y") || o.equalsIgnoreCase("y")) {
                    return true;
                }
                return false;
            } catch (IOException e) {
                //catch if user input wrong format
                System.err.println(e.getMessage());
            }
        }
    }

    public boolean checkFileIsEmpty(String nameOfFile) {
        try {
            //create a file to check
            File f = new File(nameOfFile.trim());
            BufferedReader in = new BufferedReader(new FileReader(f));
            if (in.readLine() == null) {//if have no word in file text then return true
                return true;
            }
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
            System.err.println("Can't not find out fine name " + nameOfFile);
        }
        return false;
    }

    public void writeFile(String fileName, Book[] book) {
        File f = new File(fileName);
        String dataBook = "";
        for (Book b : book) {
            dataBook += b.getBcode().trim() + " - " + b.getTitle() + " - " + b.getQuantity() + " - " + b.getLended() + " - " + b.getPrice() + "\n";
        }
        try {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
            wr.write(dataBook);
            wr.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void getDataFromFile(MyList l, String path) {
        if (isSaved) {//check if file is saved then allow get data
            //if don't check save file then will have conflic between file and list in program
            try {
                File f = new File(path);
                BufferedReader in = new BufferedReader(new FileReader(f));
                String line;
                while ((line = in.readLine()) != null) {
                    Node nodeBook = splitDataFromText(line);
                    l.addLast(nodeBook);
                }
                isSaved = true;
                System.out.println("Load data from text successfull!!!");
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
                System.out.println("Load data from text fail!!!");
                isSaved = false;
            }
        } else {
            System.err.println("You must save file before get data!!!");
        }
    }

    public Node splitDataFromText(String line) {
        if (line.isEmpty()) {
            return null;
        }
        try {
            String[] book = line.trim().split("-");
            String bcode = book[0];
            String title = book[1];
            int Quantity = Integer.parseInt(book[2].trim());
            int lend = Integer.parseInt(book[3].trim());
            double price = Double.parseDouble(book[4].trim());
            Book b = new Book(bcode, title, Quantity, lend, price);
            Node node = new Node(b);
            return node;
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public void saveListToFile(MyList l, File f) {
        if (isSaved = false) {
            Book[] b = l.toArray();
            writeFile(f.getPath(), b);
            isSaved = true;
            System.out.println("Add Success!!!");
        } else {
            System.err.println("You are saved before!!!");
        }
    }

    public void addBookToLast(MyList l, Node n) {
        l.addLast(n);
        isSaved = false;
    }

    public void addBookFirst(MyList l, Node nodeFirst) {
        l.addFirst(nodeFirst);
        isSaved = false;
    }

    public void removeList(MyList l, String xCode) {

        l.remove(xCode);
        isSaved = false;

    }

    public void sortByBCode(MyList l) {
        l.sortByName();
        isSaved = false;
    }

    public void removeNodeByPos(MyList l, int Pos) {
        l.removePos(Pos);
        isSaved = false;
    }

    public void insertAfter(MyList l, int Position, Node nodes) {
        Node nodeAtPos = l.searchBookAt(Position);
        l.insertAfter(nodes, nodeAtPos);
        isSaved = false;
    }

}
