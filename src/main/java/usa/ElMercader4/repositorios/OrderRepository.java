/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usa.ElMercader4.repositorios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import usa.ElMercader4.modelo.Order;
import usa.ElMercader4.modelo.User;
import usa.ElMercader4.repositorios.crud.CrudOrderRepository;

/**
 *
 * @author DELL
 */
@Repository
public class OrderRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private CrudOrderRepository repo;
    
    public List<Order> getAll(){
        return repo.findAll();
    }
    
    // Metodo para consulta de Id puntual
    public Optional<Order> getById(Integer id){
        return repo.getById(id);
    }
    
    public Order save(Order order){
        return repo.save(order);
    }
    
    public void delete(Order order){
        repo.delete(order);
    }    
    
    // Metodo para consulta de zona puntual
    public List<Order> getByZone(String zone){
        return repo.findByZone(zone);
    }   
    
    // Metodo para consulta por asesor puntual
    public List<Order> getBySalesMan(Integer id){
        Query query = new Query();
        Criteria criterio = Criteria.where("salesMan.id").is(id);
        query.addCriteria(criterio);
        List<Order> ordenes = mongoTemplate.find(query,Order.class);
        return ordenes;
        //return repo.getBySalesMan(id);
    }     
    
    // Metodo para consulta por estado
    public List<Order> getByState(String state, Integer id){
        Query query = new Query();
        Criteria criterio = Criteria.where("salesMan.id").is(id).and("status").is(state);
        query.addCriteria(criterio);
        List<Order> ordenes = mongoTemplate.find(query,Order.class);
        return ordenes;
        //return repo.getBySalesMan(id);
    }     
    
    // Metodo para consulta por fecha
    public List<Order> getByDate(String dateStr, Integer id){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd");       
        Query query = new Query();
        Criteria criterio = Criteria.where("registerDay")
            .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
            .lt(LocalDate.parse(dateStr, dtf).plusDays(1).atStartOfDay())
            .and("salesMan.id").is(id);
        query.addCriteria(criterio);
        List<Order> ordenes = mongoTemplate.find(query,Order.class);
        return ordenes;
        //return repo.getBySalesMan(id);
    }       
    
}
