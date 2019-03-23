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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author Josias Wattrelos
 */
@Entity
@Table(name = "livro")
@NamedQueries({
    @NamedQuery(name = "Livro.findAll", query = "SELECT l FROM Livro l")
    , @NamedQuery(name = "Livro.findById", query = "SELECT l FROM Livro l WHERE l.id = :id")
    , @NamedQuery(name = "Livro.findByIsbn", query = "SELECT l FROM Livro l WHERE l.isbn = :isbn")
    , @NamedQuery(name = "Livro.findByAno", query = "SELECT l FROM Livro l WHERE l.ano = :ano")
    , @NamedQuery(name = "Livro.findByDimensao", query = "SELECT l FROM Livro l WHERE l.dimensao = :dimensao")
    , @NamedQuery(name = "Livro.findByCusto", query = "SELECT l FROM Livro l WHERE l.custo = :custo")
    , @NamedQuery(name = "Livro.findByQuantidade", query = "SELECT l FROM Livro l WHERE l.quantidade = :quantidade")
    , @NamedQuery(name = "Livro.findByAtivo", query = "SELECT l FROM Livro l WHERE l.ativo = :ativo")
    , @NamedQuery(name = "Livro.findByImagem", query = "SELECT l FROM Livro l WHERE l.imagem = :imagem")
    , @NamedQuery(name = "Livro.findByEdicao", query = "SELECT l FROM Livro l WHERE l.edicao = :edicao")
    , @NamedQuery(name = "Livro.findByPaginas", query = "SELECT l FROM Livro l WHERE l.paginas = :paginas")
    , @NamedQuery(name = "Livro.findByPeso", query = "SELECT l FROM Livro l WHERE l.peso = :peso")
    , @NamedQuery(name = "Livro.findByDataCadastro", query = "SELECT l FROM Livro l WHERE l.dataCadastro = :dataCadastro")})
public class Livro implements Serializable {

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
    @Size(min = 1, max = 150)
    @Column(name = "titulo", unique=true)
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ano")
    private int ano;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "dimensao")
    private String dimensao;
    // @Max(value=2000)  @Min(value=0)//
    @Column(name = "custo")
    private BigDecimal custo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private int quantidade;
    @Column(name = "ativo")
    private boolean ativo;
    @Size(max = 100)
    @Column(name = "imagem")
    private String imagem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "edicao")
    private String edicao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paginas")
    private int paginas;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
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
    
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "id_editora", referencedColumnName = "id")    
    private Editora editora;

    
    @ManyToMany(cascade=CascadeType.REFRESH)
    @JoinTable(name="livro_has_categoria",
            joinColumns = @JoinColumn(name = "id_livro"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")    
    )    
    @JsonManagedReference
    private List<Categoria> categorias = new ArrayList<>();

    @ManyToMany(cascade=CascadeType.REFRESH)
    @JoinTable(name="livro_has_subcategoria",
            joinColumns = @JoinColumn(name = "id_livro"),
            inverseJoinColumns = @JoinColumn(name = "id_subcategoria")    
    )
    @JsonManagedReference
    private List<Subcategoria> subcategorias = new ArrayList<>(); 
    
    @ManyToMany(cascade=CascadeType.REFRESH)
    @JoinTable(name="livro_has_autor",
            joinColumns = @JoinColumn(name = "id_livro"),
            inverseJoinColumns = @JoinColumn(name = "id_autor")    
    )
    @JsonManagedReference
    private List<Autor> autores = new ArrayList<>();

    public Livro() {
    }

    public Livro(Integer id) {
        this.id = id;
    }
    
    public Livro(Integer id, long isbn, String titulo, int ano, String dimensao, BigDecimal custo, int quantidade, boolean ativo, String imagem, String edicao, int paginas, String sinopse, int peso, Date dataCadastro) {
    	this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.ano = ano;
        this.dimensao = dimensao;
        this.custo = custo;
        this.quantidade = quantidade;
        this.ativo = ativo;
        this.imagem = imagem;
        this.edicao = edicao;
        this.paginas = paginas;
        this.sinopse = sinopse;
        this.peso = peso;
        this.dataCadastro = dataCadastro;
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

    public List<Autor> getAutor() {
        return autores;
    }

    public void setAutor(List<Autor> autores) {
        this.autores = autores;
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
        return "Livro{" + "id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", ano=" + ano + ", dimensao=" + dimensao + ", custo=" + custo + ", quantidade=" + quantidade + ", ativo=" + ativo + ", imagem=" + imagem + ", edicao=" + edicao + ", paginas=" + paginas + ", sinopse=" + sinopse + ", peso=" + peso + ", dataCadastro=" + dataCadastro ;
    }


    
}
