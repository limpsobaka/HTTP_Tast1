import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.util.List;

public class Main {
  public static ObjectMapper mapper = new ObjectMapper();
  public static void main(String[] args) throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
    request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
    CloseableHttpResponse response = httpClient.execute(request);

    List<Cat> cats = mapper.readValue(
            response.getEntity().getContent().readAllBytes(),
            new TypeReference<>() {
            }
    );
    cats.stream()
            .filter(cat -> cat.getUpvotes() > 0)
            .forEach(System.out::println);
  }
}
