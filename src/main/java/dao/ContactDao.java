package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ContactDao {
	static Statement st;
	private DBConfig cfg = null;
	public ContactDao() {
	}
	public ContactDao (DBConfig cfg) {this.cfg = cfg;}
	protected Contact getContact(ResultSet rs) throws SQLException {
	    Contact c = new Contact();
	    c.setId(rs.getInt("id"));
	    c.setNom(rs.getString("nom"));
	    c.setTel(rs.getString("tel"));
	    return c;
	}
	public List<Contact> getAllContact() throws ClassNotFoundException, SQLException {
		List<Contact> contac=new ArrayList<Contact>();
		DbConnection dbConn = DbConnection.getInstance(cfg.getDriverName(),
			     cfg.getSubprot(), cfg.getHost(),
			     cfg.getPort(), cfg.getDb(),
			     cfg.getUid(), cfg.getPsw());
		String sql = "select * from contact";
		ResultSet rs = st.executeQuery(sql);
		try {
			while(rs.next())
			{
				Contact c=new Contact(rs.getInt(1), rs.getString(2), rs.getString(3));
				c.setId(rs.getInt(1)); 
				contac.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contac;
		}
	public int createContact(Contact c) throws SQLException {
	    DbConnection dbConn = DbConnection.getInstance(cfg.getDriverName(),
	     cfg.getSubprot(), cfg.getHost(),
	     cfg.getPort(), cfg.getDb(),
	     cfg.getUid(), cfg.getPsw());
	    String sql = "SELECT MAX(id) FROM CONTACT";
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	    stmt = dbConn.getConn().prepareStatement(sql);
	    stmt.execute();
	    rs = stmt.getResultSet();
	    rs.next();
	    int nextId = rs.getInt(1);
	    stmt.close();
	    rs.close();
	    sql = "INSERT INTO CONTACT (NOM, TEL   ) " +"VALUES (?,?)";
	    stmt = dbConn.getConn().prepareStatement(sql);
	    stmt.setString(1, c.getNom());
	    stmt.setString(2, c.getTel());
	    stmt.execute();
	    return nextId;
	    } finally {
	    if (stmt != null) {
	     stmt.close();
	    }
	    if (rs != null) {
	     rs.close();
	    }
	    dbConn.close();
	    }
	}
	public boolean updateContact(Contact c) throws SQLException {
	    DbConnection dbConn = DbConnection.getInstance(cfg.getDriverName(),
	     cfg.getSubprot(), cfg.getHost(),
	     cfg.getPort(), cfg.getDb(),
	     cfg.getUid(), cfg.getPsw());
	    String sql = "UPDATE CONTACT SET NOM=?, TEL=? WHERE ID=?";
	    PreparedStatement stmt = null;
	    try {
	    stmt = dbConn.getConn().prepareStatement(sql);
	    stmt.setString(1, c.getNom());
	    stmt.setString(2, c.getTel());
	    stmt.setInt(3, c.getId());
	    stmt.execute();
	    return true;
	    } finally {
	    if (stmt != null) {
	     stmt.close();
	    }
	    dbConn.close();
	    }
	    }
	public boolean deleteContact(int id) throws SQLException {
		   
	    DbConnection dbConn = DbConnection.getInstance(cfg.getDriverName(),
	     cfg.getSubprot(), cfg.getHost(),
	     cfg.getPort(), cfg.getDb(),
	     cfg.getUid(), cfg.getPsw());
	    String sql = "DELETE FROM CONTACT WHERE ID=?";
	    PreparedStatement stmt = null;
	    try {
	    stmt = dbConn.getConn().prepareStatement(sql);
	    stmt.setInt(1, id);
	    stmt.execute();
	    return true;
	    } finally {
	    if (stmt != null) {
	     stmt.close();
	    }
	    dbConn.close();
	    }
	}
	public Contact getContact(int id) throws SQLException {
	    DbConnection dbConn = DbConnection.getInstance(cfg.getDriverName(),
	     cfg.getSubprot(), cfg.getHost(),
	     cfg.getPort(), cfg.getDb(),
	     cfg.getUid(), cfg.getPsw());
	    String sql = "SELECT * FROM CONTACT WHERE ID=?";
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	    stmt = dbConn.getConn().prepareStatement(sql);
	    stmt.setInt(1, id);
	    if (stmt.execute()) {
	     rs = stmt.getResultSet();
	     if (rs != null && rs.next()) {
	      return getContact(rs);
	     }
	    }
	    } finally {
	    if (stmt != null) {
	     stmt.close();
	    }
	    if (rs != null) {
	     rs.close();
	    }
	    dbConn.close();
	    }
	    return null;
	}
}
