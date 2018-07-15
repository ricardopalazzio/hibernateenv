package ninja.tuxtech.envers.endpoints;

import ninja.tuxtech.envers.ClienteRepository;
import ninja.tuxtech.envers.model.Cliente;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/cliente")
public class ClienteEP {

    @Autowired
    private EntityManagerFactory factory;

    private  AuditReader getAuditReader() {
        AuditReader audit = AuditReaderFactory.get(factory.createEntityManager());
        return audit;
    }

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping()
    public ResponseEntity<?>  getAll() {
        return  ResponseEntity.ok(clienteRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> persist(@RequestBody  Cliente cliente){
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Cliente cliente){
        try {
            return ResponseEntity.ok(clienteRepository.save(cliente));
        }catch (ObjectOptimisticLockingFailureException e){
           return ResponseEntity.badRequest().body(clienteRepository.findById(cliente.getId()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody  Cliente cliente){
        clienteRepository.delete(cliente);
        return  ResponseEntity.ok(cliente);

    }

    @GetMapping("/history/{id}")
    public ResponseEntity<?> lastChange(@PathVariable Long id){
        List auditado  = getAuditReader()
                .createQuery()
                .forRevisionsOfEntity(Cliente.class, false, true)
                .add(AuditEntity.property("id").eq(id))
                .getResultList();

        return ResponseEntity.ok(auditado);



    }


}
