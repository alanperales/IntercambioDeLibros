package pe.sil.web.entities;

import pe.sil.web.common.SilEntidad;

public class ParametricaDet extends SilEntidad{
	private static final long serialVersionUID = 1L;

	private Integer PD_CodigoParametroDet;
	private Integer PR_CodigoParametroCab;
	private String PD_NombreElemento;
	private String PD_ValorAtrbuto1;
	private String PD_ValorAtrbuto2;
	private String PD_ValorAtrbuto3;
	private String PD_ValorAtrbuto4;
	private Integer PD_EstadoElemento;
	
	 public ParametricaDet(){}

	public Integer getPD_CodigoParametroDet() {
		return PD_CodigoParametroDet;
	}

	public void setPD_CodigoParametroDet(Integer pD_CodigoParametroDet) {
		PD_CodigoParametroDet = pD_CodigoParametroDet;
	}

	public Integer getPR_CodigoParametroCab() {
		return PR_CodigoParametroCab;
	}

	public void setPR_CodigoParametroCab(Integer pR_CodigoParametroCab) {
		PR_CodigoParametroCab = pR_CodigoParametroCab;
	}

	public String getPD_NombreElemento() {
		return PD_NombreElemento;
	}

	public void setPD_NombreElemento(String pD_NombreElemento) {
		PD_NombreElemento = pD_NombreElemento;
	}

	public String getPD_ValorAtrbuto1() {
		return PD_ValorAtrbuto1;
	}

	public void setPD_ValorAtrbuto1(String pD_ValorAtrbuto1) {
		PD_ValorAtrbuto1 = pD_ValorAtrbuto1;
	}

	public String getPD_ValorAtrbuto2() {
		return PD_ValorAtrbuto2;
	}

	public void setPD_ValorAtrbuto2(String pD_ValorAtrbuto2) {
		PD_ValorAtrbuto2 = pD_ValorAtrbuto2;
	}

	public String getPD_ValorAtrbuto3() {
		return PD_ValorAtrbuto3;
	}

	public void setPD_ValorAtrbuto3(String pD_ValorAtrbuto3) {
		PD_ValorAtrbuto3 = pD_ValorAtrbuto3;
	}

	public String getPD_ValorAtrbuto4() {
		return PD_ValorAtrbuto4;
	}

	public void setPD_ValorAtrbuto4(String pD_ValorAtrbuto4) {
		PD_ValorAtrbuto4 = pD_ValorAtrbuto4;
	}

	public Integer getPD_EstadoElemento() {
		return PD_EstadoElemento;
	}

	public void setPD_EstadoElemento(Integer pD_EstadoElemento) {
		PD_EstadoElemento = pD_EstadoElemento;
	}
}
