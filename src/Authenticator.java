/**
 * PEP Capping 2017 Algozzine's Class
 *
 * Utilizes an LDAP call to check if the application is currently on the server
 *
 * @author Sami Ellougani, Carlie Maxwell
 * @copyright 2017 Marist College
 * @version 0.1.0
 * @since 0.1.0
 */

package javaApplication;

import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import javax.naming.NamingException;


public class Authenticator {

    //The IP in this LdapConnection corresponds to the IP on the network
	public static LdapConnection connection = new LdapNetworkConnection( "10.11.12.27", 389 );
	
    //If the connection works, then a true value is returned, if not a false value is returned
    public static boolean authenticate() throws NamingException, LdapException {
        try {
            connection.bind();
            return true;
        } catch (LdapException e) {
            e.printStackTrace();
            return false;
        }
    }
}