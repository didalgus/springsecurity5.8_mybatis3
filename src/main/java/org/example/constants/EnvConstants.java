package org.example.constants;

import java.time.format.DateTimeFormatter;

public interface EnvConstants {

    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter FORMATTER_YMD = DateTimeFormatter.ofPattern("yyyyMMdd");
}
