package nl.fontys.sebivenlo.switcher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class PrimaryController implements Initializable, FXUIScraper {

    final RootSwitcher switcher;

    @FXML
    Parent primary; // <1>

    @FXML
    CheckBox cb1;

    @FXML
    Button primaryButton;

    @FXML
    ComboBox<String> combo;

    public PrimaryController( RootSwitcher switcher ) {
        this.switcher = switcher;
    }

    @Override
    public Parent getRoot() {
        return primary;
    }

    @FXML
    void switchToSecondary() throws IOException {
        String value = combo.getValue();
        System.out.println( "value = " + value );
        reset();
        switcher.setRoot( value );
    }

    @Override
    public void initialize( URL arg0, ResourceBundle arg1 ) {
        combo.getItems().add( "secondary" );
        combo.getItems().add( "tertiary" );
        primaryButton.setDisable( true );
    }

    @FXML
    void switchSceneCombo( ActionEvent event ) {
//        ComboBox bx = (ComboBox) event.getSource();
//        String selectedItem = (String) bx.getSelectionModel().getSelectedItem();
        primaryButton.setDisable( false );
        
//        switcher.setRoot( selectedItem );
    }

    private void reset(){
        combo.getSelectionModel().clearSelection();
        primaryButton.setDisable( true );
    }
}
