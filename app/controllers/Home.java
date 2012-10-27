package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;
import views.html.home.*;

import models.*;

public class Home extends Controller {

    public static Result index() {
      return ok(index.render());
    }

    @With(Autentificar.class)
    public static Result acerca_de() {
      return ok(acerca_de.render());
    }

    @With(Autentificar.class)
    public static Result salir() {
      session().clear();
      return redirect(routes.Home.index());
    }

    public static Result acceso_denegado() {
      return ok(acceso_denegado.render());
    }

    // GET, formulario de login al sistema
    public static Result login() {
        session().clear();
        Form<Login> formulario = form(Login.class);
        return ok(login.render(formulario));    
    }

    // POST, ingreso a la aplicacion
    public static Result ingresar() {
        Form<Login> formulario = form(Login.class).bindFromRequest();

        // Debe ingresar la el usuario y la clave
        if(formulario.get().login == "" || formulario.get().clave == "") {
            flash("invalid", "Debe ingresar usuario y clave");
            return badRequest(login.render(formulario));
        }

        // Busca el usuario por login y clave
        Usuario usuario = Usuario.find.where().eq("login", formulario.get().login).eq("password", formulario.get().clave).findUnique();
        if(usuario == null) {
            flash("invalid", "Usuario y/o clave incorrecto/s");
            return badRequest(login.render(formulario));
        }

        // Login correcto, se setean las variables de session
        session("login", usuario.login);
        session("user", usuario.name);
        session("tipo", usuario.tipousuario.name);
        return redirect(routes.Home.index());
    }

    @With(Autentificar.class)
    // GET, formulario de cambio de clave
    public static Result cambio_clave() {
        Form<Login> formulario = form(Login.class);
        return ok(cambio_clave.render(formulario));    
    }

    // POST, cambia el password
    public static Result cambiar_clave() {
        Form<Login> formulario = form(Login.class).bindFromRequest();

        // Debe ingresar los 3 datos
        if(formulario.get().clave_act == "" || formulario.get().clave_new == "" || formulario.get().clave_rep == "") {
            flash("invalid", "Debe ingresar las claves");
            return badRequest(cambio_clave.render(formulario));
        }

        // La clave nueva y repeteida deben ser iguales
        if(!formulario.get().clave_new.equals(formulario.get().clave_rep)) {
            flash("invalid", "La clave nueva debe ser igual a la repetida");
            return badRequest(cambio_clave.render(formulario));
        }

        // Busca el usuario logueado y compara la clave con la actual
        Usuario usuario = Usuario.find.where().eq("login", session("login")).eq("password", formulario.get().clave_act).findUnique();
        if(usuario == null) {
            flash("invalid", "La clave actual no es la del usuario logueado");
            return badRequest(cambio_clave.render(formulario));
        }

        // Todo correcto, se cambia la clave
        usuario.password = formulario.get().clave_new;
        usuario.save();
        return redirect(routes.Home.index());
    }

    public static Result irA(String controller, String action) {
        String url = "/";
        if(controller != "/") { url = "/" + controller + "/" + action; }
        return redirect(url);
    }

}
