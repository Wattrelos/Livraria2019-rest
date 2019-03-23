package fatec.layer11.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import fatec.domain.Categoria;
import fatec.domain.Livro;
import fatec.layer10.repositories.AutorRepository;
import fatec.layer10.repositories.CategoriaRepository;
import fatec.layer10.repositories.EditoraRepository;
import fatec.layer10.repositories.LivroRepository;
import fatec.layer10.repositories.SubcategoriaRepository;
import fatec.layer11.services.exceptions.DataIntegrityException;
import fatec.layer11.services.exceptions.ObjectNotFoundException;
import fatec.layer20.aplications.DataTransferObject.LivroDTO;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repo;
	
	@Autowired
	private AutorRepository autorrepo;
	@Autowired
	private CategoriaRepository categoriarepo;
	@Autowired
	private SubcategoriaRepository subcategoriarepo;
	@Autowired
	private EditoraRepository editrepo;
	
	// CREATE ------------------------------------------------
	@Transactional
	public Livro insert(Livro obj) {
		
		autorrepo.saveAll(obj.getAutor());
		categoriarepo.saveAll(obj.getCategorias());
		subcategoriarepo.saveAll(obj.getSubcategorias());
		
		editrepo.save(obj.getEditora());
		obj.setId(null);
		obj = repo.save(obj);
		
		return obj;
	}
	
	// READ ------------------------------------------------
	public Livro find(Integer id) {
		Optional<Livro> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id+ ", Tipo: "+ Categoria.class.getName()));		
	}
	
	public List<Livro> findAll() {
		return repo.findAll();
	}

	public Page<Livro> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// UPDATE ------------------------------------------------
	public Livro update(Livro obj) {
		Livro newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Livro newObj, Livro obj) {
		newObj.setTitulo(obj.getTitulo());
	}
	
	// DELETE ------------------------------------------------
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma livro que possui livros");
		}
	}
	
	// ------------------------------------------------------
	public Livro fromDTO(LivroDTO objDto) {
		Livro objLivro = new Livro(
			objDto.getId(),
			objDto.getIsbn(),
			objDto.getTitulo(),
			objDto.getAno(),
			objDto.getDimensao(),
			objDto.getCusto(),
			objDto.getQuantidade(),
			objDto.isAtivo(),
			objDto.getImagem(),
			objDto.getEdicao(),
			objDto.getPaginas(),
			objDto.getSinopse(),
			objDto.getPeso(),
			objDto.getDataCadastro()
		);
		objLivro.setEditora(objDto.getEditora());
		objLivro.setAutor(objDto.getAutores());
		objLivro.setCategorias(objDto.getCategorias());
		objLivro.setSubcategorias(objDto.getSubcategorias());
		
		
		return objLivro;
		
		}
}
