/*
 * Firma Digital: API
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

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configuración de la aplicacion REST. Permite especificar la ruta que será 
 * la URI base para todos los URI de recursos especificados como clases o 
 * métodos con anotaciones Path. 
 *
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 */
@ApplicationPath("")
public class JAXRSConfiguration extends Application {
}
