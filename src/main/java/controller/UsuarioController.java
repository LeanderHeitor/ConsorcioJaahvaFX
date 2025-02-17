package controller;

import exception.*;
import model.Admin;
import model.Usuario;
import repository.IRepository;
import repository.UsuarioRepository;

public class UsuarioController {
    private UsuarioRepository usuarioRepository;

    public UsuarioController() { 
        this.usuarioRepository = UsuarioRepository.getInstance(); 
    }


    // login
    public void login(String login, String senha) throws RuntimeException{
        Usuario usuario = null;
        try{
            if(login != null && senha != null) {
                if (login.length() == 11) {
                    usuario = usuarioRepository.findById(Long.parseLong(login));
                } else {
                    usuario = usuarioRepository.findAll().stream().filter(u -> u.getEmail().equals(login)).findFirst().get();
                }
                if (usuario != null) {
                    if (usuario.getSenha().equals(senha)) {
                        System.out.println("Login realizado com sucesso");
                    } else {
                        throw new FormularioIncorretoException("Senha incorreta");
                    }
                } else {
                    throw new FormularioIncorretoException("Usuário não encontrado");
                }
            } else {
                throw new FormularioIncorretoException("Preencha todos os campos!");
            }
        }catch (Exception e){
            throw new FormularioIncorretoException("Não foi possível fazer login");
        }
    }

    //Regras de negócio relacionadas ao usuario
    public void cadastrarUsuario(Usuario usuario) throws UsuarioNuloException, LimiteEmailException {
        if (usuario == null) {
            throw new UsuarioNuloException("Usuário não pode ser nulo.");
        }

        if (usuario.getNome() != null && usuario.getEmail().length() == 11) {
            throw new LimiteEmailException("Email não pode conter 11 caracteres");
        }

        if (usuario.getNome() != null && usuario.getSenha().length() == 11) {
            throw new LimiteEmailException("Email não pode conter 11 caracteres");
        }

        try{
            usuarioRepository.add(usuario);
            System.out.println("Usuario" + usuario.getNome() + " cadastrado com sucesso");
        } catch (Exception e) {
            throw new FormularioIncorretoException("Não foi possível cadastrar o usuário");
        }
    }

    public boolean isAdmin(long cpf) throws CPFNaoPodeSerNuloException, UsuarioNuloException {
        Usuario usuario = usuarioRepository.findById(cpf);
        if (usuario == null) {
            throw new UsuarioNuloException("Usuário com CPF " + cpf + " não encontrado.");
        }
        return usuario instanceof Admin;
    }

    public void cadastrarAdmin (Usuario usuario) throws UsuarioNuloException, CPFNaoPodeSerNuloException, LimiteEmailException {
        if (usuario == null) {
            throw new UsuarioNuloException("Usuário não pode ser nulo.");
        }
        if (usuario.getNome() != null) {
            if (isAdmin(usuario.getCPF()) == true) {
                usuarioRepository.add(usuario); //confirmar se seria cadastrado como usuário apenas
            }
        } else {
            throw new CPFNaoPodeSerNuloException("Admin não encontrado.");

        }
    }

    public void cadastrarCliente (Usuario usuario) throws UsuarioNuloException, CPFNaoPodeSerNuloException {
        if (usuario == null) {
            throw new UsuarioNuloException("Usuario não pode ser nulo.");
        }
        if (usuario.getNome() != null) {
            if (isAdmin(usuario.getCPF()) == false) {
                usuarioRepository.add(usuario); // confirmar método
            } else {
                throw new CPFNaoPodeSerNuloException("Usuário não pode ser um cliente");
            }
        }
    }

    public void printClientes() {
        for (Usuario usuario : usuarioRepository.findAllCliente()) {
            if (usuario instanceof Admin) {
                System.out.println("Admin: " + usuario.getNome());
            } else {
                System.out.println("Cliente: " + usuario.getNome());
            }
        }
    }
    
}