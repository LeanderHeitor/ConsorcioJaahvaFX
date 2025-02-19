package controller;

import exception.*;
import model.*;
import repository.*;
import repository.IRepository;
import repository.UsuarioRepository;
import repository.GrupoRepository;
import java.util.HashMap;
import java.util.Map;

public class UsuarioController {
    private IRepository<Usuario> usuarioRepository;
    private IRepository<Grupo> grupoRepository;
    private Map<Long, Double> penalidades;

    public UsuarioController() {
        this.usuarioRepository = UsuarioRepository.getInstance();
        this.grupoRepository = GrupoRepository.getInstance();
        this.penalidades = new HashMap<>();
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
            System.out.println("Usuario" + usuario.getNome() + "cadastrado com sucesso");
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

    public void penalizarUsuario(long cpf, double valorPenalidade) throws UsuarioNuloException, ValorDaPenalidadePositivoException {
        if (valorPenalidade <= 0) { throw new ValorDaPenalidadePositivoException("O valor da penalidade precisa ser positivo.");}

        Usuario usuario = usuarioRepository.findById(cpf);
        if (usuario == null) {
            throw new UsuarioNuloException(" Usuário com o CPF " + cpf + "não encontrado.");
        }

        penalidades.put(cpf, penalidades.getOrDefault(cpf, 0.0) + valorPenalidade);
        System.out.println("Usuário penalizado com sucesso" + usuario.getNome() + "| Penalidade: " + penalidades.get(cpf));
    }

    public double consultarPenalidade(long cpf) {
        return penalidades.getOrDefault(cpf, 0.0);
    }

    public void cancelarLance(long cpfAdmin, long cpfCliente, int idGrupo) {

        Admin admin = (Admin) usuarioRepository.findById(cpfAdmin);
        if (admin == null) {
            throw new IllegalArgumentException("Administrador não encontrado.");
        }

        Cliente cliente = (Cliente) usuarioRepository.findById(cpfCliente);
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

    private double calcularValorParcela(double valorTotal, int numParcelas, double taxaAdm) {
        double valorParcelaSemTaxa = valorTotal / numParcelas;
        double valorTaxaAdm = valorParcelaSemTaxa * (taxaAdm / 100);
        return valorParcelaSemTaxa + valorTaxaAdm;
    }


    public void alterarTaxaAdmin(long cpf,int idGrupo, double valorTaxa) {
        Admin admin = (Admin) usuarioRepository.findById(cpf);
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
    
}