/*
 * Firma Digital: API
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

import static ec.gob.firmadigital.api.BaseConstants.BASE_URL;
import static ec.gob.firmadigital.api.BaseConstants.SERVICE_CONTEXT;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Christian Espinosa <christian.espinosa@mintel.gob.ec>, Misael
 * Fernández
 */
@Path("/appfirmardocumento")
public class ServicioAppFirmarDocumento {

    // Servicio REST interno
    private static final String REST_SERVICE_URL = BASE_URL + SERVICE_CONTEXT + "/appfirmardocumento";

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String validarEndpointPost(@FormParam("pkcs12") String pkcs12, @FormParam("password") String password,
        @FormParam("documento") String documento, @FormParam("json") String json, @FormParam("base64") String base64) {
        try {
            return firmarDocumento(pkcs12, password, documento, json, base64);
        } catch (NotFoundException e) {
            return "No se encuentra el servidor de búsqueda";
        }
    }

    private String firmarDocumento(String pkcs12, String password, String documento, String json, String base64) throws NotFoundException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(REST_SERVICE_URL);
        Invocation.Builder builder = target.request();
        Form form = new Form();
        form.param("pkcs12", pkcs12);
        form.param("password", password);
        form.param("documento", documento);
        form.param("json", json);
        form.param("base64", base64);
        Invocation invocation = builder.buildPost(Entity.form(form));
        return invocation.invoke(String.class);
    }
}
