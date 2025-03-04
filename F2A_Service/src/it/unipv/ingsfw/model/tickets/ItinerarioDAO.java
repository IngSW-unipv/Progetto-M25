package it.unipv.ingsfw.model.tickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import it.unipv.ingsfw.jdbc.DBConnection;
import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.negozio.StatoTappa;
import it.unipv.ingsfw.model.negozio.Tappa;

public class ItinerarioDAO implements IItinerarioDAO {

	Connection conn;
	private static ItinerarioDAO instance = null;

	public ItinerarioDAO() {
		super();
	}

	public static ItinerarioDAO getInstance() {
		if (instance == null) {
			instance = new ItinerarioDAO();
			System.out.println("Create new instance");
		} else
			System.out.println("Instance already available");
		return instance;
	}

	@Override
	public ArrayList<Itinerario> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	// AGGIUNTO IN DATA 19/02
	@Override
	public Itinerario selectById(Itinerario i) {
		String IdIt = i.getIdItinerario();
		Itinerario itinerario = new Itinerario();
		itinerario.setListaTappeNegozi(new ArrayList<Tappa>());
		Tappa tappa;
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;

		try {
			// RISOLTO ERRORE java.lang.NullPointerException: Name is null PONENDO LA
			// CONDIZIONE IS NOT NULL SULLO STATO
			// A DB C'ERA UN VALORE CHE NULLO CHE NON VEDEVO
			// ATTENZIONE: NEI PROSSIMI COMMIT GESTIRE LA PRESENZA DI VALORI NULLI CON
			// TRY-CATCH 'NULLPOINTEREXCPTION

			// PROBABILMENTE PER ITINERARIO BASTA MEMORIZZARE SOLO L'ID (CANCELLARE QUESTO
			// METODO
			// POTREBBBE SERVIRE UNA QUERY CHE MI RESTITUISCA UN'ARRAY DI TAPPE PROPRIO
			// BASANDOMI
			// SU UN CERTO ITINERARIO
			String query = "SELECT itinerari.IdItinerario, negozi.IDN, negozi.stato, negozi.indirizzo FROM itinerari left join negozi on itinerari.IdItinerario=negozi.IdItinerario WHERE itinerari.IdItinerario=? and negozi.stato is not null;";
			st1 = conn.prepareStatement(query);
			st1.setString(1, IdIt);
			rs1 = st1.executeQuery();
			//CONTROLLO AGGIUNTO IN FASE DI TEST
			//if (rs1.next()) {
				while (rs1.next()) {
					itinerario.setIdItinerario(rs1.getString(1));
					tappa = new Negozio(rs1.getString(2), StatoTappa.valueOf(rs1.getString(3)), rs1.getString(4));
					itinerario.getListaTappeNegozi().add(tappa);
				}
				itinerario.setListaTappeNonAttraversate(itinerario.getListaTappeNegozi());
			//}else {
				//return null;
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return itinerario;
	}

	@Override
	public ArrayList<Itinerario> selectByStatoTappa(Itinerario fornInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertItinerario(Itinerario i) {
		// TODO Auto-generated method stub
		return false;
	}

}
