package service;

import java.sql.SQLException;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.soap.SOAPException;
import dao.Contact;
import dao.ContactDao;
import dao.DBConfig;



@Path("/lesContacts")
public class ServiceContact {
	private ContactDao dao = null;
	public ServiceContact () throws ClassNotFoundException {
	    dao = new ContactDao(new DBConfig());
	    try {
	    System.out.println(dao.getAllContact());
	    } catch (SQLException e) {
	    // TODO Auto‐generated catch block
	    e.printStackTrace();
	    }
	}
	@GET @Path("getContact/{id}") @Produces (MediaType.APPLICATION_JSON)
	public Contact getContactById(@PathParam ("id") int id) throws   SOAPException,
	Exception{
	    LOG.info("Doc.readContact");
	    Contact c = dao.getContact((int)id);
	    if (c == null) {
	    throw new SOAPException ("Aucun contact ....");
	    }
	    return c;
	}
	@GET @Path("getContacts") @Produces ({MediaType.APPLICATION_XML,
	MediaType.APPLICATION_JSON})
	public List<Contact> getAllContact()throws
	SOAPException, Exception {
	    LOG.info("Doc.readContact");
	    List<Contact> lstContacts = dao.getAllContact();
	    if (lstContacts== null) {
	    throw new SOAPException ("Aucun contact ....");
	    }
	    return lstContacts;
	}
}
