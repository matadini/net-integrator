package pl.inzynier.netintegrator.server.util;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.charset.Charset;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordUtil {

    public static HashCode getSha256FromString(String admin1) {
        return Hashing.sha256().hashString(admin1, Charset.forName("UTF-8"));
    }
}
