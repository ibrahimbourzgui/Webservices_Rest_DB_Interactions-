package metier;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import dao.Contact;

public class ContactServiceImp implements ContactService{
	private static Map<Integer,Contact> Contacts = new HashMap<Integer,Contact>();
	@Override
	public boolean addContact(Contact c) {
	    if(Contacts.get(c.getId()) != null) return false;
	    Contacts.put(c.getId(), c);
	    return true;
	}
	@Override
	public boolean deleteContact(int id) {
	    if(Contacts.get(id) == null) return false;
	    Contacts.remove(id);
	    return true;
	}
	@Override
	public Contact getContact(int id) {
	    return Contacts.get(id);
	}
	@Override
	public Contact[] getAllContact() {
	    Set<Integer> ids = Contacts.keySet();
	    Contact[] c = new Contact[ids.size()];
	    int i=0;
	    for(Integer id : ids){
	    c[i] = Contacts.get(id);
	    i++;
	    }
	    return c;
	}
}
