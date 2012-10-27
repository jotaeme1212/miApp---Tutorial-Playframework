package controllers;

import play.*;
import play.mvc.*;

public class Autentificar extends Action.Simple {

	public Result call(Http.Context ctx) throws Throwable {

		if(ctx.session().get("user") == null) {
			return redirect(routes.Home.acceso_denegado());
		}
		return delegate.call(ctx);

	}

}