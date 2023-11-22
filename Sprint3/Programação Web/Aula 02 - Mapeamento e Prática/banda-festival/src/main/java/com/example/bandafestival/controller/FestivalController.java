package com.example.bandafestival.controller;

import com.example.bandafestival.dto.festival.FestivalRequestDto;
import com.example.bandafestival.dto.festival.FestivalResponseDto;
import com.example.bandafestival.entity.Banda;
import com.example.bandafestival.entity.Festival;
import com.example.bandafestival.service.FestivalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/festivais")
public class FestivalController {

    private final FestivalService festivalService;

    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @GetMapping
    public ResponseEntity<List<FestivalResponseDto>> listarFestivais() {
        List<Festival> all = festivalService.getTodosFestivais();

        List<FestivalResponseDto> festivalDtos = all.stream()
                .map(festival -> new FestivalResponseDto(
                        festival.getId(),
                        festival.getNomeFestival(),
                        festival.getLocal(),
                        festival.getDataHoraFestival(),
                        festival.getBandas().stream()
                                .map(Banda::getNomeBanda).toList()
                ))
                .toList();

        return ResponseEntity.ok(festivalDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FestivalResponseDto> listarFestivalPorId(@PathVariable Integer id) {
        Festival festival = festivalService.getFestivalPorId(id);

        List<String> nomesDasBandas = festival.getBandas().stream()
                .map(Banda::getNomeBanda).toList();

        FestivalResponseDto festivalResponseDto = new FestivalResponseDto(
                festival.getId(),
                festival.getNomeFestival(),
                festival.getLocal(),
                festival.getDataHoraFestival(),
                nomesDasBandas
        );

        return ResponseEntity.ok(festivalResponseDto);
    }

    @PostMapping("/cadastrarBandaNoFestival/{idFestival}/{idBanda}")
    public ResponseEntity<FestivalResponseDto> cadastrarBandaNoFestival(@PathVariable Integer idFestival, @PathVariable Integer idBanda) {
        Festival festival = festivalService.cadastrarBandaNoFestival(idFestival, idBanda );

        List<String> nomesDasBandas = festival.getBandas().stream()
                .map(Banda::getNomeBanda).toList();

        FestivalResponseDto festivalResponseDto = new FestivalResponseDto(
                festival.getId(),
                festival.getNomeFestival(),
                festival.getLocal(),
                festival.getDataHoraFestival(),
                nomesDasBandas
        );

        return ResponseEntity.ok(festivalResponseDto);
    }

    @PostMapping
    public ResponseEntity<FestivalResponseDto> cadastrarFestival(@RequestBody @Valid FestivalRequestDto festival) {
        Festival festivalEntity = festivalService.cadastrarFestival(festival);

        FestivalResponseDto festivalResponseDto = new FestivalResponseDto(
                festivalEntity.getId(),
                festivalEntity.getNomeFestival(),
                festivalEntity.getLocal(),
                festivalEntity.getDataHoraFestival(),
                festivalEntity.getBandas().stream()
                        .map(Banda::getNomeBanda)
                        .collect(Collectors.toList())
        );

        return ResponseEntity.ok(festivalResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FestivalResponseDto> atualizarFestival(@PathVariable Integer id, @RequestBody @Valid FestivalRequestDto festival) {
        Festival festivalEntity = festivalService.atualizarFestivalPorId(id, festival);

        FestivalResponseDto festivalResponseDto = new FestivalResponseDto(
                festivalEntity.getId(),
                festivalEntity.getNomeFestival(),
                festivalEntity.getLocal(),
                festivalEntity.getDataHoraFestival(),
                festivalEntity.getBandas().stream()
                        .map(Banda::getNomeBanda)
                        .collect(Collectors.toList())
        );

        return ResponseEntity.ok(festivalResponseDto);
    }

    @DeleteMapping("/deletarBandaDoFestival/{idFestival}/{idBanda}")
    public ResponseEntity<FestivalResponseDto> deletarBandaDoFestival(@PathVariable Integer idFestival, @PathVariable Integer idBanda) {
        Festival festival = festivalService.deletarBandaDoFestival(idFestival, idBanda);

        List<String> nomesDasBandas = festival.getBandas().stream()
                .map(Banda::getNomeBanda).toList();

        FestivalResponseDto festivalResponseDto = new FestivalResponseDto(
                festival.getId(),
                festival.getNomeFestival(),
                festival.getLocal(),
                festival.getDataHoraFestival(),
                nomesDasBandas
        );

        return ResponseEntity.ok(festivalResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFestival(@PathVariable Integer id) {
        festivalService.deletarFestivalPorId(id);

        return ResponseEntity.ok().build();
    }

}