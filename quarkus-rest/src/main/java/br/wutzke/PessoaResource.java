package br.wutzke;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

    @GET
    public List<Pessoa> getPessoa() {
        return Pessoa.listAll();
    }

    @POST
    @Transactional
    public Pessoa addPessoa(Pessoa pessoa) {
        pessoa.id = null;
        Pessoa.persist(pessoa);
        return pessoa;
    }

    @PUT
    @Transactional
    public Pessoa updatePessoa(Pessoa pessoa) {
        Pessoa p = Pessoa.findById(pessoa.id);
        p.nome = pessoa.nome;
        p.anoNascimento = pessoa.anoNascimento;
        p.persist();
        return p;
    }

    @DELETE
    @Transactional
    public void deletePessoa(int id) {
        Pessoa.deleteById(id);
    }

    @GET
    @Path("anoNascimento")
    public List<Pessoa> findByAnoNascimento(@QueryParam("anoNascimento") int anoNascimento) {
        return Pessoa.findByAnoNascimento(anoNascimento);
    }
}
