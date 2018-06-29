/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ws [/validar]<br>
 * USAGE:
 * <pre>
 *        validar client = new validar();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author usuario
 */
public class validar {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/OrganismoRegulador/app";

    public validar() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("validar");
    }

    public String getVal(String dni) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (dni != null) {
            resource = resource.queryParam("dni", dni);
        }
        return resource.request().get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
