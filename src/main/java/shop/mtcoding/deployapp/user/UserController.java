package shop.mtcoding.deployapp.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.deployapp._config.JwtTokenProvider;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody User user) {
        Optional<User> userOP = userRepository.findByEmail(user.getEmail());

        if(userOP.isPresent()){
            return ResponseEntity.badRequest().body("이미 존재하는 이메일입니다");
        }

        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> userOP = userRepository.findByEmail(user.getEmail());

        if(userOP.isEmpty()){
            return ResponseEntity.badRequest().body("이메일을 찾을 수 없습니다");
        }

        User userPS = userOP.get();

        if(!userPS.getPassword().equals(user.getPassword())){
            return ResponseEntity.badRequest().body("비밀번호가 틀렸습니다");
        }

        String jwt = JwtTokenProvider.create(userPS);
        return ResponseEntity.ok().header(JwtTokenProvider.HEADER, jwt).body("로그인 완료");
    }
}
