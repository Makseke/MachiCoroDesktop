module org.machicoro {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;
    requires lombok;
    requires kryo;
    requires kryonet;
    requires org.slf4j;
    requires com.google.gson;

    opens org.machicoro to javafx.fxml;
    exports org.machicoro;

    exports org.machicoro.to.domain.server;
    exports org.machicoro.to.domain.game;
    exports org.machicoro.to.request;

    opens org.machicoro.to.domain.server to kryo, com.google.gson;
    opens org.machicoro.to.request to kryo, com.google.gson;
    opens org.machicoro.to.domain.game to kryo, com.google.gson;
    exports org.machicoro.controller.window;
    opens org.machicoro.controller.window to javafx.fxml;
}