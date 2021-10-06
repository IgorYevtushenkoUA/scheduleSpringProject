package logger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(LoggerService.class)
public class LoggerServiceAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public LoggerService loggerService() {
        return new ConsoleLoggerService();
    }
}
