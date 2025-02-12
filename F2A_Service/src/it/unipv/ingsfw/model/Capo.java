package it.unipv.ingsfw.model;

import java.util.Date;
import it.unipv.ingsfw.model.negozio.Negozio;
import it.unipv.ingsfw.model.users.Cliente;

public class Capo {
	private String idCapo;
	private StatoCapo statoCapo;
	private TipoLavaggio tipoLavaggio;
	private Date dataRitiro;
	private Date dataUltimaConsegna;
	private Negozio negozioConsegna;
	private double prezzoScontato;
	private Cliente cliente;

	/**
	 * @param idCapo
	 * @param statoCapo
	 * @param tipoLavaggio
	 * @param dataRitiro
	 * @param dataUltimaConsegna
	 * @param negozioConsegna
	 * @param prezzoScontato
	 */
	public Capo(String idCapo, StatoCapo statoCapo, TipoLavaggio tipoLavaggio, Date dataRitiro, Date dataUltimaConsegna,
			Negozio negozioConsegna, double prezzoScontato, Cliente cliente) {
		super();
		this.idCapo = idCapo;
		this.statoCapo = statoCapo;
		this.tipoLavaggio = tipoLavaggio;
		this.dataRitiro = dataRitiro;
		this.dataUltimaConsegna = dataUltimaConsegna;
		this.negozioConsegna = negozioConsegna;
		this.prezzoScontato = prezzoScontato;
		this.cliente = cliente;
	}

	public String getIdCapo() {
		return idCapo;
	}

	public void setIdCapo(String idCapo) {
		this.idCapo = idCapo;
	}

	public StatoCapo getStatoCapo() {
		return statoCapo;
	}

	public void setStatoCapo(StatoCapo statoCapo) {
		this.statoCapo = statoCapo;
	}

	public TipoLavaggio getTipoLavaggio() {
		return tipoLavaggio;
	}

	public void setTipoLavaggio(TipoLavaggio tipoLavaggio) {
		this.tipoLavaggio = tipoLavaggio;
	}

	public Date getDataRitiro() {
		return dataRitiro;
	}

	public void setDataRitiro(Date dataRitiro) {
		this.dataRitiro = dataRitiro;
	}

	public Date getDataUltimaConsegna() {
		return dataUltimaConsegna;
	}

	public void setDataUltimaConsegna(Date dataUltimaConsegna) {
		this.dataUltimaConsegna = dataUltimaConsegna;
	}

	public Negozio getNegozioConsegna() {
		return negozioConsegna;
	}

	public void setNegozioConsegna(Negozio negozioConsegna) {
		this.negozioConsegna = negozioConsegna;
	}

	public double getPrezzoScontato() {
		return prezzoScontato;
	}

	public void setPrezzoScontato(double prezzoScontato) {
		this.prezzoScontato = prezzoScontato;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	@Override
	public String toString() {
		return "Capo " + idCapo + "\nStato: " + statoCapo + "\nTipoLavaggio: " + tipoLavaggio + "\nDataRitiro: "
				+ dataRitiro + "\nDataUltimaConsegna: " + dataUltimaConsegna + "\nNegozioConsegna: "
				+ negozioConsegna.getIdTappa() + "\nPrezzoScontato: " + prezzoScontato + "\nIdCliente: " + cliente.getIdCliente();
	}

}
