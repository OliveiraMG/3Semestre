package school.sptech.relacionameto1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.relacionameto1.dto.consulta.ConsultaMedicoResponseDto;
import school.sptech.relacionameto1.dto.consulta.MedicoConsultaResponseDto;
import school.sptech.relacionameto1.entity.Consulta;
import school.sptech.relacionameto1.entity.Medico;
import school.sptech.relacionameto1.repository.ConsultaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor // Coloca em um construtor as propriedades que são "final"
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaRepository consultaRepository;

    @GetMapping
    public ResponseEntity<List<ConsultaMedicoResponseDto>> findAll(){

        List<Consulta> all = consultaRepository.findAll();

        if (all.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<ConsultaMedicoResponseDto> dtos = new ArrayList<>();

        for (Consulta consultaEntidade : all) { // enhanced for

            ConsultaMedicoResponseDto consultaDto = new ConsultaMedicoResponseDto();

            consultaDto.setId(consultaEntidade.getId());
            consultaDto.setNomePaciente(consultaEntidade.getNomePaciente());
            consultaDto.setDataHora(consultaEntidade.getDataHora());
            consultaDto.setStatus(consultaEntidade.getStatus());

            // consultaEntidade.getMedico() != null
            if (Objects.nonNull(consultaEntidade.getMedico())){

                Medico medicoEntidade = consultaEntidade.getMedico();

                MedicoConsultaResponseDto medicoDto = new MedicoConsultaResponseDto();

                medicoDto.setId(medicoEntidade.getId());
                medicoDto.setCrm(medicoEntidade.getCrm());
                medicoDto.setNome(medicoEntidade.getNome());
                medicoDto.setEspecialidade(medicoEntidade.getEspecialidade());

                consultaDto.setMedico(medicoDto);
            }

            dtos.add(consultaDto);
        }

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/medicos")
    public ResponseEntity<List<Consulta>> findByMedicoNome(@RequestParam String nome){

        List<Consulta> all = consultaRepository.findByMedicoNomeContainsIgnoreCase(nome);

        if (all.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(all);
    }

    @GetMapping("/medicos/crm")
    public ResponseEntity<List<Consulta>> findByMedicoCrm(@RequestParam String codigo){

        List<Consulta> all = consultaRepository.buscarPorCrm(codigo);

        if (all.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(all);
    }
}
