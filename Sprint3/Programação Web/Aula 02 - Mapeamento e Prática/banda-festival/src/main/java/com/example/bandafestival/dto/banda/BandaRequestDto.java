package com.example.bandafestival.dto.banda;

import java.time.LocalDateTime;

public record BandaRequestDto(String nomeBanda, String genero, LocalDateTime diaDeFormacao) {
}
