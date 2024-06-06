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

public class Item {
    private int codigo;
    private String descricao;
    private double valor;

    /* CONSTRUTOR */
    public Item(int codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    /* GETTERS & SETTERS */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    /* MÉTODOS - CADASTRAR, EDITAR, CONSULTAR E LISTAR */
    public static boolean cadastrarItem() throws IOException {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("");
            System.out.println("*** CADASTRAR ITEM ***");
            System.out.print("Insira o código: ");
            int codigo = scan.nextInt();
            scan.nextLine();
            System.out.print("Insira a descrição: ");
            String descricao = scan.nextLine();
            System.out.print("Insira o valor: ");
            double valor = scan.nextDouble();

            if (Integer.toString(codigo).isEmpty() || descricao.isEmpty() || Double.toString(valor).isEmpty()) {
                throw new NullPointerException();
            }

            FileWriter fw = new FileWriter("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\itens.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
    
            bw.write(codigo + " ; " + descricao + " ; " + valor);
            bw.newLine();
            bw.close();
            System.out.println("ITEM CADASTRADO!");
            System.out.println("");
        } catch (NullPointerException e) {
            System.out.println("");
            System.out.println("*** ERRO: ENTRADA INVÁLIDA ***");
            System.out.println("Por favor, insira todos os dados do item ou encerre a operação.");
            System.out.print("Continuar cadastro (1) ou encerrar a operação (2)? ");
            int opcao = scan.nextInt();
            scan.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarItem();
                    break;
                case 2:
                    System.out.println("Encerrando.");
                    System.out.println("");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo itens.txt!");
        } finally {
            scan.close();
        }
        return true;
    }

    public static boolean editarItem() throws IOException {
        Scanner scan = new Scanner(System.in);
        List<Item> listaItens = new ArrayList<>();
        File itens = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\itens.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(itens))) {
            if (!itens.exists() || itens.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Item item = new Item(Integer.parseInt(dados[0]), dados[1], Double.parseDouble(dados[2]));
                    listaItens.add(item);
                }
            }

        System.out.println("");
        System.out.println("*** EDITAR ITEM ***");
        System.out.print("Digite o código do item a ser editado: ");
        int codigo = scan.nextInt();
        scan.nextLine();
        System.out.println("");

        boolean cadastrado = false;
        List<String> listaLinhas = new ArrayList<>();

        for (Item item : listaItens) {
            if (item.getCodigo() == codigo) {
                cadastrado = true;
                System.out.println("Item identificado!");
                System.out.println("");
                System.out.println("*** ALTERAR DADOS ***");
                System.out.println("Insira os dados atualizados abaixo. Caso nenhuma alteração seja necessária, aperte ENTER.");
                System.out.println("");
                System.out.println("Código atual: " + item.getCodigo());
                System.out.print("Novo código: ");
                String codigoAtualizadoS = scan.nextLine();
                int codigoAtualizado;
                if (codigoAtualizadoS.isEmpty()) {
                    codigoAtualizado = item.getCodigo();
                } else {
                    codigoAtualizado = Integer.parseInt(codigoAtualizadoS);
                }
                System.out.println("Descrição atual: " + item.getDescricao());
                System.out.print("Nova descrição: ");
                String descricaoAtualizada = scan.nextLine();
                if (descricaoAtualizada.isEmpty()) {
                    descricaoAtualizada = item.getDescricao();
                }
                System.out.printf("Valor atual: R$ %.2f%n", item.getValor());
                System.out.print("Novo valor: ");
                String valorAtualizadoS = scan.nextLine();
                double valorAtualizado;
                if (valorAtualizadoS.isEmpty()) {
                    valorAtualizado = item.getValor();
                } else {
                    valorAtualizado = Double.parseDouble(valorAtualizadoS);
                }    
                String linhaAtualizada = codigoAtualizado + " ; " + descricaoAtualizada + " ; " + valorAtualizado;
                listaLinhas.add(linhaAtualizada);
                System.out.println("");
                System.out.println("Dados atualizados com sucesso!");
            } else {
                listaLinhas.add(item.getCodigo() + " ; " + item.getDescricao() + " ; " + item.getValor());
            }
        }

        if (!cadastrado) {
            System.out.println("Item com código " + codigo + " não encontrado.");
            return false;
        }

        FileWriter fw = new FileWriter(itens);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String linhaAtualizada : listaLinhas) {
            bw.write(linhaAtualizada);
            bw.newLine();
        }
        bw.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo itens.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo itens.txt!");
        } finally {
            scan.close();
        }

        return true;
    }

    public static Hospede consultarItem() {
        List<Item> listaItens = new ArrayList<>();
        File itens = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\itens.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(itens))) {
            if (!itens.exists() || itens.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Item item = new Item(Integer.parseInt(dados[0]), dados[1], Double.parseDouble(dados[2]));
                    listaItens.add(item);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo itens.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo itens.txt!");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("*** CONSULTAR ITEM ***");
        System.out.print("Digite o código do item a ser consultado: ");
        int codigo = scan.nextInt();
        scan.nextLine();
        System.out.println("");
        scan.close();

        boolean cadastrado = false;

        for (int i = 0; i < listaItens.size(); i++) {
            if (listaItens.get(i).getCodigo() == codigo) {
                cadastrado = true;
                Item item = listaItens.get(i);
                System.out.println("Item identificado!");
                System.out.println("");
                System.out.println("ITEM " + (i + 1));
                System.out.printf("CÓDIGO: %d | DESCRIÇÃO: %s | VALOR: R$ %.2f%n", item.getCodigo(), item.getDescricao(), item.getValor());
                System.out.println("");
            }
        }

        if (!cadastrado) {
            System.out.println("Item com código " + codigo + " não encontrado.");
        }

        return null;
    }

    public static List<Item> listarItens() {
        List<Item> listaItens = new ArrayList<>();
        File itens = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\itens.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(itens))) {

            if (!itens.exists() || itens.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Item item = new Item(Integer.parseInt(dados[0]), dados[1], Double.parseDouble(dados[2]));
                    listaItens.add(item);
                }
            }

            System.out.println("");
            System.out.println("*** LISTA DE ITENS ***");
            for (Item item : listaItens) {
                System.out.println("");
                System.out.println("ITEM " + (listaItens.indexOf(item) + 1));
                System.out.printf("CÓDIGO: %d | DESCRIÇÃO: %s | VALOR: R$ %.2f%n", item.getCodigo(), item.getDescricao(), item.getValor());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo itens.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo itens.txt!");
        }
        System.out.println("");
        return listaItens;
    }
}
