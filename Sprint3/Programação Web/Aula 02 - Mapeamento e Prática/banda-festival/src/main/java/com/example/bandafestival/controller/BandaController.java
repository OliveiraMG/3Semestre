package com.example.bandafestival.controller;

import com.example.bandafestival.dto.banda.BandaRequestDto;
import com.example.bandafestival.dto.banda.BandaResponseDto;
import com.example.bandafestival.entity.Banda;
import com.example.bandafestival.service.BandaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bandas")
public class BandaController {

    private BandaService bandaService;

    public BandaController(BandaService bandaService) {
        this.bandaService = bandaService;
    }

    @PostMapping
    public ResponseEntity<BandaResponseDto> cadastrarBanda(@RequestBody @Valid  BandaRequestDto banda) {
        Banda bandaEntity = bandaService.cadastrarBanda(banda);

        BandaResponseDto bandaResponseDto = new BandaResponseDto(bandaEntity.getId(), bandaEntity.getNomeBanda(), bandaEntity.getGenero(), bandaEntity.getDiaDeFormacao());

        return ResponseEntity.ok(bandaResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<BandaResponseDto>> listarBandas() {
        List<Banda> allBandas = bandaService.getTodasBandas();

        List<BandaResponseDto> dtoList = allBandas.stream()
                .map(banda -> new BandaResponseDto(banda.getId(), banda.getNomeBanda(), banda.getGenero(), banda.getDiaDeFormacao()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/bandaParcialNome")
    public ResponseEntity<List<BandaResponseDto>> listarBandasPorNomeParcial(@RequestParam  String nomeBanda) {
        List<Banda> allBandas = bandaService.getBandaPorNomeParcialIgnorandoCase(nomeBanda);

        List<BandaResponseDto> dtoList = allBandas.stream()
                .map(banda -> new BandaResponseDto(banda.getId(), banda.getNomeBanda(), banda.getGenero(), banda.getDiaDeFormacao()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/bandaGenero")
    public ResponseEntity<List<BandaResponseDto>> listarBandasPorGenero(@RequestParam String genero) {
        List<Banda> allBandas = bandaService.getBandaPorGenero(genero);

        List<BandaResponseDto> dtoList = allBandas.stream()
                .map(banda -> new BandaResponseDto(banda.getId(), banda.getNomeBanda(), banda.getGenero(), banda.getDiaDeFormacao()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/bandaMaisAntiga")
    public ResponseEntity<List<BandaResponseDto>> listar3BandasMaisAntigas() {
        List<Banda> allBandas = bandaService.get3BandasMaisAntigas();

        List<BandaResponseDto> dtoList = allBandas.stream()
                .map(banda -> new BandaResponseDto(banda.getId(), banda.getNomeBanda(), banda.getGenero(), banda.getDiaDeFormacao()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BandaResponseDto> listarBandaPorId(@PathVariable Integer id) {
        Banda banda = bandaService.getBandaPorId(id);

        BandaResponseDto bandaResponseDto = new BandaResponseDto(banda.getId(), banda.getNomeBanda(), banda.getGenero(), banda.getDiaDeFormacao());

        return ResponseEntity.ok(bandaResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BandaResponseDto> atualizarBanda(@PathVariable Integer id, @RequestBody @Valid BandaRequestDto banda) {
        Banda bandaEntity = bandaService.atualizarBandaPorId(id, banda);

        BandaResponseDto bandaResponseDto = new BandaResponseDto(bandaEntity.getId(), bandaEntity.getNomeBanda(), bandaEntity.getGenero(), bandaEntity.getDiaDeFormacao());

        return ResponseEntity.ok(bandaResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBanda(@PathVariable Integer id) {
        bandaService.deletarBandaPorId(id);

        return ResponseEntity.ok().build();
    }
}
