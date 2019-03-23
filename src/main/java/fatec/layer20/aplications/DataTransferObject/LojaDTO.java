package fatec.layer20.aplications.DataTransferObject;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import fatec.domain.Cliente;
import fatec.domain.LojaMatriz;


public class LojaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nomeFantasia;	
	private Date dataCadastro;
	// Coleções
	
	private List<Cliente> clientes = new ArrayList<>();	

	public LojaDTO() {
	}


	public LojaDTO(LojaMatriz obj) {		
		id = obj.getId();
		nomeFantasia = obj.getNomeFantasia();
		dataCadastro = obj.getDataCadastro();
		// Coleções
		clientes = obj.getClientes();
	}


	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNomeFantasia() {
		return nomeFantasia;
	}


	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}


	public Date getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	} 
	
}
