package school.sptech.marketplaceresumido.controller.dto;

public class ProdutoConsultaDto {

    private Integer id;
    private String nome;
    private String descricao;
    private String codigoBarra;
    private Double preco;

    public ProdutoConsultaDto() {
    }

    public ProdutoConsultaDto(Integer id, String nome, String descricao, String codigoBarra, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.codigoBarra = codigoBarra;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
