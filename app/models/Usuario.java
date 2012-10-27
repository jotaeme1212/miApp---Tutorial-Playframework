package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity 
public class Usuario extends Model {

    @Id
    public Long id;
    
    @Constraints.Required(message="Debe ingresar el nombre")
    public String name;
    
    @Formats.DateTime(pattern="dd-MM-yyyy - hh:mm")
    public Date created;
    
    @Formats.DateTime(pattern="dd-MM-yyyy - hh:mm")
    public Date updated;

    @Constraints.Required(message="Debe ingresar el login")
    public String login;

    @Constraints.Required(message="Debe ingresar el password")
    public String password;

	@Constraints.Required(message="Debe ingresar el mail")
    @Constraints.Email
    public String email;
	
    @ManyToOne
	@Constraints.Required(message="Debe ingresar el tipo de usuario")
    public Tipousuario tipousuario;
	public Tipousuario getTipousuario(Long id) {
		return Tipousuario.find.where().idEq(id).findUnique();
	}

    // Generic query helper for entity with id Long
    public static Model.Finder<Long,Usuario> find = new Model.Finder<Long,Usuario>(Long.class, Usuario.class);

}

