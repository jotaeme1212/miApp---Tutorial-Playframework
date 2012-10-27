package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.menus.*;
import models.*;
import java.util.List;

@With(Autorizar.class)
public class Menus extends Controller {

    public static Result index() {
        List<Menu> menus = Menu.find.orderBy("raiz asc, orden asc").findList();
        return ok(views.html.menus.index.render(menus));
    }

    // GET, formulario para un nuevo registro
    public static Result create() {
        Form<Menu> formulario = form(Menu.class);
        return ok(create.render(formulario));
    }

    // POST, se guarda el formaulario
    public static Result save() {
        Form<Menu> formulario = form(Menu.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(create.render(formulario));
        }
        formulario.get().created = new java.util.Date();
        formulario.get().updated = new java.util.Date();
        formulario.get().save();
        flash("success", "Menu " + formulario.get().name + " creado con exito!");
        return redirect("/menus/index");
    }

    // GET, editar el registro
    public static Result edit(Long id) {
        Form<Menu> formulario = form(Menu.class).fill(Menu.find.byId(id));
        return ok(edit.render(id, formulario));
    }
    
    // POST, guardar el registro editado
    public static Result update(Long id) {
        Form<Menu> formulario = form(Menu.class).bindFromRequest();
        if(formulario.hasErrors()) {
            return badRequest(edit.render(id, formulario));
        }
        formulario.get().updated = new java.util.Date();
        formulario.get().update(id);
        flash("success", "Menu " + formulario.get().name + " actualizado con exito!");
        return redirect("/menus/index");
    }

    // POST, elimina registro
    public static Result delete(Long id) {
        Menu.find.ref(id).delete();
        flash("success", "Menu ha sido eliminado!");
        return redirect("/menus/index");
    }

}
