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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
<<<<<<< HEAD
import java.util.logging.Logger;

/**
 * Esta clase permite probar el servicio REST que verifica un certificado digital mediante el CRL
 * almacenado en la base de datos del servicio.
=======
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase permite probar el servicio REST que verifica un certificado
 * digital mediante el CRL almacenado en la base de datos del servicio.
>>>>>>> gitlab/master
 *
 * @author Ricardo Arguello
 */
public class PruebaServicioCertificado {

<<<<<<< HEAD
    private static final String CERTIFICADO_URL = "http://localhost:7776/api/certificado/revocado";

    private static final Logger logger = Logger.getLogger(
        PruebaServicioCertificado.class.getName());
=======
    private static final String CERTIFICADO_URL = "https://api.firmadigital.gob.ec/api/certificado/revocado";
//    private static final String CERTIFICADO_URL = "https://impapi.firmadigital.gob.ec:8080/api/certificado/revocado";
//    private static final String CERTIFICADO_URL = "http://localhost:8080/api/certificado/revocado";

    private static final Logger LOGGER = Logger.getLogger(PruebaServicioCertificado.class.getName());
>>>>>>> gitlab/master

    public boolean verificarCrlServidor(int serial) throws IOException {
        URL url = new URL(CERTIFICADO_URL + "/" + serial);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        int responseCode = urlConnection.getResponseCode();

        if (responseCode != HttpURLConnection.HTTP_OK) {
<<<<<<< HEAD
            logger.severe(CERTIFICADO_URL + " Response Code: " + responseCode);
=======
            LOGGER.log(Level.SEVERE,CERTIFICADO_URL + " Response Code: {0}", responseCode);
>>>>>>> gitlab/master
            return false;
        }

        try (InputStream is = urlConnection.getInputStream()) {
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader in = new BufferedReader(reader);
<<<<<<< HEAD
            return Boolean.valueOf(in.readLine());
=======
            return Boolean.parseBoolean(in.readLine());
>>>>>>> gitlab/master
        }
    }

    public static void main(String[] args) throws Exception {
        PruebaServicioCertificado main = new PruebaServicioCertificado();
        int serial;
        boolean valido;

        // Este certificado si es valido
        serial = 1312818414;
        valido = main.verificarCrlServidor(serial);
<<<<<<< HEAD
        logger.info("certificado " + serial + " revocado? " + valido);

        serial = 1234567;
        valido = main.verificarCrlServidor(serial);
        logger.info("certificado " + serial + " revocado? " + valido);
=======
        LOGGER.log(Level.INFO, "certificado {0} revocado? {1}", new Object[]{serial, valido});

        serial = 1234567;
        valido = main.verificarCrlServidor(serial);
        LOGGER.log(Level.INFO, "certificado {0} revocado? {1}", new Object[]{serial, valido});
>>>>>>> gitlab/master
    }
}
