
package finalchess.players;

/**
 * Player which will either be human or AI
 * @author Nejko
 */
public abstract class Player {

    private boolean color;

    public Player() { }


    
    protected boolean color;
    public static final boolean WHITE=true, BLACK=false;
        
	public Player(boolean color) {
		this.color = color;
	}
        public boolean getColor() {
		return color;
	}
        public void setColor(boolean color){
            this.color = color;
        }
}
