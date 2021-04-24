/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

import Object.Book;

/**
 *
 * @author dinht
 */
public class MyList {

    Node head, tail;

    public MyList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    //traverse
    public void traverse() {
        Node current = head;
        int index = 0;
        while (current != null) {
            System.out.println(index + " - ");
            visit(current);
            current = current.next;
            index++;
        }
        System.out.println("");
    }

    private void visit(Node current) {
        if (current != null) {
            Book b = current.info;
            System.out.println(b.getBcode() + " | " + b.getTitle() + " | " + b.getLended() + " | " + b.getPrice() + " | " + b.getPrice() * b.getQuantity());
        }
    }

    //addfirst
    public void addFirst(Node node) {
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    //addlast
    public void addLast(Node node) {
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }

    }

    //to Array
    public Book[] toArray() {
        Book[] b = new Book[size()];
        Node current = head;
        int i = 0;
        while (current != null) {
            b[i] = current.info;
            current = current.next;
            i++;
        }
        return b;
    }

    private int size() {
        Node current = head;
        int size = 0;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    public void removePos(int k) {
        Node current = head;
        int index = 0;
        while (current != null) {
            current = current.next;
            index++;
            if (index == k) {
                remove(current);
            }
        }
    }

    void remove(Node q) {
        Node current = head;
        int pos = 0;
        while (current.next != null) {
            current = current.next;
            pos++;
            if (current.info.bcode.equalsIgnoreCase(q.info.bcode)) {
                break;
            }
        }
        if (pos == 0) {
            removeFirst();
        } else if (pos == size()) {
            removeLast();
        } else {
            current = head;
            Node prev = null;
            while (current.next != null) {
                prev = current;
                current = current.next;
                if (current.info.bcode.equalsIgnoreCase(q.info.bcode)) {
                    break;
                }
            }
            prev.next = current.next;
        }
    }

    public void sortByName() {
        Node pi, pj;
        for (pi = head; pi != null; pi = pi.next) {
            for (pj = pi.next; pj != null; pj = pj.next) {
                if (pj.info.bcode.compareTo(pi.info.bcode) < 0) {
                    Book temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
            }
        }
    }

    public boolean checkNodeExist(Node node) {
        Node current = head;
        while (current != null) {
            if (current.info.getBcode().equalsIgnoreCase(node.info.getBcode())) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Book searchBook(String xCode) {
        Node current = head;
        while (current != null) {
            if (current.info.getBcode().trim().equalsIgnoreCase(xCode.trim())) {
                return current.info;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(String xName) {
        Node current = head;
        int pos = 0;
        while (current.next != null) {
            current = current.next;
            pos++;
            if (current.info.getBcode().equalsIgnoreCase(xName)) {
                break;
            }
        }
        if (pos == 0) {
            removeFirst();
        } else if (pos == size()) {
            removeLast();
        } else {
            current = head;
            Node prev = null;
            while (current.next != null) {
                prev = current;
                current = current.next;
                if (current.info.getBcode().equalsIgnoreCase(xName)) {
                    break;
                }
            }
            prev.next = current.next;
        }
    }

    private void removeFirst() {
        head = head.next;
    }

    private void removeLast() {
        Node current = head;
        Node prev = null;
        while (current.next != null) {
            prev = current;
            current = current.next;

        }
        tail = prev.next;
        prev.next = null;
    }

    public Node searchBookAt(int Position) {
        Node current = head;
        int index = 0;
        while (index != Position) {
            current = current.next;
            index++;
        }
        return current;
    }

    public void insertAfter(Node nodeInsert, Node nodeIndex) {
        Node current = head;
        int pos = 0;
        while (!current.info.bcode.equalsIgnoreCase(nodeIndex.info.bcode)) {
            current = current.next;
            pos++;
        }
        if (isEmpty()) {
            return;
        }
        if (pos == 0) {
            addFirst(nodeInsert);
        } else {
            Node node = nodeInsert;
            current = head;
            Node previous = null;

            int i = 0;

            while (i < pos) {
                previous = current;
                current = current.next;
                if (current == null) {
                    break;
                }
                i++;
            }
            node.next = current.next;
            current.next = node;

        }
    }
}