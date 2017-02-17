package pl.lodz.uni.math.main;

import java.util.List;

import org.apache.log4j.Logger;
import org.easymock.EasyMock;

import pl.lodz.uni.math.daofactory_project.DaoFactory;
import pl.lodz.uni.math.daofactory_project.SourceDaoFactory;
import pl.lodz.uni.math.user.User;

public class Main 
{
    public static void main( String[] args )
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
    	
    	DaoFactory daoFactory = new DaoFactory();
    	daoFactory.setSourceOfData(SourceDaoFactory.DB);
    	daoFactory.getSourceOfData().selectUserById(1);
    	daoFactory.getSourceOfData().selectAllUsers();
    	DaoFactory daoFactory2 = new DaoFactory();
    	daoFactory2.setSourceOfData(SourceDaoFactory.DB);
    	daoFactory2.getSourceOfData().selectUserById(1);
    	daoFactory2.getSourceOfData().selectAllUsers();
    	
    }
}
