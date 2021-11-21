
package simple;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
abstract class Shape implements Serializable {
    
}

record Point (int x, int y){}
class Circle  extends Shape implements ArcLike,Surface {}

interface ArcLike{}
class Arc extends Shape implements ArcLike{ }
class Line extends Shape{}
interface Surface{}
class Canvas {
    void draw(List<? extends Shape> s){}
}
class Triangle extends Shape implements Surface{}

class Disk extends Circle{}
class AreaCollector {
    void collect( List<? super Circle> s){
        s.add( new Circle() );
        s.add( new Disk() );
    }
}

class Toy{
    public static void main( String[] args ) {
        Canvas canvas = new Canvas();
        
        List<Circle> circles =  new ArrayList<>();
        canvas.draw( circles );
        
        AreaCollector c = new AreaCollector();
        
        c.collect(circles);
    }
}