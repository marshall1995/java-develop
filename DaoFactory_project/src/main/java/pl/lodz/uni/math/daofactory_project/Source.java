package pl.lodz.uni.math.daofactory_project;

import java.util.List;

import pl.lodz.uni.math.user.User;

public interface Source {
	public List<User> selectAllUsers();
	
	public User selectUserById(int id);

}
