package com.emc.procheck.config;

public class PostgresConfig {
    private String host;
    private int port;
    private String dbname;
    private String username;
    private String password;
    
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDbname() {
        return dbname;
    }
    public void setDbname(String dbname) {
        this.dbname = dbname;
    }
	@Override
	public String toString() {
		return "PostgreSqlConfig [host=" + host + ", port=" + port
				+ ", dbname=" + dbname + ", username=" + username
				+ ", password=" + password + "]";
	}
    
}
