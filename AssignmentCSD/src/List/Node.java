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
public class Node {

    public Book info;
    public Node next;

    public Node(Book b) {
        info = b;
        next = null;
    }

   

}
