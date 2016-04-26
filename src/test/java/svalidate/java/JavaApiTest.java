package svalidate.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RunWith(JUnit4.class)
public class JavaApiTest {

    private static class UserData {

        private final String nickname;

        private final String email;

        private final String password;

        private final String passwordConfirm;

        public UserData(String nickname, String email, String password, String passwordConfirm) {
            this.nickname        = nickname;
            this.email           = email;
            this.password        = password;
            this.passwordConfirm = passwordConfirm;
        }

        public String nickname() {
            return this.nickname;
        }

        public String email() {
            return this.email;
        }

        public String password() {
            return this.password;
        }

        public String passwordConfirm() {
            return this.passwordConfirm;
        }

    }

    private static class Tuple<A,B> {

        private A a;

        private B b;

        public Tuple(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public A a() {
            return this.a;
        }

        public B b() {
            return this.b;
        }

    }

    // you probably want to write this more elegant (with guava or so)
    private Validator<String> nonEmptyString = new Validator<String>() {

        public List<String> validate(String s) {
            if(s != null && !"".equals(s.trim())) {
                return new ArrayList<>();
            } else {
                ArrayList errors = new ArrayList<>();
                errors.add("The string must not be empty");
                return errors;
            }
        }
        
    };

    private Validator<Tuple<String,String>> equalStrings = new Validator<Tuple<String,String>>() {

        public List<String> validate(Tuple<String,String> strings) {
            if(
              (strings.a() == null && strings.b() == null) ||
              (strings.a() != null && strings.b() != null && strings.a().equals(strings.b()))
            ) {
                return new ArrayList<>();
            } else {
                ArrayList errors = new ArrayList<>();
                errors.add("The string must not be empty");
                return errors;
            }
        }

    };

    private Form<UserData> form = new Form<UserData>(
        UserData.class,
        new Validation<UserData,String>("nickname",        (userData) -> userData.nickname(),        nonEmptyString),
        new Validation<UserData,String>("email",           (userData) -> userData.email(),           nonEmptyString),
        new Validation<UserData,String>("password",        (userData) -> userData.password(),        nonEmptyString),
        new Validation<UserData,String>("passwordConfirm", (userData) -> userData.passwordConfirm(), nonEmptyString),
        new Validation<UserData,Tuple<String,String>>("passwordConfirm", (userData) -> new Tuple<String,String>(userData.password(), userData.passwordConfirm()), equalStrings)
    );

    @Test
    public void testSimple() {
        Map<String,List<String>> errors = form.validate(new UserData("", "", "", ""));
        assertEquals("There must be an error on every label", 4, errors.size());
        assertEquals(errors.get("nickname").size(),        1);
        assertEquals(errors.get("email").size(),           1);
        assertEquals(errors.get("password").size(),        1);
        assertEquals(errors.get("passwordConfirm").size(), 1);
    }

    @Test
    public void testTupled() {
        Map<String,List<String>> errors = form.validate(new UserData("jack", "jack@blackpearl.sea", "jacktheripper", "jackthedipper"));
        assertEquals("There must be an error on the passwordConfirm label", 1, errors.size());
        assertEquals(errors.get("passwordConfirm").size(), 1);
    }

}

