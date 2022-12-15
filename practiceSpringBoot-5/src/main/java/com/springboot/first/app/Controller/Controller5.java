package com.springboot.first.app.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.first.app.Pojo.Pojo;
import com.springboot.first.app.Repo.Repository;

@RestController
public class Controller5
{
	@Autowired
	Repository rep;
	
	@PostMapping("/postdata")
	public ResponseEntity<Pojo> savedata(@RequestBody Pojo p)
	{
		return new ResponseEntity<>(rep.save(p),HttpStatus.CREATED);
	}
	
	@GetMapping("/getdata")
	public ResponseEntity<List<Pojo>> getdata()
	{
		return new ResponseEntity<>(rep.findAll(),HttpStatus.OK);
	}

	@GetMapping("/getdata/{id}")
	public ResponseEntity<Pojo> getsingledata(@PathVariable int id)
	{
		Optional<Pojo> obj=rep.findById(id);
		if(obj.isPresent())
		{
			return new ResponseEntity<>(obj.get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/putdata/{id}")
	public ResponseEntity<Pojo> putdata(@PathVariable int id,@RequestBody Pojo p)
	{

		Optional<Pojo> obj=rep.findById(id);
		if(obj.isPresent())
		{
			obj.get().setId(p.getId());
			obj.get().setName(p.getName());
			obj.get().setAge(p.getAge());
			return new ResponseEntity<>(rep.save(obj.get()),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	@DeleteMapping("/deletedata/{id}")
	public ResponseEntity<Pojo> deletedata(@PathVariable int id)
	{
		Optional<Pojo> obj=rep.findById(id);
		if(obj.isPresent())
		{
			rep.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
