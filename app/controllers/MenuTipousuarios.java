package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.menutipousuarios.*;
import models.*;
import java.util.List;

@With(Autorizar.class)
public class MenuTipousuarios extends Controller {

    public static Result index() {
        return ok(index.render(MenuTipousuario.find.all()));
    }

    // GET, formulario para un nuevo registro
    public static Result create() {
        Form<MenuTipousuario> formulario = form(MenuTipousuario.class);
        return ok(create.render(formulario));
    }

    // POST, se guarda el formaulario
    public static Result save() {
        Form<MenuTipousuario> formulario = form(MenuTipousuario.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(create.render(formulario));
        }
        formulario.get().created = new java.util.Date();
        formulario.get().updated = new java.util.Date();
        formulario.get().save();
        flash("success", "Acceso creado con exito!");
        return redirect("/menutipousuarios/index");
    }

    // GET, editar el registro
    public static Result edit(Long id) {
        Form<MenuTipousuario> formulario = form(MenuTipousuario.class).fill(MenuTipousuario.find.byId(id));
        return ok(edit.render(id, formulario));
    }
    
    // POST, guardar el registro editado
    public static Result update(Long id) {
        Form<MenuTipousuario> formulario = form(MenuTipousuario.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(edit.render(id, formulario));
        }
        formulario.get().updated = new java.util.Date();
        formulario.get().update(id);
        flash("success", "Acceso actualizado con exito!");
        return redirect("/menutipousuarios/index");
    }

    // POST, elimina registro
    public static Result delete(Long id) {
        MenuTipousuario.find.ref(id).delete();
        flash("success", "Acceso ha sido eliminado!");
        return redirect("/menutipousuarios/index");
    }

}
