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
	private Negozio negozioDeposito;
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
			Negozio negozioConsegna, Negozio negozioDeposito, double prezzoScontato, Cliente cliente) {
		super();
		this.idCapo = idCapo;
		this.statoCapo = statoCapo;
		this.tipoLavaggio = tipoLavaggio;
		this.dataRitiro = dataRitiro;
		this.dataUltimaConsegna = dataUltimaConsegna;
		this.negozioDeposito = negozioDeposito;
		this.negozioConsegna = negozioConsegna;
		this.prezzoScontato = prezzoScontato;
		this.cliente = cliente;
	}
	

	public Capo(StatoCapo statoCapo, TipoLavaggio tipoLavaggio) {
		super();
		this.idCapo = null;
		this.statoCapo = statoCapo;
		this.tipoLavaggio = tipoLavaggio;
		this.dataRitiro = null;
		this.dataUltimaConsegna = null;
		this.negozioDeposito = null;
		this.negozioConsegna = null;
		this.prezzoScontato = 0.0;
		this.cliente = null;
	}




	public Capo(String idCapo, StatoCapo stato, TipoLavaggio sol1, Date ritiro, Date consegna, Negozio sonr, Negozio sond,
			Double costo, Cliente cliente) {
		super();
		this.idCapo = idCapo;
		this.statoCapo = stato;
		this.tipoLavaggio = sol1;
		this.dataRitiro = null;
		this.dataUltimaConsegna = consegna;
		this.negozioDeposito = sond;
		this.negozioConsegna = sonr;
		this.prezzoScontato = costo;
		this.cliente = cliente;
		
	}
	public Capo(String idCapo, StatoCapo stato) {
		super();
		this.idCapo = idCapo;
		this.statoCapo = stato;
	}

	public Capo() {
		super();
		this.idCapo = null;
		this.statoCapo = null;
		this.tipoLavaggio = null;
		this.dataRitiro = null;
		this.dataUltimaConsegna = null;
		this.negozioDeposito = null;
		this.negozioConsegna = null;
		this.prezzoScontato = 0;
		this.cliente = null;
	}

	public String getIdCapo() {
		return idCapo;
	}

	public void setIdCapo(String idCapo) {
		this.idCapo = idCapo;
	}
	
	public Capo(String idCapo) {
		super();
		this.idCapo = idCapo;
	}
	
	public Capo(TipoLavaggio sol) {
		super();
		this.tipoLavaggio = sol;
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

	public Negozio getNegozioDeposito() {
		return negozioDeposito;
	}

	public void setNegozioDeposito(Negozio negozioDeposito) {
		this.negozioDeposito = negozioDeposito;
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
				+ dataRitiro + "\nDataUltimaConsegna: " + dataUltimaConsegna + "\nNegozioDeposito: "
				+ negozioDeposito.getIdTappa() + "\nNegozioConsegna: " + negozioConsegna.getIdTappa()
				+ "\nPrezzoScontato: " + prezzoScontato + "\nIdCliente: " + cliente.getIdCliente();
	}
	
	public String toStringCor() {
		return "ID Capo: " + idCapo + "\nTipoLavaggio: " + tipoLavaggio + "\nId NegozioRitiro: "
				+ negozioDeposito.getIdTappa() + "\nIdCliente: " + cliente.getIdCliente();
	}
	

}
