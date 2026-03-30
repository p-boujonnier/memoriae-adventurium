package fr.fae.project.memoriaeback.seeder;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DatabaseSeeder implements ApplicationRunner {
    private final UserSeeder userSeeder;

    DatabaseSeeder(UserSeeder userSeeder) {
        this.userSeeder = userSeeder;
    }

    @Override
    public void run(ApplicationArguments args) {
        userSeeder.seed();
    }
}