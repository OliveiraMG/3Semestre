package com.example.bandafestival.dto.festival;

import com.example.bandafestival.entity.Banda;
import java.util.List;

public record FestivalResponseDto(Integer idFestival, String nomeFestival, String local, java.time.LocalDateTime dataHoraFestival, List<String> lineUp) {
}
