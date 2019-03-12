package fatec.layer20.aplications.DataTransferObject;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import fatec.domain.Autor;
import fatec.domain.Categoria;
import fatec.domain.Editora;
import fatec.domain.Livro;
import fatec.domain.Subcategoria;



public class LivroDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private long isbn;	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=150, message="O tamanho deve ser entre 5 e 150 caracteres")
	private String titulo;
	private int ano;
	private String dimensao;
	private BigDecimal custo;
	private int quantidade;
	private boolean ativo;
	private String imagem;
	private String edicao;
	private int paginas;
	private String sinopse;
	private int peso;
	private Date dataCadastro; 
	
	private Editora editora;
	private List<Categoria> categorias = new ArrayList<>();
	private List<Subcategoria> subcategorias = new ArrayList<>();
	private List<Autor> autores = new ArrayList<>();

	public LivroDTO() {
	} 
	
	public LivroDTO(Livro obj) {
		id = obj.getId();
		isbn = obj.getIsbn();
		titulo = obj.getTitulo();
		ano = obj.getAno();
		dimensao = obj.getDimensao();
		custo = obj.getCusto();
		quantidade = obj.getQuantidade();
		ativo = obj.getAtivo();
		imagem = obj.getImagem();
		edicao = obj.getEdicao();
		paginas = obj.getPaginas();
		sinopse = obj.getSinopse();
		peso = obj.getPeso();
		dataCadastro = obj.getDataCadastro();
		editora = obj.getEditora();
		categorias = obj.getCategorias();
		subcategorias = obj.getSubcategorias();
		autores = obj.getAutor();
	}

	public List<Subcategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getDimensao() {
		return dimensao;
	}

	public void setDimensao(String dimensao) {
		this.dimensao = dimensao;
	}

	public BigDecimal getCusto() {
		return custo;
	}

	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	
}
