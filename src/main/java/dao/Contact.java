package dao;

public class Contact {
	private int id;
	private String nom;
	private String tel;
	public Contact() {};
	public Contact(int id, String nom, String tel) {
		super();
		this.id = id;
		this.nom = nom;
		this.tel = tel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", nom=" + nom + ", tel=" + tel + "]";
	}
	
}
