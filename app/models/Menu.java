package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity 
public class Menu extends Model {

    @Id
    public Long id;
    
    @Constraints.Required(message="Debe ingresar el nombre")
    public String name;
    
    @Formats.DateTime(pattern="dd-MM-yyyy - hh:mm")
    public Date created;
    
    @Formats.DateTime(pattern="dd-MM-yyyy - hh:mm")
    public Date updated;

    @Constraints.Min(0)
	@Constraints.Max(10)
    public int raiz;

    @Constraints.Min(0)
	@Constraints.Max(10)
    public int orden;

    public String accion;

    public String controlador;
	
	public String url;

	public String icon;

    // Generic query helper for entity with id Long
    public static Model.Finder<Long,Menu> find = new Model.Finder<Long,Menu>(Long.class, Menu.class);

	// Para usar en select list
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Menu c: Menu.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }

}
