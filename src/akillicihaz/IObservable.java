package akillicihaz;

public interface IObservable {

    void notifyObserver();

    void addObserver(IObserver observer);
}
