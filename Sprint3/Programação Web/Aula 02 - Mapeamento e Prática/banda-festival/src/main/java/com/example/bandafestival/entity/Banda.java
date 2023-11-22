package com.example.bandafestival.entity;

import com.example.bandafestival.dto.banda.BandaRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Banda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeBanda;

    private String genero;

    private LocalDateTime diaDeFormacao;

    @ManyToOne
    private Festival festival;

    public Banda(String nomeBanda, String genero, LocalDateTime diaDeFormacao) {}
}
