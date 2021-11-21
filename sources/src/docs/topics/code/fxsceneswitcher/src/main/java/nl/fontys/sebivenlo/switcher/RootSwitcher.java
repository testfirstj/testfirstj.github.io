package nl.fontys.sebivenlo.switcher;

/**
 * A class that can switch to named scenes.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public interface RootSwitcher {

    /**
     * Set the root of the scene on the primary stage to the named scene
     *
     * @param sceneName to use
     * @return the previous sceneName
     */
    String setRoot( String sceneName );

    /**
     * Get the FXML controller for a named scene.
     * @param sceneName
     * @return 
     */
    Object getControllerForScene( String sceneName );
}
