package mx.com.aft.controller.language;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller("languageC")
@Scope("session")
public class LanguageController implements Serializable {
    
    private Locale locale;
    private String localeCode;

    private static final Map<String, Object> countries;

    static {
        countries = new LinkedHashMap();
        countries.put("English", new Locale("en", "US"));
        countries.put("Spanish", new Locale("es", "MX"));
    }

    @PostConstruct
    public void init() {
        this.locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        if(this.locale.getLanguage().equals("es")) {
            this.localeCode = "es_MX";
        } else {
            this.localeCode = this.locale.toString();
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }
    
    public String getLang() {
        return this.locale.getLanguage();
    }
    
    public void change(String newLocaleValue) {
        this.localeCode = newLocaleValue;
        
        for (Map.Entry<String, Object> entry : LanguageController.countries.entrySet()) {
            if (entry.getValue().toString().equals(newLocaleValue)) {
                this.locale = (Locale) entry.getValue();
                
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
            }
        }
    }
    
}
