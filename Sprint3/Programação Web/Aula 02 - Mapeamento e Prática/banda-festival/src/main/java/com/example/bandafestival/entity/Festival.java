package com.example.bandafestival.entity;

import com.example.bandafestival.dto.festival.FestivalRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nomeFestival;

    @NotNull
    private String local;

    @NotNull
    private LocalDateTime dataHoraFestival;

    @OneToMany(mappedBy = "festival")
    List<Banda> bandas = new ArrayList<>();

    public Festival(String nomeFestival, String local, LocalDateTime dataHoraFestival) {}
}
