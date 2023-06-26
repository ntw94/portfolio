package project.forums.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

// "classpath:/mybatis/mapper/*.xml"

@Configuration
public class DBconfig {

    // application.yml의 설정 정보를 토대로 HikariCP 설정
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari") // 읽어올 설정 정보의 prefix 지정
    public HikariConfig hikaryConfig() {
        return new HikariConfig();
    }

    // Connection Pool을 관리하는 DataSource 인터페이스 객체 선언
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikaryConfig());
    }

    // SqlSessionTemplate에서 사용할 SqlSession을 생성하는 Factory
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        // MyBatis Mapper Source
        // MyBatis의 SqlSession에서 불러올 쿼리 정보
        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/mapper/*Mapper.xml");
        bean.setMapperLocations(res);

        // MyBatis Config Setting
        // MyBatis 설정 파일
        Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:/mybatis/mybatis-config.xml");
        bean.setConfigLocation(myBatisConfig);

        return bean.getObject();
    }

    // DataSource에서 Transaction 관리를 위한 Manager 클래스 등록
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}