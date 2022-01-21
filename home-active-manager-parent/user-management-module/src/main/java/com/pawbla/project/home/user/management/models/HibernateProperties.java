package com.pawbla.project.home.user.management.models;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hibernate")
public class HibernateProperties {
    private String dialect;
    private String show_sql;
    private String format_sql;

    public String getDialect() {
        return dialect;
    }
    public void setDialect(String dialect) {
        this.dialect = dialect;
    }
    public String getShow_sql() {
        return show_sql;
    }
    public void setShow_sql(String show_sql) {
        this.show_sql = show_sql;
    }
    public String getFormat_sql() {
        return format_sql;
    }
    public void setFormat_sql(String format_sql) {
        this.format_sql = format_sql;
    }
}

