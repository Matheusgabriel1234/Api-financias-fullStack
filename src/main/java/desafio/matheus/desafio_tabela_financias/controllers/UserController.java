package desafio.matheus.desafio_tabela_financias.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.matheus.desafio_tabela_financias.entities.DataUser;
import desafio.matheus.desafio_tabela_financias.entities.FinanceData;
import desafio.matheus.desafio_tabela_financias.repository.UserRepository;
import desafio.matheus.desafio_tabela_financias.service.FinanceService;
import desafio.matheus.desafio_tabela_financias.service.UserService;

@RequestMapping("/api/users")
@RestController
public class UserController {

private UserService serv;

private FinanceService financeService;



public UserController(UserService serv, FinanceService financeService) {
	super();
	this.serv = serv;
	this.financeService = financeService;
}

@GetMapping
public ResponseEntity<List<DataUser>> getAll(){
List<DataUser> obj = serv.getAll();
return new ResponseEntity<>(obj, HttpStatus.OK);
}

@GetMapping("/{id}")
public ResponseEntity<Optional<DataUser>> getById(@PathVariable Long id){
Optional<DataUser> user = serv.getById(id);
return new ResponseEntity<>(user,HttpStatus.OK);


}

@PostMapping
public ResponseEntity<DataUser> create(@RequestBody DataUser user){
DataUser obj = serv.create(user);
return new ResponseEntity<>(obj,HttpStatus.CREATED);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id){
serv.delete(id);
return ResponseEntity.noContent().build();
}

@PostMapping("/addfinance/{id}")
public ResponseEntity<FinanceData> createFinanceData(@PathVariable Long id,@RequestBody FinanceData finance){
FinanceData obj = financeService.addFinancialData(id, finance);
return new ResponseEntity<>(obj,HttpStatus.CREATED);
}


}
