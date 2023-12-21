package org.machicoro.util;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerConfig {
    @Getter
    private static final Logger logger = LoggerFactory.getLogger("MACHICORO");

}
