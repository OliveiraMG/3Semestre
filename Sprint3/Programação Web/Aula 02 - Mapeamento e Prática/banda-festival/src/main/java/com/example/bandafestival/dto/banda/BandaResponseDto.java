package com.example.bandafestival.dto.banda;

import java.time.LocalDateTime;

public record BandaResponseDto(Integer idBanda, String nomeBanda, String genero, LocalDateTime diaDeFormacao) {
}
