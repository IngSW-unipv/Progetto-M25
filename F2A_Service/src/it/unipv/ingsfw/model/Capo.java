package it.unipv.ingsfw.model;

import java.util.Date;
import it.unipv.ingsfw.model.negozio.Negozio;

public class Capo {
	private String IDC;
	private String statoCapo;
	private TipoLavaggio tipoLavaggio;
	private Date dataRitiro;
	private Date dataUltimaConsegna;
	private Negozio negozioConsegna;
	private double prezzoScontato;

	/**
	 * @param iDC
	 * @param statoCapo
	 * @param tipoLavaggio
	 * @param dataRitiro
	 * @param dataUltimaConsegna
	 * @param negozioConsegna
	 * @param prezzoScontato
	 */
	public Capo(String iDC, String statoCapo, TipoLavaggio tipoLavaggio, Date dataRitiro, Date dataUltimaConsegna,
			Negozio negozioConsegna, double prezzoScontato) {
		super();
		IDC = iDC;
		this.statoCapo = statoCapo;
		this.tipoLavaggio = tipoLavaggio;
		this.dataRitiro = dataRitiro;
		this.dataUltimaConsegna = dataUltimaConsegna;
		this.negozioConsegna = negozioConsegna;
		this.prezzoScontato = prezzoScontato;
	}

	public String getIDC() {
		return IDC;
	}

	public void setIDC(String iDC) {
		IDC = iDC;
	}

	public String getStatoCapo() {
		return statoCapo;
	}

	public void setStatoCapo(String statoCapo) {
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

	@Override
	public String toString() {
		return "Capo " + IDC + "\nStato: " + statoCapo + "\nTipoLavaggio: " + tipoLavaggio + "\nDataRitiro: "
				+ dataRitiro + "\nDataUltimaConsegna: " + dataUltimaConsegna + "\nNegozioConsegna: "
				+ negozioConsegna.getIdTappa() + "\nPrezzoScontato: " + prezzoScontato;
	}

}
