package wisepanda.service;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Service;

@Service
public class FacesService {
    
    public void sendNotification(String message) {
         
    }

    public void addMessage(String summary, String detail, Severity type) {
        FacesMessage message = new FacesMessage(type, summary, detail);
        FacesContext.getCurrentInstance().addMessage("messages", message);
    }
}
