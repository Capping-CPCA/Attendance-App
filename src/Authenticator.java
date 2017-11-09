package javaApplication;

import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import javax.naming.NamingException;


public class Authenticator {

	public static LdapConnection connection = new LdapNetworkConnection( "10.11.12.27", 389 );
	
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