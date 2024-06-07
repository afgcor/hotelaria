import java.io.*;
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
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("");
            System.out.println("*** CADASTRAR QUARTO ***");
            
            System.out.print("Insira o código do quarto: ");
            String codigo = scan.nextLine();
            
            System.out.print("Insira o código da categoria: ");
            String idCategoria = scan.nextLine();

            System.out.print("Insira o status do quarto: ");
            String status = scan.nextLine();
    
            if (codigo.isEmpty() || idCategoria.isEmpty() || status.isEmpty()) {
                throw new NullPointerException();
            }
    
            Categoria categoria = identificarCategoria(Integer.parseInt(idCategoria));
            
            FileWriter fw = new FileWriter("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\quartos.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
    
            bw.write(Integer.parseInt(codigo) + " ; " + categoria.getCodigo() + " ; " + status);
            bw.newLine();
            bw.close();

            System.out.println("QUARTO CADASTRADO!");
            System.out.println("");
        } catch (NullPointerException e) {
            System.out.println("");
            System.out.println("*** ERRO: ENTRADA INVÁLIDA ***");
            System.out.println("Por favor, insira todos os dados do quarto ou encerre a operação.");
            System.out.print("Continuar cadastro (S) ou encerrar a operação (N)? ");
            String opcao = scan.nextLine();
            switch (opcao.toUpperCase()) {
                case "S":
                    cadastrarQuarto();
                    break;
                case "N":
                    System.out.println("Encerrando.");
                    System.out.println("");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo quartos.txt!");
        } finally {
            scan.close();
        }
        return true;
    }

    public static boolean editarQuarto() throws IOException {
        Scanner scan = new Scanner(System.in);
        List<Quarto> listaQuartos = new ArrayList<>();
        File quartos = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\quartos.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(quartos))) {
            if (!quartos.exists() || quartos.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Categoria categoria = identificarCategoria(Integer.parseInt(dados[1]));
                    if (categoria != null) {
                        Quarto quarto = new Quarto(Integer.parseInt(dados[0]), categoria, dados[2]);
                        listaQuartos.add(quarto);
                    }
                }
            }

        System.out.println("");
        System.out.println("*** EDITAR QUARTO ***");
        System.out.print("Digite o código do quarto a ser editado: ");
        int codigo = scan.nextInt();
        scan.nextLine();
        System.out.println("");

        boolean cadastrado = false;
        List<String> listaLinhas = new ArrayList<>();

        for (Quarto quarto : listaQuartos) {
            if (quarto.getCodigo() == codigo) {
                cadastrado = true;
                System.out.println("Quarto identificado!");
                System.out.println("");
                System.out.println("*** ALTERAR DADOS ***");
                System.out.println("Insira os dados atualizados abaixo. Caso nenhuma alteração seja necessária, aperte ENTER.");
                System.out.println("");
                System.out.println("Código atual: " + quarto.getCodigo());
                System.out.print("Novo código: ");
                String codigoAtualizadoS = scan.nextLine();
                int codigoAtualizado;
                if (codigoAtualizadoS.isEmpty()) {
                    codigoAtualizado = quarto.getCodigo();
                } else {
                    codigoAtualizado = Integer.parseInt(codigoAtualizadoS);
                }
                System.out.println("Categoria atual: " + quarto.getCategoria().getDescricao());
                System.out.print("Nova categoria: ");
                String categoriaAtualizada = scan.nextLine();
                if (categoriaAtualizada.isEmpty()) {
                    categoriaAtualizada = quarto.getCategoria().getDescricao();
                }
                System.out.println("Status atual: " + quarto.getStatus());
                System.out.print("Novo status: ");
                String statusAtualizado = scan.nextLine();
                if (statusAtualizado.isEmpty()) {
                    statusAtualizado = quarto.getStatus();
                }
                String linhaAtualizada = codigoAtualizado + " ; " + categoriaAtualizada + " ; " + statusAtualizado;
                listaLinhas.add(linhaAtualizada);
                System.out.println("");
                System.out.println("Dados atualizados com sucesso!");
            } else {
                listaLinhas.add(quarto.getCodigo() + " ; " + quarto.getCategoria() + " ; " + quarto.getStatus());
            }
        }

        if (!cadastrado) {
            System.out.println("Quarto com código " + codigo + " não encontrado.");
            return false;
        }

        FileWriter fw = new FileWriter(quartos);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String linhaAtualizada : listaLinhas) {
            bw.write(linhaAtualizada);
            bw.newLine();
        }
        bw.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo quartos.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo quartos.txt!");
        } finally {
            scan.close();
        }

        return true;
    }

    public static Quarto consultarQuarto() {
        List<Quarto> listaQuartos = new ArrayList<>();
        File quartos = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\quartos.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(quartos))) {
            if (!quartos.exists() || quartos.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Categoria categoria = identificarCategoria(Integer.parseInt(dados[1]));
                    if (categoria != null) {
                        Quarto quarto = new Quarto(Integer.parseInt(dados[0]), categoria, dados[2]);
                        listaQuartos.add(quarto);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo quartos.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo quartos.txt!");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("*** CONSULTAR QUARTO ***");
        System.out.print("Digite o código do quarto a ser consultado: ");
        int codigo = scan.nextInt();
        scan.nextLine();
        System.out.println("");
        scan.close();

        boolean cadastrado = false;

        for (int i = 0; i < listaQuartos.size(); i++) {
            if (listaQuartos.get(i).getCodigo() == codigo) {
                cadastrado = true;
                Quarto quarto = listaQuartos.get(i);
                System.out.println("Quarto identificado!");
                System.out.println("");
                System.out.println("QUARTO " + (i + 1));
                System.out.println("CÓDIGO: " + quarto.getCodigo() + " | CATEGORIA: " + quarto.getCategoria().getDescricao() + " | STATUS: " + quarto.getStatus());
                System.out.println("");
            }
        }

        if (!cadastrado) {
            System.out.println("Quarto com código " + codigo + " não encontrado.");
        }

        return null;
    }

    public static List<Quarto> listarQuartos() {
        List<Quarto> listaQuartos = new ArrayList<>();
        File quartos = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\quartos.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(quartos))) {

            if (!quartos.exists() || quartos.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Categoria categoria = identificarCategoria(Integer.parseInt(dados[1]));
                    if (categoria != null) {
                        Quarto quarto = new Quarto(Integer.parseInt(dados[0]), categoria, dados[2]);
                        listaQuartos.add(quarto);
                    }
                }
            }

            System.out.println("");
            System.out.println("*** LISTA DE QUARTOS ***");
            for (Quarto quarto : listaQuartos) {
                System.out.println("");
                System.out.println("QUARTO " + (listaQuartos.indexOf(quarto) + 1));
                System.out.println("CÓDIGO: " + quarto.getCodigo() + " | CATEGORIA: " + quarto.getCategoria().getDescricao() + " | STATUS: " + quarto.getStatus());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo quartos.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo quartos.txt!");
        }
        System.out.println("");
        return listaQuartos;
    }

    public static List<Categoria> leituraCategorias() {
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
        } catch (IOException e) {
            System.out.println("ERRO! Falha na leitura do arquivo.");
        }
        return listaCategorias;
    }

    public static Categoria identificarCategoria(int codigo) {
        List<Categoria> listaCategorias = leituraCategorias();
        for (Categoria categoria : listaCategorias) {
            if (categoria.getCodigo() == codigo) {
                return categoria;
            }
        }
        return null;
    }
}