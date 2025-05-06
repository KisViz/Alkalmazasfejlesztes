module hu.alkfejl.desktop {
    requires hu.alkfejl.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires javafx.graphics;

    exports hu.alkfejl;

    opens hu.alkfejl to javafx.fxml;
    opens hu.alkfejl.view to javafx.fxml;
    opens hu.alkfejl.common to javafx.fxml;
}

