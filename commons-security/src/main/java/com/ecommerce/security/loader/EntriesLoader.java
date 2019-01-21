package com.ecommerce.security.loader;

import com.ecommerce.security.bean.AuthorizeUrl;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Component
public class EntriesLoader {

    private AuthorizeUrl authorizeUrl;
    private ConfigurableEnvironment env;

    public EntriesLoader(ConfigurableEnvironment env) {
        this.env = env;
    }

    @PostConstruct
    public void onInit(){
        Yaml yaml = new Yaml(new Constructor(AuthorizeUrl.class));

        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(env.getProperty("spring.profiles.active").concat("-entries.yml"));

        this.authorizeUrl = yaml.load(inputStream);
    }

    public String getAuthorizeUrl(){
        return authorizeUrl.getAuthorizeUrl();
    }
}
