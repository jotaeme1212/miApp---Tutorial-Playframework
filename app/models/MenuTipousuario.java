package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity 
public class MenuTipousuario extends Model {

    @Id
    public Long id;
    
    @Formats.DateTime(pattern="dd-MM-yyyy - hh:mm")
    public Date created;
    
    @Formats.DateTime(pattern="dd-MM-yyyy - hh:mm")
    public Date updated;

    @ManyToOne
	@Constraints.Required(message="Debe ingresar el menu")
    public Menu menu;
	public Menu getMenu(Long id) {
		return Menu.find.where().idEq(id).findUnique();
	}

    @ManyToOne
	@Constraints.Required(message="Debe ingresar el tipo de usuario")
    public Tipousuario tipousuario;
	public Tipousuario getTipousuario(Long id) {
		return Tipousuario.find.where().idEq(id).findUnique();
	}

    // Generic query helper for entity with id Long
    public static Model.Finder<Long,MenuTipousuario> find = new Model.Finder<Long,MenuTipousuario>(Long.class, MenuTipousuario.class);

}
