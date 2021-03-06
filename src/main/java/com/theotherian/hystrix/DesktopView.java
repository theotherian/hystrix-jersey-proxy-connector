package com.theotherian.hystrix;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.jknack.handlebars.Template;

@Path("desktop")
public class DesktopView {
  
  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response get() throws IOException, InterruptedException, ExecutionException {
    Template template = HandlebarsManager.get().compile("home");
    return Response.ok(template.apply(new Args("Ian", "Denny", "Chris"))).build();
  }

}
