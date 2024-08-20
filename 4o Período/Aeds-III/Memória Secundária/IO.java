import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import entidades.Cliente;
import entidades.Livro;

public class IO {
    public static void main(String[] args) {
        Arquivo arqClientes;
        Cliente c1 = new Cliente("José Alves", "12345678901", 3245.21F, LocalDate.of(1998, 4, 21));
        Cliente c2 = new Cliente("Ana Rodrigues", "09876543210", 4267.98F, LocalDate.of(2003, 8, 15));
        Cliente c3 = new Cliente("Carlos Mourão", "56439593721", 2854.23F, LocalDate.of(2001, 1, 7));

        Arquivo arqLivros;
        Livro l1 = new Livro("Eu, Robô", "Isaac Asimov", 14.9F);
        Livro l2 = new Livro("Eu Sou A Lenda", "Richard Matheson", 21.99F);

        try {
            // Escrita Clientes
            arqClientes = new Arquivo("clientes.db");
            arqClientes.createCliente(c1);
            arqClientes.createCliente(c2);
            arqClientes.createCliente(c3);

            // Leitura Clientes
            Cliente c = arqClientes.readCliente("56439593721");
            if(c != null) System.out.println(c);
            else System.out.println("\nCliente não encontrado!");

            c = arqClientes.readCliente("12345678901");
            if(c != null) System.out.println(c);
            else System.out.println("\nCliente não encontrado!");

            c = arqClientes.readCliente("24334564234");
            if(c != null) System.out.println(c);
            else System.out.println("\nCliente não encontrado!");

            arqClientes.close();
            System.out.println("\n-----------");

            // Escrita Livros
            arqLivros = new Arquivo("livros.db");
            arqLivros.createLivro(l1);
            arqLivros.createLivro(l2);

            // Leitura Livros
            Livro l = arqLivros.readLivro("Eu, Robô");
            if(l != null) System.out.println(l);
            else System.out.println("\nLivro não encontrado!");

            l = arqLivros.readLivro("Eu Sou A Lenda");
            if(l != null) System.out.println(l);
            else System.out.println("\nLivro não encontrado!");

            l = arqLivros.readLivro("O Hobbit");
            if(l != null) System.out.println(l);
            else System.out.println("\nLivro não encontrado!");


        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}