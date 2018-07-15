package ninja.tuxtech.envers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@RevisionEntity(UserRevisionListener.class)
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserRevEntity extends DefaultRevisionEntity implements Serializable {
    private String username;
    private String ip;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}