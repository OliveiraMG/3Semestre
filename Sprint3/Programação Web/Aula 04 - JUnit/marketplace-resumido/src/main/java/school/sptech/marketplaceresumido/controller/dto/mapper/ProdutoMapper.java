package school.sptech.marketplaceresumido.controller.dto.mapper;

import school.sptech.marketplaceresumido.controller.dto.ProdutoAtualizacaoDto;
import school.sptech.marketplaceresumido.controller.dto.ProdutoConsultaDto;
import school.sptech.marketplaceresumido.controller.dto.ProdutoCriacaoDto;
import school.sptech.marketplaceresumido.entity.Produto;

import java.util.Objects;

public class ProdutoMapper {

    private ProdutoMapper() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static ProdutoConsultaDto mapProdutoToProdutoConsultaDto(Produto produto) {

        if (Objects.isNull(produto)) {
            return null;
        }

        return new ProdutoConsultaDto(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getCodigoBarra(),
                produto.getPreco()
        );
    }

    public static Produto mapProdutoCriacaoDtoToProduto(ProdutoCriacaoDto produtoCriacaoDto) {

        if (Objects.isNull(produtoCriacaoDto)) {
            return null;
        }

        return new Produto(
                null,
                produtoCriacaoDto.getNome(),
                produtoCriacaoDto.getDescricao(),
                produtoCriacaoDto.getCodigoBarra(),
                produtoCriacaoDto.getPreco()
        );
    }

    public static Produto mapProdutoAtualizacaoDtoToProduto(Integer id, ProdutoAtualizacaoDto produtoCriacaoDto) {

        if (Objects.isNull(produtoCriacaoDto)) {
            return null;
        }

        return new Produto(
                id,
                produtoCriacaoDto.getNome(),
                produtoCriacaoDto.getDescricao(),
                produtoCriacaoDto.getCodigoBarra(),
                produtoCriacaoDto.getPreco()
        );
    }
}
