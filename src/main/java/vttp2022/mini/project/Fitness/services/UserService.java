package vttp2022.mini.project.Fitness.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.mini.project.Fitness.models.User;
import vttp2022.mini.project.Fitness.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public void addNewUser(User user) throws FitnessException {
        Optional<User> opt = userRepo.findUserByEmail(user.getEmail());
        if (opt.isPresent())
            throw new FitnessException("%s is in use".formatted(user.getEmail()));

        if (!userRepo.signup(user))
            throw new FitnessException("Unable to register. Please check with admin");
    }

    public boolean authenticate(String email, String password) {
        return 1 == userRepo.countUsersByEmailAndPassword(email, password);
    }
    
}
