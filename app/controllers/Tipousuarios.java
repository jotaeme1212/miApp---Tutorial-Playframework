package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.tipousuarios.*;

import models.*;

@With(Autorizar.class)
public class Tipousuarios extends Controller {

    public static Result index() {
      return ok(index.render(Tipousuario.find.all()));
    }

    // GET, formulario para un nuevo registro
    public static Result create() {
        Form<Tipousuario> formulario = form(Tipousuario.class);
        return ok(create.render(formulario));
    }

    // POST, se guarda el formaulario
    public static Result save() {
        Form<Tipousuario> formulario = form(Tipousuario.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(create.render(formulario));
        }
        formulario.get().created = new java.util.Date();
        formulario.get().updated = new java.util.Date();
        formulario.get().save();
        flash("success", "Tipo de Usuario " + formulario.get().name + " creado con exito!");
        return redirect("/tipousuarios/index");
    }

    // GET, editar el registro
    public static Result edit(Long id) {
        Form<Tipousuario> formulario = form(Tipousuario.class).fill(Tipousuario.find.byId(id));
        return ok(edit.render(id, formulario));
    }
    
    // POST, guardar el registro editado
    public static Result update(Long id) {
        Form<Tipousuario> formulario = form(Tipousuario.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(edit.render(id, formulario));
        }
        formulario.get().updated = new java.util.Date();
        formulario.get().update(id);
        flash("success", "Tipo de Usuario " + formulario.get().name + " actualizado con exito!");
        return redirect("/tipousuarios/index");
    }

    // POST, elimina registro
    public static Result delete(Long id) {
        Tipousuario.find.ref(id).delete();
        flash("success", "Tipo de Usuario ha sido eliminado!");
        return redirect("/tipousuarios/index");
    }

}