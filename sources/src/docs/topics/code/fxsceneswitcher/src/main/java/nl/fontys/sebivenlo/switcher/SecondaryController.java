package nl.fontys.sebivenlo.switcher;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Parent;

public class SecondaryController {

    final RootSwitcher switcher;

    @FXML
    Parent primary; // <1>

    public SecondaryController( RootSwitcher switcher ) {
        this.switcher = switcher;
    }

    @FXML
    void switchToTertiary() throws IOException {
        switcher.setRoot( "tertiary" );
    }
}
