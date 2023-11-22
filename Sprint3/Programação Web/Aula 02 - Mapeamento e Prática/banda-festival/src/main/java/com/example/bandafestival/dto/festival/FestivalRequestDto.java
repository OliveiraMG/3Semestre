package com.example.bandafestival.dto.festival;

import com.example.bandafestival.entity.Banda;

import java.time.LocalDateTime;
import java.util.List;

public record FestivalRequestDto(String nomeFestival, String local, LocalDateTime dataHoraFestival, List<Banda> banda) {
}
