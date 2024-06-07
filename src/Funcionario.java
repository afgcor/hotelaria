import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Funcionario extends Pessoa {
    private String setor;

    /* CONSTRUTOR */
    public Funcionario(String cpf, String nome, String email, String setor) {
        super(cpf, nome, email);
        this.setor = setor;
    }

    /* GETTERS & SETTERS */
    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }

    /* MÉTODOS - CADASTRAR, EDITAR, CONSULTAR E LISTAR */
    public static boolean cadastrarFuncionario() throws IOException {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("");
            System.out.println("*** CADASTRAR FUNCIONÁRIO ***");
            System.out.print("Insira o CPF: ");
            String cpf = scan.nextLine();
            System.out.print("Insira o nome: ");
            String nome = scan.nextLine();
            System.out.print("Insira o e-mail: ");
            String email = scan.nextLine();
            System.out.print("Insira o setor: ");
            String setor = scan.nextLine();
    
            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || setor.isEmpty()) {
                throw new NullPointerException();
            }
    
            FileWriter fw = new FileWriter("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\funcionarios.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
    
            bw.write(cpf + " ; " + nome + " ; " + email + " ; " + setor);
            bw.newLine();
            bw.close();
            System.out.println("FUNCIONÁRIO CADASTRADO!");
            System.out.println("");
        } catch (NullPointerException e) {
            System.out.println("");
            System.out.println("*** ERRO: ENTRADA INVÁLIDA ***");
            System.out.println("Por favor, insira todos os dados do funcionário ou encerre a operação.");
            System.out.print("Continuar cadastro (1) ou encerrar a operação (2)? ");
            int opcao = scan.nextInt();
            scan.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    System.out.println("Encerrando.");
                    System.out.println("");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo funcionarios.txt!");
        } finally {
            scan.close();
        }
        return true;
    }

    public static boolean editarFuncionario() throws IOException {
        Scanner scan = new Scanner(System.in);
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        File funcionarios = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\funcionarios.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(funcionarios))) {
            if (!funcionarios.exists() || funcionarios.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 4) {
                    Funcionario funcionario = new Funcionario(dados[0], dados[1], dados[2], dados[3]);
                    listaFuncionarios.add(funcionario);
                }
            }

        System.out.println("");
        System.out.println("*** EDITAR FUNCIONÁRIO ***");
        System.out.print("Digite o CPF do funcionário a ser editado: ");
        String cpf = scan.nextLine();
        System.out.println("");

        boolean cadastrado = false;
        List<String> listaLinhas = new ArrayList<>();

        for (Funcionario funcionario : listaFuncionarios) {
            if (funcionario.getCPF().equals(cpf)) {
                cadastrado = true;
                System.out.println("Funcionário identificado!");
                System.out.println("");
                System.out.println("*** ALTERAR DADOS ***");
                System.out.println("Insira os dados atualizados abaixo. Caso nenhuma alteração seja necessária, aperte ENTER.");
                System.out.println("");
                System.out.println("CPF atual: " + funcionario.getCPF());
                System.out.print("Novo CPF: ");
                String cpfAtualizado = scan.nextLine();
                if (cpfAtualizado.isEmpty()) {
                    cpfAtualizado = funcionario.getCPF();
                }
                System.out.println("Nome atual: " + funcionario.getNome());
                System.out.print("Novo nome: ");
                String nomeAtualizado = scan.nextLine();
                if (nomeAtualizado.isEmpty()) {
                    nomeAtualizado = funcionario.getNome();
                }
                System.out.println("E-mail atual: " + funcionario.getEmail());
                System.out.print("Novo e-mail: ");
                String emailAtualizado = scan.nextLine();
                if (emailAtualizado.isEmpty()) {
                    emailAtualizado = funcionario.getEmail();
                }
                System.out.println("Setor atual: " + funcionario.getSetor());
                System.out.print("Novo setor: ");
                String setorAtualizado = scan.nextLine();
                if (setorAtualizado.isEmpty()) {
                    setorAtualizado = funcionario.getSetor();
                }
                String linhaAtualizada = cpfAtualizado + " ; " + nomeAtualizado + " ; " + emailAtualizado + " ; " + setorAtualizado;
                listaLinhas.add(linhaAtualizada);
                System.out.println("");
                System.out.println("Dados atualizados com sucesso!");
            } else {
                listaLinhas.add(funcionario.getCPF() + " ; " + funcionario.getNome() + " ; " + funcionario.getEmail() + " ; " + funcionario.getSetor());
            }
        }

        if (!cadastrado) {
            System.out.println("Funcionário com CPF " + cpf + " não encontrado.");
            return false;
        }

        FileWriter fw = new FileWriter(funcionarios);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String linhaAtualizada : listaLinhas) {
            bw.write(linhaAtualizada);
            bw.newLine();
        }
        bw.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo funcionarios.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo funcionarios.txt!");
        } finally {
            scan.close();
        }

        return true;
    }

    public static Funcionario consultarFuncionario() {
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        File funcionarios = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\funcionarios.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(funcionarios))) {
            if (!funcionarios.exists() || funcionarios.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 4) {
                    Funcionario funcionario = new Funcionario(dados[0], dados[1], dados[2], dados[3]);
                    listaFuncionarios.add(funcionario);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo funcionarios.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo funcionarios.txt!");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("*** CONSULTAR FUNCIONÁRIO ***");
        System.out.print("Digite o CPF do funcionário a ser consultado: ");
        String cpf = scan.nextLine();
        System.out.println("");
        scan.close();

        boolean cadastrado = false;

        for (int i = 0; i < listaFuncionarios.size(); i++) {
            if (listaFuncionarios.get(i).getCPF().equals(cpf)) {
                cadastrado = true;
                Funcionario funcionario = listaFuncionarios.get(i);
                System.out.println("Funcionário identificado!");
                System.out.println("");
                System.out.println("FUNCIONÁRIO " + (i + 1));
                System.out.println("NOME: " + funcionario.getNome() + " | CPF: " + funcionario.getCPF() + " | E-MAIL: " + funcionario.getEmail() + " | SETOR: " + funcionario.getSetor());
                System.out.println("");
            }
        }

        if (!cadastrado) {
            System.out.println("Funcionário com CPF " + cpf + " não encontrado.");
        }

        return null;
    }

    public static List<Funcionario> listarFuncionarios() {
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        File funcionarios = new File("D:\\Users\\Anna\\Desktop\\ANÁLISE E DESENVOLVIMENTO DE SISTEMAS\\Unifor\\S2\\Programação Orientada a Objetos\\AV3\\arquivos\\funcionarios.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(funcionarios))) {

            if (!funcionarios.exists() || funcionarios.length() == 0) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 4) {
                    Funcionario funcionario = new Funcionario(dados[0], dados[1], dados[2], dados[3]);
                    listaFuncionarios.add(funcionario);
                }
            }

            System.out.println("");
            System.out.println("*** LISTA DE FUNCIONÁRIOS ***");
            for (Funcionario funcionario : listaFuncionarios) {
                System.out.println("");
                System.out.println("FUNCIONÁRIO " + (listaFuncionarios.indexOf(funcionario) + 1));
                System.out.println("NOME: " + funcionario.getNome() + " | CPF: " + funcionario.getCPF() + " | E-MAIL: " + funcionario.getEmail() + " | ENDEREÇO COMPLETO: " + funcionario.getSetor());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo funcionarios.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo funcionarios.txt!");
        }
        System.out.println("");
        return listaFuncionarios;
    }
}
