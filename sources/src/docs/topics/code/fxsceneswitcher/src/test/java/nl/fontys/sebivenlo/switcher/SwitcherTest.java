package nl.fontys.sebivenlo.switcher;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import static nl.fontys.sebivenlo.switcher.UIHelpers.pause;
import static nl.fontys.sebivenlo.switcher.UIHelpers.printChildren;
import static org.assertj.core.api.Assertions.*;
import org.testfx.api.FxRobot;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
@ExtendWith( ApplicationExtension.class )
public class SwitcherTest {

    static {
        if ( Boolean.getBoolean( "SERVER" ) ) {
            System.setProperty( "java.awt.headless", "true" );
            System.setProperty( "testfx.robot", "glass" );
            System.setProperty( "testfx.headless", "true" );
            System.setProperty( "prism.order", "sw" );
            System.setProperty( "prism.text", "t2k" );
            System.setProperty( "glass.platform", "Monocle" );
            System.setProperty( "monocle.platform", "Headless" );
        }
    }

    // set by start
    Switcher app;
    // set by start
    Stage stage;

    @Start
    void start( Stage stage ) throws IOException {
        app = new Switcher(); //<1>
        app.start( stage );
        this.stage = stage;
    }

//    @Disabled("think TDD")
    @Test
    public void tSwitch() {
        Parent root = stage.getScene().getRoot();
        printChildren( root );
        app.setRoot( "secondary" );
        FxRobot rob = new FxRobot();
        rob.clickOn( "#cb1" ); // <1>
        Button b = (Button) rob.lookup( "#secondaryButton" ).query(); //<2>
        rob.clickOn( b ); //<3>
        pause( 1000 );  //<4>
        b = (Button) rob.lookup( "#tertiaryButton" ).query();
        rob.clickOn( "#cb1" );
        rob.clickOn( b );
        pause( 1000 );
        assertThat( stage.getScene().getRoot().getId() ).isEqualTo( "secondary" );

//        fail( "method method reached end. You know what to do." );
    }

    //@Disabled("think TDD")
    @Test
    public void tSwitchCombo() {
        FxRobot rob = new FxRobot();
        final ComboBox b = (ComboBox) rob.lookup( "#combo" ).query(); //<2>
        rob.interact( () -> {
            b.getSelectionModel().select( "tertiary" ); //tertiary
        } );
        pause( 500 );
        rob.clickOn( "#primaryButton" );
        assertThat( stage.getScene().getRoot().getId() ).isEqualTo( "tertiary" );
//        fail( "method tSwitchCombo reached end. You know what to do." );
    }
}
