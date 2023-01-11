package com.mbo.perfumery.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    public Timestamp getTimeStamp(OffsetDateTime date)
    {
        if(date!=null)
            return Timestamp.valueOf(date.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        else
            return null;
    }

    public OffsetDateTime getOffsetDateTime(Timestamp date)
    {
        if(date != null) {
            LocalDateTime localDateTime = date.toLocalDateTime();
            return OffsetDateTime.of(localDateTime.getYear(), localDateTime.getMonthValue(),
                    localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute(),
                    localDateTime.getSecond(), localDateTime.getNano(), ZoneOffset.UTC);
        }
        else
            return null;

    }
}
