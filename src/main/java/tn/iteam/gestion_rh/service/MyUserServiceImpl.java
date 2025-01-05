package tn.iteam.gestion_rh.service;



import java.util.List;

import org.springframework.stereotype.Service;

import tn.iteam.gestion_rh.entity.MyUser;
import tn.iteam.gestion_rh.repository.MyUserRepository;



@Service
public class MyUserServiceImpl implements MyUserService{
	
	private MyUserRepository repository;

	public MyUserServiceImpl(MyUserRepository repository) {
		super();
		this.repository = repository;
	}


	@Override
	public List<MyUser> getAllMyUsers() {
		return repository.findAll();

	}

	@Override
	public MyUser saveMyUser(MyUser myuser) {
		return repository.save(myuser);

		
	}

	@Override
	public MyUser getMyUserById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public MyUser updateMyUser(MyUser myuser) {
		return repository.save(myuser);
	}

	@Override
	public void deleteMyUserById(Long id) {
		repository.deleteById(id);	

	}

	
}