package pl.inzynier.netintegrator.client.mapping;

import pl.inzynier.netintegrator.client.user.UserClient;
import pl.inzynier.netintegrator.client.user.dto.UserClientException;
import pl.inzynier.netintegrator.client.user.dto.UserWriteDTO;

public class UrlMappingClientTest {

    public static void main(String[] args) throws UserClientException {


        UserClient userClient = UserClient.create("http://localhost:8080");
        boolean authorization = userClient.authorization(new UserWriteDTO("admin", "admin"));
        System.out.println(authorization);

        userClient.getAll().forEach(System.out::println);

        Long add = userClient.create(new UserWriteDTO("mateusz", "mateusz"));
        System.out.println(add);

        userClient.getAll().forEach(System.out::println);

        userClient.delete(2l);

        userClient.getAll().forEach(System.out::println);



    }
}
