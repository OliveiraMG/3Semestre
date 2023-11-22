package school.sptech.relacionameto1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.relacionameto1.dto.medico.ConsultaMedicoDto;
import school.sptech.relacionameto1.dto.medico.MedicoConsultaDto;
import school.sptech.relacionameto1.dto.medico.mapper.MedicoMapper;
import school.sptech.relacionameto1.entity.Consulta;
import school.sptech.relacionameto1.entity.Medico;
import school.sptech.relacionameto1.repository.MedicoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    @GetMapping
    public ResponseEntity<List<MedicoConsultaDto>> findAll(){
        List<Medico> all = medicoRepository.findAll();

        if (all.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<MedicoConsultaDto> medicosDtos = all.stream()
                .map(MedicoMapper::toMedicoConsultaDto)
                .toList();

        return ResponseEntity.ok(medicosDtos);
    }
}
