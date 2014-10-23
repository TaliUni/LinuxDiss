package overview;

public interface SubjectCommandLineInput {
	
	public void registerObserverCommandLineInput(ObserverCommandLineInput obs);
	public void removeObserverCommandLineInput(ObserverCommandLineInput obs);
	public void notifyObserversCommandLineInput();

}
