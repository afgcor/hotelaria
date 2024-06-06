import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quarto {
    private int codigo;
    private Categoria categoria;
    private String status;

    /* CONSTRUTOR */
    public Quarto(int codigo, Categoria categoria, String status) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.status = status;
    }

    /* GETTERS & SETTERS */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    /* MÉTODOS - CADASTRAR, EDITAR, CONSULTAR E LISTAR */
    public static boolean cadastrarQuarto() throws IOException {
        /* Scanner scan = new Scanner(System.in);
        try {
            System.out.println("");
            System.out.println("*** CADASTRAR QUARTO ***");
            System.out.print("Insira o código: ");
            int codigo = scan.nextInt();
            System.out.print("Insira a categoria: ");
            String descricao = scan.nextLine();
            System.out.print("Insira o status: ");
            String status = scan.nextLine();
    
            if (Integer.toString(codigo).isEmpty() || status.isEmpty()) {
                throw new NullPointerException();
            }
    
            FileWriter fw = new FileWriter("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\hospedes.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
    
            bw.write(codigo + " ; " + descricao + " ; " + status);
            bw.newLine();
            bw.close();
            System.out.println("QUARTO CADASTRADO!");
            System.out.println("");
        } catch (NullPointerException e) {
            System.out.println("");
            System.out.println("*** ERRO: ENTRADA INVÁLIDA ***");
            System.out.println("Por favor, insira todos os dados do quarto ou encerre a operação.");
            System.out.print("Continuar cadastro (1) ou encerrar a operação (2)? ");
            int opcao = scan.nextInt();
            scan.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarQuarto();
                    break;
                case 2:
                    System.out.println("Encerrando.");
                    System.out.println("");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo quartos.txt!");
        } finally {
            scan.close();
        } */
        return true;
    }

    public static boolean editarQuarto() throws IOException {
        return true;
    }

    public static Quarto consultarQuarto() {
        return null;
    }

    public static List<Quarto> listarQuartos() {
        return null;
    }
}
