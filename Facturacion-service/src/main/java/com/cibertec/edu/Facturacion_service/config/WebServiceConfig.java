package com.cibertec.edu.Facturacion_service.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConfig  {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "pagos")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema pagosSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("PagoPort");
        definition.setLocationUri("/ws");
        definition.setTargetNamespace("http://cibertec.edu/pagos/ws");
        definition.setSchema(pagosSchema);
        return definition;
    }

    @Bean
    public XsdSchema pagosSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/pagos.xsd"));
    }
}
