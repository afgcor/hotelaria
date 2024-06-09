import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servico {
    private int codigo;
    private String descricao;
    private double valor;

    /* CONSTRUTOR */
    public Servico(int codigo, String descricao, double valor) {
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
    public static boolean cadastrarServico() throws IOException {
        Scanner scan = new Scanner(System.in);
        String codigo = "";
        try {
            System.out.println("");
            System.out.println("*** CADASTRAR SERVIÇO ***");
            System.out.print("Insira o código: ");
            codigo = scan.nextLine();

            Servico servico = identificarServico(Integer.parseInt(codigo));

            if (servico != null) {
                System.out.println("");
                System.out.println("ERRO: Já existe um serviço registrado com este código!");
                System.out.println("");
                System.out.printf("CÓDIGO: %d | DESCRIÇÃO: %s | VALOR: R$ %.2f%n", servico.getCodigo(), servico.getDescricao(), servico.getValor());
                throw new NullPointerException();
            }

            System.out.print("Insira o nome: ");
            String descricao = scan.nextLine();
            System.out.print("Insira o e-mail: ");
            String valor = scan.nextLine();

            if (codigo.isEmpty() || descricao.isEmpty() || valor.isEmpty()) {
                System.out.println("");
                System.out.println("ERRO: Entrada inválida (campos em branco)!");
                System.out.println("Por favor, insira todos os dados do serviço ou encerre a operação.");
                throw new NullPointerException();
            }

            FileWriter fw = new FileWriter("./arquivos/servicos.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
    
            bw.write(Integer.parseInt(codigo) + " ; " + descricao + " ; " + Double.parseDouble(valor));
            bw.newLine();
            bw.close();
            
            System.out.println("SERVIÇO CADASTRADO!");
            System.out.println("");
        } catch (NullPointerException e) {
            System.out.println("");
            System.out.print("Continuar cadastro (S/N)? ");
            String opcao = scan.nextLine();
            switch (opcao.toUpperCase()) {
                case "S":
                    cadastrarServico();
                    break;
                case "N":
                    System.out.println("Encerrando.");
                    System.out.println("");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo servicos.txt!");
        } finally {
            scan.close();
        }
        return true;
    }

    public static boolean editarServico() throws IOException {
        Scanner scan = new Scanner(System.in);
        List<Servico> listaServicos = new ArrayList<>();
        File servicos = new File("./arquivos/servicos.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(servicos))) {
            if (!servicos.exists()) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Servico servico = new Servico(Integer.parseInt(dados[0]), dados[1], Double.parseDouble(dados[2]));
                    listaServicos.add(servico);
                }
            }

        System.out.println("");
        System.out.println("*** EDITAR SERVIÇO ***");
        System.out.print("Digite o código do serviço a ser editado: ");
        int codigo = scan.nextInt();
        scan.nextLine();
        System.out.println("");

        boolean cadastrado = false;
        List<String> listaLinhas = new ArrayList<>();

        for (Servico servico : listaServicos) {
            if (servico.getCodigo() == codigo) {
                cadastrado = true;
                System.out.println("Serviço identificado!");
                System.out.println("");
                System.out.println("*** ALTERAR DADOS ***");
                System.out.println("Insira os dados atualizados abaixo. Caso nenhuma alteração seja necessária, aperte ENTER.");
                System.out.println("");
                System.out.println("Código atual: " + servico.getCodigo());
                System.out.print("Novo código: ");
                String codigoAtualizadoS = scan.nextLine();
                int codigoAtualizado;
                if (codigoAtualizadoS.isEmpty()) {
                    codigoAtualizado = servico.getCodigo();
                } else {
                    codigoAtualizado = Integer.parseInt(codigoAtualizadoS);
                }
                System.out.println("Descrição atual: " + servico.getDescricao());
                System.out.print("Nova descrição: ");
                String descricaoAtualizada = scan.nextLine();
                if (descricaoAtualizada.isEmpty()) {
                    descricaoAtualizada = servico.getDescricao();
                }
                System.out.printf("Valor atual: R$ %.2f%n", servico.getValor());
                System.out.print("Novo valor: ");
                String valorAtualizadoS = scan.nextLine();
                double valorAtualizado;
                if (valorAtualizadoS.isEmpty()) {
                    valorAtualizado = servico.getValor();
                } else {
                    valorAtualizado = Double.parseDouble(valorAtualizadoS);
                }    
                String linhaAtualizada = (codigoAtualizado + " ; " + descricaoAtualizada + " ; " + valorAtualizado);
                listaLinhas.add(linhaAtualizada);
                System.out.println("");
                System.out.println("Dados atualizados com sucesso!");
            } else {
                listaLinhas.add(servico.getCodigo() + " ; " + servico.getDescricao() + " ; " + servico.getValor());
            }
        }

        if (!cadastrado) {
            System.out.println("Serviço com código " + codigo + " não encontrado.");
            return false;
        }

        FileWriter fw = new FileWriter(servicos);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String linhaAtualizada : listaLinhas) {
            bw.write(linhaAtualizada);
            bw.newLine();
        }
        bw.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo servicos.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo servicos.txt!");
        } finally {
            scan.close();
        }

        return true;
    }

    public static Servico consultarServico() {
        List<Servico> listaServicos = new ArrayList<>();
        File servicos = new File("./arquivos/servicos.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(servicos))) {
            if (!servicos.exists()) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Servico servico = new Servico(Integer.parseInt(dados[0]), dados[1], Double.parseDouble(dados[2]));
                    listaServicos.add(servico);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo servicos.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo servicos.txt!");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("*** CONSULTAR SERVIÇO ***");
        System.out.print("Digite o código do serviço a ser consultado: ");
        int codigo = scan.nextInt();
        scan.nextLine();
        System.out.println("");
        scan.close();

        boolean cadastrado = false;

        for (int i = 0; i < listaServicos.size(); i++) {
            if (listaServicos.get(i).getCodigo() == codigo) {
                cadastrado = true;
                Servico servico = listaServicos.get(i);
                System.out.println("Serviço identificado!");
                System.out.println("");
                System.out.println("SERVIÇO " + (i + 1));
                System.out.printf("CÓDIGO: %d | DESCRIÇÃO: %s | VALOR: R$ %.2f%n", servico.getCodigo(), servico.getDescricao(), servico.getValor());
                System.out.println("");
            }
        }

        if (!cadastrado) {
            System.out.println("Serviço com código " + codigo + " não encontrado.");
        }

        return null;
    }

    public static List<Servico> listarServicos() {
        List<Servico> listaServicos = new ArrayList<>();
        File servicos = new File("./arquivos/servicos.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(servicos))) {

            if (!servicos.exists()) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Servico servico = new Servico(Integer.parseInt(dados[0]), dados[1], Double.parseDouble(dados[2]));
                    listaServicos.add(servico);
                }
            }

            System.out.println("");
            System.out.println("*** LISTA DE SERVIÇOS ***");
            for (Servico servico : listaServicos) {
                System.out.println("");
                System.out.println("SERVIÇO " + (listaServicos.indexOf(servico) + 1));
                System.out.printf("CÓDIGO: %d | DESCRIÇÃO: %s | VALOR: R$ %.2f%n", servico.getCodigo(), servico.getDescricao(), servico.getValor());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo servicos.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo servicos.txt!");
        }
        System.out.println("");
        return listaServicos;
    }

    public static List<Servico> leituraServicos() {
        List<Servico> listaServicos = new ArrayList<>();
        File servicos = new File("./arquivos/servicos.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(servicos))) {

            if (!servicos.exists()) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 3) {
                    Servico servico = new Servico(Integer.parseInt(dados[0]), dados[1], Double.parseDouble(dados[2]));
                    listaServicos.add(servico);
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO! Falha na leitura do arquivo.");
        }
        return listaServicos;
    }

    public static Servico identificarServico(int codigo) {
        List<Servico> listaServicos = leituraServicos();
        for (Servico servico : listaServicos) {
            if (servico.getCodigo() == codigo) {
                return servico;
            }
        }
        return null;
    }
}
