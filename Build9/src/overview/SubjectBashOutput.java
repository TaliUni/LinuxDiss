package overview;

public interface SubjectBashOutput {
	
	public void registerObserverBashOutput(ObserverBashOutput obs);
	public void removeObserverBashOutput(ObserverBashOutput obs);
	public void notifyObserversBashOutput();

}
