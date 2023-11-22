package school.sptech.marketplaceresumido.service.produto;

import org.springframework.stereotype.Service;
import school.sptech.marketplaceresumido.entity.Produto;
import school.sptech.marketplaceresumido.entity.data.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listar() {
        return null;
    }

    public Produto buscarPorId(Long id) {
        return null;
    }

    public Produto cadastrar(Produto produto) {
        return null;
    }

    public Produto atualizar(Produto produto) {
        return null;
    }

    public void deletar(Long id) {

    }

}
