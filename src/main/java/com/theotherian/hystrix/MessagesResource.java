package com.theotherian.hystrix;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

@Path("latestmessages")
public class MessagesResource implements Helper<String> {
  
  @GET
  @Path("{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Message> get(@PathParam("name") String name) {
    return getMessages(name);
  }
  
  @PUT
  @Path("{name}")
  public Response put(@PathParam("name") String name, List<Message> messages) {
    return Response.ok().build();
  }
  
  private List<Message> getMessages(String name) {
    return MessagesDatastore.getMessagesByName(name);
  }

  @Override
  public CharSequence apply(String context, Options options) throws IOException {
    return options.fn(getMessages(context));
  }

}
