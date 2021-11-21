package nl.fontys.sebivenlo.switcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Callback;

/**
 * JavaFX Switcher
 */
public class Switcher extends Application implements RootSwitcher {

    private static Scene scene;
    private final Map<String, Parent> sceneParents = new HashMap<>(); //<1>
    private String currentRoot = "primary"; //<2>

    final Map<String, Callback<Class<?>, Object>> controllerFactories //<3>
            = Map.ofEntries(
                    registeringFactory( "primary", ( clz ) -> new PrimaryController( this ) ),
                    registeringFactory( "secondary", ( clz ) -> new SecondaryController( this ) ),
                    registeringFactory( "tertiary", ( clz ) -> new TertiaryController( this ) )
            );

    @Override
    public void start( Stage stage ) throws IOException {
        scene = new Scene( loadFXML( currentRoot ), 640, 480 );
        stage.setScene( scene );
        stage.show();
    }

    @Override
    public String setRoot( String fxml ) {
        String prevRoot = currentRoot;
        currentRoot = fxml;
        scene.setRoot( loadFXML( currentRoot ) );
        return prevRoot;
    }

    private Parent loadFXML( String fxml ) {
        return sceneParents.computeIfAbsent( fxml, this::loadFXMLResource );
    }

    private Parent loadFXMLResource( final String forScene ) {
        Parent result = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader( Switcher.class.getResource( forScene + ".fxml" ) );
            Callback<Class<?>, Object> factory = controllerFactories.get( forScene );
            fxmlLoader.setControllerFactory( factory );
            result = fxmlLoader.load();
        } catch ( IOException ex ) {
            Logger.getLogger( Switcher.class.getName() ).log( Level.SEVERE, ex.getMessage() );
        }
        return result;
    }

    Map<String, Object> createdControllers = new HashMap<>();

    Map.Entry<String, Callback<Class<?>, Object>> registeringFactory( final String key, final Callback<Class<?>, Object> callBack ) {
        return entry( key, ( clz ) -> {
            var result = callBack.call( clz );
            createdControllers.put( key, result );
            return result;
        } );
    }

    @Override
    public Object getControllerForScene( String sceneName ) {
        return createdControllers.get( sceneName );
    }

    public static void main( String[] args ) {
        launch();
    }
}
