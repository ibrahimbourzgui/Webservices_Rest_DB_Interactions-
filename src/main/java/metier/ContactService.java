package metier;

import dao.Contact;

public interface ContactService {
	public boolean addContact(Contact c);
	public boolean deleteContact(int id);
	public Contact getContact(int id);
	public Contact[] getAllContact();
}
