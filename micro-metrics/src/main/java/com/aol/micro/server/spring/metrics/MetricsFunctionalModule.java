package com.aol.micro.server.spring.metrics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import javax.servlet.ServletContextListener;

import com.aol.micro.server.FunctionalModule;
import com.aol.micro.server.servers.model.ServerData;

/**
 * 
 * Collections of Spring configuration classes (Classes annotated with @Configuration)
 * that configure various useful pieces of functionality - such as property file loading,
 * datasources, scheduling etc
 * 
 * @author johnmcclean
 *
 */
public class MetricsFunctionalModule implements FunctionalModule{
	
	@Override
	public Set<Class> springClasses() {
		return new HashSet<>(Arrays.asList( CodahaleMetricsConfigurer.class));
	}

	@Override
	public Set<Function<ServerData, ServletContextListener>> servletContextListeners() {
		return null;
	}

	@Override
	public Set<Class> jaxRsResources() {
	
		return null;
	}

	@Override
	public Set<String> jaxRsPackages() {
	
		return null;
	}

}