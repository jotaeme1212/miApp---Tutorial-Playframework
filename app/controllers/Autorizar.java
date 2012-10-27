package controllers;

import play.*;
import play.mvc.*;
import models.*;

public class Autorizar extends Action.Simple {

	public Result call(Http.Context ctx) throws Throwable {

		Logger.info("Calling action for " + ctx);
		Logger.info("-------------------" + ctx.request().path());

		// Si no se esta logueado, no hay permiso
		if(ctx.session().get("user") == null) {
			return redirect(routes.Home.acceso_denegado());
		}

		// Se esta logueado, se recupera el tipo de usuario
		long tipoId = 0;
		String login = ctx.session().get("login");
		Usuario usuario = Usuario.find.where().eq("login", login).findUnique();
		if(usuario != null) { tipoId = usuario.tipousuario.id; }

		// Con el path, se obtiene el controlador y la accion, luego se busca en el menu esa opcion
		long menuId = 0;
		String[] path = ctx.request().path().split("/");
		String controlador = path[1];
		String accion = path[2];
		// Menu menu = Menu.find.where().eq("controlador", controlador).eq("accion", accion).findUnique();
		Menu menu = Menu.find.where().eq("controlador", controlador).findUnique();
		if(menu != null) { menuId = menu.id; }
		Logger.info("-------------------" + menuId);

		// Con el Tipo de Usuario y el Menu, se busca la combinacion en la tabla de acceso
		MenuTipousuario mtu = MenuTipousuario.find.where().eq("menu.id", menuId).eq("tipousuario.id", tipoId).findUnique();
		if(mtu == null) {
			return redirect(routes.Home.acceso_denegado());
		}

		return delegate.call(ctx);

	}

}