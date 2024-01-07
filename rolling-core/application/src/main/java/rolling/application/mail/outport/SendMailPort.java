package rolling.application.mail.outport;

public interface SendMailPort {

    void sendWelcome(String receiver);

}