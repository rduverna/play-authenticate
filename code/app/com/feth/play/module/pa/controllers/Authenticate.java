package com.feth.play.module.pa.controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.Request;
import play.mvc.Http.Response;

import com.feth.play.module.pa.PlayAuthenticate;

public class Authenticate extends Controller {

	
	private static final String PAYLOAD_KEY = "p";

	public static Result authenticate(final String provider) {
		response().setHeader(Response.CACHE_CONTROL, "no-cache, no-store, must-revalidate");  // HTTP 1.1
		response().setHeader(Response.PRAGMA, "no-cache");  // HTTP 1.0.
		response().setHeader(Response.EXPIRES, "0");  // Proxies.
		
		final String payload = getQueryString(request(), PAYLOAD_KEY);
		return PlayAuthenticate.handleAuthentication(provider, ctx(), payload);
	}
	
	public static Result logout() {
		response().setHeader(Response.CACHE_CONTROL, "no-cache, no-store, must-revalidate");  // HTTP 1.1
		response().setHeader(Response.PRAGMA, "no-cache");  // HTTP 1.0.
		response().setHeader(Response.EXPIRES, "0");  // Proxies.
		
		return PlayAuthenticate.logout(session());
	}

	// TODO remove on Play 2.1
	public static String getQueryString(final Request r, final Object key) {
		final String[] m = r.queryString().get(key);
		if(m != null && m.length > 0) {
			return m[0];
		}
		return null;
	}
}
