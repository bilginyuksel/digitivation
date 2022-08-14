package com.bilginyuksel.digitivation.infra.port.response;

import java.time.LocalDateTime;

public record EventResponse(
        LocalDateTime time,
        String place,
        String customMessage
) {
}
