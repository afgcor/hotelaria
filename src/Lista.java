/* import java.util.ArrayList;
import java.util.List;

public class Lista {
    List<Hospede> listaHospedes = new ArrayList<>();
    List<Categoria> listaCategorias = new ArrayList<>();
    List<Funcionario> listaFuncionarios = new ArrayList<>();

    MÉTODOS LISTA HÓSPEDES

    public void adicionarHospede(Hospede hospede) {
        listaHospedes.add(hospede);
    }

    public void listarHospedes() {
        System.out.println("----- LISTA DE HÓSPEDES ----- ");
        System.out.println("");
        for (Hospede hospede : listaHospedes) {
            System.out.println("**** HÓSPEDE 00" + (listaHospedes.indexOf(hospede)+1) + " ****");
            System.out.println("NOME: " + hospede.getNome());
            System.out.println("CPF: " + hospede.getCPF());
            System.out.println("E-MAIL: " + hospede.getEmail());
            System.out.println("ENDEREÇO: " + hospede.getEnderecoCompleto());
            System.out.println("");
        }
    }

    public void limparListaHospedes () {
        listaHospedes.clear();
    }

    MÉTODOS LISTA CATEGORIAS

    public void adicionarCategoria(Categoria categoria) {
        listaCategorias.add(categoria);
    }

    public void listarCategorias() {
        System.out.println("----- LISTA DE CATEGORIAS -----");
        System.out.println("");
        for (Categoria categoria : listaCategorias) {
            System.out.println("**** CATEGORIA 00" + (listaCategorias.indexOf(categoria)+1) + " ****");
            System.out.println("CÓDIGO: " + categoria.getCodigo());
            System.out.println("DESCRIÇÃO: " + categoria.getCodigo());
            System.out.println("VALOR: " + categoria.getValor());
        }
    }

    MÉTODOS LISTA FUNCIONÁRIOS

    public void adicionarFuncionario(Funcionario funcionario) {
        listaFuncionarios.add(funcionario);
    }
    
    public void listarFuncionarios() {
        System.out.println("----- LISTA DE FUNCIONÁRIOS -----");
        System.out.println("");
        for (Funcionario funcionario : listaFuncionarios) {
            System.out.println("**** FUNCIONÁRIO 00" + (listaFuncionarios.indexOf(funcionario)+1) + " ****");
            System.out.println("NOME: " + funcionario.getNome());
            System.out.println("CPF: " + funcionario.getCPF());
            System.out.println("E-MAIL: " + funcionario.getEmail());
            System.out.println("SETOR: " + funcionario.getSetor());
        }
    }
} */