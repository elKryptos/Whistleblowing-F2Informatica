package f2.tironcinio.whistleblowing.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import f2.tironcinio.whistleblowing.entities.Commento;

public interface CommentoDao extends JpaRepository <Commento, Integer> {

}
