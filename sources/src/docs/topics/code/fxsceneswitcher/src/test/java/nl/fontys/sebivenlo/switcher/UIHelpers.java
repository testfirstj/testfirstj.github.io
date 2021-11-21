package nl.fontys.sebivenlo.switcher;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;

/**
 * Helper utils for testing ad debugging.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class UIHelpers {

    /**
     * Recursively print the children of a root element which have an id
     * (getId()!=null).
     *
     * @param root top
     */
    static void printChildren( Parent root ) {
        if ( null != root.getId() ) {
            System.out.println( "parent node = #" + root.getId() + " class " + root.getClass().getSimpleName() );
        }
        root.getChildrenUnmodifiable().stream().map( node -> {
            if ( null != node.getId() ) {
                System.out.println( "node = #" + node.getId() + " class " + node.getClass().getSimpleName() );
            }
            return node;
        } ).filter( node -> ( node instanceof Parent ) ).forEachOrdered( node -> {
            printChildren( (Parent) node ); // recuurse into parent nodes.
        } );
    }

    static void pause( long milis ) {
        try {
            Thread.currentThread().sleep( milis );
        } catch ( InterruptedException ignored ) {
            Logger.getLogger( UIHelpers.class.getName() ).log( Level.SEVERE, ignored.getMessage() );
        }
    }
}
