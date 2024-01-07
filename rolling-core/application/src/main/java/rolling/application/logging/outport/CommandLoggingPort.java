package rolling.application.logging.outport;

public interface CommandLoggingPort<T> {

    T save(T logging);

}