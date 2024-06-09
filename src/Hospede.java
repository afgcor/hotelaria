import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hospede extends Pessoa {
    private String enderecoCompleto;

    /* CONSTRUTOR */
    public Hospede(String cpf, String nome, String email, String enderecoCompleto) {
        super(cpf, nome, email);
        this.enderecoCompleto = enderecoCompleto;
    }

    /* GETTERS & SETTERS */
    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    /* MÉTODOS - CADASTRAR, EDITAR, CONSULTAR E LISTAR */
    public static boolean cadastrarHospede() throws IOException {
        Scanner scan = new Scanner(System.in);
        String cpf = "";
        try {
            System.out.println("");
            System.out.println("*** CADASTRAR HÓSPEDE ***");
            System.out.print("Insira o CPF: ");
            cpf = scan.nextLine();

            Hospede hospede = identificarHospede(cpf);

            if (hospede != null) {
                System.out.println("");
                System.out.println("ERRO: Já existe um hóspede registrado com este CPF!");
                System.out.println("");
                System.out.println("NOME: " + hospede.getNome() + " | CPF: " + hospede.getCPF() + " | E-MAIL: " + hospede.getEmail() + " | ENDEREÇO: " + hospede.getEnderecoCompleto());
                throw new NullPointerException();
            }

            System.out.print("Insira o nome: ");
            String nome = scan.nextLine();
            System.out.print("Insira o e-mail: ");
            String email = scan.nextLine();
            System.out.print("Insira o endereço: ");
            String enderecoCompleto = scan.nextLine();

            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || enderecoCompleto.isEmpty()) {
                System.out.println("");
                System.out.println("ERRO: Entrada inválida (campos em branco)!");
                System.out.println("Por favor, insira todos os dados do hóspede ou encerre a operação.");
                throw new NullPointerException();
            }

            FileWriter fw = new FileWriter("./arquivos/hospedes.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
    
            bw.write(cpf + " ; " + nome + " ; " + email + " ; " + enderecoCompleto);
            bw.newLine();
            bw.close();
            
            System.out.println("HÓSPEDE CADASTRADO!");
            System.out.println("");
        } catch (NullPointerException e) {
            System.out.println("");
            System.out.print("Continuar cadastro (S/N)? ");
            String opcao = scan.nextLine();
            switch (opcao.toUpperCase()) {
                case "S":
                    cadastrarHospede();
                    break;
                case "N":
                    System.out.println("Encerrando.");
                    System.out.println("");
                    break;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo hospedes.txt!");
        } finally {
            scan.close();
        }
        return true;
    }

    public static boolean editarHospede() throws IOException {
        Scanner scan = new Scanner(System.in);
        List<Hospede> listaHospedes = new ArrayList<>();
        File hospedes = new File("./arquivos/hospedes.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(hospedes))) {
            if (!hospedes.exists()) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 4) {
                    Hospede hospede = new Hospede(dados[0], dados[1], dados[2], dados[3]);
                    listaHospedes.add(hospede);
                }
            }

        System.out.println("");
        System.out.println("*** EDITAR HÓSPEDE ***");
        System.out.print("Digite o CPF do hóspede a ser editado: ");
        String cpf = scan.nextLine();
        System.out.println("");

        boolean cadastrado = false;
        List<String> listaLinhas = new ArrayList<>();

        for (Hospede hospede : listaHospedes) {
            if (hospede.getCPF().equals(cpf)) {
                cadastrado = true;
                System.out.println("Hóspede identificado!");
                System.out.println("");
                System.out.println("*** ALTERAR DADOS ***");
                System.out.println("Insira os dados atualizados abaixo. Caso nenhuma alteração seja necessária, aperte ENTER.");
                System.out.println("");
                System.out.println("CPF atual: " + hospede.getCPF());
                System.out.print("Novo CPF: ");
                String cpfAtualizado = scan.nextLine();
                if (cpfAtualizado.isEmpty()) {
                    cpfAtualizado = hospede.getCPF();
                }
                System.out.println("Nome atual: " + hospede.getNome());
                System.out.print("Novo nome: ");
                String nomeAtualizado = scan.nextLine();
                if (nomeAtualizado.isEmpty()) {
                    nomeAtualizado = hospede.getNome();
                }
                System.out.println("E-mail atual: " + hospede.getEmail());
                System.out.print("Novo e-mail: ");
                String emailAtualizado = scan.nextLine();
                if (emailAtualizado.isEmpty()) {
                    emailAtualizado = hospede.getEmail();
                }
                System.out.println("Endereço atual: " + hospede.getEnderecoCompleto());
                System.out.print("Novo endereço: ");
                String enderecoAtualizado = scan.nextLine();
                if (enderecoAtualizado.isEmpty()) {
                    enderecoAtualizado = hospede.getEnderecoCompleto();
                }
                String linhaAtualizada = (cpfAtualizado + " ; " + nomeAtualizado + " ; " + emailAtualizado + " ; " + enderecoAtualizado);
                listaLinhas.add(linhaAtualizada);
                System.out.println("");
                System.out.println("Dados atualizados com sucesso!");
            } else {
                listaLinhas.add(hospede.getCPF() + " ; " + hospede.getNome() + " ; " + hospede.getEmail() + " ; " + hospede.getEnderecoCompleto());
            }
        }

        if (!cadastrado) {
            System.out.println("Hóspede com CPF " + cpf + " não encontrado.");
            return false;
        }

        FileWriter fw = new FileWriter(hospedes);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String linhaAtualizada : listaLinhas) {
            bw.write(linhaAtualizada);
            bw.newLine();
        }
        bw.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo hospedes.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na gravação do arquivo hospedes.txt!");
        } finally {
            scan.close();
        }

        return true;
    }

    public static Hospede consultarHospede() {
        List<Hospede> listaHospedes = new ArrayList<>();
        File hospedes = new File("./arquivos/hospedes.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(hospedes))) {
            if (!hospedes.exists()) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 4) {
                    Hospede hospede = new Hospede(dados[0], dados[1], dados[2], dados[3]);
                    listaHospedes.add(hospede);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo hospedes.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo hospedes.txt!");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("*** CONSULTAR HÓSPEDE ***");
        System.out.print("Digite o CPF do hóspede a ser consultado: ");
        String cpf = scan.nextLine();
        System.out.println("");
        scan.close();

        boolean cadastrado = false;

        for (int i = 0; i < listaHospedes.size(); i++) {
            if (listaHospedes.get(i).getCPF().equals(cpf)) {
                cadastrado = true;
                Hospede hospede = listaHospedes.get(i);
                System.out.println("Hóspede identificado!");
                System.out.println("");
                System.out.println("HÓSPEDE " + (i + 1));
                System.out.println("NOME: " + hospede.getNome() + " | CPF: " + hospede.getCPF() + " | E-MAIL: " + hospede.getEmail() + " | ENDEREÇO: " + hospede.getEnderecoCompleto());
                System.out.println("");
            }
        }

        if (!cadastrado) {
            System.out.println("Hóspede com CPF " + cpf + " não encontrado.");
        }

        return null;
    }

    public static List<Hospede> listarHospedes() {
        List<Hospede> listaHospedes = new ArrayList<>();
        File hospedes = new File("./arquivos/hospedes.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(hospedes))) {

            if (!hospedes.exists()) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 4) {
                    Hospede hospede = new Hospede(dados[0], dados[1], dados[2], dados[3]);
                    listaHospedes.add(hospede);
                }
            }

            System.out.println("");
            System.out.println("*** LISTA DE HÓSPEDES ***");
            for (Hospede hospede : listaHospedes) {
                System.out.println("");
                System.out.println("HÓSPEDE " + (listaHospedes.indexOf(hospede) + 1));
                System.out.println("NOME: " + hospede.getNome() + " | CPF: " + hospede.getCPF() + " | E-MAIL: " + hospede.getEmail() + " | ENDEREÇO COMPLETO: " + hospede.getEnderecoCompleto());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: O arquivo hospedes.txt não foi encontrado!");
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura do arquivo hospedes.txt!");
        }
        System.out.println("");
        return listaHospedes;
    }

    public static List<Hospede> leituraHospedes() {
        List<Hospede> listaHospedes = new ArrayList<>();
        File hospedes = new File("./arquivos/hospedes.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(hospedes))) {

            if (!hospedes.exists()) {
                throw new FileNotFoundException();
            }

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ; ");
                if (dados.length == 4) {
                    Hospede hospede = new Hospede(dados[0], dados[1], dados[2], dados[3]);
                    listaHospedes.add(hospede);
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO! Falha na leitura do arquivo.");
        }
        return listaHospedes;
    }

    public static Hospede identificarHospede(String cpf) {
        List<Hospede> listaHospedes = leituraHospedes();
        for (Hospede hospede : listaHospedes) {
            if (hospede.getCPF().equals(cpf)) {
                return hospede;
            }
        }
        return null;
    }
}