import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    //Atributos prontos (NÃO é necessário alterar)
    private List<Produto> listaProdutos;
    private PilhaObj<Produto> pilhaDesfazer;
    private PilhaObj<Integer> pilhaRefazer;
    private FilaObj<Produto> filaAgendamentos;

    //Construtor pronto (NÃO é necessário alterar)
    public Repositorio() {
        this.listaProdutos = new ArrayList<>();
        this.pilhaDesfazer = new PilhaObj<>(10);
        this.pilhaRefazer = new PilhaObj<>(10);
        this.filaAgendamentos = new FilaObj<>(10);
    }

    //Complete o método de acordo com enunciado:
    public void salvar(Produto p){
        listaProdutos.add(p);
    
    }

    //Complete o método de acordo com enunciado:
    public void deletar(int id){
        for (Produto produto : listaProdutos) {
            if (produto.getId() == id) {
                pilhaDesfazer.push(produto);
                listaProdutos.remove(produto);
                return;
            }
        }

        throw new IllegalArgumentException("Produto não encontrado");
    }

    //Complete o método de acordo com enunciado:
    public void desfazer(){
        if (pilhaDesfazer.isEmpty()) {
            throw new IllegalStateException("Pilha vazia");
        }

        while (!pilhaDesfazer.isEmpty()) {
            Produto produto = pilhaDesfazer.pop();
            listaProdutos.add(produto);
            pilhaRefazer.push(produto.getId());
        }
    }

    //Complete o método de acordo com enunciado:
    public void refazer(int qtd){
        if (pilhaRefazer.isEmpty()) {
            throw new IllegalStateException("Pilha vazia");
        }

        if (qtd <= 0 || qtd > pilhaRefazer.getTopo() + 1) {
            throw new IllegalStateException("Quantidade inválida");
        }

        for (int i = 0; i < qtd; i++) {
            deletar(pilhaRefazer.pop());
        }
    }

    //Complete o método de acordo com enunciado:
    public void agendarSalvar(Produto p){
        filaAgendamentos.insert(p);
    }

    //Complete o método de acordo com enunciado:
    public void executarAgendado(){
        if (filaAgendamentos.isEmpty()) {
            throw new IllegalStateException("Fila vazia");
        }

        Produto produto = filaAgendamentos.poll();
        salvar(produto);
    }
    //Complete o método de acordo com enunciado:
    public void importarTxt(String nomeArq){
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome, categoria;
        Integer id, pontuacao, quantidadeEstoque;
        Double preco;
        int contaRegistroDadosLidos = 0;
        int qtdRegistroDadosGravados = 0;

        List<Produto> listaLida = new ArrayList<>();

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
            }
            catch (IOException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }

        try {
            registro = entrada.readLine();
            while (registro != null) {
                tipoRegistro = registro.substring(1, 2);
                if (tipoRegistro.equals("00")) {
                    System.out.println("Tipo de arquivo: " + registro.substring(3, 9));
                    System.out.println("Data e hora da criação do arquivo: " + registro.substring(10, 28));
                    System.out.println("Versão do arquivo: " + registro.substring(29, 30));
                } else if (tipoRegistro.equals("01")) {
                    System.out.println("Trailer: " + registro);
                    qtdRegistroDadosGravados = Integer.parseInt(registro.substring(2, 7));
                    if (qtdRegistroDadosGravados == contaRegistroDadosLidos) {
                        System.out.println("Quantidade de registros de dados gravados é compatível com a quantidade de registros de dados lidos");
                    } else {
                        System.out.println("Quantidade de registros de dados gravados é incompatível com a quantidade de registros de dados lidos");
                    }
                } else if (tipoRegistro.equals("02")) {
                    id = Integer.parseInt(registro.substring(3, 6));
                    nome = registro.substring(7, 47);
                    categoria = registro.substring(48, 76);
                    pontuacao = Integer.parseInt(registro.substring(77, 78));
                    preco = Double.parseDouble(registro.substring(79, 84));
                    quantidadeEstoque = Integer.parseInt(registro.substring(85, 88));
                    Produto produto = new Produto(id, nome, categoria, pontuacao, preco, quantidadeEstoque);
                    listaLida.add(produto);
                    contaRegistroDadosLidos++;
                } else {
                    System.out.println("Tipo de registro inválido: " + tipoRegistro);
                }
                registro = entrada.readLine();
            }
            entrada.close();
        }
        catch (IOException e) {
            System.out.println("Erro na leitura do arquivo: " + e.getMessage());
        }
        System.out.println("Lista de produtos lida do arquivo:");
        for (int i = 0; i < listaLida.size(); i++) {
            Produto produto = listaLida.get(i);
            System.out.println(produto);
        }

    }

    //Getters & Setters (NÃO é necessário alterar)
    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public PilhaObj<Produto> getPilhaDesfazer() {
        return pilhaDesfazer;
    }

    public PilhaObj<Integer> getPilhaRefazer() {
        return pilhaRefazer;
    }

    public FilaObj<Produto> getFilaAgendamentos() {
        return filaAgendamentos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public void setPilhaDesfazer(PilhaObj<Produto> pilhaDesfazer) {
        this.pilhaDesfazer = pilhaDesfazer;
    }

    public void setPilhaRefazer(PilhaObj<Integer> pilhaRefazer) {
        this.pilhaRefazer = pilhaRefazer;
    }

    public void setFilaAgendamentos(FilaObj<Produto> filaAgendamentos) {
        this.filaAgendamentos = filaAgendamentos;
    }
}
