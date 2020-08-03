package com.mkostadinov.eticketbackend.init;

import com.mkostadinov.eticketbackend.model.entity.Authority;
import com.mkostadinov.eticketbackend.repository.AuthorityRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitializr implements ApplicationRunner {

    private final AuthorityRepository authorityRepository;

    public AppInitializr(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(this.authorityRepository.count() == 0) {
            Authority authorityAdmin = new Authority().setAuthorityName("ROLE_ADMIN");
            Authority authorityUser = new Authority().setAuthorityName("ROLE_USER");

            this.authorityRepository.saveAndFlush(authorityAdmin);
            this.authorityRepository.saveAndFlush(authorityUser);
        }
    }
}
