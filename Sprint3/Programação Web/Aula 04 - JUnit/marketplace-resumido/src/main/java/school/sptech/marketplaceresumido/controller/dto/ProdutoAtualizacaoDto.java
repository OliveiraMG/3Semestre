package school.sptech.marketplaceresumido.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProdutoAtualizacaoDto {

    @NotBlank
    @Size(min = 3)
    private String nome;

    @Size(min = 10, max = 255)
    private String descricao;

    @Size(min = 10, max = 255)
    private String codigoBarra;

    @DecimalMin("0.50")
    private Double preco;

    public ProdutoAtualizacaoDto() {
    }

    public ProdutoAtualizacaoDto(String nome, String descricao, String codigoBarra, Double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.codigoBarra = codigoBarra;
        this.preco = preco;
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
