package com.theotherian.hystrix;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ApplicationHandler;

import com.theotherian.hystrix.client.ServerSideConnector;

@Provider
public class ServerConnectorProvider implements Feature {
  
  private ApplicationHandler applicationHandler;
  
  public ServerConnectorProvider(@Context ApplicationHandler applicationHandler) {
    this.applicationHandler = applicationHandler;
  }
  
  @Override
  public boolean configure(FeatureContext context) {
    ServerConnectorFactory.init(applicationHandler);
    return true;
  }
  
  public static final class ServerConnectorFactory {
    
    private static ApplicationHandler applicationHandler;
    
    private static void init(ApplicationHandler applicationHandler) {
      ServerConnectorFactory.applicationHandler = applicationHandler;
    }
    
    private ServerConnectorFactory() {}
    
    public static ServerSideConnector build() {
      return new ServerSideConnector(applicationHandler);
    }
    
  }

}
