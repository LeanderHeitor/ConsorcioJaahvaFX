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

    public void checarLogin(long id, String senha) throws IOException, AdmLogadoComSucesso, ClienteLogadoComSucesso, UsuarioNaoExisteException, UsuarioLogadoComSucessoException, CPFincorretoException {
        UsuarioRepository repoConcreto = (UsuarioRepository) usuarioRepository;
        Usuario usuario = repoConcreto.findById(id);
        if (usuario.getId().equals(id)) {
            if (usuario.getId().equals(id) && usuario.getSenha().equals(senha)) {
                if (usuario instanceof Admin && ((Admin) usuario).isAdmin()) {
                    throw new AdmLogadoComSucesso("Administrador logado com sucesso."); //fazer a lógica de telas a serem carregadas.
                } else if (usuario instanceof  Admin && !((Admin) usuario).isAdmin()) {
                    throw new ClienteLogadoComSucesso("Cliente Logado com sucesso.");
                } else {
                    throw new CPFincorretoException("CPF incorreto.");
                }
            }
        } else {
            throw new UsuarioNaoExisteException("Usuário não encontrado.");
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

    public boolean isAdmin(long id) throws CPFNaoPodeSerNuloException, UsuarioNuloException {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new UsuarioNuloException("Usuário com ID " + id + " não encontrado.");
        }
        return usuario instanceof Admin;
    }

    public void cadastrarAdmin (Usuario usuario) throws UsuarioNuloException, CPFNaoPodeSerNuloException, LimiteEmailException {
        if (usuario == null) {
            throw new UsuarioNuloException("Usuário não pode ser nulo.");
        }
        if (usuario.getNome() != null) {
            if (isAdmin(usuario.getId()) == true) {
                usuarioRepository.add(usuario); //confirmar se seria cadastrado como usuário apenas
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
            if (isAdmin(cliente.getId()) == false) {
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

    public void cancelarLance(long idAdmin, long idCliente, int idGrupo) {

        Admin admin = (Admin) usuarioRepository.findById(idAdmin);
        if (admin == null) {
            throw new IllegalArgumentException("Administrador não encontrado.");
        }

        Cliente cliente = (Cliente) usuarioRepository.findById(idCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        Grupo grupo = grupoRepository.findById(idGrupo);
        if (grupo == null) {
            throw new IllegalArgumentException("Grupo não encontrado.");
        }

        if (grupo.getLances() == null) {
            throw new IllegalArgumentException("O cliente não fez um lance neste grupo.");
        }

        grupo.cancelarLance(grupo.getLances());
        grupo.reembolsarLance(grupo.getValorTotal());

        System.out.println("Lance cancelado pelo administrador: " + admin.getNome());
        System.out.println("Cliente reembolsado: " + cliente.getNome() + " | Valor: " + grupo.getLances());
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