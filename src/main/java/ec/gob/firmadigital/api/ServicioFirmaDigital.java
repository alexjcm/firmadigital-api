/*
 * Firma Digital: API
 * Copyright 2017 Secretaría Nacional de la Administración Pública
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ec.gob.firmadigital.api;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Servicio REST para utilizar desde la aplicación del lado del cliente.
 *
 * Es a su vez un cliente REST para invocar servicios provistos en
 * ws.firmadigital.gob.ec
 *
 * Este mecanismo permite invocar los servicios internos desde un cliente
 * externo.
 *
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 */
@Path("/firmadigital")
public class ServicioFirmaDigital {

    // Servicio REST interno
    private static final String REST_SERVICE_URL = "https://ws.firmadigital.gob.ec/servicio/documentos";

    /**
     * Obterner un documento mediante una invocación REST a ws.firmadigital.gob.ec
     *
     * @param token
     * @return
     */
    @GET
    @Path("{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDocumentos(@PathParam("token") String token) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(REST_SERVICE_URL).path("{token}").resolveTemplate("token", token);
        Builder builder = target.request(MediaType.APPLICATION_JSON);
        Invocation invocation = builder.buildGet();

        try {
            String json = invocation.invoke(String.class);
            return Response.ok(json).header("Content-Length", json.length()).build();
        } catch (BadRequestException e) {
            String mensaje = e.getResponse().readEntity(String.class);
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(mensaje).build();
        } catch (WebApplicationException e) {
            String mensaje = e.getResponse().readEntity(String.class);
            return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_PLAIN).entity(
                    "Error al invocar servicio de obtencion de documentos en ws.firmadigital.gob.ec: " + mensaje)
                    .build();
        }
    }

    /**
     * Actualizar un documento mediante una invocación REST a ws.firmadigital.gob.ec
     *
     * @param token
     * @param json
     * @return
     */
    @PUT
    @Path("{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarDocumentos(@PathParam("token") String token, String json) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(REST_SERVICE_URL).path("{token}").resolveTemplate("token", token);
        Builder builder = target.request();
        Invocation invocation = builder.buildPut(Entity.json(json));

        try {
            String jsonResponse = invocation.invoke(String.class);
            return Response.ok(jsonResponse).header("Content-Length", jsonResponse.length()).build();
        } catch (BadRequestException e) {
            String mensaje = e.getResponse().readEntity(String.class);
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(mensaje).build();
        } catch (WebApplicationException e) {
            String mensaje = e.getResponse().readEntity(String.class);
            return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_PLAIN).entity(
                    "Error al invocar servicio de obtencion de documentos en ws.firmadigital.gob.ec: " + mensaje)
                    .build();
        }
    }
}