package nl.fontys.sebivenlo.switcher;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Parent;

public class TertiaryController implements FXUIScraper {

    final RootSwitcher switcher;

    @FXML
    Parent tertiary; // <1>

    public TertiaryController( RootSwitcher switcher ) {
        this.switcher = switcher;
    }

    @FXML
   void switchToPrimary() throws IOException {
        switcher.setRoot( "secondary" );
    }

    @Override
    public Parent getRoot() {
       return tertiary;
    }
    
}
