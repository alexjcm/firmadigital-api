/*
 * Firma Digital: API
 * Copyright (C) 2017 Secretaría Nacional de la Administración Pública
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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Servicio REST para utilizar desde la aplicación del lado del cliente.
 * 
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 */
@Path("/firmadigital")
public class ServicioFirmaDigital {

    private ClienteRestServicioDocumento clienteServicioFirmaDigital = new ClienteRestServicioDocumento();

    /**
     * Obterner un documento mediante una invocación REST a
     * servicio.firmadigital.gob.ec
     * 
     * @param token
     * @return
     */
    @GET
    @Path("{token}")
    @Produces(MediaType.TEXT_PLAIN)
    public String obtenerDocumento(@PathParam("token") String token) {
        return clienteServicioFirmaDigital.obtenerDocumento(token);
    }

    /**
     * Actualizar un documento mediante una invocación REST a
     * servicio.firmadigital.gob.ec
     * 
     * @param token
     * @param documento
     * @return
     */
    @PUT
    @Path("{token}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String actualizarDocumento(@PathParam("token") String token, String documento) {
        return clienteServicioFirmaDigital.guardarDocumento(token, documento);
    }
}