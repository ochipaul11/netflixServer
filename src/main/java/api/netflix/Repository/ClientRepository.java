package api.netflix.Repository;

import api.netflix.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long > {

    boolean existsByIdentificationNumber  (Long id );
   Client findClientByIdentificationNumber(Long id);

   //Optional<Client> findById(Integer id);

  Client findClientById(int id);
  boolean existsClientById(int id);
}
