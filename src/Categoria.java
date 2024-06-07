import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Categoria {
    private int codigo;
    private String descricao;
    private double valor;

    /* CONSTRUTOR */
    public Categoria(int codigo, String descricao, double valor) {
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
    public static boolean cadastrarCategoria() throws IOException {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("");
            System.out.println("*** CADASTRAR CATEGORIA ***");
            System.out.print("Insira o código: ");
            String codigo = scan.nextLine();
            System.out.print("Insira a descrição: ");
            String descricao = scan.nextLine();
            System.out.print("Insira o valor: ");
            String valor = scan.nextLine();

            if (codigo.isEmpty()|| descricao.isEmpty() || valor.isEmpty()) {
                throw new NullPointerException();
            }

            FileWriter fw = new FileWriter("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\categorias.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
    
            bw.write(Integer.parseInt(codigo) + " ; " + descricao + " ; " + Double.parseDouble(valor));
            bw.newLine();
            bw.close();
            System.out.println("CATEGORIA CADASTRADA!");
            System.out.println("");
        } catch (NullPointerException e) {
            System.out.println("");
            System.out.println("*** ERRO: ENTRADA INVÁLIDA ***");
            System.out.println("Por favor, insira todos os dados da categoria ou encerre a operação.");
            System.out.print("Continuar cadastro (1) ou encerrar a operação (2)? ");
            int opcao = scan.nextInt();
            scan.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarCategoria();
                    break;
                case 2:
                    System.out.println("Encerrando.");
                    System.out.println("");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo categorias.txt!");
        } finally {
            scan.close();
        }
        return true;
    }

    public static boolean editarCategoria() throws IOException {
        Scanner scan = new Scanner(System.in);
        List<Categoria> listaCategorias = new ArrayList<>();
        File categorias = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\categorias.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(categorias))) {
            if (!categorias.exists() || categorias.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Categoria categoria = new Categoria(Integer.parseInt(dados[0]), dados[1], Double.parseDouble(dados[2]));
                    listaCategorias.add(categoria);
                }
            }

        System.out.println("");
        System.out.println("*** EDITAR CATEGORIA ***");
        System.out.print("Digite o código da categoria a ser editada: ");
        int codigo = scan.nextInt();
        scan.nextLine();
        System.out.println("");

        boolean cadastrado = false;
        List<String> listaLinhas = new ArrayList<>();

        for (Categoria categoria : listaCategorias) {
            if (categoria.getCodigo() == codigo) {
                cadastrado = true;
                System.out.println("Categoria identificada!");
                System.out.println("");
                System.out.println("*** ALTERAR DADOS ***");
                System.out.println("Insira os dados atualizados abaixo. Caso nenhuma alteração seja necessária, aperte ENTER.");
                System.out.println("");
                System.out.println("Código atual: " + categoria.getCodigo());
                System.out.print("Novo código: ");
                String codigoAtualizadoS = scan.nextLine();
                int codigoAtualizado;
                if (codigoAtualizadoS.isEmpty()) {
                    codigoAtualizado = categoria.getCodigo();
                } else {
                    codigoAtualizado = Integer.parseInt(codigoAtualizadoS);
                }
                System.out.println("Descrição atual: " + categoria.getDescricao());
                System.out.print("Nova descrição: ");
                String descricaoAtualizada = scan.nextLine();
                if (descricaoAtualizada.isEmpty()) {
                    descricaoAtualizada = categoria.getDescricao();
                }
                System.out.printf("Valor atual: R$ %.2f%n", categoria.getValor());
                System.out.print("Novo valor: ");
                String valorAtualizadoS = scan.nextLine();
                double valorAtualizado;
                if (valorAtualizadoS.isEmpty()) {
                    valorAtualizado = categoria.getValor();
                } else {
                    valorAtualizado = Double.parseDouble(valorAtualizadoS);
                }    
                String linhaAtualizada = codigoAtualizado + " ; " + descricaoAtualizada + " ; " + valorAtualizado;
                listaLinhas.add(linhaAtualizada);
                System.out.println("");
                System.out.println("Dados atualizados com sucesso!");
            } else {
                listaLinhas.add(categoria.getCodigo() + " ; " + categoria.getDescricao() + " ; " + categoria.getValor());
            }
        }

        if (!cadastrado) {
            System.out.println("Categoria com código " + codigo + " não encontrada.");
            return false;
        }

        FileWriter fw = new FileWriter(categorias);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String linhaAtualizada : listaLinhas) {
            bw.write(linhaAtualizada);
            bw.newLine();
        }
        bw.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo categorias.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo categorias.txt!");
        } finally {
            scan.close();
        }

        return true;
    }

    public static Categoria consultarCategoria() {
        List<Categoria> listaCategorias = new ArrayList<>();
        File categorias = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\categorias.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(categorias))) {
            if (!categorias.exists() || categorias.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Categoria categoria = new Categoria(Integer.parseInt(dados[0]), dados[1], Double.parseDouble(dados[2]));
                    listaCategorias.add(categoria);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo categorias.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo categorias.txt!");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("*** CONSULTAR CATEGORIA ***");
        System.out.print("Digite o código da categoria a ser consultada: ");
        int codigo = scan.nextInt();
        scan.nextLine();
        System.out.println("");
        scan.close();

        boolean cadastrado = false;

        for (int i = 0; i < listaCategorias.size(); i++) {
            if (listaCategorias.get(i).getCodigo() == codigo) {
                cadastrado = true;
                Categoria categoria = listaCategorias.get(i);
                System.out.println("Categoria identificada!");
                System.out.println("");
                System.out.println("CATEGORIA " + (i + 1));
                System.out.printf("CÓDIGO: %d | DESCRIÇÃO: %s | VALOR: R$ %.2f%n", categoria.getCodigo(), categoria.getDescricao(), categoria.getValor());
                System.out.println("");
            }
        }

        if (!cadastrado) {
            System.out.println("Categoria com código " + codigo + " não encontrada.");
        }

        return null;
    }

    public static List<Categoria> listarCategorias() {
        List<Categoria> listaCategorias = new ArrayList<>();
        File categorias = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\categorias.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(categorias))) {

            if (!categorias.exists() || categorias.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Categoria categoria = new Categoria(Integer.parseInt(dados[0]), dados[1], Double.parseDouble(dados[2]));
                    listaCategorias.add(categoria);
                }
            }

            System.out.println("");
            System.out.println("*** LISTA DE CATEGORIAS ***");
            for (Categoria categoria : listaCategorias) {
                System.out.println("");
                System.out.println("CATEGORIA " + (listaCategorias.indexOf(categoria) + 1));
                System.out.printf("CÓDIGO: %d | DESCRIÇÃO: %s | VALOR: R$ %.2f%n", categoria.getCodigo(), categoria.getDescricao(), categoria.getValor());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo categorias.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo categorias.txt!");
        }
        System.out.println("");
        return listaCategorias;
    }
}
