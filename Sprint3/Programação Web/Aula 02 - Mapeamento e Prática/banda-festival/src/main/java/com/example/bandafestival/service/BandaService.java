package com.example.bandafestival.service;

import com.example.bandafestival.dto.banda.BandaRequestDto;
import com.example.bandafestival.entity.Banda;
import com.example.bandafestival.repository.BandaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BandaService {

    private BandaRepository bandaRepository;

    public BandaService(BandaRepository bandaRepository) {
        this.bandaRepository = bandaRepository;
    }

    public Banda getBandaPorId(Integer id) {
        Optional<Banda> banda = bandaRepository.findById(id);

        if (banda.isPresent()) {
            return banda.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Banda não encontrada");
        }
    }

    public List<Banda> getTodasBandas() {
        if (bandaRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhuma banda encontrada");
        } else {
            return bandaRepository.findAll();
        }
    }

    public List<Banda> get3BandasMaisAntigas() {
        if (bandaRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhuma banda encontrada");
        } else {
            return bandaRepository.findTop3ByOrderByDiaDeFormacaoAsc();
        }
    }

    public List<Banda> getBandaPorGenero(String genero) {
        List<Banda> bandas = bandaRepository.findByGeneroIgnoreCase(genero);

        if (bandas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhuma banda encontrada");
        } else {
            return bandas;
        }
    }

    public List<Banda> getBandaPorNomeParcialIgnorandoCase(String nomeBanda) {
        if (bandaRepository.findByNomeBandaContainsIgnoreCase(nomeBanda).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhuma banda encontrada");
        } else {
            return bandaRepository.findByNomeBandaContainsIgnoreCase(nomeBanda);
        }
    }

    public Banda cadastrarBanda(BandaRequestDto banda) {
        Optional<Banda> bandaExistente = bandaRepository.findByNomeBanda(banda.nomeBanda());

        if (bandaExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Banda já cadastrada");
        }

        Banda novaBanda = new Banda();
        novaBanda.setNomeBanda(banda.nomeBanda());
        novaBanda.setGenero(banda.genero());
        novaBanda.setDiaDeFormacao(banda.diaDeFormacao());

        bandaRepository.save(novaBanda);

        return (novaBanda);
    }

    public Banda atualizarBandaPorId(Integer id, BandaRequestDto banda) {
        Optional<Banda> bandaExistente = bandaRepository.findById(id);

        if (bandaExistente.isPresent()) {
            bandaExistente.get().setNomeBanda(banda.nomeBanda());
            bandaExistente.get().setGenero(banda.genero());
            bandaExistente.get().setDiaDeFormacao(banda.diaDeFormacao());

            bandaRepository.save(bandaExistente.get());

            return bandaExistente.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Banda não encontrada");
        }
    }

    public Void deletarBandaPorId(Integer id) {
        Optional<Banda> banda = bandaRepository.findById(id);
        if (banda.isPresent()) {
            bandaRepository.delete(banda.get());
            return null;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Banda não encontrada");
        }
    }

    public Banda salvar(Banda banda) {
        return bandaRepository.save(banda);
    }

}
