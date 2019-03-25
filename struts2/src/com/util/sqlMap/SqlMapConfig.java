package com.util.sqlMap;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapConfig {

	private static final SqlMapClient sqlMap;
	
	static{
		
		try {
					
			String resource = "com/util/sqlMap/sqlMapConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error initializing class:" + e);
		}		
	}
	
	public static SqlMapClient getSqlMapInstance(){
		
		return sqlMap;
		
	}
	
}
