package com.example.bandafestival.service;

import com.example.bandafestival.dto.festival.FestivalRequestDto;
import com.example.bandafestival.entity.Banda;
import com.example.bandafestival.entity.Festival;
import com.example.bandafestival.repository.FestivalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FestivalService {

    @Autowired
    private BandaService bandaService;

    private final FestivalRepository festivalRepository;

    public FestivalService(FestivalRepository festivalRepository) {
        this.festivalRepository = festivalRepository;
    }

    public List<Festival> getTodosFestivais() {
        if (festivalRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum festival encontrado");
        } else {
            return festivalRepository.findAll();
        }
    }

    public Festival cadastrarFestival(FestivalRequestDto festival) {
        Optional<Festival> festivalExistente = festivalRepository.findByNomeFestival(festival.nomeFestival());

        if (festivalExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Festival já cadastrado");
        }

        Festival novoFestival = new Festival();
        novoFestival.setNomeFestival(festival.nomeFestival());
        novoFestival.setLocal(festival.local());
        novoFestival.setDataHoraFestival(festival.dataHoraFestival());

        festivalRepository.save(novoFestival);

        return (novoFestival);
    }

    public Festival atualizarFestivalPorId(Integer id, @Valid FestivalRequestDto festival) {
        Optional<Festival> festivalExistente = festivalRepository.findById(id);

        if (festivalExistente.isPresent()) {
            festivalExistente.get().setNomeFestival(festival.nomeFestival());
            festivalExistente.get().setLocal(festival.local());
            festivalExistente.get().setDataHoraFestival(festival.dataHoraFestival());

            festivalRepository.save(festivalExistente.get());

            return festivalExistente.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Festival não encontrado");
        }
    }

    public Festival getFestivalPorId(Integer id) {
        Optional<Festival> festival = festivalRepository.findById(id);

        if (festival.isPresent()) {
            return festival.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Festival não encontrado");
        }
    }

    public void deletarFestivalPorId(Integer id) {
        Optional<Festival> festival = festivalRepository.findById(id);

        if (festival.isPresent()) {
            Festival festivalParaExcluir = festival.get();

            // Antes de excluir o festival, você pode remover todas as bandas associadas a ele
            List<Banda> bandasDoFestival = festivalParaExcluir.getBandas();

            // Verifique se há bandas associadas ao festival
            if (bandasDoFestival != null && !bandasDoFestival.isEmpty()) {
                for (Banda banda : bandasDoFestival) {
                    banda.setFestival(null);
                    bandaService.salvar(banda);
                }

                festivalRepository.delete(festivalParaExcluir);

            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Festival não encontrado");
        }
    }

    public Festival cadastrarBandaNoFestival(Integer idFestival, Integer idBanda) {
        Optional<Festival> festivalOptional = festivalRepository.findById(idFestival);

        if (festivalOptional.isPresent()) {
            Festival festival = festivalOptional.get();

            Banda novaBandaCadastrada = bandaService.getBandaPorId(idBanda);

            novaBandaCadastrada.setFestival(festival);
            bandaService.salvar(novaBandaCadastrada);

            return this.festivalRepository.findById(idFestival).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Festival não encontrado");
        }
    }

    public Festival deletarBandaDoFestival(Integer idFestival, Integer idBanda) {
        Optional<Festival> festivalOptional = festivalRepository.findById(idFestival);

        if (festivalOptional.isPresent()) {
            Festival festival = festivalOptional.get();

            Banda bandaParaExcluir = bandaService.getBandaPorId(idBanda);

            bandaParaExcluir.setFestival(null);
            bandaService.salvar(bandaParaExcluir);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Festival não encontrado");
        }
        return this.festivalRepository.findById(idFestival).get();
    }

}
