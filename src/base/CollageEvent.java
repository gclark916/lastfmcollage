package base;

public class CollageEvent {
	public enum EventType {
		UPDATED, COMPLETE;
	}
	
	Collage collage;
	EventType type;
	
	/**
	 * @return the collage
	 */
	public Collage getCollage() {
		return collage;
	}
	
	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}
	
	/**
	 * @param collage
	 * @param type
	 */
	public CollageEvent(Collage collage, EventType type) {
		super();
		this.collage = collage;
		this.type = type;
	}
}
