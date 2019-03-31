package fatec.layer20.aplications.DataTransferObject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import fatec.domain.Usuario;


public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	@Email(message="Email inválido")
	private String email;
	
	@Column(name = "data_cadastro",
    		updatable=false,
    		insertable = false,
    		columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=8, max=80, message="A senha deve ter, pelo menos, 8 caracteres.")
	private String senha;
	
	private Integer perfil;

	public UsuarioDTO() {
	} 

	public UsuarioDTO(Usuario usuario) {
		id = usuario.getId();
		email = usuario.getEmail();
		dataCadastro = usuario.getDataCadastro();
		senha = usuario.getSenha();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}
