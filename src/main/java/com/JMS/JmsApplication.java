package com.JMS;

import com.JMS.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xslt.XsltView;
import org.springframework.web.servlet.view.xslt.XsltViewResolver;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Properties;

@EnableJms
@SpringBootApplication
public class JmsApplication {

	@Bean
	public ViewResolver getXLTViewResolver() {
		final XsltViewResolver xsltViewResolver = new XsltViewResolver();
		xsltViewResolver.setOrder(1);
		xsltViewResolver.setSourceKey("xmlSource");
		xsltViewResolver.setViewClass(XsltView.class);
		xsltViewResolver.setViewNames("lists");
		xsltViewResolver.setPrefix("/WEB-INF/xslt/");
		xsltViewResolver.setSuffix(".xsl");
		return xsltViewResolver;
	}

	@Bean
	public CustomObjectMapper customObjectMapper() {
		return new CustomObjectMapper();
	}

	@Bean
	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		return new MappingJackson2HttpMessageConverter(customObjectMapper());
	}

	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
													DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This provides all boot's default to this factory, including the message converter
		configurer.configure(factory, connectionFactory);
		// You could still override some of Boot's default if necessary.
		return factory;
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Bean
	public ActiveMQConnectionFactory amqConnectionFactory() {
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	}

	@Bean
	public CachingConnectionFactory connectionFactory() {
		final CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(amqConnectionFactory());
		cachingConnectionFactory.setSessionCacheSize(10);
		return cachingConnectionFactory;
	}

	@Bean
	public ReceiveMessage getJmsDbMessageReceiver() {
		return new ReceiveMessage();
	}

	@Bean
	public SMTPReceiver getJmsSMTPMessageReceiver() {
		return new SMTPReceiver();
	}

	@Bean
	public ActiveMQTopic destination() {
		return new ActiveMQTopic("jms.topic");
	}

	@NotNull
	private DefaultMessageListenerContainer getDefaultMessageListenerContainer(final ConnectionFactory connectionFactory, final Destination destination) {
		final DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
		defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
		defaultMessageListenerContainer.setDestination(destination);
		return defaultMessageListenerContainer;
	}

	@Bean(name = "dbListenerContainer")
	@Autowired
	public DefaultMessageListenerContainer dbListenerContainer(final ConnectionFactory connectionFactory, final Destination destination) {
		final DefaultMessageListenerContainer defaultMessageListenerContainer = getDefaultMessageListenerContainer(connectionFactory, destination);
		defaultMessageListenerContainer.setMessageListener(getJmsDbMessageReceiver());
		return defaultMessageListenerContainer;
	}

	@Bean(name = "emailListenerContainer")
	@Autowired
	public DefaultMessageListenerContainer emailListenerContainer(final ConnectionFactory connectionFactory, final Destination destination) {
		final DefaultMessageListenerContainer defaultMessageListenerContainer = getDefaultMessageListenerContainer(connectionFactory, destination);
		defaultMessageListenerContainer.setMessageListener(getJmsSMTPMessageReceiver());
		return defaultMessageListenerContainer;
	}

	@Bean
	@Autowired
	public JmsTemplate jmsTemplate(final ConnectionFactory connectionFactory, final Destination destination) {
		final JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDefaultDestination(destination);
		return jmsTemplate;
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("elviravalo@gmail.com");
		mailSender.setPassword("password");

		final Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

	@Bean
	public SimpleMailMessage templateChangesMessage() {
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setText("Hello! Due to your subscription to data changes, we notify you for it.\n%s\n");
		return message;
	}

	@Bean
	public EmailService emailService() {
		return new EmailServiceImpl();
	}

	@Bean
	public Sender messageSender() {
		return new Sender();
	}

	public static void main(String[] args) {
		// Launch the application
		ConfigurableApplicationContext context = SpringApplication.run(JmsApplication.class, args);

	}
}
