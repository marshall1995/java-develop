package pl.lodz.uni.math.daofactory_project;

import java.util.HashMap;
import java.util.Map;

import pl.lodz.uni.math.daofactory_project.Db;
import pl.lodz.uni.math.daofactory_project.SourceDaoFactory;
import pl.lodz.uni.math.daofactory_project.Source;
import pl.lodz.uni.math.daofactory_project.Ws;
import pl.lodz.uni.math.daofactory_project.Xml;

public class DaoFactory 
{
	private Source sourceOfData;
	Map<SourceDaoFactory, Source> sources = new HashMap<SourceDaoFactory, Source>();

	public DaoFactory() {
		sources.put(SourceDaoFactory.DB,Db.getInstance());
		sources.put(SourceDaoFactory.WS,Ws.getInstance());
		sources.put(SourceDaoFactory.XML,Xml.getInstance());
	}

	public void setSourceOfData(SourceDaoFactory source) {
		sourceOfData = sources.get(source);
	}

	public Source getSourceOfData() {
		return sourceOfData;

	}
}
