package mx.com.aft.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller("sessionsC")
@Scope("singleton")
public class SessionsController implements Serializable {
    
    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;
    
    public List<User> getAllUsers() {
        List<User> users = new ArrayList();
        
        for (Object principal: this.sessionRegistry.getAllPrincipals()) {
            if (principal instanceof User) {
                users.add((User) principal);
            }
        }
        
        return users;
    }
    
    public User getUserByUsername(String username) {
        for (Object principal: this.sessionRegistry.getAllPrincipals()) {
            if (principal instanceof User && ((User) principal).getUsername().equals(username)) {
                return (User) principal;
            }
        }
        
        return null;
    }
    
    public List<SessionInformation> getAllSessions(User user) {
        List<SessionInformation> sessions = this.sessionRegistry.getAllSessions(user, false);
        
        return sessions;
    }
    
    public List<SessionInformation> getAllSessionsByUsername(String username) {
        User user = this.getUserByUsername(username);
        
        if (user != null) {
            return this.sessionRegistry.getAllSessions(user, false);
        }
        
        return null;
    }
    
    public void destroySession(String sessionId) {
        this.sessionRegistry.getSessionInformation(sessionId).expireNow();
    }
    
    public void destroySessions(List<String> sessionsId) {
        for (String sessionId : sessionsId) {
            this.destroySession(sessionId);
        }
    }
    
    public void destroySessionsByUsername(String username) {
        List<SessionInformation> sessions = this.getAllSessionsByUsername(username);
        
        if (sessions != null) {
            for (SessionInformation session : sessions) {
                this.destroySession(session.getSessionId());
            }
        }
    }
    
}
