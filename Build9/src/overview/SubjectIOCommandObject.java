package overview;

public interface SubjectIOCommandObject {
	
	public void registerObserverOfIOCommandObject(ObserverOfIOCommandObject obs);
	public void removeObserverOfIOCommandObject(ObserverOfIOCommandObject obs);
	public void notifyObserversOfIOCommandObject();

}
