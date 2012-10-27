package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.usuarios.*;
import models.*;
import java.util.List;

@With(Autorizar.class)
public class Usuarios extends Controller {

    public static Result index() {
        return ok(index.render(Usuario.find.all()));
    }

    // GET, formulario para un nuevo registro
    public static Result create() {
        Form<Usuario> formulario = form(Usuario.class);
        return ok(create.render(formulario));
    }

    // POST, se guarda el formaulario
    public static Result save() {
        Form<Usuario> formulario = form(Usuario.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(create.render(formulario));
        }
        formulario.get().created = new java.util.Date();
        formulario.get().updated = new java.util.Date();
        formulario.get().save();
        flash("success", "Usuario " + formulario.get().name + " creado con exito!");
        return redirect("/usuarios/index");
    }

    // GET, editar el registro
    public static Result edit(Long id) {
        Form<Usuario> formulario = form(Usuario.class).fill(Usuario.find.byId(id));
        return ok(edit.render(id, formulario));
    }
    
    // POST, guardar el registro editado
    public static Result update(Long id) {
        Form<Usuario> formulario = form(Usuario.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(edit.render(id, formulario));
        }
        formulario.get().updated = new java.util.Date();
        formulario.get().update(id);
        flash("success", "Usuario " + formulario.get().name + " actualizado con exito!");
        return redirect("/usuarios/index");
    }

    // POST, elimina registro
    public static Result delete(Long id) {
        Usuario.find.ref(id).delete();
        flash("success", "Usuario ha sido eliminado!");
        return redirect("/usuarios/index");
	}

}
