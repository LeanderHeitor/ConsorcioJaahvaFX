package com.example.consorciojaahvafx.controller;

import com.example.consorciojaahvafx.exception.*;
import com.example.consorciojaahvafx.model.*;
import com.example.consorciojaahvafx.repository.IRepository;
import com.example.consorciojaahvafx.repository.UsuarioRepository;
import com.example.consorciojaahvafx.repository.GrupoRepository;
import com.example.consorciojaahvafx.model.Admin;
import com.itextpdf.io.exceptions.IOException;

import java.util.HashMap;
import java.util.Map;

public class UsuarioController {
    private static UsuarioController instance;
    private IRepository<Usuario> usuarioRepository;
    private IRepository<Grupo> grupoRepository;
    private Map<Long, Double> penalidades;

    public UsuarioController() {
        this.usuarioRepository = UsuarioRepository.getInstance();
        this.grupoRepository = GrupoRepository.getInstance();
        this.penalidades = new HashMap<>();
    }

    public static UsuarioController getInstance() {
        if (instance == null) {
            instance = new UsuarioController();
        }
        return instance;
    }

    public void checarLogin(String CPF, String senha) throws IOException, AdmLogadoComSucesso, ClienteLogadoComSucesso,SenhaIncorretaException, UsuarioNaoExisteException,UsuarioEAdminException, UsuarioNaoEAdminException, UsuarioLogadoComSucessoException, CPFincorretoException {
        UsuarioRepository repoConcreto = (UsuarioRepository) usuarioRepository;
        Usuario usuario = repoConcreto.findByCPF(CPF);
        if (usuario == null) {
            throw new UsuarioNaoExisteException("Usuário não existe.");
        }
        if (!usuario.getSenha().equals(senha)) {
            throw new SenhaIncorretaException("Senha incorreta, digite novamente.");
        }

        if (usuario instanceof Admin) {
            throw new UsuarioEAdminException("O usuário é um admnistrador.");
        } else {
            throw new UsuarioNaoEAdminException("O usuário não é um admnistrador.");

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
            System.out.println("Usuario" + usuario.getNome() + "cadastrado com sucesso");
        } catch (Exception e) {
            throw new FormularioIncorretoException("Não foi possível cadastrar o usuário");
        }
    }

    public boolean isAdmin(String CPF) throws CPFNaoPodeSerNuloException, UsuarioNuloException {
        UsuarioRepository repoConcreto = (UsuarioRepository) usuarioRepository;
        Usuario usuario = repoConcreto.findByCPF(CPF);
        if (usuario == null) {
            throw new UsuarioNuloException("Usuário com CPF " + CPF + " não encontrado.");
        }
        return usuario instanceof Admin;
    }

    public void cadastrarAdmin (Admin admin) throws UsuarioNuloException, CPFNaoPodeSerNuloException, LimiteEmailException {
        if (admin == null) {
            throw new UsuarioNuloException("Usuário não pode ser nulo.");
        }
        if (admin.getNome() != null) {
            if (isAdmin(admin.getCPF()) == true) {
                usuarioRepository.add(admin); //confirmar se seria cadastrado como usuário apenas
            }
        } else {
            throw new CPFNaoPodeSerNuloException("Admin não encontrado.");

        }
    }

    public void cadastrarCliente (Cliente cliente) throws UsuarioNuloException, CPFNaoPodeSerNuloException {
        if (cliente == null) {
            throw new UsuarioNuloException("Usuario não pode ser nulo.");
        }
        if (cliente.getNome() != null) {
            if (isAdmin(cliente.getCPF()) == false) {
                usuarioRepository.add(cliente); // confirmar método
            } else {
                throw new CPFNaoPodeSerNuloException("Usuário não pode ser um cliente");
            }
        }
    }

    public void penalizarUsuario(long id, double valorPenalidade) throws UsuarioNuloException, ValorDaPenalidadePositivoException {
        if (valorPenalidade <= 0) { throw new ValorDaPenalidadePositivoException("O valor da penalidade precisa ser positivo.");}

        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new UsuarioNuloException(" Usuário com o ID " + id + "não encontrado.");
        }

        penalidades.put(usuario.getId(), valorPenalidade);
        System.out.println("Usuário penalizado com sucesso" + usuario.getNome() + "| Penalidade: " + penalidades.get(id));
    }

    public double consultarPenalidade(String id ) {
        return penalidades.getOrDefault(id, 0.0);
    }

    public double calcularValorParcela(double valorTotal, int numParcelas, double taxaAdm) {
        double valorParcelaSemTaxa = valorTotal / numParcelas;
        double valorTaxaAdm = valorParcelaSemTaxa * (taxaAdm / 100);
        return valorParcelaSemTaxa + valorTaxaAdm;
    }

    public void alterarTaxaAdmin(long id, int idGrupo, double valorTaxa) {
        Admin admin = (Admin) usuarioRepository.findById(id);
        if (admin == null) {
            throw new IllegalArgumentException("Administrador não encontrado. ");
        }

        Grupo grupo = grupoRepository.findById(idGrupo);
        if (grupo == null) {
            throw new IllegalArgumentException("Grupo não encontrado");
        }

        if (valorTaxa < 0) {
            throw new IllegalArgumentException("A taxa de administração não pode ser negativa. ");
        }

        grupo.setTaxaAdm(valorTaxa);

        double novoValorParcela = calcularValorParcela(grupo.getValorTotal(), grupo.getNumeroParcelas(), valorTaxa);
    }

    public void mostrarClientes() {
        UsuarioRepository repoConcreto = (UsuarioRepository) usuarioRepository;
        for (Cliente cliente : repoConcreto.findAllCliente()) {
            System.out.println("Cliente: " + cliente);
        }
    }

    public void mostrarUsuarios() {
        UsuarioRepository repoConcreto = (UsuarioRepository) usuarioRepository;
        for (Usuario usuario : repoConcreto.findAll()) {
            System.out.println("Cliente: " + usuario);
        }
    }
}