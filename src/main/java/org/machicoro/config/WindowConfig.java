package org.machicoro.config;

import javafx.fxml.FXMLLoader;
import lombok.*;

@AllArgsConstructor
@Data
public class WindowConfig {
    @Getter
    private static final String windowName = "MachiCoro V0.1";
    @Getter
    private static final int windowWight = 640;
    @Getter
    private static final int windowHeight = 380;

    @Getter
    @Setter
    private static FXMLLoader loader;

}
