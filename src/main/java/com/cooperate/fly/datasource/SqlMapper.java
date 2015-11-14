package com.cooperate.fly.datasource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SqlMapper annotation for MapperScannerConfigurer to scan mapper interfaces.
 * 
 * @author ligonghui
 * @since 2015-9-22
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SqlMapper {
	
}
