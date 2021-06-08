package dataImport.csvParser;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class CsvParserUtil {
    protected static ZonedDateTime convert(Long milis){

        ZoneId zone;
        zone = ZoneId.systemDefault();


        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(milis), zone);
    }
}
