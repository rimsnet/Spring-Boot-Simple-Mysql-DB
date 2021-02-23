package me.rimsan;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){

        User user = new User();
        user.setEmail("asmas@gmail.com");
        user.setFirstname("Fathima");
        user.setLastname("Assma");
        user.setPassword("123");

        User saveUser  = repo.save(user);
        User existUser = entityManager.find(User.class, saveUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());

    }

    @Test
    public void testUserFindByEmail(){
        String email = "rimsnet@gmail.com";
        User user = repo.findByEmail(email);
        assertThat(user).isNotNull();
    }
}
