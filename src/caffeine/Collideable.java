package caffeine;

public interface Collideable {

  boolean collides(double left, double top, double right, double bottom);

  boolean onCollide();

}
