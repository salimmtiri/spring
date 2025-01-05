package tn.iteam.gestion_rh.service;


import java.util.List;

import tn.iteam.gestion_rh.entity.MyUser;


public interface MyUserService {
	List<MyUser> getAllMyUsers();
	
	MyUser saveMyUser(MyUser myuser);
	
	MyUser getMyUserById(Long id);
	
	MyUser updateMyUser(MyUser myuser);
	
	void deleteMyUserById(Long id);
}