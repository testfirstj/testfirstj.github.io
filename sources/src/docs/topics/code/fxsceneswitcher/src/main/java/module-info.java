module switcher {
    requires java.logging;
    requires javafx.controls;
    requires javafx.fxml;
    opens nl.fontys.sebivenlo.switcher to javafx.fxml;
    exports nl.fontys.sebivenlo.switcher;
}
