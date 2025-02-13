
 package repository;

 import lombok.Data;
 import model.Relatorio;

 import java.util.ArrayList;
 import java.util.List;
 @Data
 public class RelatorioRepository implements IRepository<Relatorio> {
     private static IRepository instance;
     private ArrayList<Relatorio> relatorios;
     private int indice;
     public RelatorioRepository() {
         relatorios = new ArrayList<>();
         indice = 0;
     }
     public static IRepository<Relatorio> getInstance() {
         if (instance == null) {
             instance = new RelatorioRepository();
         }
         return instance;
     }
     @Override
     public void add(Relatorio relatorio) {
         relatorios.add(relatorio);
         indice = indice + 1;
     }
     @Override
     public void remove(Relatorio relatorio) {
         int i = getIndex(relatorio.getCodigo()); //busca pelo codigo
         if (i == indice) {
             relatorios.remove(i);
             indice = indice - 1;
         } else {
             System.out.println("Relatorio não encontrado");
         }
     }
     @Override
     public void update(Relatorio relatorio) {
         int i = getIndex(Long.valueOf(relatorio.getTitulo())); //mesma coisa de acima
         if (i == indice) {
             relatorios.set(i, relatorio);
         } else {
             System.out.println("Relatorio não encontrado");
         }
     }
     @Override
     public Relatorio findById(long id) {
         int i = getIndex(id);
         if (i == indice) {
             return relatorios.get(i);
         } else {
             System.out.println("Relatorio não encontrado");
             return null;
         }
     }
     @Override
     public List<Relatorio> findAll() {
         return this.relatorios;
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
             if (relatorios.get(i).getCodigo().equals(id)) {
                 return i;
             }
         }
         return indice;
     }
 }



