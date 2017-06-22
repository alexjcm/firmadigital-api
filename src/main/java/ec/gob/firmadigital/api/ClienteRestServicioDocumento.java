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

import java.util.Map;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Cliente REST para https://servicio.firmadigital.gob.ec/servicio/documentos
 * 
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 */
public class ClienteRestServicioDocumento {

    private static String REST_SERVICE_URL = "https://ws.firmadigital.gob.ec/servicio/documentos";

    public String obtenerDocumento(String token) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(REST_SERVICE_URL);
        return target.path("{token}").resolveTemplate("token", token).request(MediaType.TEXT_PLAIN).get(String.class);
    }

    public String obtenerDocumentos(String token) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(REST_SERVICE_URL);
        return target.path("{token}").resolveTemplate("token", token).request(MediaType.APPLICATION_JSON)
                .get(String.class);
    }

    public String actualizarDocumento(String token, String archivo) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(REST_SERVICE_URL);
        return target.path("{token}").resolveTemplate("token", token).request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(archivo, MediaType.TEXT_PLAIN), String.class);
    }

    public String actualizarDocumentos(String token, JsonObject json) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(REST_SERVICE_URL);
        return target.path("{token}").resolveTemplate("token", token).request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(json, MediaType.APPLICATION_JSON), String.class);
    }
}