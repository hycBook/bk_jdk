package per.hyc.designPattern.Observer;


public interface ISubject {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyAllObservers();
}
