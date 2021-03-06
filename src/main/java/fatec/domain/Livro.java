/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Josias Wattrelos
 */
@Entity
@Table(name = "livro")
public class Livro extends EntidadeDominio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = true)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "isbn", unique=true)
    private long isbn;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "titulo", unique=true, length = 90)
    private String titulo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ano")
    private int ano;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "dimensao", length = 40)    
    private String dimensao;    
    
    @Column(name = "custo")
    private BigDecimal custo;    
    
    @Column(name = "ativo")
    private boolean ativo;
    
    @Column(name = "imagem", length = 30)
    private String imagem;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "edicao", length = 20)
    private String edicao;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "paginas")
    private int paginas;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "sinopse")
    private String sinopse;
    
    @Basic(optional = false)
    @Column(name = "peso")
    private int peso;
    
    @Column(name = "data_cadastro",
    		updatable=false,
    		insertable = false,
    		columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro; 
    
    // Coleções----------------------------------------------------------- 
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "editora_id", referencedColumnName = "id")    
    private Editora editora;
           
    @ManyToMany(cascade=CascadeType.REFRESH)
    @JoinTable(name="livro_has_categoria",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")    
    )    
    
    private List<Categoria> categorias = new ArrayList<>();

    @ManyToMany(cascade=CascadeType.REFRESH)
    @JoinTable(name="livro_has_subcategoria",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "subcategoria_id")    
    )
    private List<Subcategoria> subcategorias = new ArrayList<>(); 
    
    @ManyToMany(cascade=CascadeType.REFRESH)
    @JoinTable(name="livro_has_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")    
    )
    private List<Autor> autores = new ArrayList<>();
        
	// Construtores -----------------------------------------------------------
    public Livro() {
    }

    public Livro(Integer id) {
        this.id = id;
    }
    
    public Livro(Integer id, long isbn, String titulo, int ano, String dimensao, BigDecimal custo, boolean ativo, String imagem, String edicao, int paginas, String sinopse, int peso, Date dataCadastro) {
    	this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.ano = ano;
        this.dimensao = dimensao;
        this.custo = custo;
        this.ativo = ativo;
        this.imagem = imagem;
        this.edicao = edicao;
        this.paginas = paginas;
        this.sinopse = sinopse;
        this.peso = peso;
        this.dataCadastro = dataCadastro;
    }
    
 // Setters e Getters -----------------------------------------------------------
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

	public boolean getAtivo() {
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

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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

	// Hash codes -----------------------------------------------------------
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + (int) (this.isbn ^ (this.isbn >>> 32));
        return hash;
    }

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Livro other = (Livro) obj;
        if (this.isbn != other.isbn) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "Livro{" + "id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", ano=" + ano + ", dimensao=" + dimensao + ", custo=" + custo + ", quantidade=" + ", ativo=" + ativo + ", imagem=" + imagem + ", edicao=" + edicao + ", paginas=" + paginas + ", sinopse=" + sinopse + ", peso=" + peso + ", dataCadastro=" + dataCadastro ;
    }

    
}
