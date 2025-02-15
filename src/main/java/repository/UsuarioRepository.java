package repository;

import lombok.Data;
import model.Admin;
import model.Cliente;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioRepository implements IRepository<Usuario> {

    private static IRepository<Usuario> instance;
    private ArrayList<Usuario> usuarios;
    private int indice;

    public UsuarioRepository() {
        this.usuarios = new ArrayList<>();
        this.indice = 0;
    }

    public static IRepository<Usuario> getInstance() {
        if (instance == null) {
            instance = new UsuarioRepository();
        }
        return instance;
    }

    @Override
    public void add(Usuario usuario) {
        this.usuarios.add(usuario);
        indice = indice + 1;
    }

    @Override
    public void remove(Usuario usuario) {
        int i = getIndex(usuario.getCPF()); // busca pelo cpf
        if (i == indice) {
            this.usuarios.remove(i);
            indice = indice - 1;
        } else {
            System.out.println("Usuário não encontrado");
        }
    }

    @Override
    public void update(Usuario usuario) {
        int i = getIndex(usuario.getCPF());  //busquei pelo cpf
        if (i == indice) {
            this.usuarios.set(i, usuario);
        } else {
            System.out.println("Usuário não encontrado");
        }
    }

    @Override
    public Usuario findById(long id) {
        int i = getIndex(id);
        if (i == indice) {
            return this.usuarios.get(i);
        } else {
            System.out.println("Usuário não encontrado");
            return null;
        }
    }

    @Override
    public List<Usuario> findAll() {
        return this.usuarios;
    }

    public List<Admin> findAllAdmin() {
        List<Admin> admins = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Admin) {
                admins.add((Admin) usuario);
            }
        }
        return admins;
    }

    public List<Cliente> findAllCliente() {
        List<Cliente> clientes = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente) {
                clientes.add((Cliente) usuario);
            }
        }
        return clientes;
    }


    @Override
    public boolean existsById(long id) {
        boolean resposta;
        int i = getIndex(id);
        if (i == indice) {
            resposta = true;
        } else {
            resposta = false;
        }
        return resposta;
    }
    @Override
    public int getIndex(long id) {
        for (int i = 0; i < indice; i++) {
            if (usuarios.get(i).getCPF() == id) {
                return i;
            }
        }
        return indice;
    }
}